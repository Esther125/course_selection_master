import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RDManager {

	private String sql;
//	name, teacher, time, level, category;
	private String[] rawData;
	
	public RDManager( ArrayList<String> categoryLb,  ArrayList<String> levelLb, ArrayList<String> timeLb, String teacher, String course ) throws LabelError {
		
		if( categoryLb.size()==0&&levelLb.size()==0&&timeLb.size()==0&&teacher.length()==0&&course.length()==0 ) {
			JOptionPane.showMessageDialog( null, "You should have some courses labels.", "Error", JOptionPane.ERROR_MESSAGE );
//			throw new LabelError( "You should have some courses labels." );
		}
		
		sql = "SELECT * FROM `1102extra` WHERE";
		
		if( categoryLb.size()>0 ) {
			
			sql += "(";
			for( int i=0; i<categoryLb.size(); ++i ) {
				
				sql += String.format( "`Category`=\"%s\"", categoryLb.get(i) );
				if( i!=categoryLb.size()-1 ) {
					sql += " OR ";
				}
			}
			sql += ")";
		}
		if( categoryLb.size()>0&&levelLb.size()>0 ) {
			sql += " AND ";
			sql += "(";
		}
		for( int i=0; i<levelLb.size(); ++i ) {
			sql += String.format( "`Level`=\"%s\"", levelLb.get(i) );
			if( i!=levelLb.size()-1 ) {
				sql += " OR ";
			}else {
				sql += ")";
			}
		}
		if( (categoryLb.size()>0||levelLb.size()>0)&&timeLb.size()>0 ) {
			sql += " AND ";
			sql += "(";
		}
		for( int i=0; i<timeLb.size(); ++i ) {
			String s = "%";
			sql += String.format( "`Time` LIKE \"%s%s\"", timeLb.get(i), s );
			if( i!=timeLb.size()-1 ) {
				sql += " OR ";
			}else {
				sql += ")";
			}
		}
		if( (categoryLb.size()>0||levelLb.size()>0||timeLb.size()>0)&&teacher.length()>0 ) {
			sql += " AND ";
		}
		if(teacher.length()>0) {
			
			sql += String.format( "`Teacher`=\"%s\"", teacher );
		}
		if( (categoryLb.size()>0||levelLb.size()>0||timeLb.size()>0||teacher.length()>0)&&course.length()>0 ) {
			sql += " AND ";
		}
		if(course.length()>0) {
			
			sql += String.format( "`Course`=\"%s\"", course );
		}
		sql += ";";
		System.out.println( sql );
	}
	
	class LabelError extends Exception{
		
		public LabelError( String Error ) {
			
			super( Error);
		}
	}
	
	public String getSQL() {
		
		return sql;
	}
}
