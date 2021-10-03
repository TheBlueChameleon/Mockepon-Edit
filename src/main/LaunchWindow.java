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
import javax.swing.JPanel;

import cogs.*;
import gfxStockManager.GfxStockManager;

// ========================================================================== //

public class LaunchWindow extends JFrame implements ActionListener {
	static final long serialVersionUID = 0L;
	
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
		btnGfxStockManager.addActionListener(this);
		
		btnAnimationManager = new JButton("Launch Animation Magager");
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
		
		if (e.getSource() == btnGfxStockManager) {
			new GfxStockManager(btnGfxStockManager);
			btnGfxStockManager.setEnabled(false);
		}
	}
}
