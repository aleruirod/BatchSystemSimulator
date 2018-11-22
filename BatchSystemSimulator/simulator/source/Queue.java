package simulator.source;

import java.util.LinkedList;
import java.util.List;

import simulator.enumerators.JobType;
import simulator.enumerators.QueueType;
import simulator.exceptions.InvalidParametersException;

public class Queue {

	private QueueType type;
	private Double costPerHour;
	private Double operationalCostPerHour;
	private List<Job> jobList = new LinkedList<>();
	private Integer numJobsProcessed = 0;
	private Integer totalMachineHours = 0;
	private Double totalPaidByUsers = 0.0;

	public Queue(QueueType type, Double associatedCost, Double operationalCost) {

		if (associatedCost < 0.0)
			throw new InvalidParametersException("The associated cost for the queue must be positive.");
		if (operationalCost < 0.0)
			throw new InvalidParametersException("The operational cost for the queue must be positive.");

		this.type = type;
		costPerHour = associatedCost;
		operationalCostPerHour = operationalCost;
	}

	public QueueType getType() {
		return type;
	}

	public Double getCostPerHour() {
		return costPerHour;
	}

	public Double getOperationalCostPerHour() {
		return operationalCostPerHour;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public Integer getNumJobsProcessed() {
		return numJobsProcessed;
	}

	public Integer getTotalMachineHours() {
		return totalMachineHours;
	}

	public Double getTotalPaidByUsers() {
		return totalPaidByUsers;
	}

	public void updateJobList(List<Job> newList) {

		this.jobList = newList;

	}

	public void addJobToQueue(Job j) {
		jobList.add(j);
		numJobsProcessed++;
		totalMachineHours += j.getNodesNeeded() * j.getHoursNeeded();
	}

	public void removeJobFromQueue(Job j) {
		numJobsProcessed--;
		totalMachineHours -= j.getNodesNeeded() * j.getHoursNeeded();
		jobList.remove(j);
	}

	public void processRequest(Simulator sys, Request r) {

		Double userBudget = r.getRequester().getTotalBudget();

		if (r.getAssociatedJob().getType() != JobType.Huge
				&& (r.getAssociatedJob().getHoursNeeded() + r.getAssociatedJob().getExpectedStartingTime() > 103))

			System.out.println(r.getRequester().getName() + "'s job will go over the cut off time.");

		else if (r.getAssociatedJob().getType() == JobType.Huge
				&& (r.getAssociatedJob().getHoursNeeded() + r.getAssociatedJob().getExpectedStartingTime() > 167))

			System.out.println(r.getRequester().getName() + "'s job will go over the weekend queue time.");

		else if ((r.getAssociatedJob().getHoursNeeded() * this.getCostPerHour()) <= userBudget) {

			r.getRequester().charge(r.getAssociatedJob().getHoursNeeded() * this.getCostPerHour());
			totalPaidByUsers += r.getAssociatedJob().getHoursNeeded() * this.getCostPerHour();
			addJobToQueue(r.getAssociatedJob());
			
		} else

			System.out.println(r.getRequester().getName() + " has insufficient funds.");




	}

}
