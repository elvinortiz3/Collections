import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public class JavaRestaurant {

	public static void main(String[] args) {
		JavaRestaurant f = new JavaRestaurant();
		Queue<Customer> QList = new LinkedList<>();
		//	f.CustomerManagement(new Stack<>());
		f.FastFoodWithPacience(QList);

		//f.CustomerManagement(new ArrayList<>());

	}





	//Exercise 1
	//In this exercise you want to simulate a restaurant waiting line.
	//Time is denoted by a variable "t" that starts at 0
	//While the stack SList, waitingLine and clerk are not empty
	//
	//
	//
	//Cambiar a QUEUE RUSH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public int FastFood(Queue<Customer> queue) {

		Customer john = new Customer("John", 1, 3, 5, 0);
		Customer Austin = new Customer("Austin", 2, 3, 3, 0);
		Customer Sandra = new Customer("Sandra", 3, 3, 3, 0);

		queue.add(john);
		queue.add(Austin);
		queue.add(Sandra);


		/////////////////////////Dar a los estudiantes desde aqui///////////////////////////////
		int t = 0; //time
		int TotalMoney = 0; //money earned
		Queue<Customer> clerk = new LinkedList<>(); //where the customer will be attended
		Queue<Customer> WaitingLine = new LinkedList<>(); //waiting Line

		//Looks for first customer
		while (t != queue.peek().getTimeEntered()) {
			t++;
		}

		//Push CLient to clerk
		clerk.add(queue.poll());

		//If initial list, the waiting line and the clerk aren't empty.
		while (!queue.isEmpty() || !WaitingLine.isEmpty() || !clerk.isEmpty()) {
			/////////////////////////Dar a los estudiantes hasta aqui///////////////////////////////

			//If Clerk is available and someone is waiting in line, 
			//push customer from waiting line to clerk
			if (clerk.isEmpty() && !WaitingLine.isEmpty()) {
				clerk.add(WaitingLine.poll());
			}

			//Looks if someone enter the restaurant from SList
			//If time entered from top of stack equals t, then
			//the customer must go to the waiting line
			if (!queue.isEmpty() && queue.peek().timeEntered==t) {
				WaitingLine.add(queue.poll());
			}

			//Takes 1 unit of time from customer in clerk for their respective order
			clerk.peek().setTimeToCompleted(clerk.peek().getTimeToCompleted()-1);

			//Verifies if the order is done
			//if Orders is done, TotalMoney counter acumulates
			if (clerk.peek().getTimeToCompleted()<=0) {
				TotalMoney += clerk.peek().getBill();
				clerk.poll();
			}

			t++;


		}
		return TotalMoney;

	}



	public int FastFoodWithPacience(Queue<Customer> queue) {

		Customer Lucas = new Customer("Lucas", 0, 3, 5, 3);
		Customer john = new Customer("John", 1, 3, 5, 3);
		Customer Austin = new Customer("Austin", 2, 3, 3, 1);
		Customer Sandra = new Customer("Sandra", 3, 3, 3, 4);

		queue.add(Lucas);
		queue.add(john);
		queue.add(Austin);
		queue.add(Sandra);


		/////////////////////////Dar a los estudiantes desde aqui///////////////////////////////
		int t = 0; //time
		int TotalMoney = 0; //money earned
		Queue<Customer> cashRegister = new LinkedList<>(); //where the customer will be attended
		Queue<Customer> WaitingLine = new LinkedList<>(); //waiting Line

		//Looks for first customer
		while (t != queue.peek().getTimeEntered()) {
			t++;
		}

		//Push CLient to clerk
		cashRegister.add(queue.poll());

		//If initial list, the waiting line and the clerk aren't empty.
		while (!queue.isEmpty() || !WaitingLine.isEmpty() || !cashRegister.isEmpty()) {
			/////////////////////////Dar a los estudiantes hasta aqui///////////////////////////////

			//If Clerk is available and someone is waiting in line, 
			//push customer from waiting line to clerk
			if (cashRegister.isEmpty() && !WaitingLine.isEmpty()) {
				cashRegister.add(WaitingLine.poll());
			}

			//Looks if someone enter the restaurant from SList
			//If time entered from top of stack equals t, then
			//the customer must go to the waiting line
			if (!queue.isEmpty() && queue.peek().timeEntered==t) {
				WaitingLine.add(queue.poll());
			}

			//Takes 1 unit of time from customer in clerk for their respective order
			cashRegister.peek().setTimeToCompleted(cashRegister.peek().getTimeToCompleted()-1);

			WaitingLine = pacienceReducer(WaitingLine);
			//Verifies if the order is done
			//if Orders is done, TotalMoney counter acumulates
			if (cashRegister.peek().getTimeToCompleted()<=0) {
				TotalMoney += cashRegister.peek().getBill();
				cashRegister.poll();
			}

			t++;


		}
		return TotalMoney;

	}


	//Exercise 3
	//Implement a method to reduce 1 unit of time to every
	//customer in the waiting line to his/hers patience.
	//If patience is equals to zero, the customer will go.
	private Queue<Customer> pacienceReducer(Queue<Customer> waitingLine) {

		Queue<Customer> temp = new LinkedList<>();

		while (!waitingLine.isEmpty()) {
			waitingLine.peek().setPatience(waitingLine.peek().getPatience()-1);
			if (waitingLine.peek().getPatience()==0) {
				waitingLine.poll();
			}
			else {
				temp.add(waitingLine.poll());
			}

		}


		waitingLine = temp;
		
		return waitingLine;

	}





	//Exercise 3
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
		public int patience;
		public String Name;


		public Customer(String Name,int timeEntered, int bill, int timeToCompleted, int Pacience) {
			this.timeEntered = timeEntered;
			this.bill = bill;
			this.timeToCompleted = timeToCompleted;
			this.patience = Pacience;
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


		public int getPatience() {
			return patience;
		}


		public void setPatience(int pacience) {
			patience = pacience;
		}

		public String toString() {
			return "("+Name + ", Arrive: " + timeEntered + " \nOrders Time: " + timeToCompleted+")";
		}


		//Implement the comparable so that 
		//we can sort the Arraylist line by time completed
		public int compareTo(Customer o) {		
			if (this.getPatience()>o.getPatience()) {
				return 1;
			}

			else {
				return -1;
			}
		}	
	}

}
