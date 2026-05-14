package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
            @RequestParam int rating,
            Model model) {

        model.addAttribute("name", name);

        model.addAttribute(
                "message",
                "Thank you! You rated the campus " + rating + "/5"
        );

        return "result";
    }

}
