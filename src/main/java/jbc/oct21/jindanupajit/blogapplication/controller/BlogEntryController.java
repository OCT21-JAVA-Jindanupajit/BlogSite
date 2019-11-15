package jbc.oct21.jindanupajit.blogapplication.controller;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import jbc.oct21.jindanupajit.blogapplication.repository.BlogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class BlogEntryController {

    @Autowired
    BlogEntryRepository blogEntryRepository;

    @GetMapping(value = {"/blog/view", "/"})
    public String pageBlogView(Model model) {
        model.addAttribute("blogDatabase", blogEntryRepository.findAll());

        return "blog_view";
    }

    @GetMapping(value = {"/blog/view/{id}"})
    public String pageBlogView(Model model, @PathVariable String id) {
        Optional<BlogEntry> blog = blogEntryRepository.findById(Long.parseLong(id));

        model.addAttribute("blog", blog.orElse(new BlogEntry()));


        return "blog_view_id";
    }

}
