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
		mainWin.setVisible(true);
		
//		guaranteeSubfolderStructure();
		System.out.println( "CWD: " + RuntimeGlobals.workingDirectory );
		
		
//		new GfxStockManager(null);
	}

	// ====================================================================== //
	
	static void guaranteeSubfolderStructure() {
		File directory = new File(RuntimeGlobals.workingDirectory);
		
		if (!directory.exists()) {
			//JOptionPane.showMessageDialog(null, "msg", Constants.PROJECT_NAME, JOptionPane.WARNING_MESSAGE);
			int createFlag = JOptionPane.showOptionDialog(
					null,
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
				// TODO invoke path selector
				System.err.println("aborted due to nonexistent project directory");
				System.exit(-1);
			}
		}
		
//		String dirGfx = RuntimeGlobals.workingDirectory;
		
	    if (!directory.exists()) { directory.mkdir(); }

	}
}
