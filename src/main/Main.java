package main;

import java.io.File;

import javax.swing.JOptionPane;

import launchWindow.LaunchWindow;

// ========================================================================== //

public class Main {
	public static LaunchWindow mainWin = null;

	// ====================================================================== //
	
	public static void main(String[] args) {
		/* TODO
		 * button pack current project
		 * button unpack project into..
		 */
		
		mainWin = new LaunchWindow();	

		RuntimeGlobals.init();
		guaranteeSubfolderStructure();
		
		mainWin.setVisible(true);
		
		mainWin.openWindow(WindowID.GfxStockManager);
	}

	// ====================================================================== //
	
	static void guaranteeSubfolderStructure() {
		File directory    = new File(RuntimeGlobals.dirProject);
		File directorySub = null;
		
		if (!directory.exists()) {
			int createFlag = JOptionPane.showOptionDialog(
					mainWin,
					"The indicated project path '" +
							RuntimeGlobals.dirProject +
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
		if ( !directory.exists() ) { directory.mkdir(); }
		
		directorySub = new File(directory, "gfx");
		if (!directorySub.exists()) { directorySub.mkdir(); }
		
		directorySub = new File(directory, "ani");
		if (!directorySub.exists()) { directorySub.mkdir(); }
	}
}
