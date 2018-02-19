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

    @RequestMapping("/Account_Login")
    public String landing(Model model) {
        return "accountlogin";
    }

}
