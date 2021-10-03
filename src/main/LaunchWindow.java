package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cogs.Constants;
import gfxStockManager.GfxStockManager;

// ========================================================================== //

public class LaunchWindow extends JFrame implements ActionListener {
	static final long serialVersionUID = 0L;
	
	JFrame[] openWindows = new JFrame[ComponentWindows.Count.ordinal()];
	
	JButton btnGfxStockManager;
	JButton btnAnimationManager;
	JButton btnTileManager;
	
	JPanel content;
	JLabel statusbar;
	
	// ====================================================================== //
	
	LaunchWindow () {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Mockepon World Creator");
		this.setLocation(300, 300);
		
		this.setPreferredSize(new Dimension(400, 300));
		//this.setIconImage(Constants.PROJECT_ICON);
		this.setIconImage(Constants.PROJECT_ICON.getImage());
		
		// .................................................................. //
		// buttons
		
		btnGfxStockManager = new JButton("Launch Gfx Stock Magager");
		btnGfxStockManager.setIcon( Constants.getScaledIcon(Constants.GFX_ICON, 20, 20) );
		btnGfxStockManager.addActionListener(this);
		
		btnAnimationManager = new JButton("Launch Animation Magager");
		btnAnimationManager.setIcon( Constants.getScaledIcon(Constants.ANI_ICON, 20, 20) );
		btnAnimationManager.addActionListener(this);
		
		btnTileManager = new JButton("Launch Tile Magager");
		btnTileManager.addActionListener(this);
		
		// .................................................................. //
		// content panel
		
		content = new JPanel();
		content.setBackground(Color.white);

		content.setLayout(new GridLayout(9,1));
		
		content.add(btnGfxStockManager);
		content.add(btnAnimationManager);
		content.add(btnTileManager);
		
		// .................................................................. //
		// status bar
		
		statusbar = new JLabel("Version " + Constants.VERSION_MAJOR + "." + Constants.VERSION_MINOR);
		// statusText.setBackground(Color.gray);
		statusbar.setOpaque(true);
		
		// .................................................................. //
		// packing
		
		this.add(content  , BorderLayout.CENTER);
		this.add(statusbar, BorderLayout.SOUTH);
	    this.pack();
	    
	    /* no setVisible to true: dialog remains hidden until RuntimeGlobals is prepared, but needs to be
	     * ready before that so the ImageIcon is available for dialogs.
	     */
	}
	
	// ====================================================================== //
	// ActionListener
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println( e.toString() );
		
		if      (e.getSource() == btnGfxStockManager ) { openWindow(ComponentWindows.GfxStockManager); }
		else if (e.getSource() == btnAnimationManager) { openWindow(ComponentWindows.AnimationManager); }
	}
	
	// ====================================================================== //
	// Window management
	
	public void openWindow (ComponentWindows ID) {
		switch (ID) {
		case GfxStockManager :
			btnGfxStockManager.setEnabled(false);
			openWindows[ID.ordinal()] = new GfxStockManager();
			break;
			
		case AnimationManager :
			break;
			
		default:
			JOptionPane.showMessageDialog(
					Main.mainWin,
					"An invalid state of memory was detected. Terminating Execution:\n" +
							"Attempted to open invalid Window ID",
					Constants.PROJECT_NAME,
					JOptionPane.ERROR_MESSAGE
					);
			System.exit(-1);
		}
	}
	
	// ...................................................................... //
	
	public void closeWindow (ComponentWindows ID) {
		switch (ID) {
		case GfxStockManager :
			btnGfxStockManager.setEnabled(true);
			
		case AnimationManager :
			
		default:
			
		}

		openWindows[ID.ordinal()].dispose();
		openWindows[ID.ordinal()] = null;
	}
}
