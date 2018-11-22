package simulator.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CurriculumTest.class, GroupTest.class, InputTest.class, ITSupportTest.class, JobTest.class,
		NodeTest.class, QueueTest.class, RequestTest.class, ResearcherTest.class,
		StudentTest.class, SystemTest.class,
		UserTest.class })
public class AllTests {

	
	@BeforeClass
    public static void setUp()
    {
		System.out.println("-------- NEXT LINES ARE RELATED TO TESTS -------");
    }

    @AfterClass
    public static void tearDown()
    {
		System.out.println("-------- END OF TESTS ---------");
    }

}
