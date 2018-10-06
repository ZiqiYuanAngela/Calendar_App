/**
 * SJSU Spring 2018 CS 151
 * Programming Assignment #2 CalendarApp
 * @author Ziqi Yuan
 * @version 1.0
 * @since 03/19/2018
 */


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

//Tester
public class MyCalendarTester {
	     private static MyCalendar mC;
	     private static File file;
	     private static ArrayList<Event> myEvents;
	    	 private static ArrayList<EventDate> myEventDates;
	    	 private static Scanner sc;
	    	 private static GregorianCalendar gC;
	    	 private static final String[] monthName= {"January","February","March","April","May", "June", "July","August", "September",
	 				"October","November","December"};
	    	 private static final String[] days= {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	         private static int change_Month;
	    	
	         public static void main(String[] args) throws IOException {
	    		 
	    sc=new Scanner(System.in);
		gC= new GregorianCalendar();
		int change_Month=0;
		int change_Date=0;
		mC=new MyCalendar();
		myEvents=mC.getEvent();
		myEventDates=mC.getEventDate();
		file=new File("/Users/angelayuan/Desktop/eclipse-workspace/CalendarApp/src/events.txt.rtf");
		//file.delete();
		//file.createNewFile();
		//Display main menu
		 printCalendar(gC);
		 System.out.println("Select one of the following options:");	
		 System.out.println("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
	
		 
		boolean quit=false;
		//Interactive mode begins
		while(!quit) {
	    System.out.println("Please select an option by Entering the Letter in Bracket.");
		String option=sc.nextLine();
	    if(option.equals("L")){   //"Load" Option
		   //File file=new File("/Users/angelayuan/Desktop/eclipse-workspace/CalendarApp/src/events.rtf");
	    	   //file=new File("events.txt");
		   BufferedReader bR=new BufferedReader(new FileReader(file));
		   Scanner sc=new Scanner(file);
		   if(bR.readLine()==null) {//Check if the file is empty
			   System.out.println("This is your first run, no events found.");
			  
		   }
		   else {
			   System.out.println("Events found!");
			   while(sc.hasNextLine()) {
			   String str=sc.nextLine();
			   String title=str.substring(36);
			   int date=Integer.parseInt(str.substring(6,10))*10000+Integer.parseInt(str.substring(0,2))*100+Integer.parseInt(str.substring(3,5));
			   int startT=Integer.parseInt(str.substring(23,25))*100+Integer.parseInt(str.substring(26,28));
			   int endT=Integer.parseInt(str.substring(29,31))*100+Integer.parseInt(str.substring(32,34));
			   myEvents.add(new Event(title,date,startT,endT));
			   myEventDates.add(new EventDate(Integer.parseInt(str.substring(0,2)), Integer.parseInt(str.substring(3,5)), Integer.parseInt(str.substring(6,10))));
				 /*for(int a=0;a<myEventDates.size();a++) {
			    	  System.out.println(myEventDates.get(a).getYear());
			    }*/
			   
		   }
			   System.out.println(myEventDates.size());
		   }
		   
		   //Display main menu
		   gC=new GregorianCalendar();
		   printCalendar(gC);
		   System.out.println("Select one of the following options:");	
		   System.out.println("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
	     }
	    
	    
	     if(option.equals("V")){        //"View" Option
	    	   boolean done=false;
	    	   change_Date=0;
	    	   System.out.println("[D]ay view or [M]onth view? Enter letter in the bracket.");
	    	   option=sc.nextLine();
	    	   if(option.equals("D")) {
	    		   while(!done) {
	    		   gC=new GregorianCalendar();
	    		   gC.add(Calendar.DATE, change_Date);
	    		   String time=gC.getTime().toString();
	    		   //System.out.println(time);
	    		   String target_day=null;
	    		   for(int i=0;i<days.length;i++){
	    		        if(days[i].substring(0,3).equals(time.substring(0,3))){
	    		           target_day=days[i];
	    		      } 
	    		   
	    	   }
	    		   
	    		   System.out.println(target_day+", "+time.substring(4,10)+", "+time.substring(time.length()-4,time.length()));
	    		   mC.sortEvents();
	    		   for(int i=0;i<myEvents.size();i++) {
	    			  String timeT=Integer.toString(myEvents.get(i).getDate());
	    			  String year=timeT.substring(0, 4);
	    			  String date=timeT.substring(6, 8);
	    			  String month=timeT.substring(4,6);
	    			  int monthI=Integer.parseInt(month);
	    			  month=monthName[monthI-1];
	    			  if(year.equals(time.substring(time.length()-4,time.length()))
	    					&&  month.substring(0,3).equals(time.substring(4,7)) && date.equals(time.substring(8,10)) ) {
	    				  String start_T=null;
	    				  String end_T=null;
	    				  if(myEvents.get(i).getStartT()>=1000) {
	    				  start_T=Integer.toString(myEvents.get(i).getStartT());}
	    				  else {
	    					  start_T="0"+Integer.toString(myEvents.get(i).getStartT());
	    				  }
	    				  if(myEvents.get(i).getEndT()>=1000){
	    				  end_T=Integer.toString(myEvents.get(i).getEndT());}
	    				  else {
	    					  end_T="0"+Integer.toString(myEvents.get(i).getEndT());
	    				  }
	    				  System.out.println(myEvents.get(i).getTitle()+" "+start_T.substring(0,2)+":"+start_T.substring(2)
	    				  +"- "+end_T.substring(0,2)+":"+end_T.substring(2));
	    			  }
	    		   }
	    		   System.out.println("If you see a number 24:60 for an event, it means there is no ending time for that event.");
	    		   System.out.println("[P]revious or [N]ext or [M]ain menu ?");
	    		   option=sc.nextLine();
	    		   if(option.equals("P")) {
	    			   change_Date--;
	    		   }
	    		   if(option.equals("N")) {
	    			   change_Date++;
	    		   }
	    		   if(option.equals("M")) {
	    			   done=true;
	    			   gC=new GregorianCalendar();
	    			   //gC.add(Calendar.MONTH, 0);
	    			   printCalendar(gC);
	    			   System.out.println("Select one of the following options:");	
	    			   System.out.println("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
	    		   }
	    			   
	    	   }//while loop ends
	    	   
	    	   
	    	   }//Day view section finishes
	    	   
	    	   else { //Month view
	    		 change_Month=0;
	    	    	done=false;
	    	    	while(!done) {
	    	    		gC=new GregorianCalendar();
	    	    		gC.add(Calendar.MONTH,change_Month);
	    	    		//gC.add(Calendar.DATE, change_Date);
	    	    	    printCalendar(gC);
	    	    	    System.out.println("[P]revious or [N]ext or [M]ain menu ?");
	    	    	    option=sc.nextLine();
	    	    	    if(option.equals("P")) {
	    	    	    	   change_Month--;
	    	    	    }
	    	    	    if(option.equals("N")) {
	    	    	    	   change_Month++;
	    	    	    }
	    	        if(option.equals("M")) {
	    	        	   done=true;
	    	        	   gC=new GregorianCalendar();
	    	        	   //gC.add(Calendar.MONTH, 0);
	    	        	   //gC.add(Calendar.DATE, 0);
	    	        	   printCalendar(gC);
	    	        	   System.out.println("Select one of the following options:");	
	    	       	   System.out.println("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
	    	        }
	    	    	}//while loop ends here
	    	    }//Month view section ends here
	    	
	        }//Option V section finishes
	     
	         
	        if(option.equals("C")) {//Create events
	        	  
	        	  System.out.println("Please enter the title of the event you try to create");
	        	  String title=sc.nextLine();
	        	  System.out.println("Please enter the date of the event you try to create with format MM/DD/YYYY");
	        	  String date=sc.nextLine();
	        	  int date_event=Integer.parseInt(date.substring(6,10))*10000+Integer.parseInt(date.substring(0,2))*100+Integer.parseInt(date.substring(3,5));
	        	  System.out.println("Please enter the start time of the event you try to create with 24-hour format hh:mm, "
	        	  		+ "for example,09:30 stands for 09:30a.m. and 21:30 stands for 9:30p.m. ");
	        	  String start_time=sc.nextLine();
	        	  int start_time_event=Integer.parseInt(start_time.substring(0, 2))*100+Integer.parseInt(start_time.substring(3,5));
	        	  System.out.println("Please enter the end time of the event you try to create with the same format as start_time. Or you can leave it"
	        	  		+ "blank if there isn't one. Please remeber to press Enter twice when you want to leave it blank");
	        	  String end_time=sc.nextLine();
	        	  int end_time_event;
	        	  if(!end_time.equals("")) {
	        	   end_time_event=Integer.parseInt(end_time.substring(0, 2))*100+Integer.parseInt(end_time.substring(3,5));
	        	  }
	        	  else {
	        		  end_time_event=2460;
	        	  }
	        	  boolean add=true;
	        	  boolean finish=false;
	        	  int i=0;
	        	  while(i<myEvents.size() && !finish) {
	        		  if(date_event==myEvents.get(i).getDate()) {
	        			  if(!(end_time_event<=myEvents.get(i).getStartT()) && !(start_time_event>=myEvents.get(i).getEndT())) {
	        				  System.out.println("The new event created has time conflict with other events on the same day, please enter 0 if you want to "
	        				  		+ "give up scheduling this event.Otherwise,please press ENTER again.");
	        				  option=sc.nextLine();
	        				  finish=true;
	        			  }
	        		  }
	        		  i++;
	        	  }
	        	  
	        	  if(option.equals("0")) {
	        		  add=false;
	        	  }
	        	  if(add) {
	        	  myEvents.add(new Event(title,date_event,start_time_event,end_time_event));
	        	  myEventDates.add(new EventDate(Integer.parseInt(date.substring(0,2)),Integer.parseInt(date.substring(3,5)),Integer.parseInt(date.substring(6,10))));
	        	  }
	        	  //Display main menu
	        	  gC=new GregorianCalendar();
	        	  printCalendar(gC);
	        	  System.out.println("Select one of the following options:");	
   	       	  System.out.println("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
	        	  
	        }//Create section ends here
	      
	        
	      if(option.equals("G")) {//Go to a specific date
	    	     System.out.println("Please enter a date with format MM/DD/YYYY to view that day and events listed on that day.");
	    	     String date_requested=sc.nextLine();
	    	     int month=Integer.parseInt(date_requested.substring(0,2));
	    	     int date=Integer.parseInt(date_requested.substring(3,5));
	    	     int year=Integer.parseInt(date_requested.substring(6));
	    	     int today_month=gC.get(Calendar.MONTH)+1;
	    	     int today_date=gC.get(Calendar.DAY_OF_MONTH);
	    	     int today_year=gC.get(Calendar.YEAR);
	    	     gC.add(Calendar.DATE, date-today_date);
	    	     gC.add(Calendar.YEAR, year-today_year);
	    	     gC.add(Calendar.MONTH, month-today_month);
	    	     String time=gC.getTime().toString();
	    	     String target_day=null;
	    		   for(int i=0;i<days.length;i++){
	    		        if(days[i].substring(0,3).equals(time.substring(0,3))){
	    		           target_day=days[i];
	    		      } 
	    		   
	    	   }
	    		   
	    		   System.out.println(target_day+", "+time.substring(4,10)+", "+time.substring(time.length()-4,time.length()));
	    		   
	    		   
	    		   mC.sortEvents();
	    		   for(int i=0;i<myEvents.size();i++) {
	    			  String timeT=Integer.toString(myEvents.get(i).getDate());
	    			  String year1=timeT.substring(0, 4);
	    			  String date1=timeT.substring(6, 8);
	    			  String month1=timeT.substring(4,6);
	    			  int monthI=Integer.parseInt(month1);
	    			  month1=monthName[monthI-1];
	    			  if(year1.equals(time.substring(time.length()-4,time.length()))
	    					&&  month1.substring(0,3).equals(time.substring(4,7)) && date1.equals(time.substring(8,10)) ) {
	    				  String start_T=null;
	    				  String end_T=null;
	    				  if(myEvents.get(i).getStartT()>=1000) {
	    				  start_T=Integer.toString(myEvents.get(i).getStartT());}
	    				  else {
	    					  start_T="0"+Integer.toString(myEvents.get(i).getStartT());
	    				  }
	    				  if(myEvents.get(i).getEndT()>=1000){
	    				  end_T=Integer.toString(myEvents.get(i).getEndT());}
	    				  else {
	    					  end_T="0"+Integer.toString(myEvents.get(i).getEndT());
	    				  }
	    				  System.out.println(myEvents.get(i).getTitle()+" "+start_T.substring(0,2)+":"+start_T.substring(2)
	    				  +"- "+end_T.substring(0,2)+":"+end_T.substring(2));
	    			  }
	    		   }
	    		   System.out.println("If you see a number 24:60 for an event, it means there is no ending time for that event.");
	    		   
	    		   //Display main menu
	    		   System.out.println();
	    		   gC=new GregorianCalendar();
    			   //gC.add(Calendar.MONTH, 0);
    			   printCalendar(gC);
    			   System.out.println("Select one of the following options:");	
    			   System.out.println("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
	    		   
	      }//[G]o to session ends here
	     
	    
	      if(option.equals("E")) {// Show Event List
	    	    mC.sortEventsByDate();
	    	   /*for(int a=0;a<myEvents.size();a++) {
	    	    	System.out.println(myEventDates.get(a).getDate());
	    	    }*/
	    	   for(int i=0;i<myEvents.size();i++) {
	    	    	   String date=Integer.toString(myEvents.get(i).getDate());
	    	    	   String monthS=date.substring(4,6);
	    	    	   int monthI=Integer.parseInt(monthS);
	    	    	   String dateS=date.substring(6);
	    	    	   int dateI=Integer.parseInt(dateS);
	    	    	   String yearS=date.substring(0,4);
	    	    	   int yearI=Integer.parseInt(yearS);
	    	    	   int today_month=gC.get(Calendar.MONTH)+1;
	  	    	   int today_date=gC.get(Calendar.DAY_OF_MONTH);
	  	    	   int today_year=gC.get(Calendar.YEAR);
	  	    	   gC.add(Calendar.DATE, dateI-today_date);
	  	    	   gC.add(Calendar.YEAR, yearI-today_year);
	  	    	   gC.add(Calendar.MONTH, monthI-today_month);
	  	    	   String time=gC.getTime().toString();
	  	    	   String target_day=null;
	  	    	   for(int j=0;j<days.length;j++){
	  	    		        if(days[j].substring(0,3).equals(time.substring(0,3))){
	  	    		           target_day=days[j];
	  	    		      } 
	    	    	   
	  	    	   }
	  	    	   String start_T=null;
	  	    	   String end_T=null;
	  	    	   if(myEvents.get(i).getStartT()>=1000) {
	  	    	   start_T=Integer.toString(myEvents.get(i).getStartT());}
	  	    	   else {start_T="0"+Integer.toString(myEvents.get(i).getStartT());}
	  	    	   if(myEvents.get(i).getEndT()>=1000) {
	  	    	   end_T=Integer.toString(myEvents.get(i).getEndT());}
	  	    	   else {end_T="0"+Integer.toString(myEvents.get(i).getEndT());}
	  	    	   System.out.println(monthS+"/"+dateS+"/"+yearS+"  "+target_day+"  "+start_T.substring(0,2)+":"+start_T.substring(2)+"-"
	  	    			  + end_T.substring(0,2)+":"+end_T.substring(2)+"  "+myEvents.get(i).getTitle() );
	  	    	   
	  	    	   
	  	    	   
	  	    	   
	    	    	   System.out.println();
	    	    }
	    	    
	    	       //Display main menu
	    	       System.out.println();
	    		   gC=new GregorianCalendar();
 			   //gC.add(Calendar.MONTH, 0);
 			   printCalendar(gC);
 			   System.out.println("Select one of the following options:");	
 			   System.out.println("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
	     }//[E]vent list session ends here
	     
	     if(option.equals("D")) {// Delete events
	    	  System.out.println("Please select a specific date in MM/DD/YYYY to delete events.");
	    	  String date=sc.nextLine();
	    	  System.out.println("Please select which events you want to delete, enter S for selected events and A for all events.");
	    	  String selection=sc.nextLine();
	    	  int dateI=Integer.parseInt(date.substring(6))*10000+Integer.parseInt(date.substring(0,2))*100+Integer.parseInt(date.substring(3,5));
	    	  if(selection.equals("A")) {
	    		  for(int i=0;i<myEvents.size();i++) {
	    			  if(myEvents.get(i).getDate()==dateI) {
	    			     myEvents.remove(i);
	    			     i--;
	    			  }
	    		  }
	    		  for(int j=0;j<myEventDates.size();j++) {
	    			  if(myEventDates.get(j).getDate()==Integer.parseInt(date.substring(3,5)) && myEventDates.get(j).getMonth()==Integer.parseInt(date.substring(0,2)) &&
	    					  myEventDates.get(j).getYear()==Integer.parseInt(date.substring(6))) {
	    				  myEventDates.remove(j);
	    				  j--;
	    			  }
	    		  }
	    		 }
	    	  if(selection.equals("S")) {
	    		  System.out.println("Please enter the title of the event that you want to delete");
	    		  String title=sc.nextLine();
	    		  for(int i=0;i<myEvents.size();i++) {
	    			  if(myEvents.get(i).getDate()==dateI && myEvents.get(i).getTitle().equals(title)) {
	    				  myEvents.remove(i);
	    				  i--;
	    			  }
	    		  }
	    		  int j=0;
	    		  boolean found=false;
	    		  while(!found && j<myEventDates.size()) {
	    			  if(myEventDates.get(j).getDate()==Integer.parseInt(date.substring(0,2)) && myEventDates.get(j).getMonth()==Integer.parseInt(date.substring(0,2)) &&
	    					  myEventDates.get(j).getYear()==Integer.parseInt(date.substring(6))) {
	    				  myEventDates.remove(j);
	    				  found=true;
	    			  }
	    			  else{j++;}
	    		  }
	    	  }
	    	 
	    	 //Display main menu again 
	    	 System.out.println();
  		   gC=new GregorianCalendar();
			   //gC.add(Calendar.MONTH, 0);
			   printCalendar(gC);
			   System.out.println("Select one of the following options:");	
			   System.out.println("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
	     }//Delete Session ends here
	     
	     
	     if(option.equals("Q")) {
	    	     System.out.println("Quit successfully.");
	    	     
	    	     mC.sortEventsByDate();
	    	     PrintStream fileStream=new PrintStream("/Users/angelayuan/Desktop/eclipse-workspace/CalendarApp/src/events.txt.rtf");
	    	     System.setOut(fileStream);
		    	   /* for(int a=0;a<myEvents.size();a++) {
		    	    	System.out.println(myEvents.get(a).getStartT());
		    	    }*/
		    	   for(int i=0;i<myEvents.size();i++) {
		    	    	   String date=Integer.toString(myEvents.get(i).getDate());
		    	    	   String monthS=date.substring(4,6);
		    	    	   int monthI=Integer.parseInt(monthS);
		    	    	   String dateS=date.substring(6);
		    	    	   int dateI=Integer.parseInt(dateS);
		    	    	   String yearS=date.substring(0,4);
		    	    	   int yearI=Integer.parseInt(yearS);
		    	    	   int today_month=gC.get(Calendar.MONTH)+1;
		  	    	   int today_date=gC.get(Calendar.DAY_OF_MONTH);
		  	    	   int today_year=gC.get(Calendar.YEAR);
		  	    	   gC.add(Calendar.DATE, dateI-today_date);
		  	    	   gC.add(Calendar.YEAR, yearI-today_year);
		  	    	   gC.add(Calendar.MONTH, monthI-today_month);
		  	    	   String time=gC.getTime().toString();
		  	    	   String target_day=null;
		  	    	   for(int j=0;j<days.length;j++){
		  	    		        if(days[j].substring(0,3).equals(time.substring(0,3))){
		  	    		           target_day=days[j];
		  	    		      } 
		    	    	   
		  	    	   }
		  	    	   String start_T=null;
		  	    	   String end_T=null;
		  	    	   if(myEvents.get(i).getStartT()>=1000) {
		  	    	   start_T=Integer.toString(myEvents.get(i).getStartT());}
		  	    	   else {start_T="0"+Integer.toString(myEvents.get(i).getStartT());}
		  	    	   if(myEvents.get(i).getEndT()>=1000) {
		  	    	   end_T=Integer.toString(myEvents.get(i).getEndT());}
		  	    	   else {end_T="0"+Integer.toString(myEvents.get(i).getEndT());}
		  	    	   if(target_day.length()==6) {
		  	    	   System.out.println(monthS+"/"+dateS+"/"+yearS+"  "+target_day+"     "+start_T.substring(0,2)+":"+start_T.substring(2)+"-"
		  	    			  + end_T.substring(0,2)+":"+end_T.substring(2)+"  "+myEvents.get(i).getTitle() );}
		  	    	   else if(target_day.length()==7) {
		  	    		 System.out.println(monthS+"/"+dateS+"/"+yearS+"  "+target_day+"    "+start_T.substring(0,2)+":"+start_T.substring(2)+"-"
			  	    			  + end_T.substring(0,2)+":"+end_T.substring(2)+"  "+myEvents.get(i).getTitle() );
		  	    	   }
		  	    	   else if(target_day.length()==8) {
		  	    		 System.out.println(monthS+"/"+dateS+"/"+yearS+"  "+target_day+"   "+start_T.substring(0,2)+":"+start_T.substring(2)+"-"
			  	    			  + end_T.substring(0,2)+":"+end_T.substring(2)+"  "+myEvents.get(i).getTitle() );
		  	    	   }
		  	    	   else {
		  	    		 System.out.println(monthS+"/"+dateS+"/"+yearS+"  "+target_day+"  "+start_T.substring(0,2)+":"+start_T.substring(2)+"-"
			  	    			  + end_T.substring(0,2)+":"+end_T.substring(2)+"  "+myEvents.get(i).getTitle() );
		  	    	   }
	    	     quit=true;
		    	   }}
	}//"Quit loop ends here  
	
	}
	
	
	public static void printCalendar(Calendar c)
	{ 
		
		int today_Date=c.get(Calendar.DAY_OF_MONTH);
		int today_Year=c.get(Calendar.YEAR);
		//String[] monthName= {"January","February","March","April","May", "June", "July","August", "September",
				//"October","November","December"};
		String today_Month=monthName[c.get(Calendar.MONTH)];
		String[] daysOfWeek= {"Su", "Mo", "Tu", "We","Th","Fr","Sa"};
		
		//System.out.println(today_Year+" "+today_Month+" "+today_Date);
		//System.out.println(monthName[Calendar.MONTH+2]);
		
		c.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));
		/*Calendar cl=Calendar.getInstance();
		cl.setTime(new Date());
		cl.set(Calendar.DAY_OF_MONTH,cl.getActualMinimum(Calendar.DAY_OF_MONTH));*/
		String first_day_of_Month=c.getTime().toString().substring(0, 2);
		int start_Index=0;
		for(int i=0;i<daysOfWeek.length;i++) {
			if(first_day_of_Month.equals(daysOfWeek[i])) {
				start_Index=i;
			}
		}
		//System.out.print(c.getTime().toString().substring(0, 2));
		int daysInMonth=0;
		if(today_Month=="February") {
			if(today_Year%4==0) {
				daysInMonth=29;
			}
			else{daysInMonth=28;}
		}
		else if(today_Month=="January" || today_Month=="March" || today_Month=="May" || today_Month=="July" ||
				today_Month=="August" || today_Month=="October" || today_Month=="December") {
			daysInMonth=31;
		}
		else {
			daysInMonth=30;
		}
		
		//Print the title of the Calendar
		for(int i=1;i<=4;i++) {
			System.out.print(" ");
		}
		System.out.print(today_Month+" "+ today_Year);
		System.out.println();
		for(int i=0;i<daysOfWeek.length;i++) {
			System.out.print(" "+daysOfWeek[i]);
		}
		System.out.println();
		
		
		//Create a 2D integer[][] array storing all dates
		int[][] dates=new int[6][7];
		int count=1;
		
		//Fill the first row of calendar
		for(int j=start_Index;j<=6;j++) {
			dates[0][j]=count;
			count++;
		}
		//fill the rest of rows in the calendar
		for(int i=1;i<=5;i++) {
			for(int j=0;j<=6;j++) {
				if(count<=daysInMonth) {
				dates[i][j]=count;
				}
				count++;
			}
		}
		
		
		
		
		//Print the calendar with all dates
		
		for(int i=0;i<=5;i++) {
		   for(int j=0;j<=6;j++) {
			   if(dates[i][j]==0) {
				   System.out.print("   ");
			   }
			   if(0< dates[i][j] && dates[i][j]<10) {
				   if(DateWithEvent(dates[i][j], today_Month,today_Year)  && today_Date==dates[i][j] && today_Month.equals(monthName[Calendar.MONTH+2])) {
					   System.out.print(" {["+dates[i][j]+"]}");
				   }
				   else if(today_Date==dates[i][j] && !DateWithEvent(dates[i][j], today_Month,today_Year) && today_Month.equals(monthName[Calendar.MONTH+2]) ) {
					   System.out.print(" ["+dates[i][j]+"]");
				   }
				   else if(DateWithEvent(dates[i][j], today_Month, today_Year) && today_Date!=dates[i][j] ){
					   System.out.print(" {"+dates[i][j]+"}");
				   }
			   
			       else {System.out.print("  "+dates[i][j]);}
			   }
			   if(dates[i][j]>=10) {
				   if(DateWithEvent(dates[i][j],today_Month,today_Year) && today_Date==dates[i][j] && today_Month.equals(monthName[Calendar.MONTH]+2) ) {
					   System.out.print("{["+dates[i][j]+"]}");
				   }
				   else if(today_Date==dates[i][j] && !DateWithEvent(dates[i][j], today_Month,today_Year) && today_Month.equals(monthName[Calendar.MONTH+2]) ) {
					   System.out.print("["+dates[i][j]+"]");
				   }//Changed made here 04/27/2018 FROM today_Month.equals(monthName[Calendar.MONTH]) to ...See above
				   else if(DateWithEvent(dates[i][j],today_Month,today_Year) && today_Date!=dates[i][j]  ) {
					   System.out.print("{"+dates[i][j]+"}");
				   }
				   else {
					   System.out.print(" "+dates[i][j]);
				   }
			   }
			   
			   
		      }//second for-loop ending bracket
			   
		System.out.println();
	    }// Finish printing the calendar
		
	
		
		
		
		

	}//PrintCalendar() ending bracket
	
	/*public static boolean MonthWithEvent(String month) {
		int index=0;
		for(int i=0;i<monthName.length;i++) {
			if(monthName[i].equals(month)) {
				index=i;
			}
		}
		for(int i=0;i<myEventDates.size();i++) {
			if(index+1==myEventDates.get(i).getMonth()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean YearWithEvent(int year) {
		for(int i=0;i<myEventDates.size();i++) {
			if(myEventDates.get(i).getYear()==year) {
				return true;
			}
		}
		return false;
	}*/
	
	public static boolean DateWithEvent(int date,String month, int year) {
		int index=0;
		for(int i=0;i<monthName.length;i++) {
			if(monthName[i].equals(month)) {
				index=i;
			}
		}	
		for(int i=0;i<myEventDates.size();i++) {
			if(myEventDates.get(i).getYear()==year) {
				 // System.out.println("same year");
				if(index+1==myEventDates.get(i).getMonth()) {
					  //System.out.println("same month");
				   if(myEventDates.get(i).getDate()==date) {
				        //System.out.println("same date");
				     return true;
			  }
		  }
		}
	}
		return false;
}
		
		/* MONTHS[] arrayOfMonths = MONTHS.values();//Example
	    DAYS[] arrayOfDays = DAYS.values();
	    
	    System.out.print(arrayOfDays[c.get(Calendar.DAY_OF_WEEK)-1]);
	    System.out.print(" ");
		System.out.print(arrayOfMonths[c.get(Calendar.MONTH)]);
		System.out.print(" ");
		System.out.print(c.get(Calendar.DAY_OF_MONTH));
		System.out.print(" ");
		
		GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
	    System.out.println("The first day of this month is " + arrayOfDays[temp.get(Calendar.DAY_OF_WEEK)-1]);
			*/
	}


