package com.example.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testValidSurvey() {
        App app = new App();
        String result = app.submitSurvey("Alice", 5, 5);
        assertEquals("Survey processed for Alice. Overall Satisfaction: 5.0/5.0", result);
    }

    @Test
    public void testInvalidRating() {
        App app = new App();
        String result = app.submitSurvey("Bob", 10, 5);
        assertTrue(result.contains("Error"));
    }
}
