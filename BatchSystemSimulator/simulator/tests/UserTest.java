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

public class UserTest extends TestCase {

	public UserTest() {
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
	public void testUserConstructorEmptyName() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new User("", UserType.Small);
		});

	}

	@Test
	public void testUserRequestCreation() {

		// SMALL USER CASE

		JobType expectedJobType1 = JobType.Short;

		User user1 = new User("user1", UserType.Small);

		Request q1 = user1.createRequest(0);

		Job j1 = q1.getAssociatedJob();

		JobType type1 = j1.getType();

		Assertions.assertNotNull(j1, "The job associated to the user's created request is null.");

		Assertions.assertEquals(expectedJobType1, type1);

		// MEDIUM USER CASE

		JobType expectedJobType2 = JobType.Medium;

		User user2 = new User("user2", UserType.Medium);

		Request q2 = user2.createRequest(0);

		Job j2 = q2.getAssociatedJob();

		JobType type2 = j2.getType();

		Assertions.assertNotNull(j2, "The job associated to the user's created request is null.");

		Assertions.assertEquals(expectedJobType2, type2);

		// BIG USER CASE

		User user3 = new User("user3", UserType.Big);

		Request q3 = user3.createRequest(0);

		Job j3 = q3.getAssociatedJob();

		JobType type3 = j3.getType();

		Assertions.assertNotNull(j3, "The job associated to the user's created request is null.");

		Assertions.assertNotEquals(expectedJobType1, type3);
		Assertions.assertNotEquals(expectedJobType2, type3);


	}

}
