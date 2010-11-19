/**
 * 
 */
package passiveobjects;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lxmonk
 * 
 */
public class ManagerBoardImpl implements ManagerBoard {

	Map<String, ProjectBox> map;

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveObjects.ManagerBoard#getPendingProjects()
	 */
	@Override
	public Map<String, ProjectBox> getPendingProjects() {
		return this.map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * passiveObjects.ManagerBoard#getProjectBox(passiveObjects.ManagerSpecializtion
	 * )
	 */
	@Override
	public ProjectBoxImpl getProjectBox(ManagerSpecialization specialization) {
		return (ProjectBoxImpl) this.map.get(specialization.getSpecialization());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveobjects.ManagerBoard#createProjectsMap(java.util.Map)
	 */
	@Override
	public void createProjectsMap(Map<String, ProjectBox> initMap) {
		this.map = new ConcurrentHashMap<String, ProjectBox>(initMap);
	}

}
