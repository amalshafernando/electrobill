package com;
import java.sql.*;
public class Bill{

	private Connection connect() {

		Connection con = null;
		try { 
			   Class.forName("com.mysql.jdbc.Driver");
		       con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrobill", "root", "1234");			
			}
		catch (Exception e) {
				  e.printStackTrace();
		}
		return con;
	}
	public String readBills()
	{
		String output = "";
		try { 
			Connection con = connect();
			if (con == null) { 
				return "Error while connecting to the database for reading.";
					} 

			// Prepare the html table to be displayed
			output = "<table border='1'><tr> " + "<th>Bill ID</th>"
					+ "<th>Customer ID</th>"
					+ "	<th>Customer Name</th>"
					+ "	<th>Bill Date </th>"
					+ "	<th>Units consumed</th>"
					+ "<th> Total</th>"
					
					+ "	<th>Update</th>"
					+ "	<th>Remove</th> </tr>";
			

			String query = "select * from items";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{ 
				String billID = Integer.toString(rs.getInt("billID"));
				String customerID = Integer.toString(rs.getInt("customerID"));
	//			String customerCode = rs.getString("customerCode");
				String customerName = rs.getString("customerName");
				String bill_date = rs.getString("bill_date");
				String units = Integer.toString(rs.getInt("units"));
				Double unt_price = rs.getDouble("unt_price");
				String total = Double.toString(rs.getDouble("total"));
				

				
				
				
				// Add into the html table
				output += "<tr><td>" + customerID  + "</td>";
				output += "<td>" + customerName + "</td>";
				output += "<td>" + bill_date + "</td>";
				output += "<td>" + units + "</td>";
				output += "<td>" + unt_price + "</td>";
				output += "<td>" + total + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-primary badge-pill' data-itemid='" + billID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger badge-pill' data-itemid='" + billID + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
				 
		
		catch (Exception e) {
		
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
			}
		
			
			return output;
		}
	
	public String insertBill(String billID , String customerID,String customerName, String bill_date ,String units, String unt_price, String total) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
				}
	// create a prepared statement
	String query = " insert into items (`billID`,`customerID`,`customerName`,`bill_date`,`units`, `unt_price`, `total` )"  + " values (?, ?, ?, ?, ?,?,?)";
	
			PreparedStatement preparedStmt = con.prepareStatement(query); 	
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerID);
			preparedStmt.setString(3, customerName);
			preparedStmt.setString(4, bill_date);
			//preparedStmt.setDouble(5, Double.parseDouble(price));
			preparedStmt.setString(5, units);
			preparedStmt.setString(6,  Double.parseDouble(total));
			
					// execute the statement
			preparedStmt.execute();
			con.close();
			String newBills = readBills();
			output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
			}
		catch (Exception e)
			{
			   output = "{\"status\":\"error\", \"data\": \"Error while inserting the bill.\"}";
			   System.err.println(e.getMessage());
			}
		return output;
		}
	
	
			public String updateBill(String billID , String customerID,String customerName, String bill_date ,String units, String unt_price, String total)
			{
				String output = "";
				try
				{ 
					Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
		String query = "UPDATE bills SET customerID=?,customerName=?,units=?, unt_price=?, total=? WHERE billID=?";
			
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values 
		  /*  preparedStmt.setString(1, );
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(ID)); */
			
			// binding values
						preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, customerID);
						preparedStmt.setString(3, customerName);
						preparedStmt.setString(4, bill_date);
						//preparedStmt.setDouble(5, Double.parseDouble(price));
						preparedStmt.setString(5, units);
						preparedStmt.setString(6,  Double.parseDouble(total));
					
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newBills = readBills();
			output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
				System.err.println(e.getMessage());
			}
				return output;
				}
			public String deleteBill(String billID)
			{
				String output = "";
				try{ 
					Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for deleting.";
						}
			// create a prepared statement
			String query = "delete from bills where billID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(billID) );
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readBills();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
					System.err.println(e.getMessage());}return output;
					}
			}
	

