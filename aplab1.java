/*import libraries for reader class, 
 * 		arraylists and library functions such as sorting
 */
import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.lang.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;
class Reader{
	static BufferedReader reader;
	static StringTokenizer tokenizer;
	static void init(InputStream input){
		reader=new BufferedReader(new InputStreamReader(input));
		tokenizer=new StringTokenizer("");
	}
	static String next()throws IOException{
		while(!tokenizer.hasMoreTokens()){
			tokenizer=new StringTokenizer(reader.readLine());
		}
		return tokenizer.nextToken();
	}
	static int nextInt()throws IOException{
		return Integer.parseInt(next());
	}
	static double nextDouble()throws IOException{
		return Double.parseDouble(next());
	}
	static long nextLong()throws IOException{
		return Long.parseLong(next());
	}
}
/*
 * create a class student with details for name, roll no,program,distance 
 * by default, student is initialised without a hostel
 */
class student{
	
	private String name;
	private String roll;
	private String program;
	private int distance;
	private boolean got_hostel;
	public student(String name,String roll,String program,int distance){
		this.name=name;
		this.roll=roll;
		this.program=program;
		this.distance=distance;
		this.got_hostel=false;
	}
  /* getter for student name
  */
	public String get_name(){
		return this.name;
	}
  /* getter for roll no
   */ 
	public String get_roll(){
		return this.roll;
	}
  /* getter for program
   */ 
	public String get_program(){
		return this.program;
	}
  /* getter for distance
   */ 
	public int get_distance(){
		return this.distance;
	}
  /* invoke this method when a student has been allotted hostel
   */ 
	public void give_hostel(){
		this.got_hostel=true;
	}
  /* getter for hostel value
   */ 
	public boolean check_hostel(){
		return this.got_hostel;
	}
  /* method for printing the instance of student
   */ 
	public String toString(){
		return this.name+" "+this.roll+" "+this.program+" "+this.distance;
	}
}
class aplab1{
	private int n,m,phd_st,ug_st,pg_st,phd_room,pg_room,ug_room;
	private student[] s1;
	private ArrayList<student> phd,pg,ug;
  /* sorts an arraylist based on distance of students 
   */ 
	private static void sort(ArrayList<student> arr){
		for (int i=0;i<arr.size();i++){
			for (int j=i+1;j<arr.size();j++){
				if (arr.get(i).get_distance()<arr.get(j).get_distance()){
					student temp=arr.get(i);
					arr.set(i,arr.get(j));
					arr.set(j,temp);
				}
			}
		}
	}
  /* Array s1 is array of students
   * ArrayList phd for PhD students, pg for PG students, ug for UG students
   * integers phd_st, pg_st, ug_st gives number of PhD students, PG students and UG students respectively 
   * integers phd_room, pg_room, ug_room gives number of rooms for PhD students, PG students and UG students respectively 
   */ 
	public static void main(String[] args)throws IOException{
		Reader.init(System.in);
		int n=Reader.nextInt();
		int m=Reader.nextInt();
		student[] s1=new student[n];
		ArrayList<student> phd=new ArrayList<student>();
		ArrayList<student> pg=new ArrayList<student>();
		ArrayList<student> ug=new ArrayList<student>();
		int phd_st,pg_st,ug_st;
		phd_st=0;
		pg_st=0;
		ug_st=0;
		for (int i=0;i<n;i++){
			s1[i]=new student(Reader.next(),Reader.next(),Reader.next(),Reader.nextInt());
			if (s1[i].get_program().equals("PhD")){
				phd_st++;
				phd.add(s1[i]);
			}
			else if ((s1[i].get_program().equals("PG"))){
				pg_st++;
				pg.add(s1[i]);
			}
			else if ((s1[i].get_program().equals("UG"))){
				ug_st++;
				ug.add(s1[i]);
			}
		}
		int phd_room,pg_room,ug_room;
		ug_room=0;
		if (m%2==0){
			phd_room=m/2;
			pg_room=m/2;
		}
		else{
			phd_room=m/2+1;
			pg_room=m/2;
		}
    /* if number of phd students is less than number of rooms
			 for phd students, then give rooms to all phd students
       if condition is not satisfied, then sort students
			 on distance and give hostel according to order
     */ 
		if (phd_st<=phd_room){
			
			for (int i=0;i<phd.size();i++){
				student temp=phd.get(i);
				temp.give_hostel();
			}
			ug_room+=phd_room-phd_st;
		}
		else{
			sort(phd);
			System.out.println(phd);
			for (int i=0;i<phd_room;i++){
				phd.get(i).give_hostel();
			}
		}
    /* if number of pg students is less than number of rooms
			 for pg students, then give rooms to all pg students
     */ 
		if ((pg_st<=pg_room)&&(pg.size()!=0)){
			
			for (int i=0;i<pg.size();i++){
				student temp=pg.get(i);
				temp.give_hostel();
			}
			ug_room+=pg_room-pg_st;
		}
    /* if condition is not satisfied, then sort students
			 on distance and give hostel according to order
     */ 
		else if (pg.size()!=0){
			
			sort(pg);
			for (int i=0;i<pg_room;i++){
				pg.get(i).give_hostel();
			}
		}
    /* if number of rooms for ug students!=0,
			 give them hostel according to distance
     */ 
		if ((ug_room!=0)&&(ug.size()!=0)){
			
			sort(ug);
			for (int i=0;i<ug_room;i++){
				ug.get(i).give_hostel();
			}
		}
		for (int i=0;i<n;i++){
			if (s1[i].check_hostel()==true)System.out.println(s1[i]);
		}
	}
}
