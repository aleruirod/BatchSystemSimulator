package simulator.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import simulator.enumerators.NodeType;
import simulator.exceptions.InvalidParametersException;
import simulator.source.Node;

public class NodeTest extends TestCase {

	public NodeTest() {
		super();
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Override
	@Before
	public void setUp() throws Exception {
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNodeConstructorNotEnoughCores() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			new Node(NodeType.Traditional, 15);
		});

	}
}
