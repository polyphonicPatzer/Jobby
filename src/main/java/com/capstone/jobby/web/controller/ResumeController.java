package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.Candidate;
import com.capstone.jobby.model.Resume;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.ResumeService;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private CandidateService candidateService;

    // Upload a new resume
    @RequestMapping(value = "/auth/candidate/resumePost", method = RequestMethod.POST)
    public String addResume(Resume resume, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes, Principal principal) {


        //Get the currently authenticated candidate
        Candidate candidate = candidateService.findByUsername(principal.getName());

        //Add candidate to resume
        resume.setCandidate(candidate);
        candidate.setResume(resume);

        //Upload new resume if data is valid
        resumeService.save(resume, file);
        candidateService.save(candidate);

        // Add flash message for success
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Resume successfully uploaded!", FlashMessage.Status.SUCCESS));

        //Redirect browser to new resume's detail view
        return String.format("redirect:/auth/candidate/resume/%s",resume.getId());
    }

    // Single resume page
    @RequestMapping("/auth/candidate/resume/{resumeId}")
    public String resumeDetails(@PathVariable Long resumeId, Model model) {
        // Get resume whose id is resumeId
        Resume resume = resumeService.findById(resumeId);

        model.addAttribute("resume", resume);
        model.addAttribute("action", "/auth/candidate/resume/{resumeId}/edit");
        return "private/candidate/resumeDetails";
    }

    //Resume image data
    @RequestMapping("/auth/candidate/resume/{resumeId}.img")
    @ResponseBody
    public byte[] resumeImage(@PathVariable Long resumeId) {
        // Return image data as byte array of the Resume whose id is resumeId
        return resumeService.findById(resumeId).getBytes();
    }

    // Form for uploading a new Resume
    @RequestMapping("/auth/candidate/uploadResume")
    public String formNewResume(Model model) {
        // Add model attributes needed for new Resume upload form
        if(!model.containsAttribute("resume")) {
            model.addAttribute("resume",new Resume());
        }
        model.addAttribute("action","/auth/candidate/resumePost");
        model.addAttribute("heading","Upload");
        model.addAttribute("submit","Upload");

        return "private/candidate/resumeForm";
    }

    // Form for editing an existing resume
    @RequestMapping(value = "/auth/candidate/resume/{resumeId}/edit")
    public String formEditResume(@PathVariable Long resumeId, Model model) {
        // Add model attributes needed for edit form
        if(!model.containsAttribute("resume")) {
            model.addAttribute("resume",resumeService.findById(resumeId));
        }
        model.addAttribute("action",String.format("/auth/candidate/resumeEditPost",resumeId));
        model.addAttribute("heading","Edit Resume");
        model.addAttribute("submit","Update");

        return "private/candidate/resumeForm";
    }

    // Update an existing Resume
    @RequestMapping(value = "/auth/candidate/resumeEditPost", method = RequestMethod.POST)
    public String updateResume(Resume resume, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        // Update Resume if data is valid
        resumeService.save(resume, file);

        // Flash message
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Resume successfully updated!", FlashMessage.Status.SUCCESS));

        // Redirect browser to updated resume's detail view
        return String.format("redirect:/auth/candidate/resume/%s", resume.getId());
    }

    // Delete an existing Resume
    @RequestMapping(value = "/auth/candidate/resume/{resumeId}/delete", method = RequestMethod.POST)
    public String deleteResume(@PathVariable Long resumeId, RedirectAttributes redirectAttributes) {
        // Delete the resume whose id is resumeId
        Resume resume = resumeService.findById(resumeId);
        resumeService.delete(resume);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Resume deleted!", FlashMessage.Status.SUCCESS));

        // Redirect to candidate home page
        return "redirect:/auth/candidate/candidateProfile";
    }

    /************************************
     *                                  *
     *         Public Facing Stuff      *
     *                                  *
     ************************************/



    // Public facing candidate Resume
    @RequestMapping(value = "/resume/{resumeId}")
    public String candidateResume(Model model, @PathVariable Long resumeId){
        // Get resume whose id is resumeId
        Resume resume = resumeService.findById(resumeId);
        model.addAttribute("resume", resume);
        return "public/resume/viewResume";
    }

    //Resume image data
    @RequestMapping("/resume/{resumeId}.img")
    @ResponseBody
    public byte[] resumeImagePublic(@PathVariable Long resumeId) {
        // Return image data as byte array of the Resume whose id is resumeId
        return resumeService.findById(resumeId).getBytes();
    }
}
