import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.util.ArrayList;

public class RecommandFrame extends JFrame {
	private JPanel panel1, panel2,panel3,panel4,panel5,panel6,panel8,panel9,panel10,panel11,panel12;
	private JPanel panel7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JTextField teacherField,courseField;
	private JButton backButton,StartButton;
	private JLabel label,human,humancore,society,societycore,science,sciencecore,
	               computer,book,multi,PE,Chinese,hot,normal,cold,monday,tuesday,wednesday,thursday,friday
	               ,teacherprefer,courseprefer,ascourse,aspop,asday;
	private ResultFrame resultFrame;
	private Border border = BorderFactory.createLineBorder(Color.BLACK);
	
	private boolean bhuman=false,bhumancore=false,bsociety=false,bsocietycore=false,bscience=false,bsciencecore=false,
				    bcomputer=false,bbook=false,bmulti=false,bPE=false,bChinese=false,bhot=false,bnormal=false,bcold=false,bmonday=false,btuesday=false,bwednesday=false,bthursday=false,bfriday=false,
				    bteacherprefer=false,bcourseprefer=false,bascourse=false,baspop=false,basday=false;
	
	private ArrayList<String> categoryLb;
	private ArrayList<String> levelLb;
	private ArrayList<String> timeLb;
	
	
	private Connection conn;
	
	  class Mouse implements MouseListener{
		  
		  private boolean change;
		  private String name, type;
		  
		  public Mouse( boolean change, String name, String type ) {
			  
			  this.change = change;
			  this.name = name; 
			  this.type = type;
		  }
		  
		@Override
		public void mouseClicked(MouseEvent e) {
			
			// TODO Auto-generated method stub
			change=(!change);
			if( change ) {
				
				((JComponent) e.getComponent()).setOpaque(true);
				e.getComponent().setBackground(new Color(255,242,153)); 
				 ((JComponent) e.getComponent()).setBorder(border);
				 if( type.equals( "Category" ) ) {
					 categoryLb.add( name );
				 }else if( type.equals( "Level" ) ) {
					 levelLb.add( name );
				 }else if( type.equals( "Time" ) ) {
					 timeLb.add( name );
				 }
				
			}else {
				
				((JComponent) e.getComponent()).setOpaque(false);
				if( type.equals( "Category" ) ) {
					 categoryLb.remove( name );
				 }else if( type.equals( "Level" ) ) {
					 levelLb.remove( name );
				 }else if( type.equals( "Time" ) ) {
					 timeLb.remove( name );
				 }
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		
			
		}

		

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			e.getComponent().setForeground(Color.RED);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getComponent().getBackground()!=new Color(255,242,153)) {
			   e.getComponent().setForeground(Color.BLACK);
			}else {
				e.getComponent().setForeground(Color.RED);
			}//原本想說這樣被選到的label移走之後字還是紅的 但好像沒有用
		}

	}
  
	  
	public RecommandFrame () {
		
		createTextArea();
		createLabel();
		createTextField();
	    createButton();
	    createLayout();
		
		setTitle("Recommend Frame");
		setSize(700, 400);
		
		categoryLb = new ArrayList<>();
		levelLb = new ArrayList<>();
		timeLb = new ArrayList<>();
		
		String server = "jdbc:mysql://140.119.19.73:3315/";
		String database = "tuegroup8"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "tuegroup8"; // change to your own username
		String password = "hjz7081"; // change to your own password
		
		try {
			
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("DB Connectd");
			RecommandData data = new RecommandData(conn);
			
			
//				data.getData();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}    
	}
	
	
	
	private void createTextArea() {
		teacherField = new JTextField(10);
		courseField  = new JTextField(10);
		
	}
	
	private void createLabel() {
		
		Mouse mouseListner; 
		label = new JLabel("<html><body><div style='color:#000000;font-size:20px;font-family:微軟正黑體;'>" + ""+" 推薦志願序</div></body></html>");
		
		mouseListner = new Mouse( bhuman, "人文通", "Category" );
		human = new JLabel("人文通");
		human.setBorder(border);
		human.addMouseListener(mouseListner);
		
		mouseListner = new Mouse( bhumancore, "人文核通", "Category" );
		humancore = new JLabel("人文核通");
		humancore.setBorder(border);
		humancore.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bsociety, "社會通", "Category" );
		society = new JLabel("社會通");
		society.setBorder(border);
		society.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bsocietycore, "社會核通", "Category" );
		societycore = new JLabel("社會核通");
		societycore.setBorder(border);
		societycore.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bscience, "自然通", "Category" );
		science = new JLabel("自然通");
		science.setBorder(border);
		science.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bsciencecore, "自然核通", "Category" );
		sciencecore = new JLabel("自然核通");
		sciencecore.setBorder(border);
		sciencecore.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bcomputer, "資訊通", "Category" );
		computer = new JLabel("資訊通");
		computer.setBorder(border);
		computer.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bbook, "書院通", "Category" );
		book = new JLabel("書院通");
		book.setBorder(border);
		book.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bmulti, "跨類別通", "C  ategory" );
		multi = new JLabel("跨類別通");
		multi.setBorder(border);
		multi.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bPE, "體育", "Category" );
		PE = new JLabel("體育");
		PE.setBorder(border);
		PE.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bChinese, "國文", "Category" );
		Chinese = new JLabel("國文");
		Chinese.setBorder(border);
		Chinese.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bhot, "排一不一定會上", "Level" );
		hot = new JLabel("排一不一定會上");
		hot.setBorder(border);
		hot.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bnormal, "排前面一點就會上", "Level" );
		normal = new JLabel("排前面一點就會上");
		normal.setBorder(border);
		normal.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bcold, "隨便排都會上", "Level" );
		cold = new JLabel("隨便排都會上");
		cold.setBorder(border);
		cold.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bmonday, "一", "Time" );
		monday = new JLabel("星期一");
		monday.setBorder(border);
		monday.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( btuesday, "二", "Time" );
		tuesday = new JLabel("星期二");
		tuesday.setBorder(border);
		tuesday.addMouseListener(mouseListner);

		
		mouseListner = new Mouse( bwednesday, "三", "Time" );
		wednesday = new JLabel("星期三");
		wednesday.setBorder(border);
		wednesday.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bthursday, "四", "Time" );
		thursday = new JLabel("星期四");
		thursday.setBorder(border);
		thursday.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bfriday, "五", "Time" );
		friday = new JLabel("星期五");
		friday.setBorder(border);
		friday.addMouseListener(mouseListner);
		
		
		teacherprefer= new JLabel("如果你有想要的老師: ");
		courseprefer= new JLabel("如果你已經決定要上哪堂:  ");
		ascourse = new JLabel("按課程類型: ");
		ascourse.setHorizontalAlignment(SwingConstants.LEFT); 
		
		
		
		aspop = new JLabel("按熱門程度: ");
		asday = new JLabel("按星期幾: ");

	}
	
	private void createTextField() {
		teacherField = new JTextField(10);
		courseField = new JTextField(10);
	}
	
	
	
	private void createButton(){
		
		StartButton = new JButton("開始推薦志願序");
		
		
		
		StartButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent event){
				
				try {
					
					RDManager RDM = new RDManager( categoryLb, levelLb, timeLb, teacherField.getText(), courseField.getText() );
					String resultInfo, sql = RDM.getSQL();
					Statement st = conn.createStatement();
					boolean success = st.execute( sql );
					if( success ){
						ResultSet result = st.getResultSet();
						resultInfo = showResultSet( result );
						resultFrame = new ResultFrame( resultInfo );
						resultFrame.setVisible(true);
					}
				} catch (RDManager.LabelError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		backButton = new JButton("返回");
		backButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent event){
				dispose();
			
			}
		});
		
	}
	
	
	private void createLayout() {
		panel4 = new JPanel();
		panel4.setLayout(new BorderLayout());
		 
		panel7.add(label);
		
		panel4.add(panel7,BorderLayout.NORTH);
	
		
		panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel5.add(ascourse);
		panel5.add(human);
		panel5.add(humancore);
		panel5.add(society);
		panel5.add(societycore);
		panel5.add(science);
		panel5.add(sciencecore);
		panel5.add(computer);
		panel5.add(book);
		panel5.add(multi);
		panel5.add(PE);
		panel5.add(Chinese);
		
	    
		panel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel6.add(aspop);
		panel6.add(hot);
		panel6.add(normal);
		panel6.add(cold);
		
		panel8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel8.add(asday);
		panel8.add(monday);
		panel8.add(tuesday);
		panel8.add(wednesday);
		panel8.add(thursday);
		panel8.add(friday);
		
		panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.add(teacherprefer);
		panel1.add(teacherField);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.add(courseprefer);
		panel2.add(courseField);
		
		panel3 = new JPanel(new GridLayout(5,1));
		panel3.add(panel5);
		panel3.add(panel6);
		panel3.add(panel8);
		panel3.add(panel1);
		panel3.add(panel2);
		
		panel4.add(panel3,BorderLayout.CENTER);
		
		panel9 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel9.add(backButton);
		panel10= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel10.add(StartButton);
		panel11 = new JPanel(new GridLayout(1,2));
		panel11.add(panel9);
		panel11.add(panel10);
		
		panel4.add(panel11,BorderLayout.SOUTH);
		
		add(panel4);
		
	}
	
	public static String showResultSet(ResultSet result) throws SQLException {
		
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";
		
		while (result.next()) {
			for (int i = 1; i <= columnCount; i++) {
				output += String.format(" %s", result.getString(i));
			}
		}
		return output;
	}
}
