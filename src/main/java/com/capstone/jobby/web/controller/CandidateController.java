package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.Candidate;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    // Form for creating a new candidate
    @RequestMapping("/candidateRegistration")
    public String formNewCandidate(Model model) {
        // Add model attributes needed for new Candidate upload form
        if(!model.containsAttribute("candidate")) {
            model.addAttribute("candidate",new Candidate());
        }
        model.addAttribute("action","/addCandidate");
        model.addAttribute("heading","Sign Up");
        model.addAttribute("submit","Sign Up");

        return "candidate/registrationForm";
    }

    // Add a new Candidate
    @RequestMapping(value = "/addCandidate", method = RequestMethod.POST)
    public String addCandidate(@Valid Candidate candidate, BindingResult result, RedirectAttributes redirectAttributes) {
        // Upload new Candidate if data is valid
        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.candidate", result);

            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("candidate", candidate);

            // Redirect back to the form
            return "redirect:/candidateRegistration";
        }

        candidateService.save(candidate);

        // Add flash message for success
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Account successfully created!", FlashMessage.Status.SUCCESS));

        // Redirect browser to new Candidate's home view. Replace this with homepage when created.
        return String.format("redirect:/candidate/%s","..");
    }

}
