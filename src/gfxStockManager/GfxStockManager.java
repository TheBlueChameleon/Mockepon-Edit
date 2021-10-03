package gfxStockManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import main.WindowID;
import main.Constants;
import main.IComponentWindow;
import main.Main;
import main.RuntimeGlobals;

// ========================================================================== //

public class GfxStockManager extends JFrame implements ActionListener, WindowListener, IComponentWindow {
	static final long serialVersionUID = 1L;

	static final WindowID selfID = WindowID.GfxStockManager;
	
	JTree       tree;
	JScrollPane scrTree;
	
	JPanel pnlCenter;
	JPanel pnlButtons;
	
	JLabel statusbar;
	
	// ====================================================================== //
	
	public GfxStockManager()  {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		this.setTitle(Constants.PROJECT_NAME + " Gfx Stock Manager");
		this.setPreferredSize(new Dimension(800, 600));
		this.setLocationByPlatform(true);
		this.setIconImage(Constants.PROJECT_ICON.getImage());
		
		// .................................................................. //
		// tree
		
		makeTree();
		scrTree = new JScrollPane(tree);
		scrTree.setPreferredSize(new Dimension(200, 0));
		scrTree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
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
		
		this.add(scrTree   , BorderLayout.WEST);
		this.add(pnlCenter , BorderLayout.CENTER);
		this.add(pnlButtons, BorderLayout.EAST);
		this.add(statusbar , BorderLayout.SOUTH);
		this.pack();
		
		this.setVisible(true);
	}
	
	// ====================================================================== //
	// WindowListener

	@Override
	public void windowClosing(WindowEvent arg0) {Main.mainWin.closeWindow(selfID); Main.mainWin.shutdown(true);}
	
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
	
	private void makeTree() {
        File                   fileRoot  = new File( RuntimeGlobals.dirGfx );
        DefaultMutableTreeNode root      = new DefaultMutableTreeNode(new FileNode(fileRoot));
        DefaultTreeModel       treeModel = new DefaultTreeModel(root);
        
        tree = new JTree(treeModel);
        tree.setShowsRootHandles(true);
        
        createChildren(fileRoot, root);
    }
	
	private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
        File[] files = fileRoot.listFiles();
        if (files == null) return;

        for (File file : files) {
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file));
            node.add(childNode);
            if (file.isDirectory()) {
                createChildren(file, childNode);
            }
        }
    }
	
	// ====================================================================== //
	// Inter-Window Communication

	@Override
	public void preCloseActions() {}

}
