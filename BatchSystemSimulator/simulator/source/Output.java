package simulator.source;

public class Output {

	public static void printResults(Simulator sys) {
		
		System.out.println("\n --- PROGRAM OUTPUT --- \n");

		for (int i = 0; i < sys.getQueueList().size(); i++) {
			Queue currentQueue = sys.getQueueList().get(i);
			System.out.println(currentQueue.getType() + " queue processed " + currentQueue.getNumJobsProcessed()
					+ " jobs this week.");
			System.out.println(currentQueue.getType() + " queue consumed " + currentQueue.getTotalMachineHours()
					+ " machine hours this week.");
			System.out.println(currentQueue.getType() + " queue charged " + currentQueue.getTotalPaidByUsers()
					+ " to users this week.");
			System.out.println("The average waiting time for the " + currentQueue.getType() + " queue was "
					+ sys.getWaitingTimes()[i] / currentQueue.getJobList().size() + " hours. \n");
	
		}
	
		System.out.println(
				"The average turnaround time ratio for the system was " + sys.getAverageTurnAroundTime() + "\n");
	
		System.out.println("The economic balance of the centre is " + sys.getEconomicBalance() + "\n");
	
	}
}
