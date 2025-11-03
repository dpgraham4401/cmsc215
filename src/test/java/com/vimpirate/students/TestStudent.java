package com.vimpirate.students;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestStudent {

    @Test
    public void testConstructStudent() {
        Student student = new Student("John", 10, 40);
        assert student.toString().contains("John");
    }

    @Test
    public void testGpaReturnsPositiveFloat() {
        Student student = new Student("John", 10, 40);
        assert student.gpa() > 0.0;
    }

    @Test
    public void testSettingHonorThreshold() {
        assertDoesNotThrow(() -> {
            Student.setGpaThreshold(3.2);
        });
    }
}
