package com.veers.storage.controller;


import java.util.List;

import com.veers.storage.model.Userfile;
import com.veers.storage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.veers.storage.model.User;
import com.veers.storage.service.UserProfileService;

import javax.validation.Valid;

@Controller
@RequestMapping("/storage")
@SessionAttributes("roles")
public class StorageController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @RequestMapping(value = { "/storage" }, method = RequestMethod.GET)
    public String getStorage(ModelMap model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "storage";
    }

    @RequestMapping(value = { "/upload" }, method = RequestMethod.GET)
    public String upload(@Valid Userfile file, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }

        /**
         * @TODO realize simple write file
         */

        return "storage";
    }


    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
