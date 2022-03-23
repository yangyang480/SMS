package jpa.mainrunner;

import jpa.entitymodels.Course;
import jpa.entitymodels.InsertData;
import jpa.service.CourseService;
import jpa.service.StudentService;
import org.hibernate.exception.GenericJDBCException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SMSRunner {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService;
    private static CourseService courseService;

    public static void main(String[] args) throws GenericJDBCException {
        InsertData insertData = new InsertData();
        insertData.InsertInfo();

        studentService = new StudentService();
        courseService = new CourseService();

        int choice = 0;
        while (choice != 2 && choice != 1) {
            menu();
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        menuStudent();
                        break;
                    case 2:
                        System.out.println("See you! Have a nice day!");
                        break;
                    default:
                        System.out.println("Option is not available!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, please enter number!");
                scanner.next(); // Clear scanner buffer of wrong input
            }
            catch(Exception ex)
            {
                System.out.println("Something went wrong!");
                scanner.next(); // Clear scanner buffer of wrong input
            }
        }
    }

    private static void menu(){
        System.out.println("Welcome to Student Management System!");
        System.out.println("Are you a(n)");
        System.out.println("1. Student");
        System.out.println("2. quit");
        System.out.println("Please enter 1 or 2.");
    }

    private static void menuStudent() throws Exception{
        System.out.println("Please Enter Your Email:");
        String email = scanner.nextLine();
        email = scanner.nextLine();
        System.out.println("Please Enter Your Password:");
        String password = scanner.nextLine();
        if (studentService.validateStudent(email, password))
        {
            int choice = 0;
            while (choice != 2)
            {
                List<Course> courses = studentService.getStudentCourses(email);
                if (courses.size() > 0)
                {
                    System.out.println("My Classes:");
                    System.out.printf("%-6s%-37s%-25s\n", "#", "COURSE NAME", "INSTRUCTOR NAME");
                    for (Course c : courses)
                    {
                        System.out.printf("%-6s%-37s%-25s\n", c.getcId(), c.getcName(), c.getcInstructorName());
                    }
                }
                else {
                    System.out.println("You have no course yet, please: ");
                }
                System.out.println("1. Register to Class");
                System.out.println("2. Logout");
                System.out.println("Please enter 1 or 2.");

                try
                {
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            List<Course> allCourses = courseService.getAllCourses();
                            System.out.println("All Courses:");
                            System.out.printf("%-6s%-37s%-25s\n", "ID", "COURSE NAME", "INSTRUCTOR NAME");
                            for (Course c : allCourses) {
                                System.out.printf("%-6s%-37s%-25s\n", c.getcId(), c.getcName(), c.getcInstructorName());
                            }
                            System.out.println("Which Course?");
                            int cId = scanner.nextInt();

                            boolean hasCourse = false;
                            //we initialize hasCourse, we pass the courseId, compare with the input cId, if hasCourse=true, the return "registed already"
                            for(Course course : courses) {
                                if(course.getcId() == cId) {
                                    hasCourse = true;
                                }
                            }

                            if (hasCourse) {
                                System.out.println("You are already registered in that course!");
                            }
                            else {
                                studentService.registerStudentToCourse(email, cId);
                            }
                            break;
                        case 2:
                            System.out.println("You have been signed out.");
                            break;
                        default:
                            System.out.println("Option is not available!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Wrong input, please enter number!");
                    scanner.next();
                }
                catch(Exception ex)
                {
                    System.out.println("Something went wrong!");
                    scanner.next();
                }
            }
        }
        else
        {
            System.out.println("Wrong Credentials, please contact us to register first.");
        }
    }
}
