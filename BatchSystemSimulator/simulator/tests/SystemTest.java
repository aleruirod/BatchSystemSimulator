package simulator.tests;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import simulator.enumerators.JobType;
import simulator.enumerators.UserType;
import simulator.source.Auxiliary;
import simulator.source.ITSupport;
import simulator.source.Job;
import simulator.source.Request;
import simulator.source.Simulator;

public class SystemTest extends TestCase {

	public SystemTest() {
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
	public void testScenarioGeneration() {

		Simulator sys = new Simulator();

		sys.initialize();

		Integer numberOfUsers = 100;

		Double groupBudgets = 1000.0;

		Auxiliary.generateScenario(sys, numberOfUsers, groupBudgets);

		Assertions.assertFalse(sys.getUserList().isEmpty());
	}

	@Test
	public void testSimulatorInitialization() {

		Integer expectedNumberOfQueues = 4;
		Integer expectedNumberOfNodes = 128;

		Simulator sys = new Simulator();

		sys.initialize();

		Integer actualNumberOfQueues = sys.getQueueList().size();
		Integer actualNumberOfNodes = sys.getNodeList().size();

		Assert.assertEquals(
				"The expected number of queues generated is " + expectedNumberOfQueues + " but the actual number was "
						+ actualNumberOfQueues,
				expectedNumberOfQueues, actualNumberOfQueues);

		Assert.assertEquals(
				"The expected number of nodes generated is " + expectedNumberOfNodes + " but the actual number was "
						+ actualNumberOfNodes,
				expectedNumberOfNodes, actualNumberOfNodes);
	}

	@Test
	public void testSystemNoJobs() {

		Integer numJobsExpected = 0;
		Integer totalMachineHoursExpected = 0;
		Double totalPaidByUsersExpected = 0.0;
		Integer averageWaitingTime = 0;
		Double averageTurnaroundTime = 0.0;
		Double economicBalance = 10000.0;

		Simulator sys = new Simulator();

		sys.initialize();

		Auxiliary.dumpQueueToExecution(sys);

		Integer actualNumJobsProcessed = 0;

		Integer actualTotalMachineHours = 0;

		Double actualTotalPaidByUsers = 0.0;

		Integer actualAverageWaitingTime = 0;

		Double actualAverageTurnaroundTime = sys.getAverageTurnAroundTime();

		Double actualEconomicBalance = (double) Math.round(sys.getEconomicBalance() * 100) / 100;

		for (int i = 0; i < sys.getQueueList().size(); i++) {

			actualNumJobsProcessed += sys.getQueueList().get(i).getNumJobsProcessed();

			actualTotalMachineHours += sys.getQueueList().get(i).getTotalMachineHours();

			actualTotalPaidByUsers += sys.getQueueList().get(i).getTotalPaidByUsers();

			if (!sys.getQueueList().get(i).getJobList().isEmpty())
				actualAverageWaitingTime += sys.getWaitingTimes()[i] / sys.getQueueList().get(i).getJobList().size();
			else
				actualAverageWaitingTime += 0;

		}

		Assert.assertEquals("The expected number of jobs processed is " + numJobsExpected
				+ " but the number of jobs processed was " + actualNumJobsProcessed, numJobsExpected,
				actualNumJobsProcessed);

		Assert.assertEquals(
				"The expected total machine hours consumed is " + totalMachineHoursExpected
						+ " but the actual number of machine hours consumed was " + actualTotalMachineHours,
				totalMachineHoursExpected, actualTotalMachineHours);

		Assert.assertEquals(
				"The expected total paid by users is " + totalPaidByUsersExpected
						+ " but the actual total paid by users was " + actualTotalPaidByUsers,
				totalPaidByUsersExpected, actualTotalPaidByUsers);

		Assert.assertEquals(
				"The expected average waiting time is " + averageWaitingTime
						+ " but the actual average waiting time was " + actualAverageWaitingTime,
				averageWaitingTime, actualAverageWaitingTime);

		Assert.assertEquals(
				"The expected average turnaround time is " + averageTurnaroundTime
						+ " but the actual average turnaround time was " + actualAverageTurnaroundTime,
				averageTurnaroundTime, actualAverageTurnaroundTime);

		Assert.assertEquals(
				"The expected economic balance of the centre is " + economicBalance
						+ " but the actual economic balance was " + actualEconomicBalance,
				economicBalance, actualEconomicBalance);

	}

	@Test
	public void testSystem2ShortJobs() {

		Integer numJobsExpected = 2;
		Integer totalMachineHoursExpected = 2;
		Double totalPaidByUsersExpected = 0.4;
		Integer averageWaitingTime = 0;
		Double averageTurnaroundTime = 1.0;
		Double economicBalance = 10000.38;

		Simulator sys = new Simulator();

		ITSupport u1 = new ITSupport("user1", UserType.Small);

		Job j1 = new Job(JobType.Short, 1, 1, 0);
		Job j2 = new Job(JobType.Short, 1, 1, 0);

		Request rq1 = new Request(j1, u1);
		Request rq2 = new Request(j2, u1);

		sys.initialize();

		sys.getQueueList().get(0).processRequest(sys, rq1);
		sys.getQueueList().get(0).processRequest(sys, rq2);

		Auxiliary.dumpQueueToExecution(sys);

		Integer actualNumJobsProcessed = 0;

		Integer actualTotalMachineHours = 0;

		Double actualTotalPaidByUsers = 0.0;

		Integer actualAverageWaitingTime = 0;

		Double actualAverageTurnaroundTime = sys.getAverageTurnAroundTime();

		Double actualEconomicBalance = (double) Math.round(sys.getEconomicBalance() * 100) / 100;

		for (int i = 0; i < sys.getQueueList().size(); i++) {

			actualNumJobsProcessed += sys.getQueueList().get(i).getNumJobsProcessed();

			actualTotalMachineHours += sys.getQueueList().get(i).getTotalMachineHours();

			actualTotalPaidByUsers += sys.getQueueList().get(i).getTotalPaidByUsers();

			if (!sys.getQueueList().get(i).getJobList().isEmpty())
				actualAverageWaitingTime += sys.getWaitingTimes()[i] / sys.getQueueList().get(i).getJobList().size();
			else
				actualAverageWaitingTime += 0;

		}

		Assert.assertEquals("The expected number of jobs processed is " + numJobsExpected
				+ " but the number of jobs processed was " + actualNumJobsProcessed, numJobsExpected,
				actualNumJobsProcessed);

		Assert.assertEquals(
				"The expected total machine hours consumed is " + totalMachineHoursExpected
						+ " but the actual number of machine hours consumed was "
						+ actualTotalMachineHours,
				totalMachineHoursExpected, actualTotalMachineHours);
		
		Assert.assertEquals(
				"The expected total paid by users is " + totalPaidByUsersExpected
						+ " but the actual total paid by users was " + actualTotalPaidByUsers,
				totalPaidByUsersExpected, actualTotalPaidByUsers);

		Assert.assertEquals(
				"The expected average waiting time is " + averageWaitingTime
						+ " but the actual average waiting time was " + actualAverageWaitingTime,
				averageWaitingTime, actualAverageWaitingTime);

		Assert.assertEquals(
				"The expected average turnaround time is " + averageTurnaroundTime
						+ " but the actual average turnaround time was " + actualAverageTurnaroundTime,
				averageTurnaroundTime, actualAverageTurnaroundTime);

		Assert.assertEquals(
				"The expected economic balance of the centre is " + economicBalance
						+ " but the actual economic balance was " + actualEconomicBalance,
				economicBalance, actualEconomicBalance);

	}

	@Test
	public void testSystem2MediumJobs() {

		Integer numJobsExpected = 2;
		Integer totalMachineHoursExpected = 197;
		Double totalPaidByUsersExpected = 4.4;
		Integer averageWaitingTime = 1;
		Double averageTurnaroundTime = 1.145;
		Double economicBalance = 10002.43;

		Simulator sys = new Simulator();

		ITSupport u1 = new ITSupport("user1", UserType.Medium);

		Job j1 = new Job(JobType.Medium, 37, 4, 25);
		Job j2 = new Job(JobType.Medium, 7, 7, 27);

		Request rq1 = new Request(j1, u1);
		Request rq2 = new Request(j2, u1);

		sys.initialize();

		sys.getQueueList().get(1).processRequest(sys, rq1);
		sys.getQueueList().get(1).processRequest(sys, rq2);

		Auxiliary.dumpQueueToExecution(sys);

		Integer actualNumJobsProcessed = 0;

		Integer actualTotalMachineHours = 0;

		Double actualTotalPaidByUsers = 0.0;

		Integer actualAverageWaitingTime = 0;

		Double actualAverageTurnaroundTime = sys.getAverageTurnAroundTime();

		Double actualEconomicBalance = (double) Math.round(sys.getEconomicBalance() * 100) / 100;

		for (int i = 0; i < sys.getQueueList().size(); i++) {

			actualNumJobsProcessed += sys.getQueueList().get(i).getNumJobsProcessed();

			actualTotalMachineHours += sys.getQueueList().get(i).getTotalMachineHours();

			actualTotalPaidByUsers += sys.getQueueList().get(i).getTotalPaidByUsers();

			if (!sys.getQueueList().get(i).getJobList().isEmpty())
				actualAverageWaitingTime += sys.getWaitingTimes()[i] / sys.getQueueList().get(i).getJobList().size();
			else
				actualAverageWaitingTime += 0;

		}

		Assert.assertEquals("The expected number of jobs processed is " + numJobsExpected
				+ " but the number of jobs processed was " + actualNumJobsProcessed, numJobsExpected,
				actualNumJobsProcessed);

		Assert.assertEquals(
				"The expected total machine hours consumed is " + totalMachineHoursExpected
						+ " but the actual number of machine hours consumed was " + actualTotalMachineHours,
				totalMachineHoursExpected, actualTotalMachineHours);

		Assert.assertEquals(
				"The expected total paid by users is " + totalPaidByUsersExpected
						+ " but the actual total paid by users was " + actualTotalPaidByUsers,
				totalPaidByUsersExpected, actualTotalPaidByUsers);

		Assert.assertEquals(
				"The expected average waiting time is " + averageWaitingTime
						+ " but the actual average waiting time was " + actualAverageWaitingTime,
				averageWaitingTime, actualAverageWaitingTime);

		Assert.assertEquals(
				"The expected average turnaround time is " + averageTurnaroundTime
						+ " but the actual average turnaround time was " + actualAverageTurnaroundTime,
				averageTurnaroundTime, actualAverageTurnaroundTime);

		Assert.assertEquals(
				"The expected economic balance of the centre is " + economicBalance
						+ " but the actual economic balance was " + actualEconomicBalance,
				economicBalance, actualEconomicBalance);

	}

	@Test
	public void testSystem2LargeJobs() {

		Integer numJobsExpected = 2;
		Integer totalMachineHoursExpected = 745;
		Double totalPaidByUsersExpected = 12.0;
		Integer averageWaitingTime = 3;
		Double averageTurnaroundTime = 1.7;
		Double economicBalance = 10004.55;

		Simulator sys = new Simulator();

		ITSupport u1 = new ITSupport("user1", UserType.Big);

		Job j1 = new Job(JobType.Large, 60, 10, 42);
		Job j2 = new Job(JobType.Large, 29, 5, 45);

		Request rq1 = new Request(j1, u1);
		Request rq2 = new Request(j2, u1);

		sys.initialize();

		sys.getQueueList().get(2).processRequest(sys, rq1);
		sys.getQueueList().get(2).processRequest(sys, rq2);

		Auxiliary.dumpQueueToExecution(sys);

		Integer actualNumJobsProcessed = 0;

		Integer actualTotalMachineHours = 0;

		Double actualTotalPaidByUsers = 0.0;

		Integer actualAverageWaitingTime = 0;

		Double actualAverageTurnaroundTime = sys.getAverageTurnAroundTime();

		Double actualEconomicBalance = (double) Math.round(sys.getEconomicBalance() * 100) / 100;

		for (int i = 0; i < sys.getQueueList().size(); i++) {

			actualNumJobsProcessed += sys.getQueueList().get(i).getNumJobsProcessed();

			actualTotalMachineHours += sys.getQueueList().get(i).getTotalMachineHours();

			actualTotalPaidByUsers += sys.getQueueList().get(i).getTotalPaidByUsers();

			if (!sys.getQueueList().get(i).getJobList().isEmpty())
				actualAverageWaitingTime += sys.getWaitingTimes()[i] / sys.getQueueList().get(i).getJobList().size();
			else
				actualAverageWaitingTime += 0;

		}

		Assert.assertEquals("The expected number of jobs processed is " + numJobsExpected
				+ " but the number of jobs processed was " + actualNumJobsProcessed, numJobsExpected,
				actualNumJobsProcessed);

		Assert.assertEquals(
				"The expected total machine hours consumed is " + totalMachineHoursExpected
						+ " but the actual number of machine hours consumed was " + actualTotalMachineHours,
				totalMachineHoursExpected, actualTotalMachineHours);

		Assert.assertEquals(
				"The expected total paid by users is " + totalPaidByUsersExpected
						+ " but the actual total paid by users was " + actualTotalPaidByUsers,
				totalPaidByUsersExpected, actualTotalPaidByUsers);

		Assert.assertEquals(
				"The expected average waiting time is " + averageWaitingTime
						+ " but the actual average waiting time was " + actualAverageWaitingTime,
				averageWaitingTime, actualAverageWaitingTime);

		Assert.assertEquals(
				"The expected average turnaround time is " + averageTurnaroundTime
						+ " but the actual average turnaround time was " + actualAverageTurnaroundTime,
				averageTurnaroundTime, actualAverageTurnaroundTime);

		Assert.assertEquals(
				"The expected economic balance of the centre is " + economicBalance
						+ " but the actual economic balance was " + actualEconomicBalance,
				economicBalance, actualEconomicBalance);

	}

	@Test
	public void testSystem2HugeJobs() {

		Integer numJobsExpected = 2;
		Integer totalMachineHoursExpected = 4757;
		Double totalPaidByUsersExpected = 64.0;
		Integer averageWaitingTime = 6;
		Double averageTurnaroundTime = 1.31;
		Double economicBalance = 10016.43;

		Simulator sys = new Simulator();

		ITSupport u1 = new ITSupport("user1", UserType.Big);
		ITSupport u2 = new ITSupport("user2", UserType.Big);

		Job j1 = new Job(JobType.Huge, 110, 19, 104);
		Job j2 = new Job(JobType.Huge, 127, 21, 110);

		Request rq1 = new Request(j1, u1);
		Request rq2 = new Request(j2, u2);

		sys.initialize();

		sys.getQueueList().get(3).processRequest(sys, rq1);
		sys.getQueueList().get(3).processRequest(sys, rq2);

		Auxiliary.dumpQueueToExecution(sys);

		Integer actualNumJobsProcessed = 0;

		Integer actualTotalMachineHours = 0;

		Double actualTotalPaidByUsers = 0.0;

		Integer actualAverageWaitingTime = 0;

		Double actualAverageTurnaroundTime = sys.getAverageTurnAroundTime();

		Double actualEconomicBalance = (double) Math.round(sys.getEconomicBalance() * 100) / 100;

		for (int i = 0; i < sys.getQueueList().size(); i++) {

			actualNumJobsProcessed += sys.getQueueList().get(i).getNumJobsProcessed();

			actualTotalMachineHours += sys.getQueueList().get(i).getTotalMachineHours();

			actualTotalPaidByUsers += sys.getQueueList().get(i).getTotalPaidByUsers();

			if (!sys.getQueueList().get(i).getJobList().isEmpty())
				actualAverageWaitingTime += sys.getWaitingTimes()[i] / sys.getQueueList().get(i).getJobList().size();
			else
				actualAverageWaitingTime += 0;

		}

		Assert.assertEquals("The expected number of jobs processed is " + numJobsExpected
				+ " but the number of jobs processed was " + actualNumJobsProcessed, numJobsExpected,
				actualNumJobsProcessed);

		Assert.assertEquals(
				"The expected total machine hours consumed is " + totalMachineHoursExpected
						+ " but the actual number of machine hours consumed was " + actualTotalMachineHours,
				totalMachineHoursExpected, actualTotalMachineHours);

		Assert.assertEquals(
				"The expected total paid by users is " + totalPaidByUsersExpected
						+ " but the actual total paid by users was " + actualTotalPaidByUsers,
				totalPaidByUsersExpected, actualTotalPaidByUsers);

		Assert.assertEquals(
				"The expected average waiting time is " + averageWaitingTime
						+ " but the actual average waiting time was " + actualAverageWaitingTime,
				averageWaitingTime, actualAverageWaitingTime);

		Assert.assertEquals(
				"The expected average turnaround time is " + averageTurnaroundTime
						+ " but the actual average turnaround time was " + actualAverageTurnaroundTime,
				averageTurnaroundTime, actualAverageTurnaroundTime);

		Assert.assertEquals(
				"The expected economic balance of the centre is " + economicBalance
						+ " but the actual economic balance was " + actualEconomicBalance,
				economicBalance, actualEconomicBalance);

	}

	@Test
	public void testSystem1JobOfEachType() {

		Integer numJobsExpected = 4;
		Integer totalMachineHoursExpected = 3115;
		Double totalPaidByUsersExpected = 62.6;
		Integer averageWaitingTime = 0;
		Double averageTurnaroundTime = 1.0;
		Double economicBalance = 10031.45;

		Simulator sys = new Simulator();

		ITSupport u1 = new ITSupport("user1", UserType.Small);
		ITSupport u2 = new ITSupport("user2", UserType.Medium);
		ITSupport u3 = new ITSupport("user3", UserType.Big);
		ITSupport u4 = new ITSupport("user4", UserType.Big);

		Job j1 = new Job(JobType.Short, 1, 1, 96);
		Job j2 = new Job(JobType.Medium, 9, 6, 15);
		Job j3 = new Job(JobType.Large, 46, 15, 37);
		Job j4 = new Job(JobType.Huge, 79, 30, 105);

		Request rq1 = new Request(j1, u1);
		Request rq2 = new Request(j2, u2);
		Request rq3 = new Request(j3, u3);
		Request rq4 = new Request(j4, u4);

		sys.initialize();

		sys.getQueueList().get(0).processRequest(sys, rq1);
		sys.getQueueList().get(1).processRequest(sys, rq2);
		sys.getQueueList().get(2).processRequest(sys, rq3);
		sys.getQueueList().get(3).processRequest(sys, rq4);

		Auxiliary.dumpQueueToExecution(sys);

		Integer actualNumJobsProcessed = 0;

		Integer actualTotalMachineHours = 0;

		Double actualTotalPaidByUsers = 0.0;

		Integer actualAverageWaitingTime = 0;

		Double actualAverageTurnaroundTime = sys.getAverageTurnAroundTime();

		Double actualEconomicBalance = (double) Math.round(sys.getEconomicBalance() * 100) / 100;

		for (int i = 0; i < sys.getQueueList().size(); i++) {

			actualNumJobsProcessed += sys.getQueueList().get(i).getNumJobsProcessed();

			actualTotalMachineHours += sys.getQueueList().get(i).getTotalMachineHours();

			actualTotalPaidByUsers += sys.getQueueList().get(i).getTotalPaidByUsers();

			if (!sys.getQueueList().get(i).getJobList().isEmpty())
				actualAverageWaitingTime += sys.getWaitingTimes()[i] / sys.getQueueList().get(i).getJobList().size();
			else
				actualAverageWaitingTime += 0;

		}

		Assert.assertEquals("The expected number of jobs processed is " + numJobsExpected
				+ " but the number of jobs processed was " + actualNumJobsProcessed, numJobsExpected,
				actualNumJobsProcessed);

		Assert.assertEquals(
				"The expected total machine hours consumed is " + totalMachineHoursExpected
						+ " but the actual number of machine hours consumed was " + actualTotalMachineHours,
				totalMachineHoursExpected, actualTotalMachineHours);

		Assert.assertEquals(
				"The expected total paid by users is " + totalPaidByUsersExpected
						+ " but the actual total paid by users was " + actualTotalPaidByUsers,
				totalPaidByUsersExpected, actualTotalPaidByUsers);

		Assert.assertEquals(
				"The expected average waiting time is " + averageWaitingTime
						+ " but the actual average waiting time was " + actualAverageWaitingTime,
				averageWaitingTime, actualAverageWaitingTime);

		Assert.assertEquals(
				"The expected average turnaround time is " + averageTurnaroundTime
						+ " but the actual average turnaround time was " + actualAverageTurnaroundTime,
				averageTurnaroundTime, actualAverageTurnaroundTime);

		Assert.assertEquals(
				"The expected economic balance of the centre is " + economicBalance
						+ " but the actual economic balance was " + actualEconomicBalance,
				economicBalance, actualEconomicBalance);

	}

}
