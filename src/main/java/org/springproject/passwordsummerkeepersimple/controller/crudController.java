package org.springproject.passwordsummerkeepersimple.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springproject.passwordsummerkeepersimple.entity.PasswordInfo;
import org.springproject.passwordsummerkeepersimple.entity.PasswordInfoKeys;
import org.springproject.passwordsummerkeepersimple.model.UserSession;
import org.springproject.passwordsummerkeepersimple.service.*;

import javax.crypto.SecretKey;

@Controller
public class crudController {

    private static final Logger log = LoggerFactory.getLogger(crudController.class);
    @Autowired
    private PasswordInfoService passwordInfoService;

    @Autowired
    private UserSession userSession;

    @Autowired
    private HashingService hashingService;

    @Autowired
    private PasswordInfoKeyService passwordInfoKeyService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private PasswordGeneratorService passwordGeneratorService;

    @Autowired
    private CacheService cacheService;
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/showNewPasswordInfoForm")
    public String showNewPasswordInfoForm(Model model) {
        String session_username = userSession.getUsername();
        // Перенаправление на страницу входа, если пользователь не вошел в систему
        if (session_username == null) {
            log.info("user redirected to login page");
            return "redirect:/login";
        }

        PasswordInfo passwordInfo = new PasswordInfo();
        model.addAttribute("session_username", session_username);
        model.addAttribute("passwordInfo", passwordInfo);
        return "add";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/savePasswordInfo")
    public String savePasswordInfo(@ModelAttribute("passwordInfo") PasswordInfo passwordInfo, HttpServletResponse response) throws Exception {
        String session_username = userSession.getUsername();
        // Перенаправление на страницу входа, если пользователь не вошел в систему
        if (session_username == null) {
            log.info("user redirected to login page");
            return "redirect:/login";
        }
        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);
        // Generate salted and hashed password using HashingService
        passwordInfoService.savePasswordInfo(hashingService.generateEncryptedPassword(passwordInfo));
        //passwordInfoService.savePasswordInfo(passwordInfo);
        log.info("password info saved");
        return "redirect:/";

    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model, HttpServletResponse response) throws Exception {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            log.info("user redirected to login page");
            return "redirect:/login";
        }
        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);
        //decrypt the stored password for viewing
        PasswordInfo passwordInfo = passwordInfoService.getPasswordInfobyid(id);
        String encryptedPassword = passwordInfo.getPassword();
        SecretKey secretKey = passwordInfoKeyService.getPasswordInfoKeybyid(id).getSecretKey();
        String decryptedPassword = encryptionService.decryptData(encryptedPassword, secretKey);

        passwordInfo.setPassword(decryptedPassword);
        model.addAttribute("passwordInfo", passwordInfo);
        log.info("showFormForUpdate passwordInfo");
        return "update";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/showFormForUpdate/{id}")
    public String updatePassword(@PathVariable(value = "id") int id, @ModelAttribute("passwordInfo") PasswordInfo passwordInfo, HttpServletResponse response) throws Exception {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            return "redirect:/login";
        }
        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);
        passwordInfo.setPasswordId(id);
        PasswordInfoKeys passwordInfoKeys = passwordInfoKeyService.getPasswordInfoKeybyid(id);
        // updated both passwordinfo and passwordinfokeys
        passwordInfoService.savePasswordInfo(hashingService.updatePassword(passwordInfo, passwordInfoKeys));
        log.info("updatePassword passwordInfoKeys");
        return "redirect:/";
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/deletePasswordInfo/{id}")
    public String deletePasswordInfo(@PathVariable(value = "id") int id) {
        String session_username = userSession.getUsername();
        // Redirect to login page if user is not logged in
        if (session_username == null) {
            log.info("user redirected to login page");
            return "redirect:/login";
        }
        passwordInfoKeyService.deletePasswordInfoKey(id);
        passwordInfoService.deletePasswordInfo(id);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/generate-password")
    @ResponseBody
    public String generatePassword() {
        return passwordGeneratorService.generatePassword();
    }
}