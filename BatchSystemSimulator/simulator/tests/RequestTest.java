package simulator.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import simulator.enumerators.JobType;
import simulator.enumerators.UserType;
import simulator.exceptions.InvalidParametersException;
import simulator.source.Job;
import simulator.source.Request;
import simulator.source.User;

public class RequestTest extends TestCase {

	public RequestTest() {
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
	public void testRequestConstructorNullAssociatedJobt() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			Job j = null;
			User u = new User("username1", UserType.Small);
			new Request(j, u);
		});

	}

	@Test
	public void testRequestConstructorNullRequester() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			Job j = new Job(JobType.Short, 1, 1, 0);
			User u = null;
			new Request(j, u);
		});

	}

}
