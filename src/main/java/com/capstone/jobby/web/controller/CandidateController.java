package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.*;
import com.capstone.jobby.service.CandidateSkillService;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.SkillService;

import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class CandidateController {
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private SkillService skillService;

    @RequestMapping("/auth/candidate/candidateProfile")
    public String candidateProfile(Model model, Principal principal){
        Candidate candidate = candidateService.findByUsername(principal.getName());

        Resume resume = candidate.getResume();
        model.addAttribute("resume", resume);
        return "private/candidate/candidateProfile";
    }

    @RequestMapping("/auth/candidate/survey")
    public String candidateSurvey(Model model){
        if(!model.containsAttribute("candidateSurveyResults")) {
            model.addAttribute("candidateSurveyResults",new CandidateSurveyResults());
        }
        model.addAttribute("action","/auth/candidate/submitSurvey");
        model.addAttribute("heading","Finish");
        model.addAttribute("submit","Finish");
        return "private/candidate/survey";
    }

    @RequestMapping(value = "/auth/candidate/submitSurvey", method = RequestMethod.POST)
    public String submitSurvey(@Valid CandidateSurveyResults candidateSurveyResults, Model model, HttpServletRequest request) {

        Integer[] res = candidateSurveyResults.getResults();

        String userEmail = null;
        String userID = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("EMAIL")) {
                userEmail = cookie.getValue();
            }
            if (cookie.getName().equals("ID")) {
                userID = cookie.getValue();
            }
        }
        List<Skill> skills = skillService.findAll();

        for (int i = 0; i < 10; i++) {
            CandidateSkill temp = new CandidateSkill();
            Skill s = skills.get(i);
            temp.setSkill(s);
            temp.setCandidate(candidateService.findByUsername(userEmail));
            temp.setSkillRating(res[i]);
            candidateSkillService.save(temp);
        }
        return("private/candidate/surveySubmitted");
    }

    @RequestMapping(value = "/auth/candidate/logout")
    public String candidateLogout(Model model) {
        model.addAttribute("action","/auth/candidate/logoutPost");
        model.addAttribute("submit","Logout");
        return "private/candidate/logout";
    }

/*    @RequestMapping(value = "/candidate/surveySubmitted")
    public String surveySubmitted(Model model) {
        return "candidate/surveySubmitted";
    }*/

    @RequestMapping(value = "/auth/candidate/logoutPost", method = RequestMethod.GET)
    public void candidateLogoutPost(Model model) {
        return;
    }



    // Public facing candidate profile
    @RequestMapping(value = "/candidate/{candidateId}")
    public String candidateProfile(Model model, @PathVariable Long candidateId){
        Candidate candidate = candidateService.findById(candidateId);
        model.addAttribute("candidate", candidate);
        return "public/candidate/candidateProfile";
    }

}
