package com.capstone.jobby.web.controller;

import java.util.List;
import com.capstone.jobby.service.JobService;
import com.capstone.jobby.model.Job;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobSearchController {
    @Autowired
    private JobService jobService;

    // Index of all categories
    @SuppressWarnings("unchecked")
    @RequestMapping("/jobs")
    public String listAllJobs(Model model) {
        // TODO: Get all categories
        List<Job> jobs = jobService.findAll();

        model.addAttribute("jobs", jobs);
        return "jobs/index";
    }
}
