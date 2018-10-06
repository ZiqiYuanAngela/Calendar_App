/**
 * SJSU Spring 2018 CS 151
 * Programming Assignment #2 CalendarApp
 * @author Ziqi Yuan
 * @version 1.0
 * @since 03/19/2018
 */
public class EventDate {
   private int month;
   private int year;
   private int date;
   
   public EventDate(int month, int date, int year) {
	   this.month=month;
	   this.year=year;
	   this.date=date;
   }
   
   public int getMonth() {
	   return this.month;
	   }
   
   public int getYear() {
	   return this.year;
   }
   
   public int getDate() {
	   return this.date;
   }
}
