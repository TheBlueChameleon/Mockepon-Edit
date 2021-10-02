package main;

import java.io.File;

import gfxStockManager.GfxStockManager;

// ========================================================================== //

public class Main {
	public static void main(String[] args) {
		/* TODO
		 * settings file
		 * user-defined home directory, enabling multiple active proejcts 
		 * button pack current project
		 * button unpack project into..
		 */
		
		guaranteeSubfolderStructure();
		
		//new LaunchWindow();
		new GfxStockManager(null);
	}

	// ====================================================================== //
	
	static void guaranteeSubfolderStructure() {
		System.out.println("TODO: subfolders");
		
	    File directory = new File("./gfx/");
	    if (!directory.exists()) { directory.mkdir(); }

	}
}
