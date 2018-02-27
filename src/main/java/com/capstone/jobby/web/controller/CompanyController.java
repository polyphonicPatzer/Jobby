package com.capstone.jobby.web.controller;

import com.capstone.jobby.model.Category;
import com.capstone.jobby.service.CategoryService;
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


    @RequestMapping("/company_profile")
    public String companyProfile(Model model){
        return "company/companyProfile";
    }

}
