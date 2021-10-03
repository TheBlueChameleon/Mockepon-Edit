package cogs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.ini4j.Wini;

import main.Main;

//=========================================================================== //

public class RuntimeGlobals {
	public static String workingDirectory;

	// ====================================================================== //

	public static void init() {
		File input = new File(Constants.INI_FILE);
		Wini ini = null;
		boolean useDefaults = false;
		boolean triggerWrite = false;
		
		try 					{ini = new Wini(input);}
		catch (IOException e)	{useDefaults = true;}
		
		if (useDefaults) {
			JOptionPane.showMessageDialog(
					Main.mainWin,
					"Could not read settings file '" +
							Constants.INI_FILE +
							"'.\n" + 
							"Reverting to defaults. Please provide a project directory (in the next dialog) and check the options dialog",
					Constants.PROJECT_NAME,
					JOptionPane.WARNING_MESSAGE
					);
			
			JFileChooser dlgDirectory = new JFileChooser();
			dlgDirectory.setDialogTitle(Constants.PROJECT_NAME + " – Select Project Directory");
	        dlgDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        dlgDirectory.setCurrentDirectory(new File("./"));
	        
	        if (dlgDirectory.showOpenDialog(Main.mainWin) != 0) {
	        	JOptionPane.showMessageDialog(
	        			Main.mainWin,
						"You need to set a project directory. Restart this program and select a project directory in the folder selection dialog.",
						Constants.PROJECT_NAME,
						JOptionPane.ERROR_MESSAGE
						);
	        	System.exit(-1);
	        }

			workingDirectory = dlgDirectory.getSelectedFile().toString();
			triggerWrite = true;
			
			
		} else {
			workingDirectory = ini.get("project", "directory");
			if (workingDirectory == null) {
				workingDirectory = "./project";
				triggerWrite = true;
			}
		}
		
		
		if (triggerWrite) {
			writeIni();
		}
	}
	
	// .................................................................. //
	
	public static void writeIni() {
		System.out.println("in writer");
		FileWriter hFile;
		
		try {
			hFile = new FileWriter(Constants.INI_FILE);

			hFile.write("[project]\n");
			hFile.write("directory = " + workingDirectory + "\n");
			
			hFile.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
					Main.mainWin,
					"Could not write settings file.",
					Constants.PROJECT_NAME,
					JOptionPane.ERROR_MESSAGE
					);
			System.exit(-1);
		}
		
	}
}
