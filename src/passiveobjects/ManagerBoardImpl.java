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

	Map<ManagerSpecialization, ProjectBox> map;

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveObjects.ManagerBoard#getPendingProjects()
	 */
	@Override
	public Map<ManagerSpecialization, ProjectBox> getPendingProjects() {
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
		return (ProjectBoxImpl) this.map.get(specialization);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveobjects.ManagerBoard#createProjectsMap(java.util.Map)
	 */
	@Override
	public void createProjectsMap(Map<ManagerSpecialization, ProjectBox> initMap) {
		this.map = new ConcurrentHashMap<ManagerSpecialization, ProjectBox>(initMap);

	}

}
