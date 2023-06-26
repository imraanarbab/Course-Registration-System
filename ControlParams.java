
public class ControlParams {
	//Max number of courses a student can register for
    private int maxNumCoursesPerStudent;
  //Max number of sessions a instructor can teach
    private int maxSessionsPerFaculty;

    public ControlParams(int maxNumCoursesPerStudent, int maxSessionsPerFaculty) {
        this.maxNumCoursesPerStudent = maxNumCoursesPerStudent;
        this.maxSessionsPerFaculty = maxSessionsPerFaculty;
    }

    public int getMaxNumCoursesPerStudent() {
        return maxNumCoursesPerStudent;
    }

    public int getMaxSessionsPerFaculty() {
        return maxSessionsPerFaculty;
    }
}
