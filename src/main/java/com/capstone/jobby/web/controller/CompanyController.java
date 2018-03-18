package com.capstone.jobby.web.controller;

import com.capstone.jobby.algorithm.Pair;
import com.capstone.jobby.algorithm.Trio;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.capstone.jobby.algorithm.WeightedChoiceAlgorithm;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private CandidateTechSkillService candidateTechSkillService;
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private MatchService matchService;

    @RequestMapping("/auth/company/companyProfile")
    public String companyProfile(Model model, Principal principal){
        Company company = companyService.findByUsername(principal.getName());
        List<Job> jobs = jobService.findJobsByCompanyId(company.getId());

        //If company has posted jobs, handle matches
        if (jobs.size() > 0) {
            List<Match> allMatches = new ArrayList<>();

            for (Job job : jobs) {
                List<Match> matches = matchService.findByJobIdOrdered(job.getId());
                allMatches.addAll(matches);
            }

            if (allMatches.size() < 1) {
                model.addAttribute("matchesInfoList", null);
            } else {

                //Sort
                Collections.sort(allMatches);
                List<Match> bestMatches = new ArrayList<>();

                //Get top 5
                if (allMatches.size() > 5) {
                    bestMatches = allMatches.subList(0, 5);
                } else {
                    bestMatches = allMatches;
                }

                //Create a list for the info of the matches
                List<CompanyMatchInfo> matchesInfoList = new ArrayList<>();

                for (Match match : bestMatches) {
                    CompanyMatchInfo matchInfo = new CompanyMatchInfo();
                    Job job = jobService.findById(match.getJobID());
                    Candidate  candidate = candidateService.findById(match.getCandidateID());

                    //Update relevant info
                    matchInfo.setJobId(job.getId());
                    matchInfo.setJobName(job.getName());
                    matchInfo.setCandidateId(candidate.getId());
                    matchInfo.setCandidateName(candidate.getName());
                    matchInfo.setMatchPercentage((int)Math.round(match.getPercent()));

                    matchesInfoList.add(matchInfo);
                }

                model.addAttribute("matchesInfoList", matchesInfoList);
            }

        } else {
            //Otherwise there are no matches because there are no jobs
            model.addAttribute("matchesInfoList", null);
        }


        Collections.reverse(jobs);
        if (jobs.size() == 0)
            model.addAttribute("jobs", null);
        else if (jobs.size() > 5)
            model.addAttribute("jobs", jobs.subList(0, 5));
        else
            model.addAttribute("jobs", jobs);

        model.addAttribute("company", company);

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

        findMatches(companySurveyResults, job.getId());

        return("private/company/surveySubmitted");
    }

    public void findMatches(CompanySurveyResults companySurveyResults, Long jobID){
        List<Candidate> candidates = candidateService.findAll();
        ArrayList<Trio> job = new ArrayList<Trio>();
        Integer[] jobTechA = companySurveyResults.getTechAnswers();
        Integer[] jobTechW = companySurveyResults.getTechWeights();
        Integer[] jobCBA = companySurveyResults.getCBAnswers();
        Integer[] jobCBAW = companySurveyResults.getCBWeights();
        for (int z = 0; z < jobTechA.length; z++){
            Trio p = new Trio(z,jobTechA[z],jobTechW[z]);
            job.add(p);
        }
        for (int z = 0; z < jobCBA.length; z++){
            Trio p = new Trio(z,jobCBA[z], jobCBAW[z]);
            job.add(p);
        }

        for (Candidate candidate : candidates) {
            List<CandidateTechSkill> CTS = candidateTechSkillService.findAllByID(candidate.getId());
            List<CandidateSkill> CCBS = candidateSkillService.findAllByID(candidate.getId());
            ArrayList<Pair> cand = new ArrayList();
            for (CandidateTechSkill t : CTS) {
                Pair temp = new Pair(Math.toIntExact(t.getSkillID()), t.getSkillRating());
                cand.add(temp);
            }
            for (CandidateSkill s : CCBS) {
                Pair temp = new Pair(Math.toIntExact(s.getSkillID()), s.getSkillRating());
                cand.add(temp);
            }
            double score = WeightedChoiceAlgorithm.weightedChoiceAlgorithm(job, cand);
            Match match = new Match();
            match.setCandidateID(candidate.getId());
            match.setJobID(jobID);
            match.setPercent(score);
            matchService.save(match);
        }
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
        List<Job> jobs = jobService.findJobsByCompanyId(company.getId());
        Collections.reverse(jobs);
        model.addAttribute("jobs", jobs);
        model.addAttribute("company", company);

        return "private/company/jobPostings";
    }

    //Employee Matches. This might be altered to return employees matched to a particular job
    //In that case, the mapping will be "/auth/company/employeeMatches/{jobId}"
    @RequestMapping(value = "/auth/company/employeeMatches/{jobId}")
    public String employeeMatches(Model model, @PathVariable Long jobId, Principal principal) {
        Company company = companyService.findByUsername(principal.getName());
        Job job = jobService.findById(jobId);

        //If the job isn't null, handle matches
        if (job != null) {

            List<Match> allMatches = matchService.findByJobIdOrdered(job.getId());

            if (allMatches.size() < 1) {
                model.addAttribute("matchesInfoList", null);
            } else {

                //Sort
                Collections.sort(allMatches);
                List<Match> bestMatches = new ArrayList<>();

                //Get top 5
                if (allMatches.size() > 15) {
                    bestMatches = allMatches.subList(0, 15);
                } else {
                    bestMatches = allMatches;
                }

                //Create a list for the info of the matches
                List<CompanyMatchInfo> matchesInfoList = new ArrayList<>();

                for (Match match : bestMatches) {
                    CompanyMatchInfo matchInfo = new CompanyMatchInfo();
                    Candidate  candidate = candidateService.findById(match.getCandidateID());

                    //Update relevant info
                    matchInfo.setJobId(job.getId());
                    matchInfo.setJobName(job.getName());
                    matchInfo.setCandidateId(candidate.getId());
                    matchInfo.setCandidateName(candidate.getName());
                    matchInfo.setMatchPercentage((int)Math.round(match.getPercent()));

                    matchesInfoList.add(matchInfo);
                }

                model.addAttribute("matchesInfoList", matchesInfoList);
                model.addAttribute("job", job);
            }

        } else {
            //Otherwise there are no matches because there is no job with id equals jobId
            model.addAttribute("matchesInfoList", null);
            model.addAttribute("job", null);

        }

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
        Company company = companyService.findById(companyId);
        List<Job> jobs = jobService.findJobsByCompanyId(companyId);
        model.addAttribute("company", company);
        model.addAttribute("jobs", jobs);
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
    public String listCompaniesSearch(Model model, @RequestParam String q) {
        List<Company> allCompanies = companyService.findAll();
        if (q.equals("")) {
            model.addAttribute("companies", allCompanies);
        } else {
            List<Company> companies = new ArrayList<>();
            for (Company company : allCompanies) {
                for (String word : q.split(" ")) {
                    if (company.getName().toLowerCase().indexOf(word.toLowerCase()) >= 0 || company.getCity().toLowerCase().indexOf(word.toLowerCase()) >= 0 || company.getState().toLowerCase().indexOf(word.toLowerCase()) >= 0) {
                        if (!companies.contains(company)) {
                            companies.add(company);
                        }
                    }
                }
            }
            model.addAttribute("companies", companies);
        }
        return "public/searchResults/companySearchResults";
    }

}
