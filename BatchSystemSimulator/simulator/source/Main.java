package simulator.source;

public class Main {

	public static void main(String[] args) {

		Simulator sys = new Simulator();
		sys.initialize();

		Input.receiveParametersFromUser(sys);

		Auxiliary.dumpQueueToExecution(sys);

		Output.printResults(sys);

		// NEXT LINE IS RELATED TO TESTS

		// uncomment to run tests after program execution. (except performance tests due
		// to the execution time increasing significantly)
		// JUnitCore.main("simulator.tests.AllTests");

	}

}
