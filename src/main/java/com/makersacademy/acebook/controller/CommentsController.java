package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CommentsController {

    @Autowired
    CommentRepository repository;

    @PostMapping("/comments")
    public RedirectView create(@ModelAttribute Comment comment) {
        System.out.println("###########");
        System.out.println(comment.getUsername());
        System.out.println(comment.getPostId());
        System.out.println(comment.getCommentContent());
        System.out.println("###########");
        repository.save(comment);
        System.out.println("-----------");
        System.out.println(comment);
        System.out.println("-----------");
        return new RedirectView("/posts");
    }

}
