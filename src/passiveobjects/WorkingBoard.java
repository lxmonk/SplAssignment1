package passiveobjects;

public interface WorkingBoard {
	
	/**
	 * Adds a new task to the WorkingBoard
	 * @pre none.
	 * @post the task is added to the WorkingBoard
	 * @param task to be added to the WorkingBoard
	 */
	public void postTask(Task task);
	
	/**
	 * return a task by the stated specialty
	 * @pre none.
	 * @post none.
	 * @param specialty of the wanted task
	 * @return a Task of the wanted specialty (if exists)
	 */
	public Task getTaskBySpecialty(WorkerSpecialty specialty);
	
	/**
	 * removes a Task from the WorkingBoard
	 * @pre the task exists in the WorkingBoard
	 * @post the Task is removed from the WorkingBoard
	 * @param task to be removed
	 */
	public void removeTask(Task task);
	
	/**
	 * finds the stated Task on the WorkingBoard, and returns it
	 * @pre none.
	 * @post none.
	 * @param task The task we want to find
	 * @return The task, if found, or null if not found.
	 */
	public Task findTask(Task task);
	
}
