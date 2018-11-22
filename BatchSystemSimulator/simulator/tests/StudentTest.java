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
import simulator.source.Curriculum;
import simulator.source.Student;

public class StudentTest extends TestCase {

	public StudentTest() {
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
	public void testStudentConstructorEmptyName() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			Curriculum c = new Curriculum("group1", 1000.0);
			new Student("", c, UserType.Small);
		});

	}

	@Test
	public void testStudentConstructorNullCurriculum() {

		Assertions.assertThrows(InvalidParametersException.class, () -> {
			Curriculum c = null;
			new Student("user1", c, UserType.Small);
		});

	}

}
