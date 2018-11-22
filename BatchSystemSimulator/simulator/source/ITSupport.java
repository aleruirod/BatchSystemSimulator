package simulator.source;

import simulator.enumerators.UserType;
import simulator.exceptions.InvalidParametersException;

public class ITSupport extends User {

	public ITSupport(String username, UserType type) {
		super(username, type);
		if (username.isEmpty())
			throw new InvalidParametersException("The username of the ITSupport user is empty.");

		totalBudget = 250.0; // IT Support users have a higher individual budget compared to other types of
								// users.
	}
	
	@Override
	public void charge(Double cost) {

		totalBudget -= cost;
	}
}
