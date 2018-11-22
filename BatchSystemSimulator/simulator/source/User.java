package simulator.source;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import simulator.enumerators.JobType;
import simulator.enumerators.UserType;
import simulator.exceptions.InvalidParametersException;

public class User {
	private String userName;
	private UserType type;
	private List<Job> associatedJobs = new LinkedList<>();
	protected Double totalBudget;

	public User(String username, UserType type) {
		if (username.isEmpty())
			throw new InvalidParametersException("The username is empty.");

		userName = username;
		this.type = type;
	}

	public String getName() {
		return userName;
	}

	public UserType getType() {
		return type;
	}

	public List<Job> getAssociatedJobs() {
		return associatedJobs;
	}

	public Double getTotalBudget() {
		return totalBudget;
	}

	public void charge(Double cost) {

	}

	public Request createRequest(Integer startTime) {
		Random randGen = new Random();
		Job j;
		Integer hugeProbability = randGen.nextInt(4);// Let's say that huge jobs can only be created by big type users
														// and that the probability of this occuring is 25%

		if (this.type == UserType.Small)
			j = new Job(JobType.Short, (randGen.nextInt(2) + 1), 1, startTime); // Its 103 as we dont want to
																							// go over 5 pm on fridays
																							// and the week starts at 9
																							// am on mondays
		else if (this.type == UserType.Medium)

			j = new Job(JobType.Medium, (randGen.nextInt(12) + 1), (randGen.nextInt(8) + 1), startTime);

		else if (this.type == UserType.Big && hugeProbability != 3)

			j = new Job(JobType.Large, (randGen.nextInt(64) + 1), (randGen.nextInt(16) + 1), startTime);

		else

			j = new Job(JobType.Huge, (randGen.nextInt(128) + 1), (randGen.nextInt(64) + 1), randGen.nextInt(64) + 103);

		associatedJobs.add(j);

		Request r = new Request(j, this);

		return r;
	}
}
