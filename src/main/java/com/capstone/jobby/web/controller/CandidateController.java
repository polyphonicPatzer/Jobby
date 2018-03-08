package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.CandidateSurveyResults;
import com.capstone.jobby.service.CandidateSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.capstone.jobby.model.CandidateSkill;

import javax.validation.Valid;

@Controller
public class CandidateController {
    @Autowired
    private CandidateSkillService candidateSkillService;

    @RequestMapping("/candidate/candidateProfile")
    public String candidateProfile(Model model){
        return "candidate/candidateProfile";
    }

    @RequestMapping("/candidate/survey")
    public String candidateSurvey(Model model){
        return "candidate/survey";
    }

    @RequestMapping(value = "/candidate/submitSurvey", method = RequestMethod.POST)
    public void submitSurvey(@Valid CandidateSurveyResults results, RedirectAttributes redirectAttributes){
        Integer[] res = results.getResults();
        for (int i = 0; i<10; i++){
            CandidateSkill temp = new CandidateSkill();
            temp.setSkill(res[i]);
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
