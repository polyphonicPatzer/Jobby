package com.capstone.jobby.config;

import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.CompanyService;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.repository.query.spi.EvaluationContextExtension;
//import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CandidateService candidateService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(companyService);
        auth.userDetailsService(candidateService);
                //.passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/images/**",
                "/vendor/**",
                "/app.css",
                "/app.js",
                "/favicon.png");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/",
                            "/account_registration",
                            "/candidate_registration",
                            "/addCandidate",
                            "/company_registration",
                            "/addCompany",
                            "/select_account_type",
                            "/candidate_login",
                            "/company_login").permitAll()
                    .antMatchers("/company_profile").hasRole("COMPANY")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    //THIS LOGIN URL MIGHT HAVE TO BE CHANGED TO THAT ONE EXAMPLE WITH A PAGE AND URL???
                    .loginPage("/company_login")
                    .permitAll()
                    .successHandler(companyLoginSuccessHandler())
                    .failureHandler(companyLoginFailureHandler())
                    .and()
                .logout()
                    .logoutSuccessUrl("/");

    }

    public AuthenticationSuccessHandler companyLoginSuccessHandler() {
        return (request, response, authentication) -> response.sendRedirect("/company_profile");
    }

    public AuthenticationFailureHandler companyLoginFailureHandler() {
        return (request, response, exception) -> {
            request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
            response.sendRedirect("/company_login");
        };
    }





//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers("/",
//                            "/account_registration",
//                            "/candidate_registration",
//                            "/company_registration",
//                            "/select_account_type",
//                            "/candidate_login",
//                            "/company_login",
//                            "/jobs",
//                            "company_profile").permitAll()
//                    //.anyRequest().hasRole("COMPANY")
//                    .and()
//                .formLogin()
//                    .loginPage("/company_login")
//                    .permitAll()
//                    .successHandler(companyLoginSuccessHandler())
//                    .failureHandler(companyLoginFailureHandler())
//                    .and()
////                .formLogin()
////                    .loginPage("/candidate_login")
////                    .successHandler(candidateLoginSuccessHandler())
////                    .failureHandler(candidateLoginFailureHandler())
////                    .permitAll()
////                    .and()
//                .logout()
//                    .permitAll()
//                    .logoutSuccessUrl("/")
//                    .and()
//                .csrf();
//
////                .authorizeRequests()
////                .anyRequest().hasRole("USER")
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .successHandler(loginSuccessHandler())
////                .failureHandler(loginFailureHandler())
////                .and()
////                .logout()
////                .permitAll()
////                .logoutSuccessUrl("/login")
////                .and()
////                .csrf();
//    }
//

//
//    public AuthenticationSuccessHandler candidateLoginSuccessHandler() {
//        return (request, response, authentication) -> response.sendRedirect("/candidate_profile");
//    }
//
//    public AuthenticationFailureHandler candidateLoginFailureHandler() {
//        return (request, response, exception) -> {
//            request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
//            response.sendRedirect("/candidate_login");
//        };
//    }

//    @Bean
//    public EvaluationContextExtension securityExtension() {
//        return new EvaluationContextExtensionSupport() {
//            @Override
//            public String getExtensionId() {
//                return "security";
//            }
//
//            @Override
//            public Object getRootObject() {
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                return new SecurityExpressionRoot(authentication) {};
//            }
//        };
//    }
}
