package main;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		guaranteeSubfolderStructure();
		
		new LaunchWindow();
	}

	// ====================================================================== //
	
	static void guaranteeSubfolderStructure() {
		System.out.println("TODO: subfolders");
		
	    File directory = new File("./gfx/");
	    if (!directory.exists()) { directory.mkdir(); }

	}
}
