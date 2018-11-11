import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class JavaRestaurant {
	
	public static void main(String[] args) {
		JavaRestaurant f = new JavaRestaurant();
		f.CustomerManagement(new Stack<>());
	}

	public int CustomerManagement(Stack<Customer> SList) {
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

			//If Clerk is available and someone is waiting in line, 
			//push customer from waiting line to clerk
			if (clerk.isEmpty() && !WaitingLine.isEmpty()) {
				clerk.push(WaitingLine.pop());
			}

			//Looks if someone enter the restaurant
			if (!SList.isEmpty() && SList.peek().timeEntered==t) {
				WaitingLine.push(SList.pop());
			}

			//Takes 1 unit of time from customer in clerk for their respective order
			clerk.peek().setTimeToCompleted(clerk.peek().getTimeToCompleted()-1);

			//Verifies if the order is done
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

	public int CustomerManagement(ArrayList<Customer> AList) {



		return 0;

	}






	public class Customer {
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



	}




}
