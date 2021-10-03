package gfxStockManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import main.ComponentWindows;
import main.Constants;
import main.IComponentWindow;
import main.Main;

// ========================================================================== //

public class GfxStockManager extends JFrame implements ActionListener, WindowListener, IComponentWindow {
	static final long serialVersionUID = 1L;

	static final ComponentWindows selfID = ComponentWindows.GfxStockManager;
	
	JTree  tree;
	JPanel pnlCenter;
	JPanel pnlButtons;
	
	JLabel statusbar;
	
	// ====================================================================== //
	
	public GfxStockManager()  {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		this.setTitle(Constants.PROJECT_NAME + " Gfx Stock Manager");
		this.setPreferredSize(new Dimension(800, 600));
		this.setIconImage(Constants.PROJECT_ICON.getImage());
		
		// .................................................................. //
		// tree
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("The Java Series");
		createNodes(top);
		tree = new JTree(top);
		
		// .................................................................. //
		// panels
		
		pnlButtons = new JPanel();
		pnlButtons.setBackground(Color.black);
		pnlCenter  = new JPanel();
		pnlCenter.setBackground(Color.blue);
		
		// .................................................................. //
		// status bar

		statusbar = new JLabel("Version " + Constants.VERSION_MAJOR + "." + Constants.VERSION_MINOR);
		// statusText.setBackground(Color.gray);
		statusbar.setOpaque(true);

		// .................................................................. //
		// packing
		
		this.add(tree      , BorderLayout.WEST);
		this.add(pnlCenter , BorderLayout.CENTER);
		this.add(pnlButtons, BorderLayout.EAST);
		this.add(statusbar , BorderLayout.SOUTH);
		this.pack();
		
		this.setVisible(true);
	}
	
	// ====================================================================== //
	// WindowListener

	@Override
	public void windowClosing(WindowEvent arg0) {Main.mainWin.closeWindow(selfID);}
	
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

	// ====================================================================== //
	// Tree
	
	private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;

        category = new DefaultMutableTreeNode("Books for Java Programmers");
        top.add(category);

        //original Tutorial
        book = new DefaultMutableTreeNode("The Java Tutorial: A Short Course on the Basics");
        category.add(book);

        //Tutorial Continued
        book = new DefaultMutableTreeNode("The Java Tutorial Continued: The Rest of the JDK");
        category.add(book);

        //JFC Swing Tutorial
        book = new DefaultMutableTreeNode("The JFC Swing Tutorial: A Guide to Constructing GUIs");
        category.add(book);

        //Bloch
        book = new DefaultMutableTreeNode("Effective Java Programming Language Guide");
        category.add(book);

        //Arnold/Gosling
        book = new DefaultMutableTreeNode("The Java Programming Language");
        category.add(book);

        //Chan
        book = new DefaultMutableTreeNode("The Java Developers Almanac");
        category.add(book);

        category = new DefaultMutableTreeNode("Books for Java Implementers");
        top.add(category);

        //VM
        book = new DefaultMutableTreeNode("The Java Virtual Machine Specification");
        category.add(book);

        //Language Spec
        book = new DefaultMutableTreeNode("The Java Language Specification");
        category.add(book);
    }

	// ====================================================================== //
	// Inter-Window Communication

	@Override
	public void preCloseActions() {}

}


