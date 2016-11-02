/* 
 * PeopleInElevator.java 
 * 
 * Version: 
 *     $Id$
 * 
 * Revisions: Initial Version
 *    
 */
/**
 * PeopleInElevator class is used to handle the entering and exiting 
 * of the people in a floor in a elevator.
 *
 * @author Sanket Agarwal
 * 
 */
import java.util.LinkedList;
import java.util.Random;

public class PeopleInElevator {

	private static int sumWeight=0;
	private static int maxNoOfPpl;

	PeopleInElevator(int sumWeight, int maxNoOfPpl ){
		PeopleInElevator.maxNoOfPpl = maxNoOfPpl;
		PeopleInElevator.sumWeight = sumWeight;

	}

	private static LinkedList<Integer> peoplePresent = new LinkedList<Integer>();

	private boolean elevatorAvailable = false;

	public synchronized void enterElevator(int floor) {

		while (elevatorAvailable == false) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		System.out.println("Floor Number : "+(floor)+ " :Entering");
		Random r = new Random();
		int noOfPpl = r.nextInt(maxNoOfPpl);
		System.out.println(noOfPpl+" People standing in queue to enter the elevator");
		int[] pplWeights = new int[noOfPpl];
		for(int i=0; i<noOfPpl; i++){
			int weight = r.nextInt(60) + 40;
			pplWeights[i] = weight;
		}
		for(int i=0; i<noOfPpl; i++){
			if(sumWeight >= 300){

				System.out.println("you have to wait, The elevator is full!");
				elevatorAvailable = false;
				notifyAll();
				break;  
			}
			else{
				if((pplWeights[i] + sumWeight ) > 300){

					System.out.println("you have to wait, The elevator is full!");
					System.out.println("The elevator is already holding "+ sumWeight+" kgs");
					System.out.println("Cannot add another person of weight "+ pplWeights[i]+" kgs");
					elevatorAvailable = false;
					notifyAll();
					break;
				}
				else{
					sumWeight += pplWeights[i];
					System.out.println("A person with weight "+pplWeights[i]+" kgs entered the lift");
					peoplePresent.add(pplWeights[i]);
				}
			}
		}
		System.out.println("Number of people present in the elevator: "+peoplePresent.size());
		System.out.println("The elevator is currently holding : "+sumWeight + " kgs");
		System.out.println("--------------------------------------------------------------------------");
		elevatorAvailable = false;
		notifyAll();
	}
	public synchronized void leaveElevator(int floor){


		while (elevatorAvailable == true) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		System.out.println("Floor Number : "+(floor)+ " :leaving");
		Random r = new Random();
		int size = peoplePresent.size();
		if(size == 0){
			System.out.println("The elevator is empty, You can enter!");
			elevatorAvailable = true;
			notifyAll();
		}
		else{
			int pplToLeave = r.nextInt(size);
			System.out.println(pplToLeave+ " people leaving the lift");
			for(int i=0; i<pplToLeave; i++){
				int indexToLeave = r.nextInt(peoplePresent.size()-1);
				int personToLeave = peoplePresent.get(indexToLeave);

				sumWeight = sumWeight-personToLeave;
				System.out.println("A person with weight "+personToLeave+" kgs left the lift");
				peoplePresent.remove(indexToLeave);
			}
		}
		elevatorAvailable = true;
		notifyAll();
	}

}
