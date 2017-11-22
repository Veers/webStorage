package com.veers.storage.controller;


import com.veers.storage.model.AppFile;
import com.veers.storage.service.FileService;
import com.veers.storage.service.UserProfileService;
import com.veers.storage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping("/storage")
@SessionAttributes("roles")
public class StorageController {

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    static final Logger logger = LoggerFactory.getLogger(StorageController.class);

    @RequestMapping(value = {"/files"}, method = RequestMethod.GET)
    public String getStorage(ModelMap model) {
        String loggedinUser = getPrincipal();
        model.addAttribute("loggedinuser", loggedinUser);
        List<AppFile> appFiles = fileService.getAllFiles();
        model.addAttribute("files", appFiles);
        return "storage";
    }

    /*
    @TODO url trouble with upload:
    /storage => /StorageApp/storage
     */
    @RequestMapping(value = {"/files"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String handleFileUpload(@RequestParam("file") MultipartFile file, ModelMap model) {
        String name = file.getName();
        if (!file.isEmpty()) {
            try {
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(new File("-uploaded")));
//                stream.write(bytes);
//                stream.close();
                String loggedinUser = getPrincipal();
                model.addAttribute("loggedinuser", loggedinUser);
                List<AppFile> appFiles = fileService.getAllFiles();
                model.addAttribute("files", appFiles);
                logger.info( "Success upload file " + name + " into " + name + "-uploaded !");
                return "storage";
            } catch (Exception e) {
                return "Error while upload " + name + " => " + e.getMessage();
            }
        } else {
            return "Error while upload " + name + " file is empty.";
        }

    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
