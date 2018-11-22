package simulator.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import simulator.enumerators.JobType;
import simulator.exceptions.InvalidParametersException;
import simulator.source.Job;

public class JobTest extends TestCase {

	public JobTest() {
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
	public void testJobConstructorIncorrectNumberOfNodes1() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new Job(JobType.Short, 0, 1, 0);
		});

	}

	@Test
	public void testJobConstructorIncorrectNumberOfNodes2() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new Job(JobType.Short, 129, 1, 0);
		});

	}

	@Test
	public void testJobConstructorIncorrectNumberOfHours() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new Job(JobType.Short, 1, 0, 0);
		});

	}

	@Test
	public void testJobConstructorIncorrectExpectedStartTime() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new Job(JobType.Short, 1, 1, -1);
		});

	}
}
