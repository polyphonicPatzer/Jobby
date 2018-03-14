package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.Candidate;
import com.capstone.jobby.model.ProfilePic;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.ProfilePicService;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class ProfilePicController {
    @Autowired
    private ProfilePicService profilePicService;

    @Autowired
    private CandidateService candidateService;

    // Upload a new profile pic
    @RequestMapping(value = "/auth/candidate/profilePicPost", method = RequestMethod.POST)
    public String addProfilePIc(ProfilePic profilePic, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes, Principal principal) {

        //Get the currently authenticated candidate
        Candidate candidate = candidateService.findByUsername(principal.getName());

        //Add candidate to profilePic
        profilePic.setCandidate(candidate);
        candidate.setProfilePic(profilePic);

        //Upload new profilePic if data is valid
        profilePicService.save(profilePic, file);
        candidateService.save(candidate);

        // Add flash message for success
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Profile picture successfully uploaded!", FlashMessage.Status.SUCCESS));

        //Redirect browser to new ProfilePic's detail view
        return String.format("redirect:/auth/candidate/profilePic/%s",profilePic.getId());
    }

    // Single profile pic page
    @RequestMapping("/auth/candidate/profilePic/{profilePicId}")
    public String profilePicDetails(@PathVariable Long profilePicId, Model model) {
        // Get profile pic whose id is profilePicId
        ProfilePic profilePic = profilePicService.findById(profilePicId);

        model.addAttribute("profilePic", profilePic);
        model.addAttribute("action", "/auth/candidate/profilePic/{profilePicId}/edit");
        return "private/candidate/profilePicDetails";
    }

    //Profile pic image data
    @RequestMapping("/auth/candidate/profilePic/{profilePicId}.img")
    @ResponseBody
    public byte[] profilePicImage(@PathVariable Long profilePicId) {
        // Return image data as byte array of the profile pic whose id is profilePicId
        return profilePicService.findById(profilePicId).getBytes();
    }

    // Form for uploading a new profile pic
    @RequestMapping("/auth/candidate/uploadProfilePic")
    public String formNewProfilePic(Model model) {
        // Add model attributes needed for new profile pic upload form
        if(!model.containsAttribute("profilePic")) {
            model.addAttribute("profilePic",new ProfilePic());
        }
        model.addAttribute("action","/auth/candidate/profilePicPost");
        model.addAttribute("heading","Upload");
        model.addAttribute("submit","Upload");

        return "private/candidate/profilePicForm";
    }

    // Form for editing an existing profile pic
    @RequestMapping(value = "/auth/candidate/profilePic/{profilePicId}/edit")
    public String formEditProfilePic(@PathVariable Long profilePicId, Model model) {
        // Add model attributes needed for edit form
        if(!model.containsAttribute("profilePic")) {
            model.addAttribute("profilePic",profilePicService.findById(profilePicId));
        }
        model.addAttribute("action",String.format("/auth/candidate/profilePicEditPost",profilePicId));
        model.addAttribute("heading","Edit Profile Picture");
        model.addAttribute("submit","Update");

        return "private/candidate/profilePicForm";
    }

    // Update an existing profile pic
    @RequestMapping(value = "/auth/candidate/profilePicEditPost", method = RequestMethod.POST)
    public String updateProfilePic(ProfilePic profilePic, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        // Update profile pic if data is valid
        profilePicService.save(profilePic, file);

        // Flash message
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Profile picture successfully updated!", FlashMessage.Status.SUCCESS));

        // Redirect browser to updated profile pic's detail view
        return String.format("redirect:/auth/candidate/profilePic/%s", profilePic.getId());
    }

    // Delete an existing profile pic
    @RequestMapping(value = "/auth/candidate/profilePic/{profilePicId}/delete", method = RequestMethod.POST)
    public String deleteProfilePic(@PathVariable Long profilePicId, RedirectAttributes redirectAttributes) {
        // Delete the profile pic whose id is profilePicId
        ProfilePic profilePic = profilePicService.findById(profilePicId);
        profilePicService.delete(profilePic);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Profile picture deleted!", FlashMessage.Status.SUCCESS));

        // Redirect to candidate homepage
        return "redirect:/auth/candidate/candidateProfile";
    }

    /************************************
     *                                  *
     *         Public Facing Stuff      *
     *                                  *
     ************************************/



    // Public facing candidate profile pic
    @RequestMapping(value = "/profilePic/{profilePicId}")
    public String candidateProfilePic(Model model, @PathVariable Long profilePicId){
        // Get profile pic whose id is profilePicId
        ProfilePic profilePic = profilePicService.findById(profilePicId);
        model.addAttribute("profilePic", profilePic);
        return "public/profilePic/viewProfilePic";
    }

    //Profile pic image data
    @RequestMapping("/profilePic/{profilePicId}.img")
    @ResponseBody
    public byte[] profilePicImagePublic(@PathVariable Long profilePicId) {
        // Return image data as byte array of the profile pic whose id is profilePicId
        return profilePicService.findById(profilePicId).getBytes();
    }
}
