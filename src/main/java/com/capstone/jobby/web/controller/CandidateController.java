package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.*;
import com.capstone.jobby.service.CandidateSkillService;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.SkillService;

import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/candidate/candidateProfile")
    public String candidateProfile(Model model, Principal principal ){
        Candidate candidate = candidateService.findByUsername(principal.getName());
        Cookie email = new Cookie("EMAIL", candidate.getEmail());
        Cookie id = new Cookie("ID", Long.toString(candidate.getId()));
        email.setPath("/");
        id.setPath("/");
        Resume resume = candidate.getResume();
        model.addAttribute("resume", resume);
        return "candidate/candidateProfile";
    }

    @RequestMapping("/candidate/survey")
    public String candidateSurvey(Model model){
        if(!model.containsAttribute("candidateSurveyResults")) {
            model.addAttribute("candidateSurveyResults",new CandidateSurveyResults());
        }
        model.addAttribute("action","/candidate/submitSurvey");
        model.addAttribute("heading","Finish");
        model.addAttribute("submit","Finish");
        return "candidate/survey";
    }

    @RequestMapping(value = "/candidate/submitSurvey", method = RequestMethod.POST)
    public String submitSurvey(@Valid CandidateSurveyResults candidateSurveyResults, Model model, Principal principal) {

        Integer[] res = candidateSurveyResults.getResults();

        Candidate c = candidateService.findByUsername(principal.getName());
        String candidateEmail = c.getEmail();

        List<Skill> skills = skillService.findAll();

        for (int i = 0; i < 10; i++) {
            CandidateSkill temp = new CandidateSkill();
            Skill s = skills.get(i);
            temp.setSkillID(s.getId());
            temp.setCandidateID(candidateService.findByUsername(candidateEmail).getId());
            temp.setSkillRating(res[i]);
            candidateSkillService.save(temp);
        }
        return("/candidate/surveySubmitted");
    }

    @RequestMapping(value = "/candidate/logout")
    public String candidateLogout(Model model) {
        model.addAttribute("action","/candidate/logoutPost");
        model.addAttribute("submit","Logout");
        return "candidate/logout";
    }

/*    @RequestMapping(value = "/candidate/surveySubmitted")
    public String surveySubmitted(Model model) {
        return "candidate/surveySubmitted";
    }*/

    @RequestMapping(value = "/candidate/logoutPost", method = RequestMethod.GET)
    public void candidateLogoutPost(Model model) {
        return;
    }

}
