package com.capstone.jobby.web.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String listJobsSearch(Model model, @RequestParam String q) {
        List<Job> allJobs = jobService.findAll();
        if (q.equals("")) {
            model.addAttribute("jobs", allJobs);
        } else {
            List<Job> jobs = new ArrayList<>();
            for (Job job : allJobs) {
                for (String word : q.split(" ")) {
                    if (job.getName().toLowerCase().indexOf(word.toLowerCase()) >= 0 || job.getDescription().toLowerCase().indexOf(word.toLowerCase()) >= 0) {
                        if (!jobs.contains(job)) {
                            jobs.add(job);
                        }
                    }
                }
            }
            model.addAttribute("jobs", jobs);
        }
        return "public/searchResults/jobSearchResults";
    }
}
