package simulator.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import simulator.exceptions.InvalidParametersException;
import simulator.source.Group;

public class GroupTest extends TestCase {

	public GroupTest() {
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
	public void testGroupConstructorEmptyName() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new Group("", 10000.0);
		});

	}

	@Test
	public void testGroupConstructorNegativeBudget() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new Group("Curriculum1", -0.1);
		});

	}
}
