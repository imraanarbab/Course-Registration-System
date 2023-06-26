import java.util.Date;
import java.util.ArrayList;
//This class is the Faculty class, and it represents all of the information surrounding an instructor such as the id, etc.
public class Faculty extends Person {
    private Date hireDate;
    private boolean isTenured;
    private ControlParams params;
    private ArrayList<Course> courses;

    public Faculty(String firstName, String middleName, String lastName, String email, String phoneNumber,
                   String streetAddress, String city, String state, String zipCode,
                   Date hireDate, boolean isTenured) {
        super(firstName, middleName, lastName, email, phoneNumber, streetAddress, city, state, zipCode);
        this.hireDate = hireDate;
        this.isTenured = isTenured;
    }
    
    public void setParams(ControlParams params) {
        this.params = params;
    }
    public int getFacultyId()
    {
    	return getId();
    }
 

    public Date getHireDate() {
        return hireDate;
        
    }


    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public boolean isTenured() {
        return isTenured;
    }
    

    public void setTenured(boolean tenured) {
        isTenured = tenured;
    }
    
        
        public String getFullName () {
            return getFirstName() + " " + getMiddleName() + " " + getLastName();
        }
          
         

    @Override
    public String toString() {
        return "Faculty{" +
                "id='" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", middleName='" + getMiddleName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", streetAddress='" + getStreetAddress() + '\'' +
                ", city='" + getCity() + '\'' +
                ", state='" + getState() + '\'' +
                ", zipCode='" + getZipCode() + '\'' +
                ", hireDate=" + getHireDate() +
                ", isTenured=" + isTenured() +
                '}';
    }

	public int getMaxSessionsPerFaculty() {
		return params.getMaxSessionsPerFaculty();
	}

	public ArrayList<Course> getCoursesScheduled() {
		return courses;
	}
}
