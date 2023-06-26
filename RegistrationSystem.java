import java.io.FileWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Scanner;

class RegistrationSystem {
    ArrayList<Student> students;
    ArrayList<Faculty> faculty;
    ArrayList<Course> courses;
    int nextStudentId;
    int nextFacultyId;
    int nextSessionId;
    IdGenerator studentIdGenerator;
    IdGenerator facultyIdGenerator;
    IdGenerator sessionIdGenerator;
    SchedulingAlgorithm schedulingAlgorithm;

    RegistrationSystem() {
        // Default constructor
    }

    RegistrationSystem(IdGenerator studentIdGenerator, IdGenerator facultyIdGenerator, IdGenerator sessionIdGenerator) {
        students = new ArrayList<Student>();
        faculty = new ArrayList<Faculty>();
        courses = new ArrayList<Course>();
        nextStudentId = 1;
        nextFacultyId = 1;
        nextSessionId = 1;
        this.studentIdGenerator = studentIdGenerator;
        this.facultyIdGenerator = facultyIdGenerator;
        this.sessionIdGenerator = sessionIdGenerator;
        schedulingAlgorithm = new DefaultSchedulingAlgorithm();
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    
    
    //Adds students courses, and updates the courses arrayList.
    public void readCourseInfo(String fileName, Student student, ArrayList<Course> courses) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] courseNames = line.split(",");
                //Checks if the name in the file matches the students name
                if(student.getFullName() == courseNames[0])
                {
                    for (int i = 1; i < courseNames.length; i++) {
                        String courseName = courseNames[i].trim();
                        boolean courseFound = false;

                        for (Course course : courses) {
                        	//CS1A == CS1A
                            if (course.getCourseId().equals(courseName)) {
                                student.addCourse(course);
                                course.incrementNumStudentsEnrolled1();
                                courseFound = true;
                                break;
                            }
                        }

                        if (!courseFound) {
                            System.out.println("Course not found: " + courseName);
                        }
                    }
                }

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
    }

    public void setFaculty(ArrayList<Faculty> faculty) {
        this.faculty = faculty;
    }
    
    //Write the unscheduled Students to an output file.
    public void writeUnscheduledStudents(String fileName, ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Student student : students) {
                if (student.getEnrolledSessions().isEmpty()) {
                    writer.println("Student Name: " + student.getFullName());
                    writer.println("Student ID: " + student.getId());
                    writer.println();
                }
            }

            System.out.println("Unscheduled student information has been written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error: Failed to write to file.");
            e.printStackTrace();
        }
    }

    
  //Write the unscheduled Courses to an output file.
public void writeUnscheduledCourseSessions(String fileName, ArrayList<Course> courses) {
    try (PrintWriter writer = new PrintWriter(fileName)) {
        for (Course course : courses) {
            if (course.getSessions().isEmpty()) {
                writer.println("Course: " + course.getDescription());
                writer.println("Minimum Number of Students: " + course.getMinStudents());
                writer.println();
            }
        }
        System.out.println("Unscheduled course sessions have been written to " + fileName);
    } catch (IOException e) {
        System.out.println("Error: Failed to write to file.");
        e.printStackTrace();
    }
}
   //Write the students who have scheduled courses, and list the courseId, SessionId, and the title of the course taken
    public void writeScheduledStudents(String fileName, ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Student student : students) {
                writer.write("Student Name: " + student.getFullName() + "\n");
                writer.write("Student ID: " + student.getId() + "\n\n");

                ArrayList<Session> enrolledSessions = student.getEnrolledSessions();
                for (Session session : enrolledSessions) {
                    Course course = session.getCourse();
                    writer.write("Session ID: " + session.getSessionId() + "\n");
                    writer.write("Course ID: " + course.getCourseId() + "\n");
                    writer.write("Course Description: " + course.getDescription() + "\n\n");
                }

                writer.write("--------------------\n\n");
            }

            System.out.println("Scheduled student information has been written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error: Failed to write to file.");
            e.printStackTrace();
        }
    }
    
    //Write the scheduled courses to an output file, includes name and id of instructor, number of sessions, etc.
    public void writeScheduledCourse(String fileName, ArrayList<Course> courses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Course course : courses) {
                writer.println("Course ID: " + course.getCourseId());
                writer.println("Course Name: " + course.getDescription());
                writer.println("Instructor: " + (course.getInstructor() != null ? course.getInstructor().getFullName() : "N/A"));
                writer.println("Sessions:");
                for (Session session : course.getSessions()) {
                    writer.println("- Session ID: " + session.getSessionId());
                    writer.println("  Instructor: " + (session.getInstructor() != null ? session.getInstructor().getFullName() : "N/A"));
                    writer.println("  Number of Students: " + session.getNumStudentsEnrolled());
                }
                writer.println();
            }
            System.out.println("Scheduled course sessions have been written to the file: " + fileName);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing scheduled course sessions to the file: " + fileName);
        }
    }

	//Writing faculty details to the output file, and their courses taught
    public void writeFacultyDetails(String fileName, ArrayList<Faculty> faculty) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Faculty instructor : faculty) {
                writer.println("Faculty ID: " + instructor.getId());
                writer.println("Faculty Name: " + instructor.getFullName());
                writer.println("Courses Scheduled:");
                
                // Check if coursesScheduled is null
                ArrayList<Course> coursesScheduled = instructor.getCoursesScheduled();
                if (coursesScheduled != null) {
                    for (Course course : coursesScheduled) {
                        writer.println("- " + course.getCourseId() + ": " + course.getDescription());
                    }
                }
                
                writer.println();
            }
            System.out.println("Faculty details have been written to the file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing faculty details to the file: " + fileName);
        }
    }
    
    

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    void addStudent(Student student) {
//        student.id = studentIdGenerator.getNextId();
        students.add(student);
    }

    void addFaculty(Faculty facultyMember) {
//        facultyMember.id = facultyIdGenerator.getNextId();
        faculty.add(facultyMember);
    }

    void addCourse(Course course) {
        courses.add(course);
    }
    
    //This method is to schedule a session for a course.
    void scheduleSession(Course course, Faculty facultyMember, int numStudentsEnrolled) {
        //If the facultyMember is not empty, we will create a new session, and add it to the course, and update the course
    	//information such as the number of students enrolled
    	if (facultyMember != null) {
            int sessionId = sessionIdGenerator.getNextId();
            Session session = new Session(sessionId, course.getCourseId(), facultyMember.id, numStudentsEnrolled, course.getMaxStudents());
            course.getSessions().add(session);
            course.numSessions++;
            course.numStudentsEnrolled += numStudentsEnrolled;
        } else {
            System.out.println("Error: Faculty member is null.");
        }
    }
   

  //This method is to register a student for a session.
    void registerStudent(Session session, Student student) {
        if (session.numStudentsEnrolled < session.maxStudentsEnrolled) {
            session.enrolledStudents.add(student);
            session.numStudentsEnrolled++;
            student.numCoursesEnrolled++;
            if (session.numStudentsEnrolled == session.maxStudentsEnrolled) {
                session.isCancelled = false;
            }
        }
    }

  //This method is to cancel a session.
    void cancelSession(Session session) {
        session.setCancelled(true);
        ArrayList<Student> enrolledStudents = session.getEnrolledStudents();
        for (Student student : enrolledStudents) {
            student.setNumCoursesEnrolled(student.getNumCoursesEnrolled() - 1);
        }
        enrolledStudents.clear();
        session.setNumStudentsEnrolled(0);
    }

  //This method is to cancel a course
    void cancelCourse(Course course) {
        boolean status = true;
        course.setCancelled(status);
        ArrayList<Session> sessions = course.getSessions();
        for (Session session : sessions) {
            cancelSession(session);
            course.decrementNumSessions();
        }
    }

    //Allows us to be dynamic with the scheduling Algorithm(Not used yet as was caught up in solving first algorithm)
    void setSchedulingAlgorithm(SchedulingAlgorithm newAlgorithm) {
        schedulingAlgorithm = newAlgorithm;
    }


    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Faculty> getFaculty() {
        return faculty;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    
    //Interface declared so we can switch what scheduling Algorithm we want to use during run time
    interface SchedulingAlgorithm {
        void scheduleSessions(Course course, Faculty facultyMember);
    }

    public class DefaultSchedulingAlgorithm implements SchedulingAlgorithm {
    	//This method schedules courses and is the method called in scheduleCourses 
        public void scheduleSessions(Course course, Faculty facultyMember) {
        	//Receive number of students and sessions
            int numStudents = course.numStudentsEnrolled;
            int numSessions = (numStudents + course.getMaxStudents() - 1) / course.getMaxStudents();
         // Iterate over the number of sessions
            for (int i = 0; i < numSessions; i++) {
            	// Determine the number of students enrolled in the current session
                int numStudentsEnrolled = Math.min(numStudents, course.getMaxStudents());
                // Schedule the session with the given course, faculty member, and number of students
                scheduleSession(course, facultyMember, numStudentsEnrolled);
             // Update the remaining number of students
                numStudents -= numStudentsEnrolled;
            }
            if (course.numSessions == 0) {
                course.setCancelled(true);
            }
        }
                
    }
    
    //Method generate a random instructor from the ArrayList and returns it.
    public static Faculty getRandomInstructor(ArrayList<Faculty> instructors) {
        int size = instructors.size();
        if (size == 0) {
            throw new IllegalArgumentException("Instructor list is empty");
        }
        
        // Generate a random index using the current time in milliseconds
        long currentTime = System.currentTimeMillis();
        int index = (int) (currentTime % size);
        
        return instructors.get(index);
    }
    
    //Method to schedule the courses.
    public void scheduleCourses(ControlParams parameters) {
    	//Pre checks are here to make sure we have no null variables before scheduling the courses
        if (schedulingAlgorithm == null) {
            System.out.println("Error: Scheduling algorithm is not initialized.");
            // Handle the error appropriately, such as logging or throwing an exception
            return;
        }

        if (faculty == null || faculty.isEmpty()) {
            System.out.println("Error: Faculty list is null or empty.");
            // Handle the error appropriately, such as logging or throwing an exception
            return;
        }

        for (Course course : courses) {
            // Get a random instructor from the 'faculty' ArrayList
            Faculty instructor = getRandomInstructor(faculty);

            // Check if the instructor is null
            if (instructor == null) {
                System.out.println("Error: Faculty member is null for course " + course.getCourseId());
                // Handle the error appropriately, such as logging or throwing an exception
                continue; // Skip scheduling for this course and proceed to the next iteration
            }

            // Schedule the sessions for the course
            schedulingAlgorithm.scheduleSessions(course, instructor);
            //Set instructor for the course
            course.setInstructor(instructor);
        }
    }


    
}

