import javax.swing.JFrame;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class RecommandData {

	private Connection conn;
	private Statement stat;
	private RDManager RDM;
	private String labelMsg;
	
	public RecommandData( Connection conn ) throws SQLException {
		
		this.conn = conn;
		stat = conn.createStatement();
		labelMsg = "";
	}
	
	public void getData() throws SQLException, RDManager.LabelError {
		
//		RDM = new RDManager( labelMsg );
//		String sql = RDM.getSQL();
		String sql = "SELECT * FROM `1101extra`";
		boolean success = stat.execute( sql );
		
		if( success ) {
			
			ResultSet result = stat.getResultSet();
			String txt = showResultSet( result );
			System.out.println( txt ); 
			
		}
	}
	
	
	
	public static String showResultSet(ResultSet result) throws SQLException {
		
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";
		
		for (int i = 1; i <= columnCount; i++) {			
			output += String.format("%15s", metaData.getColumnLabel(i));
		}
		output += "\n";
		
		while (result.next()) {
			for (int i = 1; i <= columnCount; i++) {
				output += String.format("%15s", result.getString(i));
			}
			output += "\n";
		}
		return output;
	}
}
