package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCFirstRow {

	public static  ArrayList<ArrayList<String>> getDataByRow( String query) throws ClassNotFoundException{ 
		//String database,
		
		ArrayList<ArrayList<String>> sqlResults = new ArrayList<>();
		
		System.out.println("trying to connect....");
		String DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
		String URL = "jdbc:as400:10.28.49.11/exla";
		String user = "qatstfrtbl";
		String password = "qatest2019";
		Class.forName(DRIVER);

		try {
			
			Connection con = DriverManager.getConnection(URL,user, password);
			 if (con != null) {
	             System.out.println("Connected to the Database...");
	         }
			Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet resultSet = statement.executeQuery(query);
			
				// Get column count
				int columnCount = resultSet.getMetaData().getColumnCount();
				// Move cursor to last position of the ResultSet to get row count
				resultSet.last();
				// Get the number of rows returned in the ResultSet
				int rowCount = resultSet.getRow();
				// Set the cursor at the beginning of the ResultSet
				resultSet.beforeFirst();
				// Loop through rows and add each one to an ArrayList
				for (int i = 1; i <= rowCount; i++) {
					// Position the cursor over the data to be added
					resultSet.next();
					// Create temporary ArrayList for storing row data
					ArrayList<String> row = new ArrayList<>();
					// Create counter variable for iterating over columns
					int j = 1;
					// Loop over ResultSet to fill the temporary ArrayList
					while (j <= columnCount) {
						// Get the string value of the data
						String n = resultSet.getString(j).trim();
						
						// Add data to the the ArrayList
						row.add(n);
						// Increment counter
						j++;
					}
					
					sqlResults.add(row);
				}
			
		} catch (SQLException e) {
			// Print the exception if one is generated
			System.out.println("The exception is: " + e);
			// Print the vendor specific error code if an exception is generated
			System.out.println("The error code is: " + e.getErrorCode());
			// Print the vendor specific sql state if an exception is generated
			System.out.println("The sql state is: " + e.getSQLState());
		}
		return sqlResults;
		
		
	}

	
}




