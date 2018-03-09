package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.CandidateSurveyResults;
import com.capstone.jobby.model.Skill;
import com.capstone.jobby.model.Candidate;
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
import com.capstone.jobby.model.CandidateSkill;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import javax.validation.Valid;
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
    public String candidateProfile(Model model){
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
        return("/candidate/candidateProfile");
    }

    @RequestMapping(value = "/candidate/logout")
    public String candidateLogout(Model model) {
        model.addAttribute("action","/candidate/logoutPost");
        model.addAttribute("submit","Logout");
        return "candidate/logout";
    }

    @RequestMapping(value = "/candidate/logoutPost", method = RequestMethod.GET)
    public void candidateLogoutPost(Model model) {
        return;
    }

}
