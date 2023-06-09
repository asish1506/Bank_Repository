package service;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Course;
import model.Bank;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    private static final List<Bank> bank = new ArrayList<>();

    private final SecureRandom random = new SecureRandom();

    static {
        //Initialize Data
        Course courseOne = new Course("Course1", "Spring", "10Steps",
                List.of("Learn Maven", "Import Project", "First Example", "Second Example"));

        Course courseTwo = new Course("Course2", "Spring MVC", "10 Examples",
                List.of("Learn Maven", "Import Project", "First Example", "Second Example"));

        Course courseThree = new Course("Course3", "Spring Boot", "6K Students",
                List.of("Learn Maven", "Learn Spring", "Learn Spring MVC", "First Example", "Second Example"));

        Course courseFour = new Course("Course4", "Maven", "Most popular maven course on internet!",
                List.of("Pom.xml", "Build Life Cycle", "Parent POM", "Importing into Eclipse"));

        Bank ranga = new Bank("Bank1", "Ranga Karanam", "Hiker, Programmer and Architect",
                new ArrayList<>(List.of(courseOne, courseTwo, courseThree, courseFour)));

        Bank satish = new Bank("Bank2", "Satish T", "Hiker, Programmer and Architect",
                new ArrayList<>(List.of(courseOne, courseTwo, courseThree, courseFour)));

        bank.add(ranga);
        bank.add(satish);
    }

    public List<Bank> retrieveAllStudents() {
        return bank;
    }

    public Bank retrieveStudent(String studentId) {
        return bank.stream()
                .filter(student -> student.id().equals(studentId))
                .findAny()
                .orElse(null);

    }

    public List<Course> retrieveCourses(String studentId) {
        Bank student = retrieveStudent(studentId);

        if (studentId.equalsIgnoreCase("Student1")) {
            throw new RuntimeException("Something went wrong");
        }

        return student == null ? null : student.courses();
    }

    public Course retrieveCourse(String studentId, String courseId) {
        Bank student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        return student.courses().stream()
                .filter(course -> course.id().equals(courseId))
                .findAny()
                .orElse(null);
    }

    public Course addCourse(String studentId, Course course) {
        Bank student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        String randomId = new BigInteger(130, random).toString(32);
        new Course(randomId, "", "", Collections.singletonList(""));

        student.courses().add(course);

        return course;
    }
}
