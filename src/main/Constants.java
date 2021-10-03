package main;

import java.awt.Image;

import javax.swing.ImageIcon;

//========================================================================== //

public final class Constants {
	public static final int VERSION_MAJOR = 0;
	public static final int VERSION_MINOR = 1;
	
	public static final String PROJECT_NAME = "Mockepon";
	
	public static final String INI_FILE = "./settings.ini";
	
	public static final ImageIcon PROJECT_ICON = new ImageIcon("./ico/ProjectIcon.png");
	public static final ImageIcon GFX_ICON = new ImageIcon("./ico/gfxIcon.png");
	public static final ImageIcon ANI_ICON = new ImageIcon("./ico/animationIcon.png");

	// ====================================================================== //
	
	public static ImageIcon getScaledIcon(ImageIcon ico, int size_x, int size_y) {
		Image img;
		
		img = ico.getImage();
		img = img.getScaledInstance(size_x, size_y, java.awt.Image.SCALE_FAST);
		
		return new ImageIcon(img);
	}
}
