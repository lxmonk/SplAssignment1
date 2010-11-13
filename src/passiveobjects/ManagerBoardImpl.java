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
	public Map<ManagerSpecializtion, ProjectBox> getPendingProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.ManagerBoard#getProjectBox(passiveObjects.ManagerSpecializtion)
	 */
	@Override
	public ProjectBox getProjectBox(ManagerSpecializtion specializtion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void createProjectsMap(Map<ManagerSpecializtion, ProjectBox> map){
		//TODO implement!
	}

}
