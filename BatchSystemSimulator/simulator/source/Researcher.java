package simulator.source;

import simulator.enumerators.UserType;
import simulator.exceptions.InvalidParametersException;

public class Researcher extends User {
	private Group group;
	private Double additionalBudget;

	public Researcher(String username, Group group, Double grant, UserType type) {
		super(username, type);
		if (username.isEmpty())
			throw new InvalidParametersException("The username of the researcher user is empty.");
		if (group == null)
			throw new InvalidParametersException("The group this researcher belongs to can not be null.");
		if (grant < 0.0)
			throw new InvalidParametersException(
					"The additional resources granted to the researcher must be positive.");

		this.group = group;
		additionalBudget = grant;
		totalBudget = grant + group.getRemainingBudget();
	}

	public Group getGroup() {
		return group;
	}

	public Double getGrantQty() {
		return additionalBudget;
	}

	@Override
	public void charge(Double cost) {

		if (group.getRemainingBudget() < cost && additionalBudget > cost) {
			additionalBudget -= cost;
			totalBudget -= cost;

		} else if (group.getRemainingBudget() < cost && additionalBudget < cost)
			System.out.println(this.getName() + " does not have sufficient funds.");

		else {
			group.charge(cost);
			totalBudget -= cost;
		}
	}
}
