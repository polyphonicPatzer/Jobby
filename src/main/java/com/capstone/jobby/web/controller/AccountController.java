package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.Candidate;
import com.capstone.jobby.model.Company;
import com.capstone.jobby.model.Gif;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.CategoryService;
import com.capstone.jobby.service.CompanyService;
import com.capstone.jobby.service.GifService;
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
    @RequestMapping(value = "/account_registration")
    public String aRegister(Model model){
        return "account/accountRegistration";
    }

    // Form for creating a new candidate
    @RequestMapping(value = "/candidate_registration")
    public String formNewCandidate(Model model) {
        // Add model attributes needed for new Candidate upload form
        if(!model.containsAttribute("candidate")) {
            model.addAttribute("candidate",new Candidate());
        }
        model.addAttribute("action","/addCandidate");
        model.addAttribute("heading","Sign Up");
        model.addAttribute("submit","Sign Up");

        return "account/candidateRegistrationForm";
    }

    // Add a new Candidate
    @RequestMapping(value = "/addCandidate", method = RequestMethod.POST)
    public String addCandidate(@Valid Candidate candidate, BindingResult result, RedirectAttributes redirectAttributes) {
        // Upload new Candidate if data is valid
        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.candidate", result);

            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("candidate", candidate);

            // Redirect back to the form
            return "redirect:/candidate_registration";
        }

        candidateService.save(candidate);

        // Add flash message for success
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Account successfully created!", FlashMessage.Status.SUCCESS));

        // Redirect browser to new Candidate's home view. Replace this with homepage when created.
        return String.format("redirect:/candidate/%s","..");
    }

    // Form for creating a new Company
    @RequestMapping(value = "/company_registration")
    public String formNewCompany(Model model) {
        // Add model attributes needed for new Company upload form
        if(!model.containsAttribute("company")) {
            model.addAttribute("company",new Company());
        }
        model.addAttribute("action","/addCompany");
        model.addAttribute("heading","Sign Up");
        model.addAttribute("submit","Sign Up");

        return "account/companyRegistrationForm";
    }

    // Add a new Company
    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public String addCompany(@Valid Company company, BindingResult result, RedirectAttributes redirectAttributes) {
        // Upload new Company if data is valid
        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.company", result);

            // Add company if invalid was received
            redirectAttributes.addFlashAttribute("company", company);

            // Redirect back to the form
            return "redirect:company_registration";
        }

        companyService.save(company);

        // Add flash message for success
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Account successfully created!", FlashMessage.Status.SUCCESS));

        // Redirect browser to new Company's home view. Replace this with homepage when created.
        return String.format("redirect:/company/%s","..");
    }

    // Select account type for logging in
    @RequestMapping(value = "/select_account_type")
    public String selectType(Model model){
        return "account/selectType";
    }


    // Login Candidate Account
    @RequestMapping(path = "/candidate_login", method = RequestMethod.GET)
    public String candidateLoginForm(Model model, HttpServletRequest request) {
        try {
            Object flash = request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);

            request.getSession().removeAttribute("flash");
        } catch (Exception e) {
            //Flash session attribute must not exist. Do nothing and proceed.
        }
        return "account/candidateLogin";
    }

    // Login Company Account
    @RequestMapping(path = "/company_login", method = RequestMethod.GET)
    public String loginCompanyForm(Model model, HttpServletRequest request) {
        try {
            Object flash = request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);

            request.getSession().removeAttribute("flash");
        }  catch (Exception e) {
            //Flash session attribute must not exist. Do nothing and proceed.
        }
        return "account/companyLogin";
    }

}
