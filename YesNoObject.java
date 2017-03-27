/*```````````````````````````````````````````````````````````````````````````````````````````````````````
 * Assignment: 3 
 * Question:  1.b
 * Due Date: March 14th, 2017
 * Name: Jordan Reardon-Smith
 * Student Number: 115 7135
 * Course ID: 2947-50
 * Course Name: Data Structures and Algorithms
 * 
 * Description: Object to store our yes/no questions within
 */




package Assignment3;

public class YesNoObject {

	private boolean question; // False if this object is the final answer to a series of questions
	private String statement;// statement or questions for user
	private String title; //title bar
	
	
	/**
	 * Constructor element 
	 * @param title to store in new object
	 * @param question boolean  to store in new object
	 * @param statement to store in new object
	 */
	public YesNoObject(String title, boolean question, String statement){
		setQuestion(question);
		setStatement(statement);
		setTitle(title);
	}
	

	/**
	 * Setters
	 * @param t question boolean to store in object
	 * @param s statement to store in object
	 * @param t title to store in object
	 */
	public void setQuestion(boolean t){this.question =t;}
	public void setStatement(String s){this.statement =s;}
	public void setTitle(String t){this.title =t;}
	
	
	
	/**
	 * 
	 * @return object fields
	 */
	public String getStatement(){return this.statement;}
	public boolean isQuestion(){return this.question;}
	public String getTitle(){return this.title;}
	
	
	
	
}
