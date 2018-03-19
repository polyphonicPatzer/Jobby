package com.capstone.jobby.web.controller;

import com.capstone.jobby.algorithm.WeightedChoiceAlgorithm;
import com.capstone.jobby.model.*;
import com.capstone.jobby.service.*;
import com.capstone.jobby.algorithm.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class CandidateController {
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private CandidateTechSkillService candidateTechSkillService;
    @Autowired
    private WeightedChoiceAlgorithm weightedChoiceAlgorithm;
    @Autowired
    private JobService jobService;
    @Autowired
    private DesiredTechSkillService desiredTechSkillService;
    @Autowired
    private DesiredCBSkillService desiredCBSkillService;
    @Autowired
    private MatchService matchService;

    @RequestMapping("/auth/candidate/candidateProfile")
    public String candidateProfile(Model model, Principal principal){

        //Retrieve all necessary components for candidate profile
        Candidate candidate = candidateService.findByUsername(principal.getName());
        Resume resume = candidate.getResume();
        ProfilePic profilePic = candidate.getProfilePic();
        List<CandidateSkill> candidateSkills = candidateSkillService.findSkillsByCandidateId(candidate.getId());

        //If the candidate has taken the survey, handle the matches
        if (candidateSkills.size() > 0) {

            List<CandidateMatchInfo> matchesInfoList = new ArrayList<CandidateMatchInfo>();
            List<Match> matches = matchService.findByCandidateIdOrdered(candidate.getId());
            List<Match> bestMatches = new ArrayList<>();

            //Check for existing matches and add them. If no jobs have been posted then this will
            // make it appear as if the candidate hasn't taken the survey. Pretty much impossible
            // but should still be fixed.

            if (matches.size() < 1) {
                model.addAttribute("matchesInfoList", null);
            } else {

                if (matches.size() > 5) {
                    bestMatches = matches.subList(0, 5);
                } else {
                    bestMatches = matches;
                }

                for (Match match : bestMatches) {
                    CandidateMatchInfo matchInfo = new CandidateMatchInfo();
                    Job job = jobService.findById(match.getJobID());
                    Company company = companyService.findById(job.getCompanyID());
                    matchInfo.setMatchPercentage((int) Math.round(match.getPercent()));
                    matchInfo.setJobId(match.getJobID());
                    matchInfo.setJobName(job.getName());
                    matchInfo.setCompanyId(company.getId());
                    matchInfo.setCompanyName(company.getName());
                    matchInfo.setCompanyCity(company.getCity());
                    matchInfo.setCompanyState(company.getState());
                    matchesInfoList.add(matchInfo);
                }

                model.addAttribute("matchesInfoList", matchesInfoList);
            }
        } else {
            //Otherwise the candidate hasn't taken the survey
            model.addAttribute("matchesInfoList", null);
        }



        //Check for survey and add notifier to model
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

        findMatches(candidateSurveyResults, c.getId());

        return("private/candidate/surveySubmitted");
    }

    public void findMatches(CandidateSurveyResults candidateSurveyResults, Long candidateID){
        List<Job> jobs = jobService.findAll();
        Candidate c = candidateService.findById(candidateID);
        ArrayList<Pair> candidate = new ArrayList<Pair>();
        Integer[] candidateTech = candidateSurveyResults.getTechnical();
        Integer[] candidateCB = candidateSurveyResults.getCB();
        for (int z = 0; z < candidateTech.length; z++){
            Pair p = new Pair(z,candidateTech[z]);
            candidate.add(p);
        }
        for (int z = 0; z < candidateCB.length; z++){
            Pair p = new Pair(z,candidateCB[z]);
            candidate.add(p);
        }

        for (Job job : jobs) {
            List<DesiredCBSkill> DCBS = desiredCBSkillService.findAllByID(job.getId());
            List<DesiredTechSkill> DTS = desiredTechSkillService.findAllByID(job.getId());
            ArrayList<Trio> company = new ArrayList();
            for (DesiredTechSkill t : DTS) {
                Trio temp = new Trio(Math.toIntExact(t.getSkillID()), t.getSkillRating(), t.getSkillWeight());
                company.add(temp);
            }
            for (DesiredCBSkill s : DCBS) {
                Trio temp = new Trio(Math.toIntExact(s.getSkillID()), s.getSkillRating(), s.getSkillWeight());
                company.add(temp);
            }
            double score = weightedChoiceAlgorithm.weightedChoiceAlgorithm(company, candidate);
            Match match = new Match();
            match.setCandidateID(c.getId());
            match.setJobID(job.getId());
            match.setPercent(score);
            matchService.save(match);
        }
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
        Resume resume = candidate.getResume();
        ProfilePic profilePic = candidate.getProfilePic();
        List<CandidateSkill> candidateSkills = candidateSkillService.findSkillsByCandidateId(candidate.getId());
        List<CandidateMatchInfo> matchesInfoList = new ArrayList<CandidateMatchInfo>();


        //Make a query in matchDaoImpl to retrieve top 5 sorted and top 20 sorted
        List<Match> matches = matchService.findByCandidateIdOrdered(candidate.getId());
        for (Match match : matches) {
            CandidateMatchInfo matchInfo = new CandidateMatchInfo();
            Job job = jobService.findById(match.getJobID());
            Company company = companyService.findById(job.getCompanyID());
            matchInfo.setMatchPercentage((int)Math.round(match.getPercent()));
            matchInfo.setJobId(match.getJobID());
            matchInfo.setJobName(job.getName());
            matchInfo.setCompanyId(company.getId());
            matchInfo.setCompanyName(company.getName());
            matchInfo.setCompanyCity(company.getCity());
            matchInfo.setCompanyState(company.getState());
            matchesInfoList.add(matchInfo);
        }

        if (matchesInfoList.size() > 25) {
            model.addAttribute("matchesInfoList", matchesInfoList.subList(0, 25));
        } else {
            model.addAttribute("matchesInfoList", matchesInfoList);
        }

        return "private/candidate/jobMatches";
    }

    @RequestMapping(value = "/auth/candidate/logoutPost", method = RequestMethod.GET)
    public void candidateLogoutPost(Model model) {
        return;
    }


    @RequestMapping(value = "/auth/candidate/apply/{jobId}")
    public String applicationSubmitted(Model model, @PathVariable Long jobId, Principal principal) {
        Candidate candidate = candidateService.findByUsername(principal.getName());
        Job job = jobService.findById(jobId);
        Company company = companyService.findById(job.getCompanyID());
        model.addAttribute("candidateName", candidate.getName().split(" ")[0]);
        model.addAttribute("job", job);
        model.addAttribute("company", company);
        model.addAttribute("heading", "Application Submitted!");
        return "private/candidate/applicationSubmitted";
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
        if (candidateSkillService.findSkillsByCandidateId(candidateId).size() > 0)
            model.addAttribute("survey", true);
        else
            model.addAttribute("survey", false);
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
    public String listCanditateSearch(Model model, @RequestParam String q) {
        List<Candidate> allCandidates = candidateService.findAll();
        if (q.equals("")) {
            model.addAttribute("candidates", allCandidates);
        } else {
            List<Candidate> candidates = new ArrayList<>();
            for (Candidate candidate : allCandidates) {
                for (String word : q.split(" ")) {
                    if (candidate.getName().toLowerCase().indexOf(word.toLowerCase()) >= 0) {
                        if (!candidates.contains(candidate)) {
                            candidates.add(candidate);
                        }
                    }
                }
            }
            model.addAttribute("candidates", candidates);
        }
        return "public/searchResults/candidateSearchResults";
    }

}
