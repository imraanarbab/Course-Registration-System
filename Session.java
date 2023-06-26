import java.util.ArrayList;
//This class is the session class, and it represents all of the information surround a session of the course, such as the id, the courseId of the session, the id of the 
//faculty member teaching the session, etc.
public class Session {
    public int id;
    public String courseId;
    public int facultyId;
    public int numStudentsEnrolled;
    public int maxStudentsEnrolled;
    public boolean isCancelled;
    public ArrayList<Student> enrolledStudents;
    public Course course;
    public Faculty instructor;

    public Session(int id, String courseId, int facultyId, int maxStudentsEnrolled) {
        this.id = id;
        this.courseId = courseId;
        this.facultyId = facultyId;
        this.numStudentsEnrolled = 0;
        this.maxStudentsEnrolled = maxStudentsEnrolled;
        this.isCancelled = false;
        this.enrolledStudents = new ArrayList<>();
    }
    
    
    public Session(int id, String courseId, int facultyId, int numStudentsEnrolled, int maxStudentsEnrolled) {
        this.id = id;
        this.courseId = courseId;
        this.facultyId = facultyId;
        this.numStudentsEnrolled = numStudentsEnrolled;
        this.maxStudentsEnrolled = maxStudentsEnrolled;
    }
    
    public int getNumStudentsEnrolled()
    {
    	return numStudentsEnrolled;
    }
    
    public int getSessionId()
    {
    	return id;
    }
    
    public void setCancelled(boolean isCancelled)
    {
    	this.isCancelled = isCancelled;
    }
    
    public int getFacultyId()
    {
    	return facultyId;
    }
    
    public void setNumStudentsEnrolled(int numStudentsEnrolled) {
        this.numStudentsEnrolled = numStudentsEnrolled;
    }

    public void printSessionDetails() {
        System.out.println("Session ID: " + id);
        System.out.println("Course ID: " + courseId);
        System.out.println("Faculty ID: " + facultyId);
        System.out.println("Number of Students Enrolled: " + numStudentsEnrolled);
        System.out.println("Maximum Students Allowed: " + maxStudentsEnrolled);
        System.out.println("Is Cancelled: " + isCancelled);
        System.out.println("Enrolled Students: ");
        for (Student student : enrolledStudents) {
            System.out.println(student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName()  + " (" + student.getId() + ")");
        }
    }


	public ArrayList<Student> getEnrolledStudents() {
		return enrolledStudents;
	}


	public Faculty getInstructor() {
		return instructor;
	}


	public Course getCourse() {
		return course;
	}
}
