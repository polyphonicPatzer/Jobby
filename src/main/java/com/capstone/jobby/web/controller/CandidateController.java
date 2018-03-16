package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.*;
import com.capstone.jobby.service.CandidateSkillService;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.CandidateTechSkillService;
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
    @Autowired
    private CandidateTechSkillService candidateTechSkillService;

    @RequestMapping("/auth/candidate/candidateProfile")
    public String candidateProfile(Model model, Principal principal){
        Candidate candidate = candidateService.findByUsername(principal.getName());
        Resume resume = candidate.getResume();
        ProfilePic profilePic = candidate.getProfilePic();
        List<CandidateSkill> candidateSkills = candidateSkillService.findSkillsByCandidateId(candidate.getId());
        if (candidateSkills.size() != 0)
            model.addAttribute("surveyCompleted",true);
        else
            model.addAttribute("surveyCompleted",false);
        model.addAttribute("resume", resume);
        model.addAttribute("profilePic", profilePic);
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
    public String submitSurvey(@Valid CandidateSurveyResults candidateSurveyResults, Model model, Principal principal) {
        Integer[] tech = candidateSurveyResults.getTechnical();
        Integer[] CB = candidateSurveyResults.getCB();

        for (int x : CB){
            System.out.println(x);
        }

        Candidate c = candidateService.findByUsername(principal.getName());
        String candidateEmail = c.getEmail();

        List<Skill> skills = skillService.findAll();

        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                CandidateTechSkill temp2 = new CandidateTechSkill();
                Skill s = skills.get(i);
                temp2.setSkillID(s.getId());
                temp2.setCandidateID(candidateService.findByUsername(candidateEmail).getId());
                temp2.setSkillRating(CB[i]);
                candidateTechSkillService.save(temp2);
            }else{
                CandidateSkill temp = new CandidateSkill();
                Skill s = skills.get(i);
                temp.setSkillID(s.getId());
                temp.setCandidateID(candidateService.findByUsername(candidateEmail).getId());
                temp.setSkillRating(tech[i-10]);
                candidateSkillService.save(temp);
                }
        }
        return("private/candidate/surveySubmitted");
    }

    @RequestMapping(value = "/auth/candidate/logout")
    public String candidateLogout(Model model) {
        model.addAttribute("action","/auth/candidate/logoutPost");
        model.addAttribute("submit","Logout");
        return "private/candidate/logout";
    }

    @RequestMapping(value = "/auth/candidate/jobMatches")
    public String candidateJobMatches(Model model, Principal principal) {
        Candidate candidate = candidateService.findByUsername(principal.getName());

        //TODO: Write a query using candidate.getID() and the algo to get the job matches
        //List<Job> jobList = ?????

        //TODO: Once matches are obtained, add the list of them to the model
        //model.addAttribute("jobs", jobList);

        return "private/candidate/jobMatches";
    }

    @RequestMapping(value = "/auth/candidate/logoutPost", method = RequestMethod.GET)
    public void candidateLogoutPost(Model model) {
        return;
    }

    /************************************
     *                                  *
     *         Public Facing Stuff      *
     *                                  *
     ************************************/

    // Public facing candidate profile
    @RequestMapping(value = "/candidate/{candidateId}")
    public String candidateProfile(Model model, @PathVariable Long candidateId){
        Candidate candidate = candidateService.findById(candidateId);
        model.addAttribute("candidate", candidate);
        model.addAttribute("resume", candidate.getResume());
        model.addAttribute("profilePic", candidate.getProfilePic());
        return "public/candidate/candidateProfile";
    }


    /************************************
     *                                  *
     *         Search Results Stuff     *
     *                                  *
     ************************************/

    //Search for all candidates
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/candidate/search")
    public String listCanditateSearch(Model model) {
        List<Candidate> candidates = candidateService.findAll();
        model.addAttribute("candidates", candidates);
        return "public/searchResults/candidateSearchResults";
    }

}
