package com.vimpirate.students;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestUndergraduate {

    @Test
    public void testConstructUndergraduate() {
        assertDoesNotThrow(() -> {
            Undergraduate undergraduate = new Undergraduate("Alice", 30, 120, Undergraduate.Year.JUNIOR);
        });
    }

    @Test
    public void testJuniorEligible() {
        Undergraduate undergraduate = new Undergraduate("Bob", 40, 160, Undergraduate.Year.JUNIOR);
        Undergraduate.setGpaThreshold(3.0);
        assert undergraduate.eligibleForHonorSociety();
    }

    @Test
    public void testSeniorEligible() {
        Undergraduate undergraduate = new Undergraduate("Bob", 40, 160, Undergraduate.Year.SENIOR);
        Undergraduate.setGpaThreshold(3.0);
        assert undergraduate.eligibleForHonorSociety();
    }

    @Test
    public void testToStringContainsYear() {
        Undergraduate undergraduate = new Undergraduate("Charlie", 20, 80, Undergraduate.Year.FRESHMAN);
        String result = undergraduate.toString();
        assert result.contains("FRESHMAN");
    }
}
