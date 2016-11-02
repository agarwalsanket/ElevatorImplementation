/* 
 * ElevatorTest.java 
 * 
 * Version: 
 *     $Id$
 * 
 * Revisions: Initial Version
 *    
 */
/**
 * ElevatorTest class is the main class.
 *
 * @author Sanket Agarwal
 * 
 */
import java.util.Scanner;

public class ElevatorTest {

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of floors: ");
		int noOfFloors = sc.nextInt();
		if(noOfFloors <= 0){
			System.out.println("Wrong input, Enter a right input for number of floors!");
			System.exit(1);
		}
		else if(noOfFloors == 1){
			System.out.println("Elevator is not needed!");
			System.exit(1);
		}
		else{
			System.out.println("Enter the maximum number of people in a floor: ");
			int noOfPpl = sc.nextInt();
			if(noOfPpl <= 0){
				System.out.println("There will be no one in the floors, elevator service not required!");
				System.exit(1);
			}
			System.out.println("Enter the number of times the elevator would move up & down: ");
			int noOfTrips = sc.nextInt();
			if(noOfTrips <= 0){
				System.out.println("Elevator out of service!");
				System.exit(1);
			}
			PeopleInElevator Elevator = new PeopleInElevator(0,noOfPpl);
			PeopleInFloor p = new PeopleInFloor(Elevator, noOfFloors,noOfTrips);
			LeavingTheElevator e = new LeavingTheElevator(Elevator, noOfFloors,noOfTrips);
			p.start();
			e.start();
		}


		sc.close();
	}
}
