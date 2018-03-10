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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller()
public class AdminController {



}
