import java.util.Date;
import java.util.Random;
import java.util.ArrayList;
//This class is the student class, and it represents all of the information of a student such as their phone number, the courses they are taking, etc.
class Student extends Person{
    
    private Date dateOfBirth;
    private double gpa;
    private Date startDate;
    public int numCoursesEnrolled;
    private ArrayList<Course> courses;
    private ArrayList<Session> sessions;

    // constructor
    public Student(String firstName, String middleName, String lastName, String email, String phoneNumber, String streetAddress, String city, String state, String zipCode, Date dateOfBirth, double gpa, Date startDate) 
    {
        super(firstName, middleName, lastName, email, phoneNumber, streetAddress, city, state, zipCode);
        this.dateOfBirth = dateOfBirth;
        this.gpa = gpa;
        this.startDate = startDate;
        this.numCoursesEnrolled = 0;
        this.courses = new ArrayList<Course>();
        this.sessions = new ArrayList<>();
  
    }
    
    public ArrayList<Course> getCourseInfo() {
        return courses;
    }
    
    public String getFullName()
    {
    	return getFirstName() + " " + getMiddleName()+ " " + getLastName();
    }
    
    public void addCourse(Course course) {
        courses.add(course);
    }
    
    public int getNumCoursesEnrolled()
    {
    	return numCoursesEnrolled;
    }
    void setNumCoursesEnrolled(int numCoursesEnrolled)
    {
    	this.numCoursesEnrolled = numCoursesEnrolled;
    }
   
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

     
     public String generateRandomId() {
        String id = "";
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int randNum = rand.nextInt(10);
            id += Integer.toString(randNum);
        }
        return id;
    } 
     
     
    @Override
public String toString() {
    return "Student [id=" + getId() + ", firstName=" + getFirstName() + ", middleName=" + getMiddleName() + ", lastName="
            + getLastName() + ", email=" + getEmail() + ", phoneNumber=" + getPhoneNumber() + ", streetAddress=" + getStreetAddress()
            + ", city=" + getCity() + ", state=" + getState() + ", zipCode=" + getZipCode() + ", dateOfBirth=" + getDateOfBirth()
            + ", currentGPA=" + getGpa() + ", dateStarted=" + getStartDate() + "]";
}

	public ArrayList<Session> getEnrolledSessions() {
		return sessions;
	}
	public ArrayList<Course> getCourses()
	{
		return courses;
	}
}
