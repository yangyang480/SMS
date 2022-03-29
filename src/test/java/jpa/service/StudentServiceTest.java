package jpa.service;

import jpa.entitymodels.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @Test
    @DisplayName("checking student by email")
    void checkStudentByEmail() {
        // Arrange
        Student expected = new Student("aiannitti7@is.gd","Alexandra Iannitti", "TWP4hf5j");

        // Act
        Student actual = new StudentService().getStudentByEmail("aiannitti7@is.gd");

        // Assert
        Assertions.assertEquals(expected.getsEmail(), actual.getsEmail());
    }
}