package com.bjschafer.fileem;

import javax.swing.ButtonGroup;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainWindow {
	private static Text nameS;
	private static Text catS;
	private static Text idS;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(8, false));
		
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
		
		Label lblSearchForA = new Label(shell, SWT.NONE);
		lblSearchForA.setText("Search for a receipt!");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnByName = new Button(shell, SWT.RADIO);
		btnByName.setText("By name:");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		nameS = new Text(shell, SWT.BORDER);
		GridData gd_nameS = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_nameS.widthHint = 164;
		nameS.setLayoutData(gd_nameS);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnByCategory = new Button(shell, SWT.RADIO);
		btnByCategory.setText("By category:");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		catS = new Text(shell, SWT.BORDER);
		GridData gd_catS = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_catS.widthHint = 165;
		catS.setLayoutData(gd_catS);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnByUniqueId = new Button(shell, SWT.RADIO);
		btnByUniqueId.setText("By unique ID:");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		idS = new Text(shell, SWT.BORDER);
		GridData gd_idS = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_idS.widthHint = 167;
		idS.setLayoutData(gd_idS);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.setText("Search!");
		
		btnSearch.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				SearchResults sres = new SearchResults(shell, SWT.NONE);
//				sres.open();
//				if (btnByUniqueId.isEnabled()) {
//					RDB theDB = new RDB("test.sql");
//					Receipt result = theDB.retrieveReceipt(Integer.parseInt(idS.getText()));
//					ViewReceipt vr = new ViewReceipt(shell, SWT.NONE);
//					vr.open(result);
//				}
				}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		shell.open();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}
