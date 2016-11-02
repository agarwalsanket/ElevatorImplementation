/* 
 * PeopleInFloor.java 
 * 
 * Version: 
 *     $Id$
 * 
 * Revisions: Initial Version
 *    
 */
/**
 * PeopleInFloor class is a thread class used to invoke the method
 * for generating and entering people in a elevator in a synchronized way.
 *
 * @author Sanket Agarwal
 *
 */
public class PeopleInFloor extends Thread {

	private PeopleInElevator PeopleInElevator;

	private int numOfFloors;
	private int numOfTrips;

	public PeopleInFloor(PeopleInElevator PeopleInElevator,  int numOfFloors, int numOfTrips){
		this.numOfTrips = numOfTrips;
		this.PeopleInElevator = PeopleInElevator;
		this.numOfFloors  = numOfFloors;

	}
	public void run(){

		int count=0;

		while(numOfTrips > 0){
			if(count == 0){
				for(int i=0; i<numOfFloors; i++){				
					PeopleInElevator.enterElevator(i+1);

				}
			}
			else{
				for(int i=1; i<numOfFloors; i++){				
					PeopleInElevator.enterElevator(i+1);

				}
			}

			for(int i=numOfFloors-1; i>0; i--){
				//System.out.println("Floor #" + i +",People entering");
				PeopleInElevator.enterElevator(i);

			}
			count++;
			numOfTrips--;
		}

		System.out.println("The elevator has shut down!No Service!Sorry!");

	}

}
