package jpa.mainrunner;

import jpa.entitymodels.Course;
import jpa.entitymodels.InsertData;
import jpa.service.CourseService;
import jpa.service.StudentService;
import java.util.List;
import java.util.Scanner;

public class SMSRunner {
    private static final Scanner scanner = new Scanner(System.in);
    private static StudentService studentService;
    private static CourseService courseService;

    public static void main(String[] args) {
        InsertData insertData = new InsertData();
        insertData.InsertInfo();

        studentService = new StudentService();
        courseService = new CourseService();

        int choice = 0;
        while (choice != 2 && choice != 1)
        {
            menu();
            choice = scanner.nextInt();
            switch (choice)
            {
                case 1:
                    menuStudent();
                    break;
                case 2:
                    System.out.println("See you! Have a nice day!");
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
        scanner.close();
    }

    private static void menu(){
        System.out.println("Welcome to Student Management System! ");
        System.out.println("1. Student");
        System.out.println("2. quit");
        System.out.println("Please enter 1 or 2.");
    }

    private static void menuStudent(){
        System.out.println("Please Enter Your Email: ");
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
                    System.out.println("My Classes: ");
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

                choice = scanner.nextInt();
                switch (choice)
                {
                    case 1:
                        List<Course> allCourses = courseService.getAllCourses();
                        System.out.printf("%-6s%-37s%-25s\n", "ID", "COURSE NAME", "INSTRUCTOR NAME");
                        for (Course c : allCourses)
                        {
                            System.out.printf("%-6s%-37s%-25s\n", c.getcId(), c.getcName(), c.getcInstructorName());
                        }
                        System.out.println("Which course you want to register?");
                        int cId = scanner.nextInt();

                        boolean hasCourse = false;
                        //we initialize hasCourse, we pass the courseId, compare with the input cId, if hasCourse=true, the return "registed already"
                        for(Course course : courses) {
                            if(course.getcId() == cId) {
                                hasCourse = true;
                            }
                        }

                        if (hasCourse) {
                            System.out.println("You are already registered in this course");
                        }
                        else {
                            studentService.registerStudentToCourse(email, cId);
                        }
                        break;
                    case 2:
                        System.out.println("See you! Have a nice day!");
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
            }
        }
        else
        {
            System.out.println("Wrong Credentials, please contact us to register first.");
        }
    }

}
