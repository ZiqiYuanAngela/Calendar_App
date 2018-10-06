/**
 * SJSU Spring 2018 CS 151
 * Programming Assignment #2 CalendarApp
 * @author Ziqi Yuan
 * @version 1.0
 * @since 03/19/2018
 */



public class Event {
 
	private String title;
	private int date;
	private int start_time;
	private int end_time;
	
	
	public Event(String title, int date, int start_time, int end_time) {
		this.title=title;
		this.date=date;
		this.start_time=start_time;
		this.end_time=end_time;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getDate() {
		return this.date;
	}
	
	public int getStartT() {
		return this.start_time;
	}
	
	public int getEndT() {
		return this.end_time;
	}
	
}
