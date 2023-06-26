import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException {
    	
        //Declaring the parameters and variables
    	ArrayList<Student> students;
    	ArrayList<Faculty> instructors;
    	ArrayList<Course> courses;
    	ControlParams parameters;
    	
    	Scanner input = new Scanner(System.in);
    	
    	//Declaring the randomIdGenerators
    	IdGenerator studentIdGenerator = new RandomIdGenerator();
    	IdGenerator facultyIdGenerator = new RandomIdGenerator();
    	IdGenerator sessionIdGenerator = new RandomIdGenerator();

    	RegistrationSystem registrationSystem = new RegistrationSystem(studentIdGenerator, facultyIdGenerator, sessionIdGenerator);

    	//Reading in the student information(such as their id, etc.), parameters(Max sessions a instructor can teach), and the instructor information
    	parameters = readParameters("ControlParameters.txt");
    	students = readStudents("StudentInfo.txt");
    	instructors = readInstructors("FacultyInfo.txt");
    	
    	//Passing the parameters to the instructors
    	for (Faculty instructor : instructors) {
    	    instructor.setParams(parameters);
    	}
    	
    	//Reading the course info into the program;
    	courses = readCourses("CourseInfo.txt");
    	
    	
        //Reading the courses the student wants and adding their courses.
    	for (Student student : students) {
    		registrationSystem.readCourseInfo("StudentCourseInfo.txt", student, courses);
    	}
    	
    	
    	System.out.print("Enter 1 if you would like the Student Id to be generated through the Random class, or choose 2 if you would like a different method: ");
    	int choice = input.nextInt();
    	if(choice == 2)
    	{
    		System.out.println("Perfect, Generating the Student Ids.");
    		for (Student student : students) {
        	    int studentId = studentIdGenerator.getNextId();
        	    student.setId(studentId);
        	}
    	}
    	else
    	{
    		System.out.println("Perfect, Generating the Student Ids.");
    		for (Student student : students) {
        	    int studentId = studentIdGenerator.generateId();
        	    student.setId(studentId);
        	}
    	}
    	
    	System.out.println();
    	// Assign IDs to the students using the random ID generator
    	
    	
    	System.out.print("Enter 1 if you would like the Faculty Id to be generated through the Random class, or choose 2 if you would like a different method: ");
    	int choice1 = input.nextInt();
    	if(choice1 == 2)
    	{
    		System.out.println("Perfect, Generating the Faculty Ids.");
    		for (Faculty faculty : instructors) {
        	    int facultyId = facultyIdGenerator.getNextId();
        	    faculty.setId(facultyId);
        	}
    	}
    	else
    	{
    		for (Faculty faculty : instructors) {
        	    int facultyId = facultyIdGenerator.generateId();
        	    faculty.setId(facultyId);
        	}
    	}
    	
    	
    	

     	
    	System.out.println();
    	
    	

    	// Set the students, faculty, and courses in the RegistrationSystem
    	registrationSystem.setStudents(students);
    	registrationSystem.setFaculty(instructors);
    	registrationSystem.setCourses(courses);
    	
    	

    	// Schedule the courses
    	registrationSystem.scheduleCourses(parameters);

        
        String fileName = "ScheduledCourseSessions.txt";
        
        
        
        //Writing to the files(Output files should show up, but they are not recognized)
        registrationSystem.writeScheduledCourse(fileName, courses);
        registrationSystem.writeUnscheduledCourseSessions("UnscheduledCourseSessions.txt", courses);
        registrationSystem.writeFacultyDetails("Faculty.txt", instructors);
        registrationSystem.writeScheduledStudents("ScheduledStudents.txt:", students);
        registrationSystem.writeUnscheduledStudents("UnscheduledStudents.txt:", students);
        
        //Printing results to the console
        System.out.println();
        System.out.println("Here are the results");
        generateReport(registrationSystem);
        
 
        
    }
    
    
    
//This function prints the report out to the console. The function displays information such as the total sessions scheduled, the total number of students, etc.
public static void generateReport(RegistrationSystem registrationSystem) {
    ArrayList<Student> students = registrationSystem.getStudents();
    ArrayList<Faculty> faculty = registrationSystem.getFaculty();
    ArrayList<Course> courses = registrationSystem.getCourses();
    
    int totalStudents = students.size();
    int totalFaculty = faculty.size();
    int totalCourses = courses.size();
    
    int totalSessionsScheduled = 0;
    int totalUnscheduledCourses = 0;
    int totalStudentsWithNoClasses = 0;
    
    for (Course course : courses) {
        totalSessionsScheduled += course.getNumSessions();
        if (course.getNumSessions() == 0) {
            totalUnscheduledCourses++;
        }
    }
    
    for (Student student : students) {
        if (student.getNumCoursesEnrolled() == 0) {
            totalStudentsWithNoClasses++;
        }
    }
    
    System.out.println("Total Students: " + totalStudents);
    System.out.println("Total Faculty: " + totalFaculty);
    System.out.println("Total Courses: " + totalCourses);
    System.out.println("Total Sessions Scheduled: " + totalSessionsScheduled);
    System.out.println("Total Courses (not sessions) Unscheduled: " + totalUnscheduledCourses);
    System.out.println("Total Students With No Classes: " + totalStudentsWithNoClasses);
}
    
    //This function reads the student information from the text file and initializes the student arraylist
    public static ArrayList<Student> readStudents(String path) throws FileNotFoundException {
        ArrayList<Student> students = new ArrayList<Student>();
        File file = new File(path);
        Scanner inputFile;
        String firstName;
        String middleName;
        String lastName;
        String email;
        String phoneNumber;
        String streetAddress;
        String city;
        String state;
        String zipCode;
        // String id;

        Date dateOfBirth;
        double gpa;
        Date startDate;

        StringTokenizer token = null;

        if(file.exists()) {
            inputFile = new Scanner(file);
            // Skip the first line
            inputFile.nextLine();
            while(inputFile.hasNext()) {
                token = new StringTokenizer(inputFile.nextLine(), ",");
                firstName = token.nextToken();
                middleName = token.nextToken();
                lastName = token.nextToken();
                email = token.nextToken();
                phoneNumber = token.nextToken();
                streetAddress = token.nextToken();
                city = token.nextToken();
                state = token.nextToken();
                zipCode = token.nextToken();

                String dateString = token.nextToken();
                dateOfBirth = parseDate(dateString);

                gpa = Double.parseDouble(token.nextToken());

                dateString = token.nextToken();
                startDate = parseDate(dateString);

                Student student = new Student(firstName, middleName, lastName, email, phoneNumber, streetAddress, city, state, zipCode, dateOfBirth, gpa, startDate);
//                String id = student.generateRandomId();
//                student.setId(id);
                students.add(student);
            }
        }
        return students;
    }
    
  //This function reads the faculty information from the text file and initializes the instructors arraylist
    public static ArrayList<Faculty> readInstructors(String path) throws FileNotFoundException {
        ArrayList<Faculty> instructors = new ArrayList<Faculty>();
        File file = new File(path);
        Scanner inputFile;
        String firstName;
        String middleName;
        String lastName;
        String email;
        String phoneNumber;
        String streetAddress;
        String city;
        String state;
        String zipCode;
        // String id;

        // Date dateOfBirth;
        Date hireDate;
        boolean tenured;

        StringTokenizer token = null;

        if(file.exists()) {
            inputFile = new Scanner(file);
            // Skip the first line
            inputFile.nextLine();
            while(inputFile.hasNext()) {
                token = new StringTokenizer(inputFile.nextLine(), ",");
                firstName = token.nextToken();
                middleName = token.nextToken();
                lastName = token.nextToken();
                email = token.nextToken();
                phoneNumber = token.nextToken();
                streetAddress = token.nextToken();
                city = token.nextToken();
                state = token.nextToken();
                zipCode = token.nextToken();

                
                String hireString = token.nextToken();
                hireDate = parseDate(hireString);

                tenured = Boolean.parseBoolean(token.nextToken());

               

                Faculty instructor = new Faculty(firstName, middleName, lastName, email, phoneNumber, streetAddress, city, state, zipCode, hireDate, tenured);
//                String id = instructor.generateId();
//                instructor.setParams(params);
                instructors.add(instructor);
            }
        }
        return instructors;
    }
    
  //This function reads the course information from the text file and initializes the courses arraylist
    public static ArrayList<Course> readCourses(String path) throws FileNotFoundException {
        ArrayList<Course> courses = new ArrayList<>();
        File file = new File(path);

        if (file.exists()) {
            try (Scanner inputFile = new Scanner(file)) {
                while (inputFile.hasNextLine()) {
                    String line = inputFile.nextLine();
                    String[] tokens = line.split(",");
              

                    String department = tokens[0].trim();
                    String code = tokens[1].trim();
                    String description = tokens[2].trim();
                    String courseId = tokens[3].trim();
                    int minStudents = Integer.parseInt(tokens[4].trim());
                    int maxStudents = Integer.parseInt(tokens[5].trim());
                    int numSessions = Integer.parseInt(tokens[6].trim());

                    Course course = new Course(department, code, courseId, description, minStudents, maxStudents, numSessions);
                    courses.add(course);
                }
            }
        }

        return courses;
    }

    
  //This function reads the parameters from the text file and initializes the parameters object in main
    public static ControlParams readParameters(String path) throws FileNotFoundException{
        File file = new File(path);
        Scanner inputFile;
        int maxNumCoursesPerStudent = 0; // initialize variables outside the loop
        int maxSessionsPerFaculty = 0;
        ControlParams parameters = null;

        if(file.exists()) {
            inputFile = new Scanner(file);
            while(inputFile.hasNext()) {
                String line = inputFile.nextLine();
                String[] tokens = line.split("=");
                if (tokens.length != 2) {
                    continue; // ignore lines that don't match the expected format
                }
                String paramName = tokens[0].trim();
                int paramValue = Integer.parseInt(tokens[1].trim());
                if (paramName.equals("maxNumCoursesPerStudent")) {
                    maxNumCoursesPerStudent = paramValue;
                } else if (paramName.equals("maxSessionsPerFaculty")) {
                    maxSessionsPerFaculty = paramValue;
                }
            }
            parameters = new ControlParams(maxNumCoursesPerStudent, maxSessionsPerFaculty);
        }
        return parameters;
    }
    
    	
    	
  //This function helps read the Date objects in the Student and Faculty objects
    public static Date parseDate(String dateString) {
        String[] dateParts = dateString.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2].trim());
        return new Date(year - 1900, month - 1, day);
    }
}
    
