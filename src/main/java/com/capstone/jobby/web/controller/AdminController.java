package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.*;
import com.capstone.jobby.service.*;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller()
public class AdminController {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private JobService jobService;
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private CandidateTechSkillService candidateTechSkillService;
    @Autowired
    private DesiredCBSkillService desiredCBSkillService;
    @Autowired
    private DesiredTechSkillService desiredTechSkillService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private ProfilePicService profilePicService;
    @Autowired
    private ResumeService resumeService;

    // Administrator Dashboard
    @RequestMapping(value = "/auth/admin/adminDashboard")
    public String adminDashboard(Model model){
        return "private/admin/adminDashboard";
    }

    @RequestMapping(value = "/auth/admin/logout")
    public String adminLogout(Model model) {
        model.addAttribute("action","/auth/admin/logoutPost");
        model.addAttribute("submit","Logout");
        return "private/admin/logout";
    }

    @RequestMapping(value = "/auth/admin/logoutPost", method = RequestMethod.GET)
    public void adminLogoutPost(Model model) {
        return;
    }


    /*************************************
     *                                   *
     *         Candidate Queries         *
     *                                   *
     *************************************/

    @RequestMapping(value = "/auth/admin/adminDashboard/query/findAllCandidates")
    public String findAllCandidates(Model model) {
        List<Candidate> candidates = candidateService.findAll();
        model.addAttribute("candidates", candidates);
        return "private/admin/findAllQuery";
    }





    /*************************************
     *                                   *
     *          Company Queries          *
     *                                   *
     *************************************/

    @RequestMapping(value = "/auth/admin/adminDashboard/query/findAllCompanies")
    public String findAllCompanies(Model model) {
        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);
        return "private/admin/findAllQuery";
    }




    /*******************************
     *                             *
     *         Job Queries         *
     *                             *
     *******************************/

    @RequestMapping(value = "/auth/admin/adminDashboard/query/findAllJobs")
    public String findAllJobs(Model model) {
        List<Job> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);
        return "private/admin/findAllQuery";
    }




//    /******************************************
//     *                                        *
//     *         CandidateSkill Queries         *
//     *                                        *
//     ******************************************/
//
//    @RequestMapping(value = "/auth/admin/adminDashboard/query/findAllCandidateSkills")
//    public String findAllCandidateSkills(Model model) {
//        List<CandidateSkill> candidateSkills = candidateSkillService.findAll();
//        model.addAttribute("candidateSkills", candidateSkills);
//        return "private/admin/findAllQuery";
//    }
//
//
//
//
//    /**********************************************
//     *                                            *
//     *         CandidateTechSkill Queries         *
//     *                                            *
//     **********************************************/
//
//    @RequestMapping(value = "/auth/admin/adminDashboard/query/findAllCandidateTechSkills")
//    public String findAllCandidateTechSkills(Model model) {
//        List<CandidateTechSkill> candidateTechSkills = candidateTechSkillService.findAll();
//        model.addAttribute("candidateTechSkills", candidateTechSkills);
//        return "private/admin/findAllQuery";
//    }
//
//
//
//
//    /******************************************
//     *                                        *
//     *         DesiredCBSkill Queries         *
//     *                                        *
//     ******************************************/
//
//    @RequestMapping(value = "/auth/admin/adminDashboard/query/findAllDesiredCBSkills")
//    public String findAllDesiredCBSkills(Model model) {
//        List<DesiredCBSkill> desiredCBSkills = desiredCBSkillService.findAll();
//        model.addAttribute("desiredCBSkills", desiredCBSkills);
//        return "private/admin/findAllQuery";
//    }
//
//
//
//
//    /********************************************
//     *                                          *
//     *         DesiredTechSkill Queries         *
//     *                                          *
//     ********************************************/
//
//    @RequestMapping(value = "/auth/admin/adminDashboard/query/findAllDesiredTechSkills")
//    public String findAllDesiredTechSkills(Model model) {
//        List<DesiredTechSkill> desiredTechSkills = desiredTechSkillService.findAll();
//        model.addAttribute("desiredTechSkills", desiredTechSkills);
//        return "private/admin/findAllQuery";
//    }
//
//
//
//
//    /*********************************
//     *                               *
//     *         Match Queries         *
//     *                               *
//     *********************************/
//
//    @RequestMapping(value = "/auth/admin/adminDashboard/query/findAllMatches")
//    public String findAllMatches(Model model) {
//        List<Match> matches = matchService.findAll();
//        model.addAttribute("matches", matches);
//        return "private/admin/findAllQuery";
//    }
//
//
//
//
//    /**************************************
//     *                                    *
//     *         ProfilePic Queries         *
//     *                                    *
//     **************************************/
//
//    @RequestMapping(value = "/auth/admin/adminDashboard/query/findAllProfilePics")
//    public String findAllProfilePics(Model model) {
//        List<ProfilePic> profilePics = profilePicService.findAll();
//        model.addAttribute("profilePics", profilePics);
//        return "private/admin/findAllQuery";
//    }
//
//
//
//
//    /**********************************
//     *                                *
//     *         Resume Queries         *
//     *                                *
//     **********************************/
//
//    @RequestMapping(value = "/auth/admin/adminDashboard/query/findAllResumes")
//    public String findAllResumes(Model model) {
//        List<Resume> resumes = resumeService.findAll();
//        model.addAttribute("resumes", resumes);
//        return "private/admin/findAllQuery";
//    }






}
