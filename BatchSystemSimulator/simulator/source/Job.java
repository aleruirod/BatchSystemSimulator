package simulator.source;

import simulator.enumerators.JobType;
import simulator.exceptions.InvalidParametersException;

public class Job {
	private JobType type;
	private Integer nodesNeeded;
	private Integer hoursNeeded;
	private Integer expectedStartingTime;
	private Integer actualStartingTime;

	public Job(JobType type, Integer nodes, Integer hours, Integer expectedStart) {

		if (nodes <= 0 || nodes > 128)
			throw new InvalidParametersException("The number of cores is not valid.");
		if (hours <= 0)
			throw new InvalidParametersException("The number of hours is invalid.");
		if (expectedStart < 0)
			throw new InvalidParametersException("The expected start time for this job must be greater than 0.");

		this.type = type;
		nodesNeeded = nodes;
		hoursNeeded = hours;
		expectedStartingTime = expectedStart;
		actualStartingTime = 100000;

	}

	public JobType getType() {
		return type;
	}

	public Integer getNodesNeeded() {
		return nodesNeeded;
	}

	public Integer getHoursNeeded() {
		return hoursNeeded;
	}

	public Integer getExpectedStartingTime() {
		return expectedStartingTime;
	}

	public Integer getEndTime() {
		return actualStartingTime + hoursNeeded;
	}

	public Integer getActualStartingTime() {
		return actualStartingTime;
	}

	public Double getTurnaroundTime() {
		Double time = (double) (((double) this.getEndTime() - (double) expectedStartingTime) / (double) hoursNeeded);

		return (double) Math.round(time * 100) / 100;
	}

	public void rescheduleStartingTime(Integer time) {
		actualStartingTime = time;
	}

}
