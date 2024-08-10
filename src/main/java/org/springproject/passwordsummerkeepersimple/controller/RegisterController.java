package org.springproject.passwordsummerkeepersimple.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springproject.passwordsummerkeepersimple.entity.UserAccount;
import org.springproject.passwordsummerkeepersimple.service.AccountValidationService;
import org.springproject.passwordsummerkeepersimple.service.CacheService;
import org.springproject.passwordsummerkeepersimple.service.HashingService;
import org.springproject.passwordsummerkeepersimple.service.UserAccountService;

@Controller
public class RegisterController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private HashingService hashingService;

    @Autowired
    private AccountValidationService accountValidationService;

    // Handler method for GET requests to "/register" endpoint
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/register")
    public String showRegistrationForm(HttpServletResponse response) {
        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);

        // Return the name of the view (register.html in this case)
        return "templates/register";
    }

    // Handler method for POST requests to "/register" endpoint
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/register")
    public String register(String username, String password, String confirmpassword) throws Exception {
        // Validate user credentials and registration inputs

        // Check if passwords match and if the username is not already registered
        if (password.equals(confirmpassword) && !accountValidationService.validateUserCredentials(username, password)) {
            // Create a new UserInfo object with username and password
            UserAccount userAccount = new UserAccount(username, password);

            // Generate salted and hashed password using HashingService
            userAccountService.saveUserInfo(hashingService.generateSaltHashedPassword(userAccount));
        } else if (accountValidationService.validateUserCredentials(username, password)) {
            // Redirect to registration page with error message if account already exists
            return "redirect:/register?error=Account already exists";
        } else{
            // Redirect to registration page with error message if passwords do not match
            return "redirect:/register?error=Passwords do not match"; // Redirect to registration page with error message
        }
        // Redirect to login page after successful registration
        return "login";
    }
}