/**
 * 
 */
package passiveobjects;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lxmonk
 * 
 */
public class WorkingBoardImplTest {
	WorkingBoardImpl workingBoard;
	int CONST = 100;

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
	 * @throws InterruptedException 
	 */
	@Test
	public void testGetTaskBySpecialty() throws InterruptedException {
		String randStr = UUID.randomUUID().toString();
		String randStr2 = UUID.randomUUID().toString();
		WorkerSpecialty workerSpecialty = new WorkerSpecialty(randStr);
		WorkerSpecialty falseWorkerSpecialty = new WorkerSpecialty(randStr
				+ "2");
		ManagerSpecialization managerSpecialization = new ManagerSpecialization(
				randStr2);
		int size = (int) Math.round(Math.random() * this.CONST) + 1;
		List<Resource> resources = null;
		Task task = new TaskImpl(randStr+"aaa", managerSpecialization, workerSpecialty, size,
				resources);
		this.workingBoard.postTask(task, null);
		assertEquals(task, this.workingBoard.getTaskBySpecialty(workerSpecialty));
		assertEquals(null, this.workingBoard
				.getTaskBySpecialty(falseWorkerSpecialty));

	}

	/**
	 * Test method for
	 * {@link passiveObjects.WorkingBoardImpl#postTask(passiveObjects.Task)}.
	 * @throws InterruptedException 
	 */
	@Test
	public void testPostTask() throws InterruptedException {
		String randStr = UUID.randomUUID().toString();
		String randStr2 = UUID.randomUUID().toString();
		WorkerSpecialty workerSpecialty = new WorkerSpecialty(randStr);
		ManagerSpecialization managerSpecialization = new ManagerSpecialization(
				randStr2);
		int size = (int) Math.round(Math.random() * 100) + 1;
		List<Resource> resources = null;
		Task task = new TaskImpl(randStr2+"jj", managerSpecialization, workerSpecialty, size,
				resources);
		workingBoard.postTask(task,null);
		assertEquals(true, workingBoard.findTask(task));
		Task falseTask = new TaskImpl(randStr2 ,managerSpecialization, workerSpecialty, size,
				resources);
		assertEquals(false, workingBoard.findTask(falseTask));
	}

	/**
	 * Test method for
	 * {@link passiveObjects.WorkingBoardImpl#removeTask(passiveObjects.Task)}.
	 * @throws InterruptedException 
	 */
	@Test
	public void testRemoveTask() throws InterruptedException {
		String randStr = UUID.randomUUID().toString();
		String randStr2 = UUID.randomUUID().toString();
		WorkerSpecialty workerSpecialty = new WorkerSpecialty(randStr);
		ManagerSpecialization managerSpecialization = new ManagerSpecialization(
				randStr2);
		int size = (int) Math.round(Math.random() * 100) + 1;
		List<Resource> resources = null;
		Task task = new TaskImpl(randStr+"999", managerSpecialization, workerSpecialty, size,
				resources);
		workingBoard.postTask(task,null);
		assertEquals(task, workingBoard.findTask(task));
		workingBoard.removeTask(task);
		assertEquals(null, workingBoard.findTask(task));
	}

}
