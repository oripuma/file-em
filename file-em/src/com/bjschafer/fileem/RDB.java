package com.bjschafer.fileem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RDB {
	
	private final String dbDriver = "jdbc:sqlite:";
	private Connection con = null;
//	private Statement stmt = null;

	/**
	 * Creates the receipt db object, and creates the connection to the sqlite db
	 * @param loc the location of the database (i.e. filename)
	 */
	public RDB(String loc) {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection((dbDriver+loc));
//			stmt = con.createStatement();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	/**
	 * Initializes the database connection.
	 * Creates the table 'receipts' if it doesn't exist.
	 * If said table does, in fact, exist, then it shouldn't do anything.
	 * @throws SQLException mandatory when mucking with SQL
	 */
	public void initialize() throws SQLException {
		Statement stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS receipts (id INTEGER PRIMARY KEY," +
				"name TEXT NOT NULL," +
				"price REAL NOT NULL," +
				"category TEXT NOT NULL," +
				"datetime INTEGER NOT NULL," +
				"salestax REAL NOT NULL," +
				"paymentmethod TEXT NOT NULL," +
				"notes TEXT)");
		stmt.close();
	}
	
	/**
	 * Stores a given receipt object in the database.
	 * It doesn't serialize the object or do anything awful like that,
	 * it extracts all of the components and stores them akin to their type.
	 * This is why we really need to deal with epoch time, I'd feel uncomfortable
	 * storing a blob of a date object in the database, as that would introduce
	 * unnecessary complexities when searching later on.
	 * So, date/time is stored as a java long (SQL integer).
	 * @param storeMe the Receipt object to store
	 * @throws SQLException mandatory when mucking with SQL
	 */
	public void storeReceipt(Receipt storeMe) throws SQLException {
		int sortId = storeMe.getSortId();
		String name = storeMe.getName();
		double total = storeMe.getTotal();
		String category = storeMe.getCategory();
		long date = storeMe.getEpochTime();
		double salesTax = storeMe.getSalesTax();
		String methodOfPayment = storeMe.getMethodOfPayment();
		String notes;
		if (storeMe.getNotes().equals(""))
			notes = null;
		else
			notes = storeMe.getNotes();
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO receipts VALUES (?,?,?,?,?,?,?,?)");
//		Statement stmt = con.createStatement();
//		stmt.executeUpdate("INSERT INTO receipts (id, name, price, category, datetime, salestax, paymentmethod, notes) VALUES (" + sortId + "," + name + "," + total + "," +
//				category + "," + date + "," + salesTax + "," + methodOfPayment + "," + notes + ")");
//		
//		stmt.close();
		ps.setInt(1,sortId);
		ps.setString(2,name);
		ps.setDouble(3, total);
		ps.setString(4, category);
		ps.setLong(5,date);
		ps.setDouble(6,salesTax);
		ps.setString(7, methodOfPayment);
		ps.setString(8, notes);
		
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Retrieves a receipt from the database given its ID.
	 * This allows for retrieval of exactly one receipt, no matter what.
	 * This is the first step, I think.  Next up comes methods to search the database.
	 * @param id the unique ID of a receipt to search for
	 * @return returns the Receipt given its ID
	 * @throws SQLException mandatory when mucking with SQL
	 */
	public Receipt retrieveReceipt(int id) throws SQLException {
		PreparedStatement ps  = con.prepareStatement("SELECT * FROM receipts WHERE id = ?");
		ps.setInt(1,id);
		
		ResultSet rs = ps.executeQuery();
		
		String name = rs.getString("name");
		double total = rs.getDouble("price");
		String category = rs.getString("category");
		Long date = rs.getLong("datetime");
		double tax = rs.getDouble("salestax");
		String mop = rs.getString("paymentmethod");
		String notes = rs.getString("notes");
		rs.close();
		ps.close();

		Receipt retMe = new Receipt(id,name,total,category,date,tax,mop,notes);
		return retMe;
	}

	public ArrayList<Receipt> searchByName(String name) throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM receipts WHERE name LIKE ?");
		ps.setString(1,name);
		
		ResultSet rs = ps.executeQuery();
		ArrayList<Receipt> retMe = new ArrayList<Receipt>();
		while (rs.next()) {
			retMe.add(retrieveReceipt(rs.getInt("id")));
		}
		rs.close();
		ps.close();
		return retMe;
	}

	public ArrayList<Receipt> searchByCategory(String category) throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM receipts WHERE category = ?");
		ps.setString(1,category);
		
		ResultSet rs = ps.executeQuery();
		ArrayList<Receipt> retMe = new ArrayList<Receipt>();
		while(rs.next()) {
			retMe.add(retrieveReceipt(rs.getInt("id")));
		}
		rs.close();
		ps.close();
		return retMe;
	}
	
	public void close() throws SQLException {
//		stmt.close();
		con.close();
	}
}
