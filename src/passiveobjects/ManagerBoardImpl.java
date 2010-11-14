/**
 * 
 */
package passiveobjects;

import java.util.Map;

/**
 * @author lxmonk
 *
 */
public class ManagerBoardImpl implements ManagerBoard {

	/* (non-Javadoc)
	 * @see passiveObjects.ManagerBoard#getPendingProjects()
	 */
	@Override
	public Map<ManagerSpecializtion, ProjectBoxImpl> getPendingProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.ManagerBoard#getProjectBox(passiveObjects.ManagerSpecializtion)
	 */
	@Override
	public ProjectBoxImpl getProjectBox(ManagerSpecializtion specializtion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void createProjectsMap(Map<ManagerSpecializtion, ProjectBoxImpl> map){
		//TODO implement!
	}

}
