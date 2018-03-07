package com.capstone.jobby.config;

import com.capstone.jobby.service.CandidateService;
import com.capstone.jobby.service.CompanyService;
import com.capstone.jobby.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.repository.query.spi.EvaluationContextExtension;
//import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.core.annotation.Order;
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
    public static class CompanySecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private CompanyService companyService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(companyService);
            //.passwordEncoder(passwordEncoder());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/company/*").authorizeRequests().anyRequest().hasRole("COMPANY")
                    .and()
                    .formLogin()
                    //THIS LOGIN URL MIGHT HAVE TO BE CHANGED TO THAT ONE EXAMPLE WITH A PAGE AND URL???
                    .loginPage("/company/companyLogin").permitAll()
                    //THIS PERMIT ALL MAY NOT BE NECESSARY
                    //.permitAll()
                    .successHandler(companyLoginSuccessHandler())
                    .failureHandler(companyLoginFailureHandler())
                    .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/company/logout")
                    .logoutSuccessUrl("/");

        }

        public AuthenticationSuccessHandler companyLoginSuccessHandler() {
            System.out.println("\n\n\nINSIDE COMPANY LOGIN SUCCESS HANDLER\n\n\n");
            return (request, response, authentication) -> response.sendRedirect("/company/companyProfile");
        }

        public AuthenticationFailureHandler companyLoginFailureHandler() {
            System.out.println("\n\n\nINSIDE COMPANY LOGIN FAILURE HANDLER\n\n\n");
            return (request, response, exception) -> {
                request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
                response.sendRedirect("/company/companyLogin");
            };
        }
    }

    @Configuration
    @Order(4)
    public static class CandidateSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private CandidateService candidateService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(candidateService);
            //.passwordEncoder(passwordEncoder());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/candidate/*").authorizeRequests().anyRequest().hasRole("CANDIDATE")
                    .and()
                    .formLogin()
                    //THIS LOGIN URL MIGHT HAVE TO BE CHANGED TO THAT ONE EXAMPLE WITH A PAGE AND URL???
                    .loginPage("/candidate/candidateLogin").permitAll()
                    //THIS PERMIT ALL MAY NOT BE NECESSARY
                    //.permitAll()
                    .successHandler(candidateLoginSuccessHandler())
                    .failureHandler(candidateLoginFailureHandler())
                    .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/candidate/logout")
                    .logoutSuccessUrl("/");
//                    .and()
//                    .csrf();

        }

        public AuthenticationSuccessHandler candidateLoginSuccessHandler() {
            return (request, response, authentication) -> response.sendRedirect("/candidate/candidateProfile");
        }

        public AuthenticationFailureHandler candidateLoginFailureHandler() {
            return (request, response, exception) -> {
                request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
                response.sendRedirect("/candidate/candidateLogin");
            };
        }
    }





//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
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
