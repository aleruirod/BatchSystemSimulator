package simulator.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import simulator.enumerators.QueueType;
import simulator.exceptions.InvalidParametersException;
import simulator.source.Queue;

public class QueueTest extends TestCase {

	public QueueTest() {
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
	public void testQueueConstructorIncorrectAssociatedCost() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new Queue(QueueType.Short, -1.0, 1.0);
		});

	}

	@Test
	public void testQueueConstructorIncorrectOperationalCost() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new Queue(QueueType.Short, 1.0, -1.0);
		});

	}

}
