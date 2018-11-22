package simulator.source;

import java.util.Locale;
import java.util.Scanner;

import simulator.exceptions.InvalidParametersException;

public class Input {

	public static void receiveParametersFromUser(Simulator sys) {

		Integer usrNum;
		Double budgets;

		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);// so the input allows a dot instead of a comma for expressing doubles.
		System.out.println("Insert the number of users desired: ");
		usrNum = sc.nextInt();
		checkNumberOfUSers(usrNum);
		System.out.println("Insert the budget for groups and curriculums: ");
		budgets = sc.nextDouble();
		checkBudgets(budgets);

		sc.close();

		Auxiliary.generateScenario(sys, usrNum, budgets);
		
	}

	public static void checkNumberOfUSers(Integer usrNum) {
		if (usrNum < 0)
			throw new InvalidParametersException("The number of users should be positive.");
	}

	public static void checkBudgets(Double budgets) {
		if (budgets < 0.0)
			throw new InvalidParametersException("The budgets for groups and curriculums should be positive.");
	}
}
