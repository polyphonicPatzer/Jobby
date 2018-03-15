package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.*;
import com.capstone.jobby.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private DesiredCBSkillService desiredCBSkillService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private JobService jobService;
    @Autowired
    private DesiredTechSkillService desiredTechSkillService;

    @RequestMapping("/auth/company/companyProfile")
    public String companyProfile(Model model){
        return "private/company/companyProfile";
    }


    @RequestMapping("/auth/company/survey")
    public String companySurvey(Model model){
        if(!model.containsAttribute("companySurveyResults")) {
            model.addAttribute("companySurveyResults", new CompanySurveyResults());
        }
        if(!model.containsAttribute("job")) {
            model.addAttribute("job", new Job());
        }
        model.addAttribute("action","/auth/company/submitSurvey");
        model.addAttribute("heading","Finish");
        model.addAttribute("submit","Finish");
        return "private/company/survey";
    }

    @RequestMapping(value = "/auth/company/submitSurvey", method = RequestMethod.POST)
    public String submitSurvey(@Valid CompanySurveyResults companySurveyResults, Principal principal) {

        Integer[] techAnswers = companySurveyResults.getTechAnswers();
        Integer[] techWeights = companySurveyResults.getTechWeights();
        Integer[] cbAnswers = companySurveyResults.getCBAnswers();
        Integer[] cbWeights = companySurveyResults.getCBWeights();

        Company c = companyService.findByUsername(principal.getName());
        String companyEmail = c.getEmail();

        Long max = (long)1;
        List<Job> jobs = jobService.findAll();
        for (Job jobo : jobs) {
            if (jobo.getId() >= max) {
                max = jobo.getId();
            }
        }


        Job job = new Job();
        job.setName(companySurveyResults.getName());
        job.setDescription(companySurveyResults.getDescription());
        job.setCompanyID(c.getId());
        job.setId((max+1));
        jobService.save(job);

        List<Skill> skills = skillService.findAll();

        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                DesiredTechSkill temp = new DesiredTechSkill();
                Skill s = skills.get(i);
                temp.setSkillID(s.getId());
                temp.setSkillRating(techAnswers[i]);
                temp.setSkillWeight(techWeights[i]);
                temp.setJobID(max + 1);
                desiredTechSkillService.save(temp);
            }else{
                DesiredCBSkill temp2 = new DesiredCBSkill();
                Skill s = skills.get(i);
                temp2.setSkillID(s.getId());
                temp2.setSkillRating(cbAnswers[i-10]);
                temp2.setSkillWeight(cbWeights[i-10]);
                temp2.setJobID(max + 1);
                desiredCBSkillService.save(temp2);
            }
        }


        return("private/company/surveySubmitted");
    }



    /******************************
     *                            *
     *         Nav Bar Stuff      *
     *                            *
     ******************************/

    //Job Postings
    @RequestMapping(value = "/auth/company/jobPostings")
    public String companyJobPostings(Model model, Principal principal) {
        Company company = companyService.findByUsername(principal.getName());

        //TODO: Write a query to retrieve all job postings using company.getId()
        //List<Job> jobsList = ?????

        //TODO: Add the posted jobs to the model and return the jobPostings template
        //model.addAttribute("jobs", jobsList);

        return "private/company/jobPostings";
    }

    //Employee Matches. This might be altered to return employees matched to a particular job
    //In that case, the mapping will be "/auth/company/employeeMatches/{jobId}"
    @RequestMapping(value = "/auth/company/employeeMatches")
    public String employeeMatches(Model model) {

        //TODO: I think this method should have a @PathVariable for a jobId in the mapping and query for matched candidates

        return "private/company/employeeMatches";
    }



    @RequestMapping(value = "/auth/company/logout")
    public String companyLogout(Model model) {
        model.addAttribute("action","/auth/company/logoutPost");
        model.addAttribute("submit","Logout");
        return "private/company/logout";
    }

    @RequestMapping(value = "/auth/company/logoutPost", method = RequestMethod.GET)
    public void companyLogoutPost(Model model) {
        return;
    }


    /************************************
     *                                  *
     *         Public Facing Stuff      *
     *                                  *
     ************************************/

    // Public facing company profile
    @RequestMapping(value = "/company/{companyId}")
    public String candidateProfile(Model model, @PathVariable Long companyId){
        Candidate candidate = candidateService.findById(companyId);
        model.addAttribute("company", candidate);
        return "public/company/companyProfile";
    }

    /************************************
     *                                  *
     *         Search Results Stuff     *
     *                                  *
     ************************************/

    //Search for all companies
    @SuppressWarnings("unchecked")
    @RequestMapping("/company/search")
    public String listCompaniesSearch(Model model) {
        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);
        return "public/searchResults/companySearchResults";
    }

}
