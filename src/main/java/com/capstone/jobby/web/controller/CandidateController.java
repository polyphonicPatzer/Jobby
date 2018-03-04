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

    @RequestMapping("/candidate_profile")
    public String candidateProfile(Model model){
        return "candidate/candidateProfile";
    }

    @RequestMapping("/survey")
    public String candidateSurvey(Model model){
        return "candidate/survey";
    }

}
