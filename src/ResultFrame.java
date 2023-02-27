import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Color;

public class ResultFrame extends JFrame{
	
	private JPanel panel1, panel2,panel3,panel4,panel5,panel6, panel7;
	private JTextArea textArea;
	private JButton backButton,finishButton;
	private JLabel label, col1, col2, col3, col4, col5;
	private JScrollPane jcp;
	private String[] coursesTxt;
	private String resultInfo;
	private ArrayList<JLabel> courses;
	
	public ResultFrame ( String resultInfo ) {
		
		this.resultInfo = resultInfo;
		coursesTxt = resultInfo.split( " " );
		courses = new ArrayList<>();
		createLabel();
		createTextArea();
	    createButton();
		createLayout();
		setTitle("Result Frame");
		setSize(900, 400);
		
	}
	private void createLayout() {
		
		panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.add(backButton);
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel2.add(finishButton);
		
		panel3 = new JPanel(new GridLayout(1,2));
		panel3.add(panel1);
		panel3.add(panel2);
		
		panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel5.add(label);
		
		panel6 = new JPanel();
		int i = 5;
		for( JLabel jb: courses ) {
			i++;
		}
		panel6.setLayout(new GridLayout(i/5,5));
		panel6.add( col1 );
		panel6.add( col2 );
		panel6.add( col3 );
		panel6.add( col4 );
		panel6.add( col5 );
		for( JLabel jb: courses ) {
			panel6.add( jb );
		}
		jcp = new JScrollPane( panel6 );
		
		panel4 = new JPanel(new BorderLayout());
		panel4.add(panel5,BorderLayout.NORTH);
		panel4.add(jcp,BorderLayout.CENTER);
		panel4.add(panel3,BorderLayout.SOUTH);
		
		add(panel4);
		
	}
	private void createButton() {
		finishButton = new JButton("����");
		finishButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
		});
		
		backButton = new JButton("��^");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		
	}
	private void createTextArea() {
	
		textArea = new JTextArea(100,100);
		textArea.setText( resultInfo );
		
	}
	private void createLabel() {
		label = new JLabel("<html><body><div style='color:#000000;font-size:20px;font-family:�L�n������;'>" + ""+" �z�ﵲ�G</div></body></ht");
		
		col1 = new JLabel( "�ҵ{�W��" );
		col2 = new JLabel( "�Ѯv�W�r" );
		col3 = new JLabel( "�ɶ�" );
		col4 = new JLabel( "��ҵ��G" );
		col5 = new JLabel( "�ҵ{���O" );
		
		for( int i=0; i<coursesTxt.length; ++i  ) {
			
			if( (i%7==2||i%7==3||i%7==4||i%7==5||i%7==0)&&i!=0 ) {
				
				JLabel jb = new JLabel( coursesTxt[i] );
				courses.add( jb );
//				System.out.printf( "%d:%s\n", i, coursesTxt[i] );
			}
		}
	}
	
}
