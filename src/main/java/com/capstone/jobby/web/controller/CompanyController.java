package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.CompanySurveyResults;
import com.capstone.jobby.model.DesiredCBSkill;
import com.capstone.jobby.model.Skill;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.CompanyService;
import com.capstone.jobby.service.DesiredCBSkillService;
import com.capstone.jobby.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private DesiredCBSkillService desiredCBSkillService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private SkillService skillService;

    @RequestMapping("/company/companyProfile")
    public String companyProfile(Model model){
        return "company/companyProfile";
    }

    @RequestMapping("/company/survey")
    public String companySurvey(Model model){
        if(!model.containsAttribute("companySurveyResults")) {
            model.addAttribute("companySurveyResults", new CompanySurveyResults());
        }
        model.addAttribute("action","/company/submitSurvey");
        model.addAttribute("heading","Finish");
        model.addAttribute("submit","Finish");
        return "company/survey";
    }

    @RequestMapping(value = "/company/submitSurvey", method = RequestMethod.POST)
    public String submitSurvey(@Valid CompanySurveyResults companySurveyResults, Model model, HttpServletRequest request) {

        Integer[] answers = companySurveyResults.getAnswers();
        Integer[] weights = companySurveyResults.getWeights();

        String companyEmail = null;
        String companyID = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("EMAIL")) {
                companyEmail = cookie.getValue();
            }
            if (cookie.getName().equals("ID")) {
                companyID = cookie.getValue();
            }
        }
        List<Skill> skills = skillService.findAll();

        for (int i = 0; i < 10; i++) {
            DesiredCBSkill temp = new DesiredCBSkill();
            Skill s = skills.get(i);
            temp.setSkill(s);
            temp.setCandidate(candidateService.findByUsername(companyEmail));
            temp.setSkillRating(answers[i]);
            temp.setSkillWeight(weights[i]);
            desiredCBSkillService.save(temp);
        }
        return("/candidate/companyProfile");
    }

    @RequestMapping(value = "/company/logout")
    public String companyLogout(Model model) {
        model.addAttribute("action","/company/logoutPost");
        model.addAttribute("submit","Logout");
        return "company/logout";
    }

    @RequestMapping(value = "/company/logoutPost", method = RequestMethod.GET)
    public void companyLogoutPost(Model model) {
        return;
    }
}
