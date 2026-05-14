package com.example.app;

public class App {
    public String submitSurvey(String studentName, int campusRating, int academicRating) {
        if (campusRating < 1 || campusRating > 5 || academicRating < 1 || academicRating > 5) {
            return "Error: Ratings must be between 1 and 5";
        }
        
        double average = (campusRating + academicRating) / 2.0;
        return String.format("Survey processed for %s. Overall Satisfaction: %.1f/5.0", 
                              studentName, average);
    }

    public static void main(String[] args) {
        App surveyApp = new App();
        System.out.println("--- College OEE Survey System ---");
        // Simulated real-time input
        String response = surveyApp.submitSurvey("John Doe", 4, 5);
        System.out.println(response);
    }
}
