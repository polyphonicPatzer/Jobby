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
import java.security.Principal;
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
    private MatchService matchService;
//    @Autowired
//    private CandidateSkillService candidateSkillService;
//    @Autowired
//    private CandidateTechSkillService candidateTechSkillService;
//    @Autowired
//    private DesiredCBSkillService desiredCBSkillService;
//    @Autowired
//    private DesiredTechSkillService desiredTechSkillService;

//    @Autowired
//    private ProfilePicService profilePicService;
//    @Autowired
//    private ResumeService resumeService;

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

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/findCandidateById")
    public String findCandidateById(Model model, @RequestParam String q) {
        Candidate candidate = candidateService.findById(Long.parseLong(q));
        model.addAttribute("candidate", candidate);
        return "private/admin/findByIdQuery";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/findCandidateByEmail")
    public String findCandidateByEmail(Model model, @RequestParam String q) {
        Candidate candidate = candidateService.findByUsername(q);
        model.addAttribute("candidate", candidate);
        return "private/admin/findByEmailQuery";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/activateCandidateById", method = RequestMethod.POST)
    public String activateCandidateById(@RequestParam String q, RedirectAttributes redirectAttributes) {
        Candidate candidate = candidateService.findById(Long.parseLong(q));
        if (candidate != null) {
            if (!candidate.isEnabled()) {
                candidate.setEnabled(true);
                candidateService.save(candidate);
                redirectAttributes.addFlashAttribute("flash", new FlashMessage("Candidate account activated!", FlashMessage.Status.SUCCESS));
            }
        }
        return String.format("redirect:/auth/admin/adminDashboard/query/findCandidateById?q=%s",q);
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/deactivateCandidateById", method = RequestMethod.POST)
    public String deactivateCandidateById(@RequestParam String q, RedirectAttributes redirectAttributes) {
        Candidate candidate = candidateService.findById(Long.parseLong(q));
        if (candidate != null) {
            if (candidate.isEnabled()) {
                candidate.setEnabled(false);
                candidateService.save(candidate);
                redirectAttributes.addFlashAttribute("flash", new FlashMessage("Candidate account already deactivated...", FlashMessage.Status.FAILURE));
            }
        }
        return String.format("redirect:/auth/admin/adminDashboard/query/findCandidateById?q=%s",q);
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/deleteCandidateById", method = RequestMethod.POST)
    public String deleteCandidateById(@RequestParam String q, RedirectAttributes redirectAttributes) {
        Candidate candidate = candidateService.findById(Long.parseLong(q));
        if (candidate != null) {
            candidateService.delete(candidate);
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Candidate deleted!", FlashMessage.Status.SUCCESS));
        } else {
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("No such candidate...", FlashMessage.Status.FAILURE));
        }
        return String.format("redirect:/auth/admin/adminDashboard");
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

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/findCompanyById")
    public String findCompanyById(Model model, @RequestParam String q) {
        Company company = companyService.findById(Long.parseLong(q));
        if (company != null) {
            List<Job> jobs = jobService.findJobsByCompanyId(company.getId());
            boolean hasJobs = jobs.size() > 0;
            model.addAttribute("company", company);
            model.addAttribute("hasJobs", hasJobs);
            model.addAttribute("jobs", jobs);
        } else {
            model.addAttribute("company", null);
        }
        return "private/admin/findByIdQuery";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/findCompanyByEmail")
    public String findCompanyByEmail(Model model, @RequestParam String q) {
        Company company = companyService.findByUsername(q);
        if (company != null) {
            List<Job> jobs = jobService.findJobsByCompanyId(company.getId());
            boolean hasJobs = jobs.size() > 0;
            model.addAttribute("company", company);
            model.addAttribute("hasJobs", hasJobs);
            model.addAttribute("jobs", jobs);
        } else {
            model.addAttribute("company", null);
        }
        return "private/admin/findByEmailQuery";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/activateCompanyById", method = RequestMethod.POST)
    public String activateCompanyById(@RequestParam String q, RedirectAttributes redirectAttributes) {
        Company company = companyService.findById(Long.parseLong(q));
        if (company != null) {
            if (!company.isEnabled()) {
                company.setEnabled(true);
                companyService.save(company);
                redirectAttributes.addFlashAttribute("flash", new FlashMessage("Company account activated!", FlashMessage.Status.SUCCESS));
            }
        }
        return String.format("redirect:/auth/admin/adminDashboard/query/findCompanyById?q=%s",q);


//        } else {
//            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Company account already activated...", FlashMessage.Status.FAILURE));
//        }
//        return "redirect:/auth/admin/adminDashboard";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/deactivateCompanyById", method = RequestMethod.POST)
    public String deactivateCompanyById(@RequestParam String q, RedirectAttributes redirectAttributes) {
        Company company = companyService.findById(Long.parseLong(q));
        if (company != null) {
            if (company.isEnabled()) {
                company.setEnabled(false);
                companyService.save(company);
                redirectAttributes.addFlashAttribute("flash", new FlashMessage("Company account deactivated!", FlashMessage.Status.SUCCESS));
            }
        }
        return String.format("redirect:/auth/admin/adminDashboard/query/findCompanyById?q=%s",q);


//        } else {
//            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Company account already deactivated...", FlashMessage.Status.FAILURE));
//        }
//        return "redirect:/auth/admin/adminDashboard";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/deleteCompanyById", method = RequestMethod.POST)
    public String deleteCompanyById(@RequestParam String q, RedirectAttributes redirectAttributes) {
        Company company = companyService.findById(Long.parseLong(q));
        if (company != null) {
            companyService.delete(company);
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Company deleted!", FlashMessage.Status.SUCCESS));
        } else {
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("No such company...", FlashMessage.Status.FAILURE));
        }
        return "redirect:/auth/admin/adminDashboard";
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

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/findJobById")
    public String findJobById(Model model, @RequestParam String q) {
        Job job = jobService.findById(Long.parseLong(q));
        model.addAttribute("job", job);
        return "private/admin/findByIdQuery";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/auth/admin/adminDashboard/query/deleteJobById", method = RequestMethod.POST)
    public String deleteJobById(@RequestParam String q, RedirectAttributes redirectAttributes) {
        Job job = jobService.findById(Long.parseLong(q));
        if (job != null) {
            Company company = companyService.findById(job.getCompanyID());
            matchService.deleteByJobId(job.getId());
            jobService.delete(job);
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Job and associated matches deleted!", FlashMessage.Status.SUCCESS));
        } else {
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("No such job...", FlashMessage.Status.FAILURE));
        }
        return "redirect:/auth/admin/adminDashboard";
    }


}
