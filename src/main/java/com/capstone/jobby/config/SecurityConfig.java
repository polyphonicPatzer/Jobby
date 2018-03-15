package com.capstone.jobby.config;

import com.capstone.jobby.service.AdminService;
import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.CompanyService;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.repository.query.spi.EvaluationContextExtension;
//import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/images/**",
                "/vendor/**",
                "/app.css",
                "/app.js",
                "/favicon.png");
        //web.debug(true);
    }

    @Configuration
    @Order(1)
    public static class HomePageSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/")
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(2)
    public static class AccountSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/account/*")
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(3)
    public static class PublicCandidateSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/candidate/*")
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(4)
    public static class PublicCandidateSurveyResultsSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/candidateSurveyResults/*")
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(5)
    public static class PublicCompanySecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/company/*")
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(6)
    public static class PublicJobSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/job*")
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(7)
    public static class PublicProfilePicConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/profilePic/*")
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(8)
    public static class PublicResumeConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/resume/*")
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(9)
    public static class PublicErrorConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/error")
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(10)
    public static class CompanySecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private CompanyService companyService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(companyService).passwordEncoder(passwordEncoder());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/auth/company/*").authorizeRequests().anyRequest().hasRole("COMPANY")
                    .and()
                    .formLogin()
                    .loginPage("/auth/company/companyLogin").permitAll()
                    .successHandler(companyLoginSuccessHandler())
                    .failureHandler(companyLoginFailureHandler())
                    .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/auth/company/logoutPost")
                    .logoutSuccessUrl("/")
                    .and()
                    .csrf().disable();

        }

        public AuthenticationSuccessHandler companyLoginSuccessHandler() {
            return (request, response, authentication) -> response.sendRedirect("/auth/company/companyProfile");
        }

        public AuthenticationFailureHandler companyLoginFailureHandler() {
            return (request, response, exception) -> {
                request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
                response.sendRedirect("/auth/company/companyLogin");
            };
        }
    }

    @Configuration
    @Order(11)
    public static class CandidateSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private CandidateService candidateService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(candidateService).passwordEncoder(passwordEncoder());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/auth/candidate/*").authorizeRequests().anyRequest().hasRole("CANDIDATE")
                    .and()
                    .formLogin()
                    .loginPage("/auth/candidate/candidateLogin").permitAll()
                    .successHandler(candidateLoginSuccessHandler())
                    .failureHandler(candidateLoginFailureHandler())
                    .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/auth/candidate/logoutPost")
                    .logoutSuccessUrl("/")
                    .and()
                    .csrf().disable();

        }

        public AuthenticationSuccessHandler candidateLoginSuccessHandler() {
            return (request, response, authentication) -> response.sendRedirect("/auth/candidate/candidateProfile");
        }

        public AuthenticationFailureHandler candidateLoginFailureHandler() {
            return (request, response, exception) -> {
                request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
                response.sendRedirect("/auth/candidate/candidateLogin");
            };
        }
    }


    @Configuration
    @Order(12)
    public static class AdminSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private AdminService adminService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(adminService).passwordEncoder(passwordEncoder());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/auth/admin/*").authorizeRequests().anyRequest().hasRole("ADMIN")
                    .and()
                    .formLogin()
                    .loginPage("/auth/admin/adminLogin").permitAll()
                    .successHandler(adminLoginSuccessHandler())
                    .failureHandler(adminLoginFailureHandler())
                    .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/auth/admin/logoutPost")
                    .logoutSuccessUrl("/")
                    .and()
                    .csrf().disable();

        }

        public AuthenticationSuccessHandler adminLoginSuccessHandler() {
            return (request, response, authentication) -> { response.sendRedirect("/auth/admin/adminDashboard"); };
        }

        public AuthenticationFailureHandler adminLoginFailureHandler() {
            return (request, response, exception) -> {
                request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
                response.sendRedirect("/auth/admin/adminLogin");
            };
        }
    }









//    @Bean
//    public static EvaluationContextExtension securityExtension() {
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
