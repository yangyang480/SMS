package jpa.mainrunner;

import jpa.entitymodels.InsertData;
import jpa.service.CourseService;
import jpa.service.StudentService;

import java.util.Scanner;

public class SMSRunner {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService;
    private static CourseService courseService;

    public static void main(String[] args) {
        InsertData insertData = new InsertData();
        insertData.InsertInfo();

        studentService = new StudentService();

        courseService = new CourseService();
        courseService.getAllCourses();

        Integer choice = 0;
        while (choice != 2)
        {
            menu();
            choice = scanner.nextInt();
            switch (choice)
            {
                case 1:
                    menuStudent();
                    break;
                case 2:
                    System.out.println("See you!");
                    break;
            }
        }
        scanner.close();
    }

    private static void menu(){
        System.out.println("Are you a(n)");
        System.out.println("1. Student");
        System.out.println("2. quit");
    }

    private static void menuStudent(){
        System.out.println("Enter Your Email: ");
        String email = scanner.nextLine();
        email = scanner.nextLine();
        System.out.println("Enter Your Password:");
        String password = scanner.nextLine();

        if (studentService.validateStudent(email, password) == true)
        {
            // Render student menu
            Integer choice = 0;
            while (choice != 2)
            {
                studentService.getStudentCourses(email);

                System.out.println("1. Register to Class");
                System.out.println("2. Logout");

                choice = scanner.nextInt();
                switch (choice)
                {
                    case 1:
                        courseService.getAllCourses();
                        System.out.println("Which course?");
                        int cId = scanner.nextInt();
                        studentService.registerStudentToCourse(email, cId);
                        //TODO if already existed then "You are already registered in that courese!"
                        break;
                    case 2:
                        System.out.println("See you!");
                        break;
                }//TODO after select logout don't show option anymore, just say"Have a nice day"
            }
            System.out.println("1. Register to Class");
            System.out.println("2. Logout");
        }
        else
        {
            System.out.println("Wrong Credentials");
            return;
        }
    }
}
