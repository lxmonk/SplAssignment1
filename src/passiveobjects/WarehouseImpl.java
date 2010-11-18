/**
 * 
 */
package passiveobjects;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resources;

/**
 * @author lxmonk
 *
 */
public class WarehouseImpl implements Warehouse {

	Map<String,Resource> map;
	
	/**
	 * the constructor for the {@link Warehouse} database 
	 */
	public WarehouseImpl() {
		this.map=new ConcurrentHashMap<String, Resource>();
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
	public synchronized void returnResources(List<Resource> resourceList) {
		for (Resource aResource : resourceList) {
			this.map.get(aResource.getName()).incAmount();
		}
	}
	

	/* (non-Javadoc)
	 * @see passiveObjects.Warehouse#takeResources(java.util.List)
	 */
	@Override
	public synchronized boolean getResources(Task task)  throws InterruptedException {		
		while(!this.resourcesAvailable(task.getNeededResources()) && (!task.isComplete()) && (!task.isAborted())) // didn't find resources && task not done
			try {
				this.wait(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		if (!task.isComplete() && !task.isAborted()) {
			for (Resource resource : task.getNeededResources())
				this.map.get(resource.getName()).decAmount();
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
