package com.bjschafer.fileem;

import java.sql.SQLException;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewReceipt extends Dialog {

	protected Object result;
	protected Shell shlAddANew;
	private Text rName;
	private Label lblTotalPrice;
	private Text rPrice;
	private Label lblCategory;
	private Combo rCat;
	private Label lblSalesTax;
	private Text rTax;
	private Label lblPaymentMethod;
	private Text rMop;
	private Label lblNotes;
	private Text rNotes;
	private Button btnOk;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public NewReceipt(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlAddANew.open();
		shlAddANew.layout();
		Display display = getParent().getDisplay();
		while (!shlAddANew.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlAddANew = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlAddANew.setMinimumSize(new Point(132, 50));
		shlAddANew.setSize(450, 385);
		shlAddANew.setText("Add a New Receipt");
		shlAddANew.setLayout(new GridLayout(1, false));
		
		Label lblNameOfReceipt = new Label(shlAddANew, SWT.NONE);
		lblNameOfReceipt.setText("Name of Receipt (e.g. vendor):");
		
		rName = new Text(shlAddANew, SWT.BORDER);
		rName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblTotalPrice = new Label(shlAddANew, SWT.NONE);
		lblTotalPrice.setText("Total price:");
		
		rPrice = new Text(shlAddANew, SWT.BORDER);
		rPrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblCategory = new Label(shlAddANew, SWT.NONE);
		lblCategory.setText("Category:");
		
		rCat = new Combo(shlAddANew, SWT.NONE);
		rCat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblSalesTax = new Label(shlAddANew, SWT.NONE);
		lblSalesTax.setText("Sales tax:");
		
		rTax = new Text(shlAddANew, SWT.BORDER);
		rTax.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblPaymentMethod = new Label(shlAddANew, SWT.NONE);
		lblPaymentMethod.setText("Payment method:");
		
		rMop = new Text(shlAddANew, SWT.BORDER);
		rMop.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblNotes = new Label(shlAddANew, SWT.NONE);
		lblNotes.setText("Notes:");
		
		rNotes = new Text(shlAddANew, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		GridData gd_rNotes = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_rNotes.heightHint = 53;
		rNotes.setLayoutData(gd_rNotes);
		
		btnOk = new Button(shlAddANew, SWT.NONE);
		GridData gd_btnOk = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnOk.widthHint = 63;
		btnOk.setLayoutData(gd_btnOk);
		btnOk.setText("OK");
		
	    btnOk.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent event) {
//		        input = rName.getText();
		        Receipt userCreated = new Receipt(0, rName.getText(), Double.parseDouble(rPrice.getText()), rCat.getText(), (new Date()), Double.parseDouble(rTax.getText()), rMop.getText(), rNotes.getText());
		        RDB theDB = new RDB("test.sql");
		        try{
		        	theDB.initialize();
		        	theDB.storeReceipt(userCreated);
		        	theDB.close();
		        	}
		        catch (SQLException e) {
		        	System.err.println("NewReceipt.java");
		        	System.err.println(e.getMessage());
		        }
		        
		        
		        shlAddANew.close();
		      }
		    });

	}

}
