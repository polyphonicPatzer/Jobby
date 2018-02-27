package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.Category;
import com.capstone.jobby.model.Company;
import com.capstone.jobby.service.CategoryService;
import com.capstone.jobby.service.CompanyService;
import com.capstone.jobby.web.Color;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    // Form for creating a new Company
    @RequestMapping("/companyRegistration")
    public String formNewCompany(Model model) {
        // Add model attributes needed for new Company upload form
        if(!model.containsAttribute("company")) {
            model.addAttribute("company",new Company());
        }
        model.addAttribute("action","/addCompany");
        model.addAttribute("heading","Sign Up");
        model.addAttribute("submit","Sign Up");

        return "company/registrationForm";
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
            return "redirect:/companyRegistration";
        }

        companyService.save(company);

        // Add flash message for success
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Account successfully created!", FlashMessage.Status.SUCCESS));

        // Redirect browser to new Company's home view. Replace this with homepage when created.
        return String.format("redirect:/company/%s","..");
    }


    @RequestMapping("/company_profile")
    public String companyProfile(Model model){
        return "company/companyProfile";
    }

}
