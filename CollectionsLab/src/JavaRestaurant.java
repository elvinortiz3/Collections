import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;




public class JavaRestaurant {


	//Exercise 1
	//In this exercise you want to simulate a restaurant waiting line.
	//Time is denoted by a variable "t" that starts at 0
	//Follow the instructions in the comments to complete this exercise
	//Return the total amount of money that the customers paid for there service.
	public static int FastFood(Queue<Customer> incomingCustomers) {

		int t = 0; //time
		int TotalMoney = 0; //money earned
		Customer CustomerAtCashRegister = null; //where the customer will be attended
		Queue<Customer> WaitingLine = new LinkedList<>(); //waiting Line

		//Looks for first customer
		while (t != incomingCustomers.peek().getTimeEntered()) {
			t++;
		}

		//Assign first customer to cash register
		CustomerAtCashRegister = (incomingCustomers.poll());

		//If initial list, the waiting line and the cash register aren't empty.
		while (!incomingCustomers.isEmpty() || !WaitingLine.isEmpty() || CustomerAtCashRegister!= null) {
			/////////////////////////Dar a los estudiantes hasta aqui///////////////////////////////

			//If cash register is available and someone is waiting in line, 
			//then move the next customer from waiting line to cash register
			if (CustomerAtCashRegister==null && !WaitingLine.isEmpty()) {
				CustomerAtCashRegister = (WaitingLine.poll());
			}

			//Looks if some incoming customer can enter the restaurant
			//If that is the case, move that customer to the waiting line
			if (!incomingCustomers.isEmpty() && incomingCustomers.peek().timeEntered<=t) {
				WaitingLine.add(incomingCustomers.poll());
			}

			//Takes 1 unit of time from customer in cash register for their respective order
			CustomerAtCashRegister.setTimeToCompleted(CustomerAtCashRegister.getTimeToCompleted()-1);

			//If the cash register is done, accumulate the money from the order
			//and free the cash register.
			if (CustomerAtCashRegister.getTimeToCompleted()<=0) {
				TotalMoney += CustomerAtCashRegister.getBill();
				CustomerAtCashRegister = null;
			}

			t++;


		}
		return TotalMoney;

	}



	public static int FastFoodWithPatience(Queue<Customer> queue) {

		/////////////////////////Dar a los estudiantes desde aqui///////////////////////////////
		int t = 0; //time
		int TotalMoney = 0; //money earned
		Customer cashRegister = null; //where the customer will be attended
		Queue<Customer> WaitingLine = new LinkedList<>(); //waiting Line

		//Looks for first customer
		while (t != queue.peek().getTimeEntered()) {
			t++;
		}

		//Push CLient to clerk
		cashRegister = queue.poll();

		//If initial list, the waiting line and the clerk aren't empty.
		while (!queue.isEmpty() || !WaitingLine.isEmpty() || cashRegister!=null) {
			/////////////////////////Dar a los estudiantes hasta aqui///////////////////////////////

			//If Clerk is available and someone is waiting in line, 
			//push customer from waiting line to clerk
			if (cashRegister==null && !WaitingLine.isEmpty()) {
				cashRegister = WaitingLine.poll();
			}

			//Looks if someone enter the restaurant from SList
			//If time entered from top of stack equals t, then
			//the customer must go to the waiting line
			if (!queue.isEmpty() && queue.peek().timeEntered==t) {
				WaitingLine.add(queue.poll());
			}

			//Takes 1 unit of time from customer in clerk for their respective order
			cashRegister.setTimeToCompleted(cashRegister.getTimeToCompleted()-1);

			
			//Reduce 1 unit of patience to every Customer in the waitingLine
			WaitingLine = pacienceReducer(WaitingLine);
			
			
			
			//Verifies if the order is done
			//if Orders is done, TotalMoney counter acumulates
			if (cashRegister.getTimeToCompleted()<=0) {
				TotalMoney += cashRegister.getBill();
				cashRegister = null;
			}

			t++;


		}
		return TotalMoney;

	}


	//Exercise 2
	//Implement a method to reduce 1 unit of time to every
	//customer in the waiting line to his/hers patience.
	//If patience is equals to zero, the customer will get out of the line and leave.
	private static Queue<Customer> pacienceReducer(Queue<Customer> waitingLine) {

		Queue<Customer> temp = new LinkedList<>();

		while (!waitingLine.isEmpty()) {
			waitingLine.peek().setPatience(waitingLine.peek().getPatience()-1);
			if (waitingLine.peek().getPatience()<=0) {
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
	//know the order they have enter. Sort the WaitingLine Queue to attend
	//the customers patience that take less time.
	//Hint: Use the Comparator and Collections.sort();
	@SuppressWarnings("unchecked")
	public static int CustomerManagement(Queue<Customer> WaitingLine) {

		
		int totalMoney = 0;
				
		Collections.sort((List<Customer>) WaitingLine, new Comparator<Customer>() {

			@Override
			public int compare(Customer o1, Customer o2) {
				if (o1.getPatience()<o2.getPatience()) {
					return 1;
				}
				return -1;
			}
		});


		Customer cashRegister = null;

		while (!WaitingLine.isEmpty()||cashRegister != null) {
			if (cashRegister != null) {
				cashRegister.setTimeToCompleted(cashRegister.getTimeToCompleted()-1);
				if (cashRegister.getTimeToCompleted()<=0) {
					totalMoney = totalMoney +cashRegister.getBill();
					cashRegister = null;
				}
			}
			else {
				cashRegister = WaitingLine.poll();
			}	

		}


		return totalMoney;

	}





	public static class Customer implements Comparable<Customer> {
		public int timeEntered;
		public int bill;
		public int timeToCompleted;
		public int patience;
		public String Name;

		public Customer(String Name,int timeEntered, int bill, int timeToCompleted, int patience) {
			this.timeEntered = timeEntered;
			this.bill = bill;
			this.timeToCompleted = timeToCompleted;
			this.patience = patience;
			this.Name = Name;
		}
		
		public Customer(CustomerBuilder builder) {
			this(builder.Name,
					builder.timeEntered, 
					builder.bill, 
					builder.timeToCompleted, 
					builder.patience);
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
			return "("+Name + ", Patience: " + patience + " \nOrders Time: " + timeToCompleted+")";
		}


		//Implement the comparable so that 
		//we can sort the WaitingLine by Patience
		public int compareTo(Customer o) {		
			if (this.getPatience()>o.getPatience()) {
				return 1;
			}
			else {
				return -1;
			}
		}	
	}
	public static class CustomerBuilder{

		private int timeEntered = 0;
		private int bill = 0;
		private int timeToCompleted = 0;
		private int patience = 0;
		private String Name = "";

		public CustomerBuilder timeEntered(int val) {
			timeEntered = val;
			return this;
		}
		public CustomerBuilder bill(int val) {
			bill = val;
			return this;
		}
		public CustomerBuilder timeToCompleted(int val) {
			timeToCompleted = val;
			return this;
		}
		public CustomerBuilder patience(int val) {
			patience = val;
			return this;
		}
		public CustomerBuilder Name(String val) {
			Name = val;
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}
	}
}