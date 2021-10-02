package gfxStockManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GfxStockManager extends JFrame implements ActionListener, WindowListener {

	static final long serialVersionUID = 1L;

	JLabel dummy;
	JButton parentButton = null;
	
	// ====================================================================== //
	
	public GfxStockManager(JButton parent)  {
		this.parentButton = parent;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.addWindowListener(this);
		
		// .................................................................. //
		// obj
		
		dummy = new JLabel("dummy text");
		
		// .................................................................. //
		// packing
		
		this.add(dummy);
		this.pack();
		
		this.setVisible(true);
	}
	
	// ====================================================================== //
	// WindowListener

	@Override
	public void windowClosing(WindowEvent arg0) {parentButton.setEnabled(true);}
	
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
