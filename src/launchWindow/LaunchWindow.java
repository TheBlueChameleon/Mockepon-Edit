package launchWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gfxStockManager.GfxStockManager;
import main.ComponentWindows;
import main.Constants;
import main.IComponentWindow;
import main.Main;
import optionsWindow.OptionsWindow;

// ========================================================================== //

public class LaunchWindow extends JFrame implements ActionListener, WindowListener  {
	static final long serialVersionUID = 0L;
	
	IComponentWindow[] openWindows = new IComponentWindow[ComponentWindows.values().length];
	
	JButton btnGfxStockManager;
	JButton btnAnimationManager;
	JButton btnTileManager;
	
	JButton btnOptions;
	
	JPanel content;
	JLabel statusbar;
	
	// ====================================================================== //
	
	public LaunchWindow () {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		this.setTitle("Mockepon World Creator");
		this.setLocation(300, 300);
		this.setPreferredSize(new Dimension(400, 300));
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
		
		btnOptions = new JButton("Options");
		btnOptions.addActionListener(this);
		
		// .................................................................. //
		// content panel
		
		content = new JPanel();
		content.setBackground(Color.white);

		content.setLayout(new GridLayout(9,1));
		
		content.add(btnGfxStockManager);
		content.add(btnAnimationManager);
		content.add(btnTileManager);
		content.add(btnOptions);
		
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
	// WindowListener

	@Override
	public void windowClosing(WindowEvent arg0) { shutdown(); }
	
	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
	
	// ====================================================================== //
	// ActionListener
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println( e.toString() );
		
		if      (e.getSource() == btnGfxStockManager ) { openWindow(ComponentWindows.GfxStockManager); }
		else if (e.getSource() == btnAnimationManager) { openWindow(ComponentWindows.AnimationManager); }
		else if (e.getSource() == btnOptions         ) { openWindow(ComponentWindows.OptionsWindow); }
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
			
		
		case OptionsWindow :
			btnOptions.setEnabled(false);
			openWindows[ID.ordinal()] = new OptionsWindow();
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
			
		case OptionsWindow :
			btnOptions.setEnabled(true);
			
		default:
			
		}

		openWindows[ID.ordinal()].preCloseActions();
		openWindows[ID.ordinal()].dispose();
		openWindows[ID.ordinal()] = null;
	}
	
	// ---------------------------------------------------------------------- //
	
	public void shutdown () {
		for (IComponentWindow win : openWindows) {
			if (win != null) {
				win.preCloseActions();
				win.dispose();
			}
		}
		this.dispose();
		
		System.exit(0);
		
	}
}
