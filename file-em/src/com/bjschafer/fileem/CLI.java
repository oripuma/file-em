package com.bjschafer.fileem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CLI {

	
	private static String receiptInfo(Receipt receipt) {
		String toReturn = "ID: " + receipt.getSortId() +
				"\nName: " + receipt.getName() +
				"\nTotal: " + receipt.getTotal() +
				"\nCategory: " + receipt.getCategory() +
				"\nDate: " + receipt.getFriendlyDate() +
				"\nSales Tax: " + receipt.getSalesTax() +
				"\nMethod of Payment: " + receipt.getMethodOfPayment() +
				"\nNotes: " + receipt.getNotes();
		return toReturn;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean keepRunning = true;
		
		while (keepRunning) {
			System.out.println("What would you like to do?  You can:\n" +
					"a) Create a new receipt by entering it manually.\n" +
					"b) Create a new receipt by using a scanned image.\n" +
					"c) Enter the search subscreen.\n" +
					"q) Quit.");
			
			String toDo;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				toDo = br.readLine();
			}
			catch (IOException e) {
				System.err.println("Stupid error.  Exiting.");
				break;
			}

			if (toDo.equalsIgnoreCase("a")) {
				System.out.println("Ok, we're creating a new receipt.  Let's enter some information:");
				
				String name;
				System.out.println("Name (e.g. retailer): ");
				try {
					name = br.readLine();
				}
				catch (IOException e) {
					System.err.println("Stupid error.  Exiting.");
					break;
				}
				
				double total;
				System.out.println("Total price: ");
				try {
					total = Double.parseDouble(br.readLine());
				}
				catch (IOException e) {
					System.err.println("Not a number.  Exiting.");
					break;
				}
				
				String category;
				System.out.println("Category of Receipt: ");
				try {
					category = br.readLine();
				}
				catch (IOException e) {
					System.err.println("Stupid error.  Exiting.");
					break;
				}
				
				String dateStr;
				System.out.println("Enter the date on the receipt, in the format MM-DD-YYYY: ");
				try {
					dateStr = br.readLine();
				}
				catch (IOException e) {
					System.err.println("Stupid error.  Exiting.");
					break;
				}
				
//				Calendar cal = Calendar.getInstance();
				Date cal = new Date();
				int year = Integer.parseInt(dateStr.substring(6,9));
				int month = Integer.parseInt(dateStr.substring(0,2));
				int day = Integer.parseInt(dateStr.substring(3,5));
				cal.setYear(year);
				cal.setMonth(month);
				cal.setDate(day);
//				cal.set(year, month, day);
				
				Double salesTax;
				System.out.println("Enter the sales tax from the receipt: ");
				try {
					salesTax = Double.parseDouble(br.readLine());
				}
				catch (IOException e) {
					System.err.println("Stupid error.  Exiting.");
					break;
				}
				
				String mop;
				System.out.println("Enter the method of payment: ");
				try {
					mop = br.readLine();
				}
				catch (IOException e) {
					System.err.println("Stupid error.  Exiting.");
					break;
				}
				
				String notes;
				System.out.println("Enter notes, if any: ");
				try {
					notes = br.readLine();
				}
				catch (IOException e) {
					System.err.println("Stupid error.  Exiting.");
					break;
				}
				
				Receipt receipt = new Receipt(name, total, category, cal, salesTax, mop, notes);
				RDB theDB = new RDB("test.sql");
				try {
					theDB.initialize();
					theDB.storeReceipt(receipt);
					theDB.close();
				}
				catch (SQLException e) {
					System.err.println("Error writing to database.  Exiting.");
					System.err.println(e);
					break;
				}
				
				
			}
			
			else if (toDo.equalsIgnoreCase("b")) {
				System.out.println("Please enter the filename of the receipt: ");
				
				String filename;
				try {
					filename = br.readLine();
				}
				catch (IOException e) {
					System.err.println("Stupid error.  Exiting.");
					break;
				}
				
				Receipt receipt = new Receipt();
				receipt.takeImage(new File(filename));
				
				RDB theDB = new RDB("test.sql");
				try {
					theDB.initialize();
					theDB.storeReceipt(receipt);
					theDB.close();
				}
				catch (SQLException e) {
					System.err.println("Error writing to database.  Exiting.");
					break;
				}
				
			}
			
			else if (toDo.equalsIgnoreCase("c")) {
				System.out.println("What would you like to search by?  You can...");
				System.out.println("a) search by unique ID");
				System.out.println("b) search by name");
				System.out.println("c) search by category");
				System.out.println("q) quit");
				System.out.print(" More will come.");
				
				String searchOpts;
				try {
					searchOpts = br.readLine();
				}
				catch (IOException e) {
					System.err.println("Stupid error.  Exiting.");
					break;
				}
				
				if (searchOpts.equalsIgnoreCase("a")) {
					System.out.println("Enter the ID: ");
					
					int id;
					
					try {
						id = Integer.parseInt(br.readLine());
					}
					catch (IOException e) {
						System.err.println("Stupid error.  Exiting.");
						break;
					}
					
					RDB theDB = new RDB("test.sql");
					
					Receipt receipt;
					try {
						theDB.initialize();
						receipt = theDB.retrieveReceipt(id);
						theDB.close();
					}
					catch (SQLException e) {
						System.err.println("Error reading from database.  Exiting.");
						System.err.println(e.getMessage());
						break;
					}
					
					System.out.println(receiptInfo(receipt));
				}
				
				else if (searchOpts.equalsIgnoreCase("b")) {
					System.out.println("Enter the name: ");
					
					String searchName;
					
					try {
						searchName = br.readLine();
					}
					catch (IOException e) {
						System.err.println("Stupid error.  Exiting.");
						break;
					}
					
					RDB theDB = new RDB("test.sql");
					
					ArrayList<Receipt> receipts = new ArrayList<Receipt>();
					
					try {
						theDB.initialize();
						receipts = theDB.searchByName(searchName);
						theDB.close();
					}
					catch (SQLException e) {
						System.err.println("Error reading from database.  Exiting.");
						System.err.println(e.getMessage());
						break;
					}
					
					System.out.println("Printing names and dates of matching receipts.");
					for (int i = 0; i < receipts.size(); i++) {
						System.out.println((i+1) + ") " + 
						receipts.get(i).getName() + 
						" date: " +
						receipts.get(i).getFriendlyDate());
					}
					
					System.out.println("Select a receipt for more info or -1 to quit.");
					
					int selection;
					try {
						selection = Integer.parseInt(br.readLine());
					}
					catch (IOException e) {
						System.err.println("Stupid error.  Exiting.");
						break;
					}
					
					if (selection == -1)
						break;
					
					else if (selection < receipts.size()+1)
						System.out.println(receiptInfo(receipts.get(selection-1)));
				}
				
				else if (searchOpts.equalsIgnoreCase("c")) {
					System.out.println("Enter the category: ");
					
					String searchCategory;
					
					try {
						searchCategory = br.readLine();
					}
					catch (IOException e) {
						System.err.println("Stupid error.  Exiting.");
						break;
					}
					
					RDB theDB = new RDB("test.sql");
					
					ArrayList<Receipt> receipts = new ArrayList<Receipt>();
					
					try {
						theDB.initialize();
						receipts = theDB.searchByCategory(searchCategory);
						theDB.close();
					}
					catch (SQLException e) {
						System.err.println("Error reading from database.  Returning to beginning");
						System.err.println(e.getMessage());
						break;
					}
					
					System.out.println("Printing names and dates of matching receipts.");
					for (int i = 0; i < receipts.size(); i++) {
						System.out.println((i+1) + ") " + 
						receipts.get(i).getName() +
						" date: " +
						receipts.get(i).getFriendlyDate());
					}
					
					System.out.println("Select a receipt for more info or -1 to quit.");
					
					int selection;
					try {
						selection = Integer.parseInt(br.readLine());
					}
					catch (IOException e) {
						System.err.println("Stupid error.  Exiting.");
						break;
					}
					
					if (selection == -1)
						break;
					
					else if (selection < receipts.size() + 1)
						System.out.println(receiptInfo(receipts.get(selection-1)));
				}
				
				else if (searchOpts.equalsIgnoreCase("q"))
					break;
			}
			
			else if (toDo.equalsIgnoreCase("q"))
				break;
		}
	}

}
