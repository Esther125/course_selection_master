import java.util.HashMap;

public class Course {
    
    private int order;
    private String name;
    private String teacher;
    private String number;
    private double credit;
    private String place;

    private HashMap< Character, Integer > day;
    private HashMap< Character, Integer > time;

    public Course( int order, String number, String name, String teacher, double credit, String place ){

        this.order = order;
        this.number = number;
        this.name = name;
        this.teacher = teacher;
        this.credit = credit;
        this.place = place;

        time = new HashMap<>();
        time.put( 'A', 1 );
        time.put( 'B', 2 );
        time.put( '1', 3 );
        time.put( '2', 4 );
        time.put( '3', 5 );
        time.put( '4', 6 );
        time.put( 'C', 7 );
        time.put( 'D', 8 );
        time.put( '5', 9 );
        time.put( '6', 10 );
        time.put( '7', 11 );
        time.put( '8', 12 );
        time.put( 'E', 13 );
        time.put( 'F', 14 );
        time.put( 'G', 15 );
        time.put( 'H', 16 );

        day = new HashMap<>();
        day.put( '一', 1 );
        day.put( '二', 2 );
        day.put( '三', 3 );
        day.put( '四', 4 );
        day.put( '五', 5 );

    }

    public int getOrder(){
        return order;
    }
    public String getNumber(){
        return number;
    }
    public String getName(){
        return name;
    }
    public String getTeacher(){
        return teacher;
    }

    public int getCredit(){
        return (int)Math.floor( credit );
    }
    public int getDay(){
        return day.get( place.charAt(0) );
    }
    public int getTime(){
        return time.get( place.charAt(1) );
    }
    public String getRawPlace(){
        return place;
    }
    public String getPlace(){
        int pos=0;
        for( int i=0; i<place.length(); ++i ){
            if( place.charAt(i)=='/' ){
                pos = i;
                break;
            }
        }
        String p = place.substring(pos+1);
        return p;
    }

}
