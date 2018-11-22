package simulator.source;

import simulator.enumerators.UserType;
import simulator.exceptions.InvalidParametersException;

public class Student extends User {

	private Curriculum curriculum;

	public Student(String username, Curriculum curriculum, UserType type) {
		super(username, type);

		if (username.isEmpty())
			throw new InvalidParametersException("The username of the student user is empty.");
		if (curriculum == null)
			throw new InvalidParametersException("The curriculum this student belongs to can not be null.");

		this.curriculum = curriculum;
		totalBudget = curriculum.getRemainingBudget();

	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	@Override
	public void charge(Double cost) {

		if (curriculum.getRemainingBudget() < cost) {
			System.out.println(this.getName() + " does not have sufficient funds.");

		} else {
			curriculum.charge(cost);
			totalBudget -= cost;
		}
	}
}
