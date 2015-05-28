package businesslogic.coursebl;

import po.coursepo.CoursePO;
import vo.coursevo.CourseListItemVO;
import businesslogic.managerbl.Faculty;
import businesslogic.planbl.Plan;

/**
 * Transform 用于ui层与bl层交互时CoursePo和CourseListItemVO对象的转换
 * @author vlery
 *
 */
public class Transform {

	public static CourseListItemVO POToListItemVO(CoursePO cp) {
		return new CourseListItemVO(cp.getName(), cp.getID(), cp.getTeacherID(),
				cp.getPlace(), cp.getCampus(), cp.getGrade(), cp.getModule(),
				cp.getCredit(), cp.getPeriod(), cp.getLimit(), cp.getTime(),
				cp.getRequire(), cp.getInfo(),new Plan().getFacultyName(cp.getFacultyID()));
	}

	public static CoursePO listItemVOToPO(CourseListItemVO cpv) {
		String facultyID = new Plan().getFacultyID(cpv.getFacultyName());
		return new CoursePO(cpv.getCno(), cpv.getName(), cpv.getTeacherID(),
				cpv.getCampus(), cpv.getGrade(), cpv.getPlace(), cpv.getTime(),
				cpv.getPeriod(), cpv.getRequire(), facultyID, cpv.getCredit(),
				cpv.getModule(), cpv.getLimit(), cpv.getInfo());
	}

	public static String getFacultyNameByFTeacherID(String ID) {
		return new Plan().getFacultyName(new Faculty(ID).getFacultyID());
	}
}