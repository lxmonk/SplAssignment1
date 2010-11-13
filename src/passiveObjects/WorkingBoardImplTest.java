/**
 * 
 */
package passiveObjects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	 * Test method for {@link passiveObjects.WorkingBoardImpl#getTaskBySpecialty(passiveObjects.WorkerSpecialty)}.
	 */
	@Test
	public void testGetTaskBySpecialty() {
		//Task task = new TaskImpl();
		//workingBoard.postTask(task);
		//assertEquals(task, workingBoard.getTaskBySpecialty(task.GetSpecialty))
		
	}

	/**
	 * Test method for {@link passiveObjects.WorkingBoardImpl#postTask(passiveObjects.Task)}.
	 */
	@Test
	public void testPostTask() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link passiveObjects.WorkingBoardImpl#removeTask(passiveObjects.Task)}.
	 */
	@Test
	public void testRemoveTask() {
		fail("Not yet implemented"); // TODO
	}

}
