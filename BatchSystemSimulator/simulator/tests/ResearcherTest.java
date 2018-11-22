package simulator.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import simulator.enumerators.UserType;
import simulator.exceptions.InvalidParametersException;
import simulator.source.Group;
import simulator.source.Researcher;

public class ResearcherTest extends TestCase {

	public ResearcherTest() {
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
	public void testResearcherConstructorEmptyName() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			Group g = new Group("group1", 1000.0);
			new Researcher("", g, 25.0, UserType.Small);
		});

	}

	@Test
	public void testResearcherConstructorNullGroup() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			Group g = null;
			new Researcher("user1", g, 25.0, UserType.Small);
		});

	}

	@Test
	public void testResearcherConstructorNegativeAdditionalBudget() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			Group g = new Group("group1", 1000.0);
			new Researcher("user1", g, -1.0, UserType.Small);
		});

	}

}
