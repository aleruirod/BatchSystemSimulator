package simulator.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import simulator.source.Auxiliary;
import simulator.source.Output;
import simulator.source.Simulator;

public class PerformanceTest extends TestCase {

	public PerformanceTest() {
		super();
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Override
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Override
	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void test1000Users() {

		Double startTime = 0.0;
		Double endTime = 0.0;

		startTime = (double) System.currentTimeMillis();
		Simulator sys = new Simulator();
		sys.initialize();

		Auxiliary.generateScenario(sys, 1000, 15000.0);

		Auxiliary.dumpQueueToExecution(sys);

		Output.printResults(sys);

		endTime = (double) System.currentTimeMillis();

		Double timeSpent = (endTime - startTime) / 1000.0;

		System.out.println("The program took " + timeSpent + " seconds to finish.");

	}

	@Test
	public void test5000Users() {

		Double startTime = 0.0;
		Double endTime = 0.0;

		startTime = (double) System.currentTimeMillis();
		Simulator sys = new Simulator();
		sys.initialize();

		Auxiliary.generateScenario(sys, 5000, 15000.0);

		Auxiliary.dumpQueueToExecution(sys);

		Output.printResults(sys);

		endTime = (double) System.currentTimeMillis();

		Double timeSpent = (endTime - startTime) / 1000.0;

		System.out.println("The program took " + timeSpent + " seconds to finish.");

	}

	@Test
	public void test9000Users() {

		Double startTime = 0.0;
		Double endTime = 0.0;

		startTime = (double) System.currentTimeMillis();
		Simulator sys = new Simulator();
		sys.initialize();

		Auxiliary.generateScenario(sys, 9000, 15000.0);

		Auxiliary.dumpQueueToExecution(sys);

		Output.printResults(sys);

		endTime = (double) System.currentTimeMillis();

		Double timeSpent = (endTime - startTime) / 1000.0;

		System.out.println("The program took " + timeSpent + " seconds to finish.");

	}

	@Test
	public void test12000Users() {

		Double startTime = 0.0;
		Double endTime = 0.0;

		startTime = (double) System.currentTimeMillis();
		Simulator sys = new Simulator();
		sys.initialize();

		Auxiliary.generateScenario(sys, 12000, 15000.0);

		Auxiliary.dumpQueueToExecution(sys);

		Output.printResults(sys);

		endTime = (double) System.currentTimeMillis();

		Double timeSpent = (endTime - startTime) / 1000.0;

		System.out.println("The program took " + timeSpent + " seconds to finish.");

	}

}