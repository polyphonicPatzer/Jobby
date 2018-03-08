package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.CandidateSurveyResults;
import com.capstone.jobby.model.Skill;
import com.capstone.jobby.model.Candidate;
import com.capstone.jobby.service.CandidateSkillService;
import com.capstone.jobby.service.CandidateService;

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

@Controller
public class CandidateController {
    @Autowired
    private CandidateSkillService candidateSkillService;

    @Autowired
    private CandidateService candidateService;

    @RequestMapping("/candidate/candidateProfile")
    public String candidateProfile(Model model){
        model.addAttribute("action","/candidate/submitSurvey");
        return "candidate/candidateProfile";
    }

    @RequestMapping("/candidate/survey")
    public String candidateSurvey(Model model){
        return "candidate/survey";
    }

    @RequestMapping(value = "/candidate/submitSurvey", method = RequestMethod.POST)
    public String submitSurvey(@Valid CandidateSurveyResults results, Model model, HttpServletRequest request) {
        Integer[] res = results.getResults();
        String userEmail = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userEmail = cookie.getName();
            }
        }
        for (int i = 0; i < 10; i++) {
            CandidateSkill temp = new CandidateSkill();
            Skill skill = new Skill();
            skill.setId(i);
            skill.setName(String.format("Q(%d)", i));
            temp.setSkill(skill);
            temp.setCandidate(candidateService.findByUsername(userEmail));
            temp.setSkillRating(res[i]);
            candidateSkillService.save(temp);
        }
        return("redirect:candidate/candidateProfile");
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
