package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.Resume;
import com.capstone.jobby.service.ResumeService;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    // Upload a new resume
    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    public String addResume(Resume resume, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        //Upload new resume if data is valid
        resumeService.save(resume, file);

        // Add flash message for success
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Resume successfully uploaded!", FlashMessage.Status.SUCCESS));

        // TODO: Redirect browser to new GIF's detail view
        return String.format("redirect:/resume/%s",resume.getId());
    }

    // Single resume page
    @RequestMapping("/resume/{resumeId}")
    public String resumeDetails(@PathVariable Long resumeId, Model model) {
        // Get resume whose id is resumeId
        Resume resume = resumeService.findById(resumeId);

        model.addAttribute("resume", resume);
        return "resume/details";
    }

    // Resume image data
    @RequestMapping("/resume/{resumeId}.pdf")
    @ResponseBody
    public byte[] resumeImage(@PathVariable Long resumeId) {
        // Return image data as byte array of the Resume whose id is resumeId
        return resumeService.findById(resumeId).getBytes();
    }

    // Form for uploading a new Resume
    @RequestMapping("/uploadResume")
    public String formNewResume(Model model) {
        // Add model attributes needed for new Resume upload form
        if(!model.containsAttribute("resume")) {
            model.addAttribute("resume",new Resume());
        }
        model.addAttribute("action","/resume");
        model.addAttribute("heading","Upload");
        model.addAttribute("submit","Add");

        return "resume/form";
    }

    // Form for editing an existing resume
    @RequestMapping(value = "/resume/{resumeId}/edit")
    public String formEditResume(@PathVariable Long resumeId, Model model) {
        // Add model attributes needed for edit form
        if(!model.containsAttribute("resume")) {
            model.addAttribute("resume",resumeService.findById(resumeId));
        }
        model.addAttribute("action",String.format("/resume/%s",resumeId));
        model.addAttribute("heading","Edit Resume");
        model.addAttribute("submit","Update");

        return "resume/form";
    }

    // Update an existing Resume
    @RequestMapping(value = "/resume/{resumeId}", method = RequestMethod.POST)
    public String updateResume(Resume resume, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        // Update Resume if data is valid
        resumeService.save(resume, file);

        // Flash message
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Resume successfully updated!", FlashMessage.Status.SUCCESS));

        // Redirect browser to updated resume's detail view
        return String.format("redirect:/resume/%s", resume.getId());
    }

    // Delete an existing Resume
    @RequestMapping(value = "/resume/{resumeId}/delete", method = RequestMethod.POST)
    public String deleteResume(@PathVariable Long resumeId, RedirectAttributes redirectAttributes) {
        // Delete the resume whose id is resumeId
        Resume resume = resumeService.findById(resumeId);
        resumeService.delete(resume);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Resume deleted!", FlashMessage.Status.SUCCESS));

        // Redirect to app root
        return "redirect:/";
    }


}
