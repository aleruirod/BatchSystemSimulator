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
import simulator.source.ITSupport;

public class ITSupportTest extends TestCase {

	public ITSupportTest() {
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
	public void testITSupportConstructorEmptyName() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new ITSupport("", UserType.Small);
		});

	}

}
