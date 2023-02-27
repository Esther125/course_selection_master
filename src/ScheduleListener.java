import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class ScheduleListener implements ActionListener {

    private static final int SCHEDULE_HEIGHT = 600;
    private static final int SCHEDULE_WIDTH = 400;
    private static final int MOCK_HEIGHT = 600;
    private static final int MOCK_WIDTH = 900;
    private static final int INFO_HEIGHT = 100;
    private static final int INFO_WIDTH = 350;

    private static HashMap <Integer,String> day = new HashMap<>();

    @Override
    public void actionPerformed( ActionEvent e ){

        day.put(0,"星期一");
        day.put(1,"星期二");
        day.put(2,"星期三");
        day.put(3,"星期四");
        day.put(4,"星期五");

        //設定複製視窗樣式
        //design schedule frame

        JFrame schedule = new JFrame( "Schedule" );
        JLabel copyMsg = new JLabel( "<html><body><div style='color:#000000;font-size:16px;font-family:微軟正黑體;'>貼上在全校課程查詢系統複製的資料</div></body></html>" );
        JTextArea rawData = new JTextArea();
        JScrollPane scrollRawData = new JScrollPane( rawData );
        JButton mocking = new JButton( "<html><body><div style='color:#0538AD;font-size:20px;font-family:微軟正黑體;'>匯整資料</div></body></html>" );

        rawData.setLineWrap( true );

        schedule.add( copyMsg, BorderLayout.NORTH );
        schedule.add( scrollRawData, BorderLayout.CENTER );
        schedule.add( mocking, BorderLayout.SOUTH );
        schedule.setSize( SCHEDULE_WIDTH, SCHEDULE_HEIGHT );
        schedule.setVisible( true );


        //弄進來這麼多資料，有些是不要的
        //所以要先將不要的剔除

        class CleanListener implements ActionListener{

            @Override
            public void actionPerformed( ActionEvent e ){

                schedule.dispose();

                //將輸到TextArea裡面的資料，轉成ArrayList<Course>
                //convert the data from textArea to ArrayList

                String rawTxt = rawData.getText();
                rawTxt = rawTxt.replaceAll("\r|\n|\t", " ");
                rawTxt = rawTxt.replace("    "," ");
                String[] txt = rawTxt.split( " " );


                //將資訊用Course包起來
                int order=0;
                double credit=0;
                String number="", name="", teacher="", place="";
                ArrayList<Course> courses = new ArrayList<>();
                for( int i=0; i<txt.length; ++i ){
                    if( i%7==0 ){
                        order = Integer.parseInt(txt[i]);
                    }else if( i%7==1 ){
                        number = txt[i];
                    }else if( i%7==2 ){
                        name = txt[i];
                    }else if( i%7==3 ){
                        teacher = txt[i];
                    }else if( i%7==4 ){
                        credit = Double.parseDouble( txt[i] );
                    }else if( i%7==5 ){
                        place = txt[i];
                    }else{
                        courses.add( new Course( order, number, name, teacher, credit, place ) );
                    }
                }


                //按下這裡的課程按鈕，課程就會從列表被取消
                JFrame clean = new JFrame( "ORGANIZING" );
                clean.setVisible( true );
                clean.setSize( SCHEDULE_WIDTH+500,SCHEDULE_HEIGHT );

                JPanel rawjp = new JPanel();
                rawjp.setLayout( new GridLayout( courses.size()+1,1) );
        
                JScrollPane scrollCourseButton = new JScrollPane( rawjp );
                
                // JButton rawCourses;
                ArrayList<JCheckBox>rawCourses = new ArrayList<JCheckBox>();
                for( Course cs : courses ){
                    JCheckBox jcb = new JCheckBox( String.format( "%-20s序號：%d    科目代碼：%s    教師姓名：%s    學分數：%d    上課時間/上課教室：%s", cs.getName(), cs.getOrder(), cs.getNumber(), cs.getTeacher(), cs.getCredit(), cs.getRawPlace()));
                    // JCheckBox jcb = new JCheckBox( String.format("<html><body><div style='color:#0492B3;font-size:10px;font-family:微軟正黑體;'>%-20s序號：%d    科目代碼：%s    教師姓名：%s    學分數：%d    上課時間/上課教室：%s</div></body></html>", cs.getName(), cs.getOrder(), cs.getNumber(), cs.getTeacher(), cs.getCredit(), cs.getRawPlace() ) );
                    jcb.setHorizontalAlignment( SwingConstants.LEFT );
                    jcb.setBackground( new Color(252,227,204) );
                    rawCourses.add( jcb );
                    rawjp.add( jcb );
                }

                JButton rawCourse =  new JButton( "<html><body><div style='color:#0538AD;font-size:20px;font-family:微軟正黑體;'>匯整成模擬課表</div></body></html>" );
                rawjp.add( rawCourse );
                clean.add( scrollCourseButton );


                //已經將所有需要的課程都篩出來了，只要將它們變成一張課表即可

                class MockingListener implements ActionListener{
            
                    @Override
                    public void actionPerformed( ActionEvent e ){
                
                        clean.dispose();
                        //將剛剛沒有勾選的課刪掉
                        for( JCheckBox jcb: rawCourses ){
                            if( !jcb.isSelected() ){
                                String s = jcb.getText();
                                System.out.println( s );
                                for( int i=0; i<s.length(); ++i ){
                                    if( s.charAt(i) == ' ' ){
                                        s = s.substring( 0, i );
                                        break;
                                    }
                                }
                                System.out.println( s );
                                for( Course cs: courses ){
                                    if( cs.getName().equals(s) ){ 
                                        courses.remove( cs );
                                        break;
                                    }
                                }
                            }
                        }

                        //將整理好的課程資訊彙整成功課表
                        //from schedule to mockSchedule

                        JFrame mockSchedule = new JFrame( "MOCK SCHEDULE" );
                        mockSchedule.setSize( MOCK_WIDTH, MOCK_HEIGHT );
                        mockSchedule.setVisible( true );
                        mockSchedule.setLayout( new GridBagLayout() );


                        //按下course的按鈕之後，會出現課程內容
                        //info or content
                        class CourseInfoListener implements ActionListener{

                            private Course course;

                            public CourseInfoListener( Course course ){
                                this.course = course;
                            }

                            @Override
                            public void actionPerformed( ActionEvent e ){

                                JFrame info = new JFrame( course.getName() );
                                JLabel content = new JLabel(String.format("<html><body><div style='color:#000000;font-size:10px;font-family:微軟正黑體;'>課程名稱：%s<br>老師：%s<br>上課地點：%s</div></body></html>", course.getName(), course.getTeacher(), course.getPlace()));

                                info.add( content );
                                info.setVisible( true );
                                info.setSize( INFO_WIDTH, INFO_HEIGHT );

                            }
                        }

                        GridBagConstraints c = new GridBagConstraints();

                        //先講些需要先建立空按鈕的地方找出來
                        //沒有課的地方，先預設為-1，有客的地方先幫他上色為他在courses裡面的index
                        int [][] arr = new int [6][17];
                        for( int i=0; i<6; ++i ){
                            for( int j=0; j<17; ++j ){
                                arr[i][j] = -1;
                            }
                        }
                        for( int k=0; k<courses.size(); ++k ){
                            int i=courses.get(k).getDay();
                            for( int j=courses.get(k).getTime(); j<courses.get(k).getTime()+courses.get(k).getCredit(); ++j ){
                                arr[i][j] = k;
                            }
                        }

                        //將課表中，沒有課的地方，先放上+Button
                        //將課程設置成按鈕
                        for( int i=0; i<6; ++i ){
                            for( int j=0; j<17; ++j ){
                                Course cs;
                                JButton courseButton;
                                if( arr[i][j] < 0 ){
                                    // bk.setBorder( ln );
                                    c.gridx = i;
                                    c.gridy = j;
                                    c.fill = GridBagConstraints.BOTH;
                                    c.gridheight = 1;
                                    c.ipadx = 10;

                                    if( i==0&&j==0 ){
                                        courseButton = new JButton( "     " );
                                        courseButton.setBackground(new Color(255,250,148));
                                    }else if( i==0 ){
                                        courseButton = new JButton( String.format("%d~%d", j+5, j+6 ) );
                                        courseButton.setBackground(new Color(252,246,121));
                                    }else if( j==0 ){
                                        courseButton = new JButton( day.get(i-1) );
                                        courseButton.setBackground(new Color(252,246,121));
                                    }else{
                                        courseButton = new JButton( "+" );
                                        courseButton.setBackground(new Color(207,176,255));
                                    }
                                    mockSchedule.add( courseButton, c );
                                }else{
                                    cs = courses.get( arr[i][j] );
                                    c.gridx = cs.getDay();
                                    c.gridy = cs.getTime();
                                    if( cs.getCredit()==1 ){
                                        c.gridheight = 2;
                                    }else{
                                        c.gridheight = cs.getCredit();
                                    }
                                    c.gridwidth = 1;
                                    c.fill = GridBagConstraints.BOTH;
                                    courseButton = new JButton( String.format("<html><body><div style='color:#ffffff;font-size:10px;font-family:微軟正黑體;'>%s (%d)</div></body></html>",cs.getName(),cs.getCredit()) );
                                    courseButton.setBackground(new Color(91,25,179));
                                    courseButton.addActionListener( new CourseInfoListener( cs ) );
                                    mockSchedule.add( courseButton,c );
                                }
                            }
                        }
                    }
                }
                rawCourse.addActionListener( new MockingListener() );
            }
        }
        //在schedule中的按鈕
        mocking.addActionListener( new CleanListener() );
    }
}