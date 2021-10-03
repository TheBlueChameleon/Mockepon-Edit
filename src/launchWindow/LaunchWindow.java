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
import main.WindowID;
import main.Constants;
import main.IComponentWindow;
import main.Main;
import optionsWindow.OptionsWindow;

// ========================================================================== //

public class LaunchWindow extends JFrame implements ActionListener, WindowListener  {
	static final long serialVersionUID = 0L;
	static final int numberOfButtons = WindowID.values().length;
	
	IComponentWindow[] winsOpened = new IComponentWindow[numberOfButtons];
	
	JButton[] btnsWins = new JButton[numberOfButtons];
	
	JPanel content;
	JLabel statusbar;
	
	// ====================================================================== //
	
	public LaunchWindow () {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		this.setTitle(Constants.PROJECT_NAME + " World Creator");
		this.setLocation(50, 30);
		this.setPreferredSize(new Dimension(400, 300));
		this.setIconImage(Constants.PROJECT_ICON.getImage());
		
		// .................................................................. //
		// buttons
		
		btnsWins[WindowID.GfxStockManager.ordinal()] = new JButton("Launch Gfx Stock Magager");
		btnsWins[WindowID.GfxStockManager.ordinal()].setIcon( Constants.getScaledIcon(Constants.GFX_ICON, 20, 20) );
		btnsWins[WindowID.GfxStockManager.ordinal()].addActionListener(this);
		
		btnsWins[WindowID.AnimationManager.ordinal()] = new JButton("Launch Animation Magager");
		btnsWins[WindowID.AnimationManager.ordinal()].setIcon( Constants.getScaledIcon(Constants.ANI_ICON, 20, 20) );
		btnsWins[WindowID.AnimationManager.ordinal()].addActionListener(this);
		
		btnsWins[WindowID.TileManager.ordinal()] = new JButton("Launch Tile Magager");
		btnsWins[WindowID.TileManager.ordinal()].addActionListener(this);
		
		btnsWins[WindowID.OptionsWindow.ordinal()] = new JButton("Options");
		btnsWins[WindowID.OptionsWindow.ordinal()].addActionListener(this);
		
		// .................................................................. //
		// content panel
		
		content = new JPanel();
		content.setLayout(new GridLayout(numberOfButtons,1));
		content.setBackground(Color.white);
		
		for (int i = 0; i < numberOfButtons; ++i) { content.add(btnsWins[i]); }
		
		// .................................................................. //
		// status bar
		
		statusbar = new JLabel("Version " + Constants.VERSION_MAJOR + "." + Constants.VERSION_MINOR);
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
	public void windowClosing(WindowEvent arg0) { shutdown(true); }
	
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
		
		for (WindowID win : WindowID.values()) {
			if (e.getSource() == btnsWins[win.ordinal()] ) {
				openWindow(win);
				System.out.println(win.name());
			}
		}
	}
	
	// ====================================================================== //
	// Window management
	
	public void openWindow (WindowID ID) {
		btnsWins[ID.ordinal()].setEnabled(false);
		
		switch (ID) {
		case GfxStockManager :
			winsOpened[ID.ordinal()] = new GfxStockManager();
			break;
			
		case AnimationManager :
			break;
			
		case OptionsWindow :
			winsOpened[ID.ordinal()] = new OptionsWindow();
			break;
			
		default:
			JOptionPane.showMessageDialog(
					Main.mainWin,
					"Option not yet implemented\n",
					Constants.PROJECT_NAME,
					JOptionPane.WARNING_MESSAGE
					);
		}
	}
	
	// ...................................................................... //
	
	public void closeWindow (WindowID ID) {
		winsOpened[ID.ordinal()].preCloseActions();
		winsOpened[ID.ordinal()].dispose();
		winsOpened[ID.ordinal()] = null;
		
		btnsWins[ID.ordinal()].setEnabled(true);
	}
	
	// ---------------------------------------------------------------------- //
	
	public void shutdown (boolean terminate) {
		for (WindowID winID : WindowID.values()) {
			IComponentWindow win = winsOpened[winID.ordinal()];
			if (win != null) {
				win.preCloseActions();
				win.dispose();
				winsOpened[winID.ordinal()] = null;
				btnsWins [winID.ordinal()].setEnabled(true);;
			}
		}
		
		if (terminate) {
			this.dispose();
			System.exit(0);
		}
		
	}
}
