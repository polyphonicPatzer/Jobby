package com.capstone.jobby.web.controller;

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

    // Public facing candidate Survey Results
    @RequestMapping(value = "/candidateSurveyResults/{candidateId}")
    public String candidateSurvey(Model model, @PathVariable Long candidateId){
        //List<CandidateSkill> candidateSkills = candidateSkillService.findAllById(candidateId);
        return "account/viewSurveyResults";
    }
}
