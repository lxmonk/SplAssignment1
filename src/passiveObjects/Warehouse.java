/**
 * 
 */
package passiveObjects;

import java.util.List;

/**
 * @author lxmonk
 * 
 */
public interface Warehouse {

	/**
	 * Check whether the resources in resourceList are ALL available.
	 * 
	 * @pre none.
	 * @post none.
	 * @param resourceList
	 *            list of resources needed.
	 * @return a boolean stating that all the resources are available.
	 */
	public boolean resourcesAvailable(List<Resource> resourceList);

	/**
	 * Takes resources from the warehouse.
	 * 
	 * @pre all the resources in resourceList are available.
	 * @post the taken resources are decreased from the inventory in the
	 *       warehouse.
	 * @param resourceList
	 *            list of resources needed.
	 */
	public void takeResources(List<Resource> resourceList);

	/**
	 * @post the resources in resourceList are added back to the inventory in
	 *       the warehouse.
	 * @param resourceList
	 *            list of resources to be returned.
	 */
	public void returnResources(List<Resource> resourceList);

	/**
	 * adds a new resource to the warehouse. This method is used for
	 * initialization only.
	 * 
	 * @pre none
	 * @post the resources in resourceList are added to the inventory in the
	 *       warehouse, in the amount stated.
	 * 
	 * @param resource
	 *            the resource
	 */
	public void addResource(Resource resource);

	/**
	 * return the amount of the stated {@link Resource} in the {@link Warehouse}
	 * 
	 * 
	 * @param resource
	 *            resource
	 * @return the amount of the stated {@link Resource} in the
	 *         {@link Warehouse}.
	 */
	public int queryResource(Resource resource);

}
