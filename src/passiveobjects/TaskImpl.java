/**
 * 
 */
package passiveobjects;

import java.util.List;

import acitiveobjects.Manager;
import acitiveobjects.Worker;

/**
 * @author lxmonk
 * 
 */
public class TaskImpl implements Task {

	ManagerSpecializtion managerSpecialization;
	WorkerSpecialty workerSpecialty;
	Manager manager;
	long size;
	List<Resource> resources;
	List<Worker> workers;
	
	//FIXME stub. implementation by Tom..
	public TaskImpl(){}
	
	@Override
	public WorkerSpecialty getWorkerSpecialty() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> getAllWorkerNames() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getHoursDone() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getHoursStillNeeded() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Manager getManager() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getManagerName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ManagerSpecializtion getManagerSpecializtion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Resource> getNeededResources() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Worker> getWorkers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void taskIsDone() {
		// TODO Auto-generated method stub
		
	}

}
