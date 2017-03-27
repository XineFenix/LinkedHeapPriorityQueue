
 /**
 * Name: Jordan Reardon-Smith
 * Student Number: 115 7135
 * Course ID: 2947-50
 * Course Name: Data Structures and Algorithms
 * 
 * Description: Driver program which tests our LinkedHeapPriorityQueue by mimicing an emergency waiting room patient tracker.
 * Add patients and answer questions which determines priority.  Check the list and call the next patient.
 * Breadththrough read through is used to print list to mimic what the array would like.   
 */


package Assignment3;

import java.util.Scanner;

public class _Driver2 {

	
	public static LinkedHeapPriorityQueue<Integer,String> heap = new LinkedHeapPriorityQueue<Integer, String>();
	public static Scanner kb = new Scanner(System.in);
	
	public static void main(String args[]){
		
		innitializePatients(); //method that adde to patients to list, for testing from a non-empty list, comment it to not-use.
		
		while(true){mainMenu();}
	}
	
	/**
	 * Our main interactive menu which provides options for our user to use
	 */
	public static void mainMenu(){

		//user choices
		System.out.print("Emergency Room - Priority Selection\n"
				 + "**********************************\n"
				 + "Please Select an option:\n"
				 + "[1] - Add Patient\n"
				 + "[2] - Check who's next\n"
				 + "[3] - Call next patient\n"
				 + "[4] - Print how man people are wait\n"
				 + "[5] - Show Wait List (Breadth-Wise Order)\n" 
				 + "[6] - QUIT\n" 
				 + "Please make a choice: ");

		int choice = validInt();
		if(choice>6){
			System.out.println("INVALID CHOICE\n\n\n\n\n");
			
		
		}
		selector(choice);//calls method which determines next choice
		
	}
	
	
	/**
	 * This method is called only if an option between 1-6 is selected, then calls the appropriate method.
	 * @param c inputed selection from user
	 */
	public static void selector(int c){
		switch(c){
		case 1: addPatient();break;
		case 2: check();break;
		case 3: nextPatient();break;
		case 4: printSize();break;
		case 5: printTree();break;
		case 6: System.exit(0);	
		}
	}
	
	/**
	 * Adds to default patients to our list
	 */
	public static void innitializePatients(){
		
		int p1Key = 149, p2Key = 0;
		String p1Value = "John Doe(Junkie) - ID:unkown",p2Value = "David Conrad(GunShot Victim) ID-546-123-554";
		
		heap.insert(p1Key, p1Value);
		heap.insert(p2Key, p2Value);		
	}
	
	/**
	 * This method asks basic questions to create a value and key to store in to our LinkedHeap.
	 */
	public static void addPatient(){
		String value;
		int key = 0;
		String v;
		
		//Value Questions:
		kb.nextLine();
		System.out.println("Patient Full Name: ");
		value = kb.nextLine();
			
		System.out.print("\nInjury or Illness: ");
		v = kb.nextLine();
		value += "(" + v + ") - ";
		
		System.out.print("\nHealth Number: ");
		value += "ID:" + kb.nextLine();
	
		
		//key Questions
		System.out.print("\nHow many Hours was patient waiting: ");
		int k = validInt(); 
		if(k<12){key = 12 - k;}
		
		System.out.print("\nHow man hours longer can the patient safely wait?: ");
		k = validInt(); 
		key += k;
				
		System.out.print("\nHow sever is injuries (0-Critical, 2 -Urgent, 3 -Regular...): ");
		k = validInt(); 
		key += k*10;
		
		System.out.print("\nHave you detected a heart anonymouly from inntial scan? [Y/N]: ");
		String heart = kb.next();
		if(heart.toUpperCase().equals("Y") || heart.toUpperCase().equals("YES")){ key = 0;}
		
		heap.insert(key, value);
		System.out.print("PATIENT ADDED***\n\n\n\n");

		
	}
	
	/**
	 * Displays who is next in our list, returns message if list is empty.
	 */
	public static void check(){
		if(heap.isEmpty()){System.out.println("There are currently no patients***\n\n\n\n");}
		else{System.out.println("The next Patient in will be: " + heap.min() + "***\n\n\n\n");}
		
	}	

	
	/**
	 * Calls our next patient and then removes them from the list. 
	 */
	public static void nextPatient(){
		if(heap.isEmpty()){System.out.println("There are currently no patients***\n\n\n\n");}
		else{System.out.println(heap.removeMin() + " - THE DOCTOR WILL NOW SEE YOU***\nPlease follow the nurse to your room\n\n\n\n");}
		
	}	
	
	/***
	 *Prints total amount of patients waiting 
	 */
	public static void printSize(){
		if(heap.isEmpty()){System.out.println("There are currently no patients***\n\n\n\n");}
		else{System.out.println("There are currently " + heap.size() + " patients waiting.\n\n\n\n");}

	}	

	
	/**
	 * Prints out the entire tree in breadththrough order.
	 */
	public static void printTree(){
		if(heap.isEmpty()){System.out.println("There are currently no patients***\n\n\n\n");}
		else{System.out.print(heap +"\n\n\n\n") ;}

	}	
	
	/**
	 * 
	 * @return value as an accepted int
	 */
	public static int validInt(){

		//this method ensures (whenever called) that when we ask for inputed ints,
		//loops until a valid int is detected
		while(!kb.hasNextInt()){
			System.out.print("\nPlease enter a numerical selection:");
			kb.next();
		}//end while
		
		//returns the inputed value to be used as out data
		int value = kb.nextInt();	
		return value;
	
	}// end method
	


	
	
}
