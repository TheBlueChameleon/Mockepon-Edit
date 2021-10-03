package main;

import java.io.File;

import javax.swing.JOptionPane;

import cogs.Constants;
import cogs.RuntimeGlobals;

// ========================================================================== //

public class Main {
	public static LaunchWindow mainWin = null;
	
	public static void main(String[] args) {
		/* TODO
		 * settings file
		 * user-defined home directory, enabling multiple active proejcts 
		 * button pack current project
		 * button unpack project into..
		 */
		
		mainWin = new LaunchWindow();	

		RuntimeGlobals.init();
		guaranteeSubfolderStructure();
		
		mainWin.setVisible(true);
		
//		new GfxStockManager(null);
	}

	// ====================================================================== //
	
	static void guaranteeSubfolderStructure() {
		File directory = new File(RuntimeGlobals.workingDirectory);
		File directorySub;
		
		if (!directory.exists()) {
			//JOptionPane.showMessageDialog(null, "msg", Constants.PROJECT_NAME, JOptionPane.WARNING_MESSAGE);
			int createFlag = JOptionPane.showOptionDialog(
					mainWin,
					"The indicated project path '" +
							RuntimeGlobals.workingDirectory +
							"' does not exist.\n" + 
							"Create?",
					Constants.PROJECT_NAME,
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					null
					);
			
			if (createFlag != 0) {
				JOptionPane.showMessageDialog(
						Main.mainWin,
						"Please review your settings file.\n" + 
								"A project directory must be created.",
						Constants.PROJECT_NAME,
						JOptionPane.ERROR_MESSAGE
						);
				System.exit(-1);
			}
		}
		if (!directory.exists()) { directory.mkdir(); }
		
		directorySub = new File(directory, "gfx");
		if (!directorySub.exists()) { directorySub.mkdir(); }
		
		directorySub = new File(directory, "ani");
		if (!directorySub.exists()) { directorySub.mkdir(); }
	}
}
