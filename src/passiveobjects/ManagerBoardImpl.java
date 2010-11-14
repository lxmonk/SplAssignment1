/**
 * 
 */
package passiveobjects;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxmonk
 * 
 */
public class ManagerBoardImpl implements ManagerBoard {

	final Map<ManagerSpecializtion, ProjectBox> projectsMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveObjects.ManagerBoard#getPendingProjects()
	 */
	@Override
	public Map<ManagerSpecializtion, ProjectBox> getPendingProjects() {
		return new HashMap<ManagerSpecializtion, ProjectBox>(projectsMap);
	}

	/*
	 * private Map deepCopy(Map<ManagerSpecializtion, ProjectBox> map) {
	 * Map<ManagerSpecializtion, ProjectBox> ret = new
	 * HashMap<ManagerSpecializtion, ProjectBox>(); ret.putAll(map);
	 * 
	 * return ret; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * passiveObjects.ManagerBoard#getProjectBox(passiveObjects.ManagerSpecializtion
	 * )
	 */
	@Override
	public ProjectBox getProjectBox(ManagerSpecializtion specializtion) {
		return this.projectsMap.get(specializtion);
	}

	public void createProjectsMap(Map<ManagerSpecializtion, ProjectBox> map) {
		// TODO implement!
	}

}
