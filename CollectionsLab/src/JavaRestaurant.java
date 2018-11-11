import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class JavaRestaurant {

	public int CustomerManagement(Stack<Customer> SList) {



		return 0;

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


		public Customer(int timeEntered, int bill, int timeToCompleted, int Pacience) {
			this.timeEntered = timeEntered;
			this.bill = bill;
			this.timeToCompleted = timeToCompleted;
			this.Pacience = Pacience;		
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



	}




}
