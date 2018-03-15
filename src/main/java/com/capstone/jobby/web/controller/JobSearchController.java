package com.capstone.jobby.web.controller;

import java.util.List;

import com.capstone.jobby.model.Company;
import com.capstone.jobby.service.CompanyService;
import com.capstone.jobby.service.JobService;
import com.capstone.jobby.model.Job;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobSearchController {
    @Autowired
    private JobService jobService;

    @Autowired
    private CompanyService companyService;

    //Public job view
    @RequestMapping(value = "/job/{jobId}")
    public String publicJobView(Model model, @PathVariable Long jobId){
        Job job = jobService.findById(jobId);
        Company company = companyService.findById(job.getCompanyID());
        model.addAttribute("job", job);
        model.addAttribute("company", company);
        return "public/job/jobDetails";
    }

    /************************************
     *                                  *
     *         Search Results Stuff     *
     *                                  *
     ************************************/

    //Search for all jobs
    @SuppressWarnings("unchecked")
    @RequestMapping("/job/search")
    public String listJobsSearch(Model model) {
        List<Job> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);
        return "public/searchResults/jobSearchResults";
    }
}
