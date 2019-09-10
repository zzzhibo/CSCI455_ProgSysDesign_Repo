// lab 2 Date.java Zhibo Wang 

import java.util.Calendar;
import java.util.GregorianCalendar;
//import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Date {

    // private static final DateFormat dateFormat = new
    // SimpleDateFormat("M/d/yyyy");

    public static void main(String[] args) {

        GregorianCalendar dt = new GregorianCalendar(1995, Calendar.JANUARY, 20);
        System.out.println(
                (dt.get(Calendar.MONTH) + 1) + "/" + dt.get(Calendar.DATE) + "/" + dt.get(Calendar.YEAR) + "\n");

        dt.add((GregorianCalendar.DAY_OF_MONTH), 20);
        System.out.println(
                (dt.get(Calendar.MONTH) + 1) + "/" + dt.get(Calendar.DATE) + "/" + dt.get(Calendar.YEAR) + "\n");

    }

}