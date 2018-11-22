package simulator.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import simulator.exceptions.InvalidParametersException;
import simulator.source.Input;

public class InputTest extends TestCase {

	public InputTest() {
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
	public void testInputNegativeUserNumber() {

		Integer usrNum = -1;

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			Input.checkNumberOfUSers(usrNum);
		});

	}

	@Test
	public void testInputNegativeBudgets() {

		Double budgets = -0.1;

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			Input.checkBudgets(budgets);
		});

	}

}