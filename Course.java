import java.util.ArrayList;
//This class is the course class, and it represents all of the information surrounding a course such as the department, id, etc.
public class Course {
    private String department;
    private String code;
    private String courseId;
    private String description;
    private int minStudents;
    private int maxStudents;
    public int numStudentsEnrolled;
    public int numSessions;
    private boolean isCancelled;
    private ArrayList<Session> sessions;
    private Faculty instructor;

    public Faculty getInstructor() {
        return instructor;
    }

    public void setInstructor(Faculty instructor) {
        this.instructor = instructor;
    }

    public Course(String department, String code, String courseId, String description, int minStudents, int maxStudents, int numSessions) {
        this.department = department;
        this.code = code;
        this.courseId = courseId;
        this.description = description;
        this.minStudents = minStudents;
        this.maxStudents = maxStudents;
        this.numStudentsEnrolled = 0;
        this.numSessions = numSessions;
        this.isCancelled = false;
        this.sessions = new ArrayList<>();
    }
    
    public int getNumStudentsEnrolled()
    {
    	return numStudentsEnrolled;
    }
    public String getDescription()
    {
    	return description;
    }
    
    public void setNumStudentsEnrolled(int numStudentsEnrolled)
    {
    	this.numStudentsEnrolled = numStudentsEnrolled;
    }
    public int getNumSessions()
    {
    	return numSessions;
    }
    
    public void setNumSessions(int numSessions)
    {
    	this.numSessions = numSessions;
    }
    

    
    public int getMaxStudents()
    {
    	return maxStudents;
    }
    public int getMinStudents()
    {
    	return minStudents;
    }
    public void setCancelled(boolean isCancelled)
    {
    	this.isCancelled = isCancelled;
    }
    public boolean getCancelled()
    {
    	return isCancelled;
    }
    
    public String getCourseId()
    {
    	return courseId;
    }
    
    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void printCourseDetails() {
        System.out.println("Course Details:");
        System.out.println("Department: " + department);
        System.out.println("Code: " + code);
        System.out.println("Course ID: " + courseId);
        System.out.println("Description: " + description);
        System.out.println("Minimum Students: " + minStudents);
        System.out.println("Maximum Students: " + maxStudents);
        System.out.println("Enrolled Students: " + numStudentsEnrolled);
        System.out.println("Number of Sessions: " + numSessions);
        System.out.println("Cancelled: " + isCancelled);
    }

	public void incrementNumSessions() {
		numSessions++;
	}
	public void decrementNumSessions() {
		numSessions--;
	}
	public void incrementNumStudentsEnrolled1() {
        numStudentsEnrolled++; 
    }
    public void incrementNumStudentsEnrolled(int numStudents) {
        this.numStudentsEnrolled += numStudents;
    }
}
