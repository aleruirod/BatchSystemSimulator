package simulator.source;

import simulator.exceptions.InvalidParametersException;

public class Group {

	private String name;
	private Double capBudget;

	public Group(String name, Double budget) {

		if (name.isEmpty())
			throw new InvalidParametersException("The name of the group is empty.");
		if (budget < 0)
			throw new InvalidParametersException("The group budget must be positive.");

		this.name = name;
		capBudget = budget;
	}

	public String getName() {
		return name;
	}

	public Double getRemainingBudget() {
		return capBudget;
	}

	public void charge(Double cost) {
		capBudget -= cost;
	}

}
