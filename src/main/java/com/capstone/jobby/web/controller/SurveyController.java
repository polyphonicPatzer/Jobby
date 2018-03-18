package com.capstone.jobby.web.controller;


import com.capstone.jobby.model.CandidateTechSkill;
import com.capstone.jobby.model.Skill;
import com.capstone.jobby.model.Candidate;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.CandidateTechSkillService;
import com.capstone.jobby.service.SkillService;
import com.capstone.jobby.model.CandidateSkill;
import com.capstone.jobby.service.CandidateSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
public class SurveyController {
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private CandidateTechSkillService candidateTechSkillService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private CandidateService candidateService;

    // Public facing candidate Survey Results
    @RequestMapping(value = "/candidateSurveyResults/{candidateId}")
    public String candidateSurvey(Model model, @PathVariable Long candidateId){

        Candidate candidate = candidateService.findById(candidateId);
        model.addAttribute(candidate);

        List<Skill> skills = skillService.findAll();
        model.addAttribute("skills", skills);

        List<CandidateSkill> candidateSkills = candidateSkillService.findSkillsByCandidateId(candidateId);
        model.addAttribute("candidateSkills", candidateSkills);

        List<CandidateTechSkill> candidateTechSkills = candidateTechSkillService.findAllByID(candidateId);
        model.addAttribute("candidateTechSkills", candidateTechSkills);

        return "public/candidateSurvey/viewSurveyResults";
    }
}
