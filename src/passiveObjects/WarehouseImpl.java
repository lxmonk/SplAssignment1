/**
 * 
 */
package passiveObjects;

import java.util.List;

/**
 * @author lxmonk
 *
 */
public class WarehouseImpl implements Warehouse {

	/* (non-Javadoc)
	 * @see passiveObjects.Warehouse#addResource(passiveObjects.Resource, int)
	 */
	@Override
	public void addResource(Resource resource, int amount) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see passiveObjects.Warehouse#queryResource(passiveObjects.Resource)
	 */
	@Override
	public int queryResource(Resource resource) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Warehouse#resourcesAvailable(java.util.List)
	 */
	@Override
	public boolean resourcesAvailable(List<Resource> resourceList) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Warehouse#returnResources(java.util.List)
	 */
	@Override
	public void returnResources(List<Resource> resourceList) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see passiveObjects.Warehouse#takeResources(java.util.List)
	 */
	@Override
	public void takeResources(List<Resource> resourceList) {
		// TODO Auto-generated method stub

	}

}
