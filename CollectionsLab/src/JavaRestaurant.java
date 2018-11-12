import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class JavaRestaurant {

	public static void main(String[] args) {
		JavaRestaurant f = new JavaRestaurant();
		//	f.CustomerManagement(new Stack<>());
		//	f.CustomerManagement(new PriorityQueue<>());
		f.CustomerManagement(new ArrayList<>());
	}

	//Exercise 1
	//In this exercise you want to simulate a restaurant waiting line.
	//Time is denoted by a variable "t" that starts at 0
	//While the stack SList, waitingLine and clerk are not empty
	//
	//
	//
	public int CustomerManagement(Stack<Customer> SList) {
	
/////////////////////////Dar a los estudiantes desde aqui///////////////////////////////
		int t = 0; //time
		int TotalMoney = 0; //money earned
		Stack<Customer> clerk = new Stack<>(); //where the customer will be attended
		Stack<Customer> WaitingLine = new Stack<>(); //waiting Line

		//Looks for first customer
		while (t != SList.peek().getTimeEntered()) {
			t++;
		}

		//Push CLient to clerk
		clerk.push(SList.pop());

		//If initial list, the waiting line and the clerk aren't empty.
		while (!SList.isEmpty() || !WaitingLine.isEmpty() || !clerk.isEmpty()) {
/////////////////////////Dar a los estudiantes hasta aqui///////////////////////////////
			
			//If Clerk is available and someone is waiting in line, 
			//push customer from waiting line to clerk
			if (clerk.isEmpty() && !WaitingLine.isEmpty()) {
				clerk.push(WaitingLine.pop());
			}

			//Looks if someone enter the restaurant from SList
			//If time entered from top of stack equals t, then
			//the customer must go to the waiting line
			if (!SList.isEmpty() && SList.peek().timeEntered==t) {
				WaitingLine.push(SList.pop());
			}

			//Takes 1 unit of time from customer in clerk for their respective order
			clerk.peek().setTimeToCompleted(clerk.peek().getTimeToCompleted()-1);

			//Verifies if the order is done
			//if Orders is done, TotalMoney counter acumulates
			if (clerk.peek().getTimeToCompleted()<=0) {
				TotalMoney += clerk.peek().getBill();
				clerk.pop();
			}

			t++;


		}
		return TotalMoney;

	}


	public int CustomerManagement(PriorityQueue<Customer> QList) {



		return 0;

	}

	//Exercise 2
	//A lot of people have entered the restaurant and you don't 
	//know the order they have enter. Sort the Arraylist to attend
	//the customers by the orders that take less time.
	//Hint: Use the compareTo method in Customer class and Collections.sort();
	public ArrayList<Customer> CustomerManagement(ArrayList<Customer> AList) {

		ArrayList<Customer> l = new ArrayList<>();

		l.add(new Customer("A", 0, 3, 7, 9));
		l.add(new Customer("B", 0, 3, 1, 9));
		l.add(new Customer("C", 0, 3, 6, 9));
		l.add(new Customer("D", 0, 3, 9, 9));

		Collections.sort(l);


		return l;

	}






	public class Customer implements Comparable<Customer> {
		public int timeEntered;
		public int bill;
		public int timeToCompleted;
		public int Pacience;
		public String Name;


		public Customer(String Name,int timeEntered, int bill, int timeToCompleted, int Pacience) {
			this.timeEntered = timeEntered;
			this.bill = bill;
			this.timeToCompleted = timeToCompleted;
			this.Pacience = Pacience;
			this.Name = Name;
		}


		public String getName() {
			return Name;
		}


		public void setName(String name) {
			Name = name;
		}


		public int getTimeEntered() {
			return timeEntered;
		}


		public void setTimeEntered(int timeEntered) {
			this.timeEntered = timeEntered;
		}


		public int getBill() {
			return bill;
		}


		public void setBill(int bill) {
			this.bill = bill;
		}


		public int getTimeToCompleted() {
			return timeToCompleted;
		}


		public void setTimeToCompleted(int timeToCompleted) {
			this.timeToCompleted = timeToCompleted;
		}


		public int getPacience() {
			return Pacience;
		}


		public void setPacience(int pacience) {
			Pacience = pacience;
		}

		public String toString() {
			return "Name: " +Name + " Arrive: " + timeEntered + " \nOrders Time: " + timeToCompleted;
		}


		//Implement the comparable so that 
		//we can sort the Arraylist line by time completed
		public int compareTo(Customer o) {		
			if (this.getTimeToCompleted()>o.getTimeToCompleted()) {
				return 1;
			}

			else {
				return -1;
			}
		}
		
		
		
	}
}
