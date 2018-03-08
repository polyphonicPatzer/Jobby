package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.Company;
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

    @RequestMapping("/company/companyProfile")
    public String companyProfile(Model model){
        return "company/companyProfile";
    }

    @RequestMapping("/company/survey")
    public String companySurvey(Model model){
        return "company/survey";
    }

    @RequestMapping(value = "/company/logout")
    public String companyLogout(Model model) {
        model.addAttribute("action","/company/logoutPost");
        model.addAttribute("submit","Logout");
        return "company/logout";
    }

    @RequestMapping(value = "/company/logoutPost", method = RequestMethod.GET)
    public void companyLogoutPost(Model model) {
        return;
    }
}
