package com.squashtmjar;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SquashtmSQLQuery {
	
private ResultSet resultSet = null;
	
	//db object
	SquashtmDB db = new SquashtmDB();
	
	public void sqlQuery() throws SQLException, IOException, ClassNotFoundException {
		
		// get the result of the sql query
		resultSet = db.dbConnect().executeQuery("select PROJECT.NAME, count(TEST_CASE_LIBRARY_NODE.NAME) as Testcases\n" + 
				"From squashtm.TEST_CASE_LIBRARY_NODE\n" + 
				"inner join squashtm.TEST_CASE on squashtm.TEST_CASE_LIBRARY_NODE.TCLN_ID = squashtm.TEST_CASE.TCLN_ID\n" + 
				"inner join squashtm.PROJECT on squashtm.TEST_CASE_LIBRARY_NODE.PROJECT_ID = squashtm.PROJECT.PROJECT_ID\n" + 
				"group by PROJECT.NAME;");

	 writeResultSet(resultSet);
}
	private void writeResultSet(ResultSet resultSet) throws SQLException {
		
		// ResultSet is initially before the first data set
		while (resultSet.next()) {

			String project_name = resultSet.getString("PROJECT.NAME");
			String test_cases = resultSet.getString("Testcases");

			System.out.println("Project Name: " + project_name + "\n" + "Testcases: " + test_cases + "\n");

		}
	}

}
