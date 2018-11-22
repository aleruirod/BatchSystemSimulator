package simulator.source;

import simulator.exceptions.InvalidParametersException;

public class Request {

	private Job associatedJob;
	private User requester;

	public Request(Job job, User requester) {

		if (job == null)
			throw new InvalidParametersException("The associated job to this request can not be null.");
		if (requester == null)
			throw new InvalidParametersException("The requester can not be null.");

		associatedJob = job;
		this.requester = requester;
	}

	public Job getAssociatedJob() {
		return associatedJob;
	}

	public User getRequester() {
		return requester;
	}
}
