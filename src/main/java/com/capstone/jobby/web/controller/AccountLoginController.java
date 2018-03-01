package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.Gif;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.CategoryService;
import com.capstone.jobby.service.CompanyService;
import com.capstone.jobby.service.GifService;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountLoginController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CompanyService companyService;


    // Home page
    @RequestMapping("/")
    public String landing(Model model) {
        return "landing";
    }

    // Create Account
    @RequestMapping("/account_registration")
    public String aRegister(Model model){
        return "account/accountRegistration";
    }

    // Create Employee Account
    @RequestMapping("/employee_registration")
    public String eRegister(Model model){
        return "account/employeeRegistration";
    }

    // Create Account
    @RequestMapping("/select_account_type")
    public String selectType(Model model){
        return "account/selectType";
    }

    // Login Candidate Account
    @RequestMapping("/candidate_login")
    public String loginCandidate(Model model){
        return "account/candidateLogin";
    }

    // Login Company Account
    @RequestMapping("/company_login")
    public String loginCompany(Model model){
        return "account/companyLogin";
    }


    // Account Login
    @RequestMapping("/account_login")
    public String aLogin(Model model){
        return "account/accountLogin";
    }
}
