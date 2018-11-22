package simulator.source;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import simulator.enumerators.JobType;
import simulator.enumerators.UserType;

public class Auxiliary {


	public static void generateScenario(Simulator sys, Integer numberOfUsers, Double groupBudgets) {
		Random randomGen = new Random();
		Group g = new Group("Group 1", groupBudgets);
		Curriculum c = new Curriculum("Computational Methods", groupBudgets);
		Integer nextJobStartTime = 0;
		
		for (int i = 0; i < numberOfUsers; i++) {
			Integer userType = randomGen.nextInt(3);
			Integer userRole = randomGen.nextInt(3);
			Integer numJobsPerUser = (int) (getNextExpDistr(2) + 1); // let's say that on average, each user sends 2 job
																		// requests. We add 1 because we want to make
																		// sure that no user sends 0 requests.

			nextJobStartTime = (nextJobStartTime % 103) + (int) (getNextExpDistr(3).longValue()); // here, we make sure
																									// that the start
																									// hours don't go
																									// over the cutoff
																									// time by reducing
																									// the previous
																									// nextJobStartTime
																									// with the module
																									// of the start
																									// time.
			
			switch(userRole) {
			
			case 0:
				ITSupport it;
				if (userType == 0)
					it = new ITSupport("user" + i, UserType.Small);
				
				else if (userType == 1)
					it = new ITSupport("user" + i, UserType.Medium);
				
				else 
					it = new ITSupport("user" + i, UserType.Big);
				
				for (int j = 0; j < numJobsPerUser; j++) {
					scheduler(sys, it.createRequest(nextJobStartTime));
					nextJobStartTime = (nextJobStartTime % 103) + (int) (getNextExpDistr(3).longValue());
				}
				sys.addUser(it);
				break;

			case 1:
				Researcher r;
				if (userType == 0)
					r = new Researcher("user" + i, g, 5.0, UserType.Small);
				
				else if (userType == 1)
					r = new Researcher("user" + i, g, 5.0, UserType.Medium);

				else
					r = new Researcher("user" + i, g, 5.0, UserType.Big);

				for (int j = 0; j < numJobsPerUser; j++) {
					scheduler(sys, r.createRequest(nextJobStartTime));
					nextJobStartTime = (nextJobStartTime % 103) + (int) (getNextExpDistr(3).longValue());
				}
				sys.addUser(r);
				break;

			default:
				Student s;
				if (userType == 0)
					s = new Student("user" + i, c, UserType.Small);

				else if (userType == 1)
					s = new Student("user" + i, c, UserType.Medium);

				else
					s = new Student("user" + i, c, UserType.Big);
				
				for (int j = 0; j < numJobsPerUser; j++) {
					scheduler(sys, s.createRequest(nextJobStartTime));
					nextJobStartTime = (nextJobStartTime % 103) + (int) (getNextExpDistr(3).longValue());
				}

				sys.addUser(s);
				break;
			}
		}
		
	}

	public static void scheduler(Simulator sys, Request r) {

		Integer designatedQueue = 0;
		
		switch(r.getAssociatedJob().getType().toString()) { //The queues are referenced by their position in the queueList in the System.
		
		case "Short":
			designatedQueue = 0;
			break;
			
		case "Medium":
			designatedQueue = 1;
			break;
			
		case "Large":
			designatedQueue = 2;
			break;
			
		case "Huge":
			designatedQueue = 3;
			break;

		}

		sys.getQueueList().get(designatedQueue).processRequest(sys, r);

		sortQueues(sys); // The first-come first-serve sorting is carried out by this function. If the
							// criterion for preference of request processing is to be modified, the sorting
							// function should replace this one.

		
	}

	public static void sortQueues(Simulator sys) { // Essentially,this function sorts the jobs by the start date
													// attribute.

		for(int i = 0; i < sys.getQueueList().size(); i++) {
			Queue currentQueue = sys.getQueueList().get(i);
			
			sys.getQueueList().get(i).updateJobList(currentQueue.getJobList().stream()
					.sorted((x, y) -> x.getExpectedStartingTime().compareTo(y.getExpectedStartingTime()))
					.collect(Collectors.toList()));
		}
	}

	public static void dumpQueueToExecution(Simulator sys) {

		List<List<Job>> allJobs = sys.getQueueList().stream().map(x -> x.getJobList()).collect(Collectors.toList());
		List<Job> currentQueue;


		for (int i = 0; i < allJobs.size(); i++) {
			Integer currentStartTime = 0;
			currentQueue = allJobs.get(i);

			if (currentQueue.isEmpty()) {
				continue;
			}

			if (i == 3)
				currentStartTime = 103;

			Job currentJob = currentQueue.get(0);
			
			for (int j = 0; j < currentQueue.size(); j++) {
				
				currentJob = currentQueue.get(j);

				if (currentJob.getType() != JobType.Huge && currentStartTime + currentJob.getHoursNeeded() > 103) {

					System.out.println(
							currentJob.getType() + " job " + j + " will go over the cut off time after waiting.");
					currentQueue.remove(j);
					sys.getQueueList().get(i).removeJobFromQueue(currentJob);


				} else if (currentJob.getType() == JobType.Huge
						&& currentStartTime + currentJob.getHoursNeeded() > 167) {

					System.out.println(
							currentJob.getType() + " job " + j + " will go over the weekend queue time after waiting.");
					currentQueue.remove(j);
					sys.getQueueList().get(i).removeJobFromQueue(currentJob);

				} else if (checkAvailability(sys, currentStartTime, currentJob, currentQueue, i)
						&& currentStartTime >= currentJob.getExpectedStartingTime()) {

					Integer previousActualStartTime = currentQueue.get(j).getActualStartingTime();
					currentQueue.get(j).rescheduleStartingTime(currentStartTime);
					if (checkAvailability(sys, currentStartTime, currentJob, currentQueue, i)) {
						sys.getQueueList().get(i).getJobList().get(j).rescheduleStartingTime(currentStartTime);
						sys.increaseWaitingTimeForQueue(i,
								currentJob.getActualStartingTime() - currentJob.getExpectedStartingTime());
						sys.increaseTurnAroundtime(currentJob.getTurnaroundTime());

						// THIS COMMENT BLOCK CAN BE USEFUL TO SEE ALL TASKS IN EXECUTION
						// System.out.println(currentJob.getActualStartingTime() + " " +
						// currentJob.getEndTime() + " "
						// + currentJob.getType() + " " + currentJob.getHoursNeeded() + " "
						// + currentJob.getNodesNeeded());

					} else {
						currentQueue.get(j).rescheduleStartingTime(previousActualStartTime);
						j--;
						currentStartTime++;
					}

				} else {

					j--;
					currentStartTime++;
				}
			}

			sys.receivePayment(sys.getQueueList().get(i).getTotalPaidByUsers());
			sys.addToExecution(currentQueue);

		}

		sys.applyOperationalCosts();

	}

	public static Boolean checkAvailability(Simulator sys, Integer currentStartTime, Job currentJob,
			List<Job> currentQueue, Integer queueNumber) {
		Integer coresBeingUsed = 0;
		Boolean availability = true;

		for (int i = 0; i < currentQueue.size(); i++) {

			if (currentQueue.get(i).getEndTime() > currentStartTime
					&& currentQueue.get(i).getActualStartingTime() <= currentStartTime) {
				coresBeingUsed += currentQueue.get(i).getNodesNeeded();

			}
		}

		switch (queueNumber) {

		case 0:
			if (coresBeingUsed > 12)
				availability = false;
			break;

		case 1:
			if (coresBeingUsed > 38)
				availability = false;
			break;

		case 2:
			if (coresBeingUsed > 64)
				availability = false;
			break;

		case 3:
			if (coresBeingUsed > 128)
				availability = false;
			break;
		}

		return availability;
	}

	public static Double getNextExpDistr(Integer range) {
		Random rand = new Random();
		return (Math.log(1 - rand.nextDouble()) / (-1)) * range;
	}




}
