/**
 * 
 */
package passiveobjects;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lxmonk
 * 
 */
public class ManagerBoardTest {

	ManagerBoard managerBoard;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		managerBoard = new ManagerBoardImpl();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link passiveobjects.ManagerBoard#getPendingProjects()}.
	 */
	@Test
	public void testGetPendingProjects() {
		// simple Queries are never tested.
	}

	/**
	 * Test method for
	 * {@link passiveobjects.ManagerBoard#getProjectBox(passiveobjects.ManagerSpecializtion)}
	 * . Implemented in ManagerBoardImplTest
	 */
	@Test
	public void testGetProjectBox() {
//		String projectName = UUID.randomUUID().toString();
//		Map<ManagerSpecializtion, ProjectBox> map = new HashMap<ManagerSpecializtion, ProjectBox>();
		
//		managerBoard.createProjectsMap(map);
		
//		fail("Not yet implemented"); // TODO
	}

}
