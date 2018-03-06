package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.Candidate;
import com.capstone.jobby.model.Company;
import com.capstone.jobby.model.Role;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.CompanyService;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller()
public class AccountController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CompanyService companyService;

    // Home page
    @RequestMapping(value = "/")
    public String landing(Model model) {
        return "landing";
    }

    // Create Account
    @RequestMapping(value = "/account/accountRegistration")
    public String aRegister(Model model){
        return "account/accountRegistration";
    }

    // Form for creating a new candidate
    @RequestMapping(value = "/account/candidateRegistration")
    public String formNewCandidate(Model model) {
        // Add model attributes needed for new Candidate upload form
        if(!model.containsAttribute("candidate")) {
            model.addAttribute("candidate",new Candidate());
        }
        model.addAttribute("action","/account/addCandidate");
        model.addAttribute("heading","Sign Up");
        model.addAttribute("submit","Sign Up");

        return "account/candidateRegistrationForm";
    }

    // Add a new Candidate
    @RequestMapping(value = "/account/addCandidate", method = RequestMethod.POST)
    public String addCandidate(@Valid Candidate candidate, BindingResult result, RedirectAttributes redirectAttributes) {
        // Upload new Candidate if data is valid
        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.candidate", result);

            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("candidate", candidate);

            // Redirect back to the form
            return "redirect:/account/candidateRegistration";
        }

        //Assign Candidate role
        Role role = new Role();
        role.setId(Integer.toUnsignedLong(2));
        role.setName("ROLE_CANDIDATE");
        candidate.setRole(role);

        //Enable Candidate account
        candidate.setEnabled(true);

        //Save Candidate to database
        candidateService.save(candidate);

        // Add flash message for success
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Account successfully created!", FlashMessage.Status.SUCCESS));

        // Redirect browser to new Candidate's home view. Replace this with homepage when created.
        return String.format("redirect:/candidate/candidateLogin");
    }

    // Form for creating a new Company
    @RequestMapping(value = "/account/companyRegistration")
    public String formNewCompany(Model model) {
        // Add model attributes needed for new Company upload form
        if(!model.containsAttribute("company")) {
            model.addAttribute("company",new Company());
        }
        model.addAttribute("action","/account/addCompany");
        model.addAttribute("heading","Sign Up");
        model.addAttribute("submit","Sign Up");

        return "account/companyRegistrationForm";
    }

    // Add a new Company
    @RequestMapping(value = "/account/addCompany", method = RequestMethod.POST)
    public String addCompany(@Valid Company company, BindingResult result, RedirectAttributes redirectAttributes) {
        // Upload new Company if data is valid
        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.company", result);

            // Add company if invalid was received
            redirectAttributes.addFlashAttribute("company", company);

            // Redirect back to the form
            return "redirect:/account/companyRegistration";
        }

        //Assign Company role
        Role role = new Role();
        role.setId(Integer.toUnsignedLong(1));
        role.setName("ROLE_COMPANY");
        company.setRole(role);

        //Enable Company account
        company.setEnabled(true);

        //Save company to database
        companyService.save(company);

        // Add flash message for success
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Account successfully created!", FlashMessage.Status.SUCCESS));

        // Redirect browser to new Company's home view. Replace this with homepage when created.
        return String.format("redirect:/company/companyLogin"); //MAKE THIS THE GET URI AND HAVE THAT RETURN THE POST URI
    }

    // Select account type for logging in
    @RequestMapping(value = "/account/selectType")
    public String selectType(Model model){
        return "account/selectType";
    }


    // Login Candidate Account
    @RequestMapping(value = "/candidate/candidateLogin", method = RequestMethod.GET)
    public String candidateLoginForm(Model model, HttpServletRequest request) {
        model.addAttribute("candidate", new Candidate());
        try {
            Object flash = request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);
            model.addAttribute("action", "/candidate/candidateLogin"); //MAKE THIS CALL THE POSTER
            model.addAttribute("submit","Login");

            request.getSession().removeAttribute("flash");
        } catch (Exception e) {
            //Flash session attribute must not exist. Do nothing and proceed.
        }
        return "account/candidateLogin";
    }

    // Login Company Account
    @RequestMapping(value = "/company/companyLogin", method = RequestMethod.GET)
    public String loginCompanyForm(Model model, HttpServletRequest request) {
        model.addAttribute("company", new Company());
        try {
            Object flash = request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);
            model.addAttribute("action", "/company/companyLogin"); //MAKE THIS CALL THE POSTER
            model.addAttribute("submit","Login");

            request.getSession().removeAttribute("flash");
        }  catch (Exception e) {
            //Flash session attribute must not exist. Do nothing and proceed.
        }
        return "account/companyLogin";
    }
}
