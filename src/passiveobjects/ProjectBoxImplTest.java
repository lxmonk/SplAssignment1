/**
 * 
 */
package passiveobjects;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lxmonk
 * 
 */
public class ProjectBoxImplTest {

	ProjectBoxImpl projectBox;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String randStr = UUID.randomUUID().toString();
		ManagerSpecialization managerSpecialization = new ManagerSpecialization(
				randStr);
		this.projectBox = new ProjectBoxImpl(managerSpecialization);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link passiveobjects.ProjectBoxImpl#addProject(passiveobjects.Project)}.
	 */
	@Test
	public void testAddProject() {
		String randStr = UUID.randomUUID().toString();
		String randStr2 = UUID.randomUUID().toString();
		ManagerSpecialization managerSpecialization = projectBox
				.getManagerSpecializtion();
		List<Task> taskList = new ArrayList<Task>();
		Task task = new TaskImpl(randStr + "T", managerSpecialization,
				new WorkerSpecialty(randStr), (int) Math
						.round(Math.random() * 100) + 1, null);
		taskList.add(task);
		Project project = new ProjectImpl(randStr2, taskList);
		// if the box is empty the thread will wait - untestable.
		projectBox.addProject(project);
		assertEquals(project, projectBox.getProject());

		ManagerSpecialization falseManagerSpecializtion = new ManagerSpecialization(
				randStr2 + "33");
		Task wrongTask = new TaskImpl(randStr2 + "AAA",
				falseManagerSpecializtion, new WorkerSpecialty(randStr),
				(int) Math.round(Math.random() * 100) + 1, null);
		taskList = new ArrayList<Task>();
		taskList.add(wrongTask);
		Project unrelatedProject = new ProjectImpl(randStr2, taskList);
		try {
			projectBox.addProject(unrelatedProject);
		} catch (RuntimeException e) {
			return; // test is successful
		}
		fail("an unrelated project was successfully added to the ProjectBox");

	}

	/**
	 * Test method for {@link passiveobjects.ProjectBoxImpl#getProject()}.
	 */
	@Test
	public void testGetProject() {
		String randStr = UUID.randomUUID().toString();
		String randStr2 = UUID.randomUUID().toString();
		ManagerSpecialization managerSpecialization = projectBox
				.getManagerSpecializtion();
		List<Task> taskList = new ArrayList<Task>();
		Task task = new TaskImpl(managerSpecialization, new WorkerSpecialty(
				randStr), (int) Math.round(Math.random() * 100) + 1, null);
		taskList.add(task);
		Project project = new ProjectImpl(randStr2, taskList);
		projectBox.addProject(project);
		// if the box is empty the thread will wait - untestable.
		assertEquals(project, projectBox.getProject());
	}

	/**
	 * Test method for
	 * {@link passiveobjects.ProjectBoxImpl#removeProject(passiveobjects.Project)}
	 * .
	 */
	@Test
	public void testRemoveProject() {
		String randStr = UUID.randomUUID().toString();
		String randStr2 = UUID.randomUUID().toString();
		ManagerSpecialization managerSpecialization = projectBox
				.getManagerSpecializtion();
		List<Task> taskList = new ArrayList<Task>();
		Task task = new TaskImpl(managerSpecialization, new WorkerSpecialty(
				randStr), (int) Math.round(Math.random() * 100) + 1, null);
		taskList.add(task);
		Project project = new ProjectImpl(randStr2, taskList);
		Project project2 = new ProjectImpl(randStr + "29", taskList);

		projectBox.addProject(project);
		projectBox.addProject(project2);
		assertEquals(project, projectBox.getProject());
		projectBox.removeProject(project);
		assertEquals(project2, projectBox.getProject());
	}

	/**
	 * Test method for
	 * {@link passiveobjects.ProjectBoxImpl#getManagerSpecializtion(passiveobjects.Project)}
	 */
	public void testGetManagerSpecializtion() {
		// simple queries are not tested.

	}
}
