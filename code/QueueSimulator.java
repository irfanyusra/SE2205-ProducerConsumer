import java.lang.*;

public class QueueSimulator {

	public enum Event {
		ARRIVAL, DEPARTURE
	}; // store information about the next event that will occur in the simulation

	private double currTime; // keeps tracks of the time progression of a simulation
	private double arrivalRate; // store lambda
	private double serviceTime; // 1/mu
	private double timeForNextArrival; // stores the time at which the next packet will arrive into the buffer queue
	private double timeForNextDeparture; // keep track of the time at which a packet will depart from the buffer queue
	private double totalSimTime; // the total time duration for which the simulation is run for
	LinkedListQueue<Data> buffer = new LinkedListQueue<Data>(); // will mimic an actual queue in a router
	LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>(); // to store information about packets entering and
																	// exiting the buffer queue
	private Event e;

	public double getRandTime(double arrivalRate) {
		double num, time1, max = 1, min = 0, randNUM;
		randNUM = Math.random();
		time1 = (-1 / arrivalRate) * (Math.log(1 - randNUM));
	//	 System.out.println("TIM " + time1);
		return time1;
	}

	public QueueSimulator(double aR, double servT, double simT) {
		currTime = 0;
		arrivalRate = aR;
		serviceTime = servT;
		totalSimTime = simT;
		timeForNextArrival = getRandTime(arrivalRate);
		timeForNextDeparture = serviceTime + timeForNextArrival;
		e = Event.ARRIVAL;
	}

	public double calcAverageWaitingTime() {
		double sum = 0;
		int size = eventQueue.size();
		for (int i = 0; i < size; i++) {
			Data a = eventQueue.dequeue();
			sum += (a.getDepartureTime() - a.getArrivalTime()); 
		}
		return (sum / size);
	}

	public double runSimulation() {
		currTime = timeForNextArrival;
		
		while (currTime < totalSimTime) {
			
			if (e == Event.ARRIVAL) {
				Data d = new Data();
				d.setArrivalTime(currTime);
				buffer.enqueue(d);
				
				timeForNextArrival = currTime + getRandTime(arrivalRate);	
			} 
			
			else if (e == Event.DEPARTURE) {

				buffer.first().setDepartureTime(currTime);
				eventQueue.enqueue(buffer.dequeue());
				
				if (buffer.isEmpty())
					timeForNextDeparture = timeForNextArrival +serviceTime;			
				else 
					timeForNextDeparture = currTime + serviceTime; 
				
			}
			
		
			if ((buffer.isEmpty()) ||(timeForNextArrival < timeForNextDeparture)) {
				currTime = timeForNextArrival;
				e = Event.ARRIVAL;
			} else {
				currTime = timeForNextDeparture;
				e = Event.DEPARTURE;
			}
			
		}

		return calcAverageWaitingTime();
	}

}
