package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.ini4j.Wini;

//=========================================================================== //

public class RuntimeGlobals {
	public static String dirProject;
	public static String dirGfx;

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

			dirProject = dlgDirectory.getSelectedFile().toString();
			triggerWrite = true;
			
			
		} else {
			dirProject = ini.get("project", "directory");
			if (dirProject == null) {
				dirProject = "./project";
				triggerWrite = true;
			}
		}
		
		if (triggerWrite) {writeIni();}
		
		dirGfx = new File(new File(dirProject), "gfx").toString();
	}
	
	// .................................................................. //
	
	public static void writeIni() {
		FileWriter hFile;
		
		try {
			hFile = new FileWriter(Constants.INI_FILE);

			hFile.write("[project]\n");
			hFile.write("directory = " + dirProject + "\n");
			
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
