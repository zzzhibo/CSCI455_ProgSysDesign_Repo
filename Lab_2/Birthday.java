// lab 2 Birthday.java Zhibo Wang 

import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Birthday {

//    private static final DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy"); 

    public static void main(String[] args) {
       
      
       
  //     GregorianCalendar dt = new GregorianCalendar(1995,Calendar.JANUARY,20);
  //    System.out.println((dt.get(Calendar.MONTH)+1)+"/"+dt.get(Calendar.DATE)+"/"+dt.get(Calendar.YEAR)+"\n");
       
       
  //     dt.add((GregorianCalendar.DAY_OF_MONTH), 20);
  //   System.out.println((dt.get(Calendar.MONTH)+1)+"/"+dt.get(Calendar.DATE)+"/"+dt.get(Calendar.YEAR)+"\n");
       
       
       Scanner in = new Scanner(System.in);
       
       System.out.println("Enter your birth month [1..12]: ");
       int birthmonth = in.nextInt();
       
       System.out.println("Enter your birth day of month: ");
       int birthday = in.nextInt();
       
       System.out.println("Enter your birth year [4-digit year]: ");
       int birthyear = in.nextInt();
       
       
 //      System.out.println(birthmonth+birthday+birthyear);
       
       Calendar today = Calendar.getInstance();
      
       System.out.println(today.getTime());
       
       int todayday = today.get(Calendar.DATE);
       int todaymonth = today.get(Calendar.MONTH)+1;
       int todayyear = today.get(Calendar.YEAR);
       
       int ageticker = -1;
      
       
       if (birthmonth > todaymonth){
          System.out.println("Your birthday has not yet happened this year.");
          
       } else if (birthmonth < todaymonth){
          ageticker += 1;
          System.out.println("Your birthday has already happened this year.");
                 
       } else if (birthmonth == todaymonth){
         
          if (birthday > todayday){
             System.out.println("Your birthday has not yet happened this year.");
          }
          else {
             ageticker += 1;
             System.out.println("Your birthday has already happened this year.");
          }
          
       }
       
       
       int age;
       
       age = todayyear - birthyear + ageticker;
       
       System.out.printf("You're %d years old. \n", age);
       
         

    }

}