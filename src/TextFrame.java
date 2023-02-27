import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import java.awt.SystemColor;

public class TextFrame extends JFrame implements MouseListener{

	private JFrame frame;
	private JTextField entercourse;
	private static final int FRAME_WIDTH = 450; 
	private static final int FRAME_HEIGHT = 600;
	private Connection conn; 
	private Statement stat; 
	private boolean success;
	private String information;


	public TextFrame()throws SQLException {
		
		String server = "jdbc:mysql://140.119.19.73:3315/"; 
		String database = "tuegroup8"; // change to your own database 
		String url = server + database + "?useSSL=false"; 
		String username = "tuegroup8"; // change to your own username 
		String password = "hjz7081"; // change to your own password 

		try { 
			//連資料庫+GUI
			Connection conn = DriverManager.getConnection(url, username, password); 
			
			frame = new JFrame();
			frame.getContentPane().setLayout(null);
			stat = conn.createStatement();
			
			JLabel title = new JLabel("\u6B77\u5E74\u52A0\u7C3D\u8CC7\u8A0A\u7D71\u6574");
			title.setFont(new Font("微软雅黑 Light", Font.BOLD, 12));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setBounds(144, 26, 103, 27);
			frame.getContentPane().add(title);
			
			JLabel resulttitle = new JLabel();
			resulttitle.setBounds(52, 114, 296, 16);
			frame.getContentPane().add(resulttitle);
			resulttitle.setFont(new Font("微软雅黑 Light", Font.PLAIN, 12));
			
			entercourse = new JTextField();
			entercourse.setBounds(52, 83, 249, 21);
			frame.getContentPane().add(entercourse);
			entercourse.setColumns(10);
			
			JButton search = new JButton("\u641C\u5C0B");
			search.setHorizontalAlignment(SwingConstants.LEFT);
			search.setFont(new Font("微软雅黑 Light", Font.PLAIN, 12));
			search.setBounds(311, 80, 63, 23);
			search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					resulttitle.setText("有關 "+entercourse.getText()+" 的搜尋結果:");
					
					//從資料庫抓使用者搜尋的課程
					String query="";
					String a="";
					ArrayList<String> allshow = new ArrayList<String>();
					ArrayList<String> year = new ArrayList<String>();
					Integer[] years={1102,1101,1092,1091,1082,1081,1072,1071};
				
					try {
						String[] searchshow = null;
						ResultSet result=null;
						String str = "%";
						for(int k=0;k<years.length;k++) {
							a = String.format("SELECT COURSE,TEACHER,TIME FROM `%dadd` WHERE COURSE LIKE '%s%s%s'",years[k],str,entercourse.getText(),str);
							query = a;
							success = stat.execute(query);
							if(success) {
								result = stat.getResultSet();
								String show = showData(result);
								searchshow = show.split("\n");
								for (String x : searchshow){
									if(x!="")
						            allshow.add(x);
						        }
							}						
						}
						
						//讓課程不要重複出現(但還不確定有沒有成功)
						ArrayList<String> norepeat = new ArrayList();
						for(String s:allshow) {
							if (!norepeat.contains(s)) {
								norepeat.add(s);
							}
						}
						int length = allshow.size();
						String[] finalshow = new String[length];
						
						//把arraylist換成array再加上title 方便之後放進jlist
						for(int n=1;n<length;n++) {
							finalshow[0] = showResultSet(result);
							finalshow[n] = norepeat.get(n);
						}
						
							JList list = new JList(finalshow);
							list.setVisibleRowCount(3);
							setInformation((String)list.getSelectedValue());
							list.addMouseListener(new MouseListener() {
								public void mouseClicked(java.awt.event.MouseEvent e) {
									// TODO Auto-generated method stub
									try {
										if(e.getClickCount() == 2) {
											if(list.getSelectedIndex()!=0) {
												
												new CourseFrame((String)list.getSelectedValue());
												
											}
										}
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
								
								@Override
								public void mousePressed(java.awt.event.MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mouseReleased(java.awt.event.MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mouseEntered(java.awt.event.MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mouseExited(java.awt.event.MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
							});
							
							JScrollPane scrollPane_1 = new JScrollPane(list);
							scrollPane_1.setBounds(52, 140, 322, 330);
							frame.getContentPane().add(scrollPane_1);
							result.close();
						}
						
					
					catch(Exception e) {
						System.out.print(e);
					}
				}
			});
			frame.getContentPane().add(search);
			
			JLabel searchtitle = new JLabel("\u8ACB\u8F38\u5165\u60F3\u67E5\u8A62\u7684\u8AB2\u7A0B:");
			searchtitle.setFont(new Font("微软雅黑 Light", Font.PLAIN, 12));
			searchtitle.setBounds(52, 63, 195, 15);
			frame.getContentPane().add(searchtitle);
			
			JButton back = new JButton("\u4E0A\u4E00\u9801");
			back.setBackground(SystemColor.activeCaption);
			back.setFont(new Font("微软雅黑 Light", Font.PLAIN, 12));
			back.setBounds(329, 517, 85, 23);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					frame.dispose();
				}
			});
			frame.getContentPane().add(back);
			
			createLayout();
			
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 

	}
	
	
	
	private void createLayout() {
		 
		frame.setTitle("加簽專區(選課小精靈)");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//只show title
	public static String showResultSet(ResultSet result) throws SQLException { 

		ResultSetMetaData metaData = result.getMetaData(); 
		int columnCount = metaData.getColumnCount(); 
		String output = ""; 
 

		for (int i = 1; i <= columnCount; i++) {			 
			output += String.format("     %s", metaData.getColumnLabel(i)); 
		} 

		output += "\n"; 

		return output; 
	} 
	
	//只show data
	public static String showData(ResultSet result) throws SQLException { 

		ResultSetMetaData metaData = result.getMetaData(); 
		int columnCount = metaData.getColumnCount(); 
		String output = ""; 

		while (result.next()) { 
			for (int i = 1; i <= columnCount; i++) { 
				output += String.format("   %s", result.getString(i)); 
			} 
			output += "\n"; 
		} 
		return output; 
	} 
	
	private JButton createButtons(String text) {
        JButton button = new JButton(text);
        return button;
    }

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setInformation(String s) {
		this.information = s;
	}
	
	public String getInformation() {
		return information;
	}

	
}