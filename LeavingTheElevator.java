/* 
 * LeavingTheElevator.java 
 * 
 * Version: 
 *     $Id$
 * 
 * Revisions: Initial Version
 *    
 */
/**
 * LeavingTheElevator class is a thread class used to invoke the method
 * which handles the people leaving from a elevator
 * in a synchronized way. .
 *
 *
 * @author Sanket Agarwal
 * 
 */
public class LeavingTheElevator extends Thread{

	private PeopleInElevator PeopleInElevator;
	private int numOfTrips;

	//private String direction;
	private int numOfFloors;

	public LeavingTheElevator(PeopleInElevator PeopleInElevator,  int numOfFloors, int  numOfTrips){
		//this.direction = direction;
		this.numOfTrips = numOfTrips;
		this.PeopleInElevator = PeopleInElevator;
		this.numOfFloors  = numOfFloors;

	}
	public void run(){

		int count =0;
		while(numOfTrips > 0){
			if(count==0){
				for(int i=0; i<numOfFloors; i++){
					//System.out.println("Floor #" + (i+1) +",People leaving");
					PeopleInElevator.leaveElevator(i+1);

				}
			}
			else{
				for(int i=1; i<numOfFloors; i++){
					//System.out.println("Floor #" + (i+1) +",People leaving");
					PeopleInElevator.leaveElevator(i+1);

				}
			}
			for(int i=numOfFloors-1; i>0; i--){
				//System.out.println("Floor #" + i +",People leaving");
				PeopleInElevator.leaveElevator(i);

			}
			numOfTrips--;
			count++;
		}



	}
}
