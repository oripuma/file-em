package com.bjschafer.fileem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class MainWindow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Display display = new Display();
		final Shell shell = new Shell(display);
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmNewReceipt = new MenuItem(menu_1, SWT.NONE);
		mntmNewReceipt.setText("New Receipt");
		
		mntmNewReceipt.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				NewReceipt receipt = new NewReceipt(shell, SWT.NONE);
				receipt.open();
				
				}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		MenuItem mntmQuit = new MenuItem(menu_1, SWT.NONE);
		mntmQuit.setText("Quit");
		
		mntmQuit.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		MenuItem mntmEdit = new MenuItem(menu, SWT.CASCADE);
		mntmEdit.setText("Edit");
		
		Menu menu_2 = new Menu(mntmEdit);
		mntmEdit.setMenu(menu_2);
		
		MenuItem mntmUndo = new MenuItem(menu_2, SWT.NONE);
		mntmUndo.setText("Undo");
		
		MenuItem mntmRedo = new MenuItem(menu_2, SWT.NONE);
		mntmRedo.setText("Redo");
		
		MenuItem mntmView = new MenuItem(menu, SWT.CASCADE);
		mntmView.setText("View");
		
		Menu menu_3 = new Menu(mntmView);
		mntmView.setMenu(menu_3);
		
		MenuItem mntmplaceholder = new MenuItem(menu_3, SWT.NONE);
		mntmplaceholder.setText("[Placeholder]");
		
		MenuItem mntmTools = new MenuItem(menu, SWT.CASCADE);
		mntmTools.setText("Tools");
		
		Menu menu_4 = new Menu(mntmTools);
		mntmTools.setMenu(menu_4);
		
		MenuItem mntmOptions = new MenuItem(menu_4, SWT.NONE);
		mntmOptions.setText("Options");
		
		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");
		
		Menu menu_5 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_5);
		
		MenuItem mntmAbout = new MenuItem(menu_5, SWT.NONE);
		mntmAbout.setText("About");
		
		shell.open();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}
