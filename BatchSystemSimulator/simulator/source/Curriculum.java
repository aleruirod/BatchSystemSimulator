package simulator.source;

import simulator.exceptions.InvalidParametersException;

public class Curriculum {

	private String name;
	private Double capBudget;

	public Curriculum(String name, Double budget) {

		if (name.isEmpty())
			throw new InvalidParametersException("The name of the curriculum is empty.");
		if (budget < 0)
			throw new InvalidParametersException("The curriculum budget must be positive.");

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
