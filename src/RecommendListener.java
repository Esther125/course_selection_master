import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RecommendListener implements ActionListener {
	
	   @Override
       public void actionPerformed( ActionEvent e ) {
		   
		   RecommandFrame frame = new RecommandFrame();
		   frame.setVisible(true);
	}
}