/**
 * 
 */
package passiveobjects;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import acitiveobjects.Manager;

/**
 * @author lxmonk
 * 
 */
public class WorkingBoardImplTest {
	WorkingBoardImpl workingBoard;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		workingBoard = new WorkingBoardImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link passiveObjects.WorkingBoardImpl#getTaskBySpecialty(passiveObjects.WorkerSpecialty)}
	 * .
	 */
	@Test
	public void testGetTaskBySpecialty() {
		String randStr = UUID.randomUUID().toString();
		String randStr2 = UUID.randomUUID().toString();
		WorkerSpecialty workerSpecialty = new WorkerSpecialty(randStr);
		WorkerSpecialty falseWorkerSpecialty = new WorkerSpecialty(randStr
				+ "2");
		ManagerSpecialization managerSpecialization = new ManagerSpecialization(
				randStr2);
		int size = (int) Math.round(Math.random() * 100) + 1;
		List<Resource> resources = null;
		Task task = new TaskImpl(randStr+"aaa", managerSpecialization, workerSpecialty, size,
				resources);
		workingBoard.postTask(task);
		assertEquals(task, workingBoard.getTaskBySpecialty(workerSpecialty));
		assertEquals(null, workingBoard
				.getTaskBySpecialty(falseWorkerSpecialty));

	}

	/**
	 * Test method for
	 * {@link passiveObjects.WorkingBoardImpl#postTask(passiveObjects.Task)}.
	 */
	@Test
	public void testPostTask() {
		String randStr = UUID.randomUUID().toString();
		String randStr2 = UUID.randomUUID().toString();
		WorkerSpecialty workerSpecialty = new WorkerSpecialty(randStr);
		ManagerSpecialization managerSpecialization = new ManagerSpecialization(
				randStr2);
		int size = (int) Math.round(Math.random() * 100) + 1;
		List<Resource> resources = null;
		Task task = new TaskImpl(randStr2+"jj", managerSpecialization, workerSpecialty, size,
				resources);
		workingBoard.postTask(task);
		assertEquals(task, workingBoard.findTask(task));
		Task falseTask = new TaskImpl(randStr2 ,managerSpecialization, workerSpecialty, size,
				resources);
		assertEquals(null, workingBoard.findTask(falseTask));
	}

	/**
	 * Test method for
	 * {@link passiveObjects.WorkingBoardImpl#removeTask(passiveObjects.Task)}.
	 */
	@Test
	public void testRemoveTask() {
		String randStr = UUID.randomUUID().toString();
		String randStr2 = UUID.randomUUID().toString();
		WorkerSpecialty workerSpecialty = new WorkerSpecialty(randStr);
		ManagerSpecialization managerSpecialization = new ManagerSpecialization(
				randStr2);
		int size = (int) Math.round(Math.random() * 100) + 1;
		List<Resource> resources = null;
		Task task = new TaskImpl(randStr+"999", managerSpecialization, workerSpecialty, size,
				resources);
		workingBoard.postTask(task);
		assertEquals(task, workingBoard.findTask(task));
		workingBoard.removeTask(task);
		assertEquals(null, workingBoard.findTask(task));
	}

}
