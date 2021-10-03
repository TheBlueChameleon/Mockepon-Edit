package optionsWindow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.ComponentWindows;
import main.Constants;
import main.IComponentWindow;
import main.Main;
import main.RuntimeGlobals;

public class OptionsWindow extends JFrame implements IComponentWindow, ActionListener, WindowListener {
	static final long serialVersionUID = 3L;
	
	static final ComponentWindows selfID = ComponentWindows.OptionsWindow;
	
	boolean changed = false;
	
	JPanel pnlBottom;
	JPanel pnlContent;
	
	JLabel     lblWorkingDirectory;
	JTextField txtWorkingDirectory;
	JButton    btnWorkingDirectory;
	
	JButton btnOK;
	JButton btnCancel;
	JButton btnReset;
	
	// ====================================================================== //


	public OptionsWindow() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		this.setTitle(Constants.PROJECT_NAME + " Options");
		this.setPreferredSize(new Dimension(640, 200));
		this.setLocation(320, 320);
		this.setIconImage(Constants.PROJECT_ICON.getImage());

		// .................................................................. //
		// main content panel
		
		lblWorkingDirectory = new JLabel("Project Directory");
		
		txtWorkingDirectory = new JTextField();// + RuntimeGlobals.workingDirectory);
		txtWorkingDirectory.setText(RuntimeGlobals.workingDirectory);
		txtWorkingDirectory.setPreferredSize(new Dimension(400, 20));
		txtWorkingDirectory.setEditable(false);
		
		btnWorkingDirectory = new JButton("Browse");
		btnWorkingDirectory.addActionListener(this);
		
		// .................................................................. //
		// buttons bottom panel

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		
		btnOK = new JButton("OK");
		btnOK.addActionListener(this);
		
		// .................................................................. //
		// panels
		
		pnlContent = new JPanel();
		pnlContent.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 10;
		pnlContent.add(lblWorkingDirectory, c);
		
		c.gridx = 1;
		c.gridy = 0;
		pnlContent.add(txtWorkingDirectory, c);
		
		c.gridx = 2;
		c.gridy = 0;
		pnlContent.add(btnWorkingDirectory, c);
		
		pnlBottom  = new JPanel();
		pnlBottom.add(btnCancel);
		pnlBottom.add(btnReset);
		pnlBottom.add(btnOK);
		
		// .................................................................. //
		// packing
		
		this.add(pnlContent, BorderLayout.CENTER);
		this.add(pnlBottom , BorderLayout.SOUTH);
		this.pack();
		
		this.setVisible(true);
	}
	
	// ====================================================================== //
	// WindowListener
	
	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {Main.mainWin.closeWindow(selfID);}

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
		if        (e.getSource() == btnOK)    {
			updateRuntimeGlobals();
			RuntimeGlobals.writeIni();
			changed = false;
			Main.mainWin.closeWindow(selfID);
			
		} else if (e.getSource() == btnCancel) {
			changed = false;
			Main.mainWin.closeWindow(selfID);
			
		} else if (e.getSource() == btnReset) {
			
		} else if (e.getSource() == btnWorkingDirectory) {
			pickNewPath();
		}
	}
	
	// ====================================================================== //
	// Inter-Window Communication

	@Override
	public void preCloseActions() {
		if (changed) {
			if (JOptionPane.showConfirmDialog(
					Main.mainWin,
					"Confirm new settings?",
					Constants.PROJECT_NAME,
					JOptionPane.YES_NO_OPTION
					) == JOptionPane.OK_OPTION) {
				updateRuntimeGlobals();
				RuntimeGlobals.writeIni();
			}
		}
	}
	
	// ====================================================================== //
	// Internal Logic
	
	void updateRuntimeGlobals() {}
	
	void pickNewPath() {
		JFileChooser dlgDirectory = new JFileChooser();
		dlgDirectory.setDialogTitle(Constants.PROJECT_NAME + " – Select Project Directory");
        dlgDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dlgDirectory.setCurrentDirectory(new File(RuntimeGlobals.workingDirectory));
        
        if (dlgDirectory.showOpenDialog(this) == 0) {
        	changed = true;
        	txtWorkingDirectory.setText( dlgDirectory.getSelectedFile().toString() );
        }
	}

}
