/**
 * 
 */
package passiveobjects;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * @author lxmonk
 *
 */
public class WarehouseImpl implements Warehouse {

	static final int SECOND_15 = 15000;
	
	Map<String,Resource> map;
	Logger logger;
	
	/**
	 * the constructor for the {@link Warehouse} database 
	 * @param aLogger a logger
	 */
	public WarehouseImpl(Logger aLogger) {
		this.map=new ConcurrentHashMap<String, Resource>();
		this.logger=aLogger;
	}
	
	/* (non-Javadoc)
	 * @see passiveObjects.Warehouse#queryResource(passiveObjects.Resource)
	 */
	@Override
	public int queryResource(Resource aResource) {
		Resource resource = this.map.get(aResource.getName());
		if (resource == null) 
			return 0;
		else
			return resource.getAmount();
		

	}

	/* (non-Javadoc)
	 * @see passiveObjects.Warehouse#resourcesAvailable(java.util.List)
	 */
	@Override
	public synchronized boolean resourcesAvailable(List<Resource> resourceList) {
			for (Resource aResource : resourceList) {
				Resource temp=this.map.get(aResource.getName());
				if ((temp == null) || (temp.getAmount() <= 0))
					return false;
			}
		return true;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Warehouse#returnResources(java.util.List)
	 */
	@Override
	public synchronized void returnResources(List<Resource> resourceList,String workerName) {
		for (Resource aResource : resourceList) {
			this.map.get(aResource.getName()).incAmount();
			this.logger.info(workerName + " relesed "+ aResource.getName()+" at "+ Helpers.staticTimeNow());

		}
		this.notifyAll();
	}
	

	/* (non-Javadoc)
	 * @see passiveObjects.Warehouse#takeResources(java.util.List)
	 */
	@Override
	public synchronized boolean getResources(Task task,String workerName)  throws InterruptedException {		
		while(!this.resourcesAvailable(task.getNeededResources()) && (!task.isComplete()) && (!task.isAborted())) // didn't find resources && task not done
			try {
				this.wait(WarehouseImpl.SECOND_15); // the worker will always wake up after 15 seconds
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		if (!task.isComplete() && !task.isAborted()) {
			for (Resource resource : task.getNeededResources()) {
				this.map.get(resource.getName()).decAmount();
				this.logger.info(workerName + " acquired "+ resource.getName()+" at "+ Helpers.staticTimeNow());
			}
			return true;
		} else
			return false;
	}

	@Override
	public synchronized void addResource(Resource resource) {
		if (this.map.containsKey(resource.getName()))
				this.map.get(resource.getName()).incAmount();
		else
			this.map.put(resource.getName(), resource);
	}


}
