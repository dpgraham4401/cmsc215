package com.vimpirate.students;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestGraduate {

    @Test
    public void testConstructGraduate() {
        assertDoesNotThrow(() -> {
            Graduate student = new Graduate("Alice", 30, 120, Graduate.Degree.MASTERS);
        });
    }

    @Test
    public void testMasterEligible() {
        Graduate student = new Graduate("Bob", 40, 160, Graduate.Degree.MASTERS);
        Undergraduate.setGpaThreshold(3.0);
        assert student.eligibleForHonorSociety();
    }
}
