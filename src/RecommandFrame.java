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
			}//�쥻�Q���o�˳Q��쪺label��������r�٬O���� ���n���S����
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
		label = new JLabel("<html><body><div style='color:#000000;font-size:20px;font-family:�L�n������;'>" + ""+" ���˧��@��</div></body></html>");
		
		mouseListner = new Mouse( bhuman, "�H��q", "Category" );
		human = new JLabel("�H��q");
		human.setBorder(border);
		human.addMouseListener(mouseListner);
		
		mouseListner = new Mouse( bhumancore, "�H��ֳq", "Category" );
		humancore = new JLabel("�H��ֳq");
		humancore.setBorder(border);
		humancore.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bsociety, "���|�q", "Category" );
		society = new JLabel("���|�q");
		society.setBorder(border);
		society.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bsocietycore, "���|�ֳq", "Category" );
		societycore = new JLabel("���|�ֳq");
		societycore.setBorder(border);
		societycore.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bscience, "�۵M�q", "Category" );
		science = new JLabel("�۵M�q");
		science.setBorder(border);
		science.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bsciencecore, "�۵M�ֳq", "Category" );
		sciencecore = new JLabel("�۵M�ֳq");
		sciencecore.setBorder(border);
		sciencecore.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bcomputer, "��T�q", "Category" );
		computer = new JLabel("��T�q");
		computer.setBorder(border);
		computer.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bbook, "�Ѱ|�q", "Category" );
		book = new JLabel("�Ѱ|�q");
		book.setBorder(border);
		book.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bmulti, "�����O�q", "C  ategory" );
		multi = new JLabel("�����O�q");
		multi.setBorder(border);
		multi.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bPE, "��|", "Category" );
		PE = new JLabel("��|");
		PE.setBorder(border);
		PE.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bChinese, "���", "Category" );
		Chinese = new JLabel("���");
		Chinese.setBorder(border);
		Chinese.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bhot, "�Ƥ@���@�w�|�W", "Level" );
		hot = new JLabel("�Ƥ@���@�w�|�W");
		hot.setBorder(border);
		hot.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bnormal, "�ƫe���@�I�N�|�W", "Level" );
		normal = new JLabel("�ƫe���@�I�N�|�W");
		normal.setBorder(border);
		normal.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bcold, "�H�K�Ƴ��|�W", "Level" );
		cold = new JLabel("�H�K�Ƴ��|�W");
		cold.setBorder(border);
		cold.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bmonday, "�@", "Time" );
		monday = new JLabel("�P���@");
		monday.setBorder(border);
		monday.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( btuesday, "�G", "Time" );
		tuesday = new JLabel("�P���G");
		tuesday.setBorder(border);
		tuesday.addMouseListener(mouseListner);

		
		mouseListner = new Mouse( bwednesday, "�T", "Time" );
		wednesday = new JLabel("�P���T");
		wednesday.setBorder(border);
		wednesday.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bthursday, "�|", "Time" );
		thursday = new JLabel("�P���|");
		thursday.setBorder(border);
		thursday.addMouseListener(mouseListner);
		
		
		mouseListner = new Mouse( bfriday, "��", "Time" );
		friday = new JLabel("�P����");
		friday.setBorder(border);
		friday.addMouseListener(mouseListner);
		
		
		teacherprefer= new JLabel("�p�G�A���Q�n���Ѯv: ");
		courseprefer= new JLabel("�p�G�A�w�g�M�w�n�W����:  ");
		ascourse = new JLabel("���ҵ{����: ");
		ascourse.setHorizontalAlignment(SwingConstants.LEFT); 
		
		
		
		aspop = new JLabel("�������{��: ");
		asday = new JLabel("���P���X: ");

	}
	
	private void createTextField() {
		teacherField = new JTextField(10);
		courseField = new JTextField(10);
	}
	
	
	
	private void createButton(){
		
		StartButton = new JButton("�}�l���˧��@��");
		
		
		
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
		
		backButton = new JButton("��^");
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
