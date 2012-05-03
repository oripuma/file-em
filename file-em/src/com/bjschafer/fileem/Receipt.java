package com.bjschafer.fileem;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Receipt {

	private int sortId = -1;
	private String name = "";
	private double total = -1;
	private String category = "";
	private Date theDate = new Date();;
//	private Calendar theDate = Calendar.getInstance();
	// TODO: Implement tracking of individual items.
	//    private Hashtable purchases = new Hashtable();
	private double salesTax = -1;
	private String methodOfPayment = "";
	private String notes = "";

	
	public Receipt() {
		
	}
	/**
	 * Creates a receipt given just an ID, autogenerates date/time.
	 * @param mySortID the sort ID, a requirement.  Must be unique, of course.
	 */
	public Receipt(int mySortID) {
//		theDate = (Calendar.getInstance());
		theDate = new Date();
		sortId = mySortID;
	}

	/**
	 * Creates a receipt given all parameters, including a Date object.
	 * @param SortID the sort ID, must be unique
	 * @param Name a friendly name for the receipt (possibly retailer/vendor)
	 * @param Total the total purchase prices
	 * @param Category the category of purchase
	 * @param THEDate the Date date
	 * @param SalesTax the sales tax paid on the receipt
	 * @param MethodOf the method of payment used
	 * @param Notes any notes
	 */
	public Receipt(String Name, double Total, String Category, Date THEDate, double SalesTax, String MethodOf, String Notes) {
		sortId = this.autogenID(THEDate);
		name = Name;
		total = Total;
		category = Category;
		theDate = THEDate;
		salesTax = SalesTax;
		methodOfPayment = MethodOf;
		notes = Notes;
	}
	
	/**
	 * Creates a receipt given all parameters, including a long of Unix epoch time.
	 * @param SortID the sord ID, must be unique
	 * @param Name a friendly name for the receipt (possibly retailer/vendor)
	 * @param Total the total purchase price
	 * @param Category the category of purchase
	 * @param THEDate the date as a Unix epoch time, a long
	 * @param SalesTax the sales tax paid on the receipt
	 * @param MethodOf the method of payment used
	 * @param Notes any notes
	 */
	public Receipt(int SortID, String Name, double Total, String Category, long THEDate, double SalesTax, String MethodOf, String Notes) {
//		Date temp = new Date();
//		temp.setTime(THEDate);
//		this.theDate.setTime(temp);
//		this.theDate.setTimeInMillis(THEDate);
//		sortId = this.autogenID(this.theDate);
		this.theDate.setTime(THEDate);
		name = Name;
		total = Total;
		category = Category;
//		this.theDate.setTime(THEDate);
		salesTax = SalesTax;
		methodOfPayment = MethodOf;
		notes = Notes;
	}
	
	/**
	 * Processes and parses the string returned from the OCR processes.
	 * @param ocrd string of ocr'd text
	 */
	private void processOCR(String ocrd) {
		ocrd = ocrd.trim(); // remove trailing/leading whitespace.
		ArrayList<String> words = new ArrayList<String>(); // keep track of individual words in the ocr'd string.
		int lastIndex = 0;
		/*
		 * This loops through each character in the string, and if it's a space, it adds that word back
		 * to the previous space to the arraylist words.
		 * WARNING: watch out for off-by-one.
		 */
		for (int i = 0; i < ocrd.length(); i++) {
			if (ocrd.substring(i,i+1).equals(" ")) {
				words.add(ocrd.substring(lastIndex,i));
				lastIndex = i;
			}
		}


		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).equalsIgnoreCase("total"))
				total = Integer.parseInt(words.get(i+1));
			else if (words.get(i).contains("tax".toLowerCase()))
				salesTax = Integer.parseInt(words.get(i+1)); 
		}
	}

	/**
	 * Processes an images file using Tesseract OCR and returns a boolean given success
	 * @param image the image file to process
	 * @return true if successful, else false
	 */
	public boolean takeImage(File image) {
		Tesseract instance = Tesseract.getInstance();

		try {
			String result = instance.doOCR(image);
			processOCR(result);
			return true;
		}
		catch (TesseractException e) {
			System.err.println(e.getMessage());
			return false;
		}

	}
	
	private int autogenID(Date date) {
		
		long time = date.getTime();
		Random generator = new Random(time);
		int temp = generator.nextInt();
		return Math.abs(temp + generator.nextInt());
	}

	/**
	 * @return the sortId
	 */
	public int getSortId() {
		return sortId;
	}

	/**
	 * @param sortId the sortId to set
	 */
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the theDate
	 */
	public Date getTheDate() {
		return theDate;
	}
	
	/**
	 * @return the date/time in Unix epoch time
	 */
	public long getEpochTime() {
		return theDate.getTime();
	}

	/**
	 * @param theDate the Date to set
	 */
	public void setTheDate(Date theDate) {
		this.theDate = theDate;
	}

	/**
	 * @param epoch the date/time to set in unix epoch time
	 */
	public void setTheDate(long epoch) {
		this.theDate.setTime(epoch);
	}

	/**
	 * @return the salesTax
	 */
	public double getSalesTax() {
		return salesTax;
	}

	/**
	 * @param salesTax the salesTax to set
	 */
	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}

	/**
	 * @return the methodOfPayment
	 */
	public String getMethodOfPayment() {
		return methodOfPayment;
	}

	/**
	 * @param methodOfPayment the methodOfPayment to set
	 */
	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getFriendlyDate() {
//		String toReturn = this.theDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
//		toReturn += " ";
//		toReturn += this.theDate.getDisplayName(Calendar.DATE, Calendar.LONG, Locale.getDefault());
//		toReturn += ", ";
//		toReturn += this.theDate.getDisplayName(Calendar.YEAR, Calendar.LONG, Locale.getDefault());
//		return toReturn;
		String toReturn = this.theDate.toString();
		return toReturn;
	}

	public String toString() {
		return name + total;
	}


}
