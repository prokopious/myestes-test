package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class SQLDataList {
	
	Logger logger = Logger.getLogger(SQLDataList.class);
	
	private void connectJdbcDriver() {
		try {
			Class.forName(ConnectConfig.AS400_JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getExlaqaConnection() throws SQLException {
		try {
			return DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
					ConnectConfig.PASSWORD1);
		} catch (SQLException e) {
			logger.info("Connection to EXLAQA could not be established");
			e.printStackTrace();
			throw e;
		}
	}
	
	private Statement createStatement(Connection con) throws SQLException {
		try {
			return con.createStatement();
		} catch (SQLException e) {
			logger.info("Statement could not be made from the given connection");
			e.printStackTrace();
			throw e;
		}
	}

	public List<List<String>> getDataByColumn(String database, String query)
			throws java.lang.ClassNotFoundException {
		return getDataByColumn(database, query);

	}

	public List<List<String>> getDataByRow(String database, String query)
			throws java.lang.ClassNotFoundException {
		return getDataByRow(database, query);
	}

	public boolean validateValueFromDataBase(String query) {
		boolean flag = false;
		List<String> databaseVal = getFirstRowDetailsFromEXLAQA(query);
		if (databaseVal.isEmpty()) {
			logger.error("Unable to fetch the details from Database..");
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	public List<String> getFirstRowDetailsFromEXLAQA(String query) {
		logger.info("Executing Query: " + query);
		ArrayList<String> databaseVal = new ArrayList<>();
		connectJdbcDriver();
		try (Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
				ConnectConfig.PASSWORD1); Statement statement = con.createStatement();) {
			ResultSet rs = statement.executeQuery(query);
			rs.getRow();
			rs.next();
			if (rs.getRow() > 0) {
				int columnCount = rs.getMetaData().getColumnCount();
				logger.info("Fetching Column no from Database: " + columnCount);
				for (int col = 1; col <= columnCount; col++) {
					databaseVal.add(rs.getString(col));
				}
			}
		} catch (SQLException e) {
			logger.error("Interrupted connection/Invalid query");
			e.printStackTrace();
		}
		return databaseVal;
	}
	
	public List<String> getQuoteFromQuery(String query) {
        logger.info("Executing Query: "+query);
        ArrayList<String> quoteNumber = new ArrayList<>();
       connectJdbcDriver();
		try (Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
				ConnectConfig.PASSWORD1); 
				Statement statement = con.createStatement();) {
			
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				quoteNumber.add(rs.getString(1));
				logger.info(rs.getString(1));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        
        return quoteNumber;
  }

	

	public List<String> getProFromQuery(String query) {
		logger.info("Executing Query: " + query);
		connectJdbcDriver();
		List<String> proNumber = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
				ConnectConfig.PASSWORD1); 
				Statement statement = con.createStatement();) {

			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String ot = rs.getString(1);
				if (ot.length() == 2) {
					ot = "0" + ot;
				} else if (ot.length() == 1) {
					ot = "00" + ot;
				}
				proNumber.add(ot + "-" + rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proNumber;
	}

	public Set<String> getBolFromQuery(String query) {
		logger.info("Executing Query: " + query);
		connectJdbcDriver();
		Set<String> bolNumber = new HashSet<>();
		try (Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
				ConnectConfig.PASSWORD1); Statement statement = con.createStatement();) {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				if (bolNumber.size() < 25) {
					bolNumber.add(rs.getString(1).trim());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return bolNumber;
  }

	public List<String> fetchAllRowDetailsFromEXLAQA(String query, int colIndex, int colIndex1) {
		logger.info("Executing Query: " + query);
		ArrayList<String> databaseVal = new ArrayList<>();
		connectJdbcDriver();
		try (Connection con = getExlaqaConnection(); Statement statement = con.createStatement();) {
			ResultSet rs = statement.executeQuery(query);
			rs.getRow();

			while (rs.next()) {

				if (colIndex1 != 0) {
					String colValue1 = rs.getString(colIndex);
					String colValue2 = rs.getString(colIndex1);
					databaseVal.add(colValue1 + " " + colValue2);
				} else {
					String colValue1 = rs.getString(colIndex);
					databaseVal.add(colValue1);
				}
			}
		} catch (SQLException e) {
			logger.error("Interrupted connection/Invalid query");
			e.printStackTrace();
		}
		return databaseVal;
	}

	public List<String> getAccountNumber(String query) {
		logger.info("Executing Query: " + query);
		ArrayList<String> databaseVal = new ArrayList<>();
		connectJdbcDriver();
		try (Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
				ConnectConfig.PASSWORD1); Statement statement = createStatement(con);) {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				databaseVal.add(rs.getString("ACCOUNTNUMBER"));
			}
		} catch (SQLException e) {
			logger.error("Interrupted connection/Invalid query");
			e.printStackTrace();
		}
		return databaseVal;
	}
	

	public void updateRecordInTable(String query) {
		logger.info("Update the Table Query : " + query);
		connectJdbcDriver();
		try (Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
				ConnectConfig.PASSWORD1); Statement statement = con.createStatement();) {
			boolean condition = statement.execute(query);
			logger.info("condition : " + condition);
			logger.info("Record updated successfully.");
		} catch (SQLException e) {
			logger.error("Interrupted connection/Invalid query");
			e.printStackTrace();
		}
	}

	public String getColumnValue(String query) throws SQLException  {

		connectJdbcDriver();
		String databaseVal;
		try (Connection con = getExlaqaConnection(); Statement statement = createStatement(con);) {
			ResultSet rs = statement.executeQuery(query);
			databaseVal = null;
			while (rs.next()) {
				databaseVal = rs.getString(1);
			}
			logger.info("Value returned from  Databas" + databaseVal);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return databaseVal;
	}

	public List<String> getValuesFromExlaDatabase(String query) {
		logger.info("Executing Query: " + query);
		ArrayList<String> databaseVal = new ArrayList<>();
		
		try (Connection con = DriverManager.getConnection("jdbc:as400:10.28.49.11/exla", "qatstfrtbl", "qatest2019");
			Statement statement = con.createStatement();){
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			ResultSet rs = statement.executeQuery(query);
			rs.getRow();
			while (rs.next()) {
				int columnCount = rs.getMetaData().getColumnCount();
				logger.info("Fetch Column No From Database: " + columnCount);
				for (int col = 1; col <= columnCount; col++) {
					databaseVal.add(rs.getString(col));
				}
			}
		} catch (ClassNotFoundException e) {
			logger.error("Invalid Database driver...");
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("Interrupted connection/Invalid query");
			e.printStackTrace();
		} finally {
			logger.info("Query Processed");
		}
		return databaseVal;
	}

	public List<String> getDataFromFRP001andFRP002Tables(String query) {
		
		logger.info("Executing Query: " + query);
		ArrayList<String> databaseVal = new ArrayList<>();
        
        connectJdbcDriver();
		try (Connection con = getExlaqaConnection();
				Statement statement = createStatement(con);
				ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				logger.info("FHOT : " + rs.getBigDecimal("FHOT"));
				databaseVal.add(rs.getBigDecimal("FHOT").toString());
				logger.info("FHPRO : " + rs.getBigDecimal("FHPRO"));
				databaseVal.add(rs.getBigDecimal("FHPRO").toString());
				logger.info("FHCNM : " + rs.getString("FHCNM"));
				databaseVal.add(rs.getString("FHCNM"));
				logger.info("FDCMCL : " + rs.getString("FDCMCL"));
				databaseVal.add(rs.getString("FDCMCL"));
				logger.info("FDDES : " + rs.getString("FDDES"));
				databaseVal.add(rs.getString("FDDES"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return databaseVal;
	}
	/**
	 * Use for browsing SQL query results on EXLAQA
	 * 
	 * @author coxda
	 * @param query The query to be executed
	 * @return
	 */
	/**
	 * Use for browsing SQL query results on EXLAQA
	 * 
	 * @author coxda
	 * @param query The query to be executed
	 * @return
	 */
	public List<List<String>> viewQueryResults(String query, int rowCount) {
		
		logger.info("Executing Query: [" + query + "]");
		
		List<List<String>> resultTable = new ArrayList<>();
		connectJdbcDriver();
		
		try (Connection connection = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING,
					ConnectConfig.USERNAME1, ConnectConfig.PASSWORD1);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);) {
			
			ResultSetMetaData metaData = resultSet.getMetaData();

			StringBuilder metaString = new StringBuilder("    ");
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				metaString.append("[" + i + " " + metaData.getColumnName(i) + "] ");
			}
			logger.info(metaString.toString());

			int row = 1;
			while (resultSet.next() && row <= rowCount) {
				List<String> resultRow = new ArrayList<>();
				if (resultSet.getRow() > 0) {
					int columnCount = resultSet.getMetaData().getColumnCount();
					StringBuilder sb = new StringBuilder();
					sb.append("[" + row + "] ");
					for (int col = 1; col <= columnCount; col++) {
						sb.append('[');
						String data = resultSet.getString(col);
						if (data != null) {
							sb.append(data.trim());
							resultRow.add(data);
						} else {
							resultRow.add("");
						}
						sb.append("]");
					}
					logger.info(sb.toString());
				}
				resultTable.add(resultRow);
				row++;
			}
		} catch (SQLException e) {
			logger.error("Interrupted connection/Invalid query");
			e.printStackTrace();
		}
		return resultTable;
	}


	
	public List<String> getSpecificRowDetailsFromEXLAQA(String query, int count) {
		logger.info("Executing Query: " + query);
		ArrayList<String> databaseVal = new ArrayList<>();
		connectJdbcDriver();
		try (Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1,
				ConnectConfig.PASSWORD1);
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(query);) {
			rs.getRow();
			// Based on count, shift along the row to a new data set of queried values.
			for (int i = 0; i < count; i++) {
				rs.next();
			}
			if (rs.getRow() > 0) {
				int columnCount = rs.getMetaData().getColumnCount();
				logger.info("Fetching Column no from Database: " + columnCount);
				for (int col = 1; col <= columnCount; col++) {
					databaseVal.add(rs.getString(col));
				}
			}
		} catch (SQLException e) {
			logger.error("Interrupted connection/Invalid query");
			e.printStackTrace();
		}
		return databaseVal;
	}

	public List<List<String>> viewQueryResults(String query) {
		return viewQueryResults(query, 100);
	}



	/**
	 * Use for SQL update statements on EXLAQA
	 * 
	 * @author coxda
	 * @param update The update to be executed
	 * @return
	 */
	public void updateQuery(String update) {

		logger.info("Executing Query: [" + update + "]");
		connectJdbcDriver();
		try (Connection connection = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING,
				ConnectConfig.USERNAME1, ConnectConfig.PASSWORD1);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(update);
		} catch (SQLException e) {
			logger.error("Interrupted connection/Invalid query");
			e.printStackTrace();
		}
	}
	
	
	public List<String> sqlQueryResultsForShipperDetails(String query, int whichShipper) {

		List<String> shipperAccount = getSpecificRowDetailsFromEXLAQA(query, whichShipper);
		ArrayList<String> customerDetails = new ArrayList<>();
		String originalTerminal = "";

		// shiper code

		customerDetails.add(shipperAccount.get(1));
		// Shipper Name
		customerDetails.add(shipperAccount.get(2));
		// Shipper Zip Code
		customerDetails.add(shipperAccount.get(3));
		// Shipper State
		customerDetails.add(shipperAccount.get(4));
		// Shipper City
		customerDetails.add(shipperAccount.get(5));
		// Shipper Origin Terminal
		originalTerminal = shipperAccount.get(8);

		if (originalTerminal.length() < 3) {
			originalTerminal = "0" + originalTerminal;
			customerDetails.add(originalTerminal);
		} else {
			customerDetails.add(originalTerminal);
		}
		logger.info(customerDetails.toString());
		return customerDetails;
	}
	
	public Map<String, Object> getValidUser(String query) throws Exception {  
		logger.info("Executing Query: "+query);
		Class.forName(ConnectConfig.AS400_JDBC_DRIVER);
		Connection con = DriverManager.getConnection(ConnectConfig.EXLAQA_CONN_STRING, ConnectConfig.USERNAME1, ConnectConfig.PASSWORD1);
		Statement statement = con.createStatement();
		Map<String, Object> acctInfo = new HashMap<String, Object>();
		
		try {
			ResultSet rs=statement.executeQuery(query);
			
			 while(rs.next()){ 
				 acctInfo.put("userName", rs.getString(2));
				 acctInfo.put("password", rs.getString(3));
			 }
			statement.close();

		} catch (SQLException e) {
			statement.close();
		}
		return acctInfo;
	}
}
