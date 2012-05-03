package com.bjschafer.fileem;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class ViewReceipt extends Dialog {

	protected Object result;
	protected Shell shell;
	Receipt receipt;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ViewReceipt(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(Receipt aReceipt) {
		this.receipt = aReceipt;
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(new GridLayout(2, false));
		
		Label lblId = new Label(shell, SWT.NONE);
		lblId.setText("ID:");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText(Integer.toString(this.receipt.getSortId()));
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Name:");
		
		Label recName = new Label(shell, SWT.NONE);
		recName.setText(this.receipt.getName());
		
		Label lblDate = new Label(shell, SWT.NONE);
		lblDate.setText("Date:");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText(this.receipt.getTheDate().toString());
		
		Label lblCategory = new Label(shell, SWT.NONE);
		lblCategory.setText("Category:");
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText(this.receipt.getCategory());
		
		Label lblSalesTax = new Label(shell, SWT.NONE);
		lblSalesTax.setText("Sales Tax:");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText(Double.toString(this.receipt.getSalesTax()));
		
		Label lblMethodOfPayment = new Label(shell, SWT.NONE);
		lblMethodOfPayment.setText("Method of Payment:");
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText(this.receipt.getMethodOfPayment());
		
		Label lblNotes = new Label(shell, SWT.NONE);
		lblNotes.setText("Notes:");
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText(this.receipt.getNotes());

	}
}
