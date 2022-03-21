package jpa.entitymodels;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;

public class InsertData {
    public  void InsertInfo() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Course course1 = new Course("English", "Anderea Scamaden");
        Course course2 = new Course("Mathematics", "Eustace Niemetz");
        Course course3 = new Course("Anatomy", "Reynolds Pastor");
        Course course4 = new Course("Organic Chemistry", "Odessa Belcher");
        Course course5 = new Course( "Physics", "Dani Swallow");
        Course course6 = new Course("Digital Logic", "Glenden Reilingen");
        Course course7 = new Course( "Object Oriented Programming", "Giselle Ardy");
        Course course8 = new Course("Data Structures", "Carolan Stoller");
        Course course9 = new Course("Politics", "Carmita De Maine");
        Course course10 = new Course("Art", "Kingsly Doxsey");

        //insert data below by passing data to constructor
        Student student1 = new Student("hluckham0@google.ru", "Hazel Luckham", "X1uZcoIh0dj", null);
        student1.setCourses(new HashSet<Course>(){{
            add(course1);
            add(course2);
        }});
        Student student2 = new Student("sbowden1@yellowbook.com", "Sonnnie Bowden", "SJc4aWSU", null);
        Student student3 = new Student("qllorens2@howstuffworks.com", "Quillan Llorens", "W6rJuxd", null);
        Student student4 = new Student("cstartin3@flickr.com", "Clem Startin", "XYHzJ1S", null);
        Student student5 = new Student("tattwool4@biglobe.ne.jp", "Thornie Attwool", "Hjt0SoVmuBz", null);
        Student student6 = new Student("hguerre5@deviantart.com", "Harcourt Guerre", "OzcxzD1PGs", null);
        Student student7 = new Student("htaffley6@columbia.edu", "Holmes Taffley", "xowtOQ", null);
        Student student8 = new Student("aiannitti7@is.gd", "Alexandra Iannitti", "TWP4hf5j", null);
        Student student9 = new Student("ljiroudek8@sitemeter.com", "Laryssa Jiroudek", "bXRoLUP", null);
        Student student10 = new Student("cjaulme9@bing.com", "Cahra Jaulme", "FnVklVgC6r6", null);

        session.saveOrUpdate(student1);
        session.saveOrUpdate(student2);
        session.saveOrUpdate(student3);
        session.saveOrUpdate(student4);
        session.saveOrUpdate(student5);
        session.saveOrUpdate(student6);
        session.saveOrUpdate(student7);
        session.saveOrUpdate(student8);
        session.saveOrUpdate(student9);
        session.saveOrUpdate(student10);

        //session.saveOrUpdate(course1); //we are doing add above, so don't need to save here anymore
        //session.saveOrUpdate(course2);
        session.saveOrUpdate(course3);
        session.saveOrUpdate(course4);
        session.saveOrUpdate(course5);
        session.saveOrUpdate(course6);
        session.saveOrUpdate(course7);
        session.saveOrUpdate(course8);
        session.saveOrUpdate(course9);
        session.saveOrUpdate(course10);

        transaction.commit();
        factory.close();
        session.close();
    }
}
