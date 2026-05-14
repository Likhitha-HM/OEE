package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SurveyController {

    @GetMapping("/")
    public String showForm() {

        return "survey";

    }

    @PostMapping("/submit")
    public String submitSurvey(

            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String department,
            @RequestParam String year,
            @RequestParam int placement,
            @RequestParam int facilities,
            @RequestParam int teaching,
            @RequestParam int education,
            @RequestParam int activities,
            @RequestParam String feedback,
            @RequestParam String suggestions,

            Model model) {

        model.addAttribute("name", name);

        model.addAttribute("message",
                "Thank you for submitting the College Campus Survey!");

        model.addAttribute("department", department);

        model.addAttribute("year", year);

        model.addAttribute("average",
                (placement + facilities + teaching +
                 education + activities) / 5.0);

        return "result";
    }
}
