package passiveobjects;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
		// simple Queries are never tested.
	}

	/**
	 * Test method for
	 * {@link passiveobjects.ManagerBoardImpl#getProjectBox(passiveobjects.ManagerSpecializtion)}
	 * .
	 */
	@Test
	public void testGetProjectBox() {// TODO: finish Task creation.
		String projectName = "pName", manSpec = "specialization";
		ManagerSpecializtion managerSpecializtion = new ManagerSpecializtion(
				manSpec);
		LinkedList<Task> taskList = new LinkedList<Task>();
		taskList.add(new TaskImpl());
		Project project = new ProjectImpl(projectName, taskList);
		ProjectBox projectBox = new ProjectBox(managerSpecializtion);
		projectBox.addProject(project);
		Map<ManagerSpecializtion, ProjectBox> map = new HashMap<ManagerSpecializtion, ProjectBox>();
		map.put(managerSpecializtion, projectBox);
		((ManagerBoardImpl) managerBoard).createProjectsMap(map);
		assertEquals(projectBox, managerBoard.getProjectBox(managerSpecializtion));
	}
}
