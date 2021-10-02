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

public class LaunchWindow extends JFrame implements ActionListener {
	static final long serialVersionUID = 0L;
	
	JPanel statusbar;
	JPanel content;
	
	JLabel statusText;
	
	JButton btnGfxStockManager;
	JButton btnAnimationManager;
	JButton btnTileManager;
	
	// ====================================================================== //
	
	LaunchWindow () {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Mockepon World Creator");
		this.setLocation(30, 30);
		
		this.setPreferredSize(new Dimension(800, 600));
		
		// .................................................................. //
		// buttons
		
		btnGfxStockManager = new JButton("Launch Gfx Stock Magager");
		btnGfxStockManager.addActionListener(this);
		
		btnAnimationManager = new JButton("Launch Animation Magager");
		btnAnimationManager.addActionListener(this);
		
		btnTileManager = new JButton("Launch Tile Magager");
		btnTileManager.addActionListener(this);
		
		// .................................................................. //
		// content pane
		
		content = new JPanel();
		content.setBackground(Color.white);
		content.setPreferredSize(new Dimension(20, 20));
		
		content.add(btnGfxStockManager);
		content.add(btnAnimationManager);
		content.add(btnTileManager);
		
		content.setLayout(new GridLayout(9,1));
		
		// .................................................................. //
		// status pane
		
		statusText = new JLabel("Version " + Constants.VERSION_MAJOR + "." + Constants.VERSION_MINOR);
		
		statusbar = new JPanel();
		statusbar.setPreferredSize(new Dimension(20, 20));
		statusbar.setOpaque(true);
		//statusbar.setBackground(Color.gray);
		statusbar.setLayout(new BorderLayout());
		
		statusbar.add(statusText);
		
		// .................................................................. //
		// packing
		
		this.add(content  , BorderLayout.CENTER);
		this.add(statusbar, BorderLayout.SOUTH);
	    this.pack();
		
		this.setVisible(true);
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
