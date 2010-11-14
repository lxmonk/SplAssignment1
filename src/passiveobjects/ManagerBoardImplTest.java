package passiveobjects;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ManagerBoardImplTest {
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
	 * Test method for
	 * {@link passiveobjects.ManagerBoardImpl#getPendingProjects()}.
	 */
	@Test
	public void testGetPendingProjects() {
		// simple Queries are not tested.
	}

	/**
	 * Test method for
	 * {@link passiveobjects.ManagerBoardImpl#getProjectBox(passiveobjects.ManagerSpecialization)}
	 * .
	 */
	@Test
	public void testGetProjectBox() { 
		String randStr = UUID.randomUUID().toString();
		ManagerSpecialization managerSpecialization = new ManagerSpecialization(
				randStr);
		ProjectBoxImpl projectBoxImpl = new ProjectBoxImpl(managerSpecialization);
		Map<ManagerSpecialization, ProjectBox> map = new ConcurrentHashMap<ManagerSpecialization, ProjectBox>();
		map.put(managerSpecialization, projectBoxImpl);
		((ManagerBoardImpl) this.managerBoard).createProjectsMap(map);
		assertEquals(projectBoxImpl, managerBoard
				.getProjectBox(managerSpecialization));
		ManagerSpecialization falseManagerSpecializtion = new ManagerSpecialization(
				randStr + "33");
		assertEquals(null, managerBoard
				.getProjectBox(falseManagerSpecializtion));

	}
}
