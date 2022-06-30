package com.makersacademy.acebook.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;

@Controller
public class ProfilePageController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile")
    public String profile(Model model){
        Authentication loggedIn = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(loggedIn.getName());
        model.addAttribute("user",user);
        return "profile/profile";
    }

    @PostMapping("/profile/save")
    public RedirectView saveUser(@ModelAttribute User user, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Authentication loggedIn = SecurityContextHolder.getContext().getAuthentication();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        user = userRepository.findByUsername(loggedIn.getName());
        user.setPhotoSrc(fileName);
        userRepository.save(user);  
        String uploadDir = "src/main/resources/static/image/" + user.getID(); 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
         
        return new RedirectView("/profile", true);
    }
}
