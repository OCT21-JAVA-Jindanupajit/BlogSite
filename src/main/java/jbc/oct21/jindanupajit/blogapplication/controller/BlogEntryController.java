package jbc.oct21.jindanupajit.blogapplication.controller;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import jbc.oct21.jindanupajit.blogapplication.model.User;
import jbc.oct21.jindanupajit.blogapplication.repository.BlogEntryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.CategoryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.UserRepository;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.NavbarViewModel;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.ViewModel;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Link;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.NavItem;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Navbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

@Controller
public class BlogEntryController {

    @Autowired
    BlogEntryRepository blogEntryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value = {"/"})
    public String pageBlogView() {
        return "redirect:/blog/view";
    }

    @GetMapping(value = {"/blog/view"})
    public String pageBlogView(Model model) {

        NavbarViewModel navbarViewModel = new NavbarViewModel(NavbarViewModel.navbarDefault());
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItem("Category", categoryRepository));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItem("Article", (BlogEntry) null));
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryListViewModel",
                new ViewModel<Iterable<BlogEntry>>("fragment/blogentry :: list", blogEntryRepository.findAll()));

        return "blog_view";
    }

    @GetMapping(value = {"/blog/view/{id}"})
    public String pageBlogView(Model model, @PathVariable String id) {
        Optional<BlogEntry> blog = blogEntryRepository.findById(Long.parseLong(id));

        NavbarViewModel navbarViewModel = new NavbarViewModel(NavbarViewModel.navbarDefault());
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItem("Category", categoryRepository));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItem("Article", blog.orElse(new BlogEntry())));
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryViewModel",
                new ViewModel<BlogEntry>("fragment/blogentry :: page", blog.orElse(new BlogEntry())));


        return "blog_view_id";
    }

    @GetMapping(value = {"/blog/edit/{idString}"})
    public String pageBlogEdit(Model model, @PathVariable String idString) {

        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            id = 0;
        }

        Optional<BlogEntry> blog = (id == 0)?Optional.of(new BlogEntry()):blogEntryRepository.findById(id);


        NavbarViewModel navbarViewModel = new NavbarViewModel(new Navbar());
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryViewModel",
                new ViewModel<BlogEntry>("fragment/blogentry :: edit", blog.orElse(new BlogEntry())));


        model.addAttribute("allCategory", categoryRepository.findAll());


        model.addAttribute("newBlogEntry", blog.orElse(new BlogEntry()));

        return "blog_view_id";
    }

    @PostMapping(value = {"/blog/edit/process"})
    public String pageBlogEdit(BlogEntry blogEntry) {

        blogEntry.setTimestamp(new Timestamp(System.currentTimeMillis()));
        User user = userRepository.findByName("Krissada");
        blogEntry.setUser(user);
        blogEntryRepository.save(blogEntry);

        return "redirect:/blog/view/"+blogEntry.getId();
    }

    @GetMapping(value = {"/blog/delete/{idString}"})
    public String pageBlogDelete(@PathVariable String idString) {

        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            id = 0;
        }
        Optional<BlogEntry> blog = (id == 0)?Optional.of(new BlogEntry()):blogEntryRepository.findById(id);

        blog.ifPresent(blogEntry -> blogEntryRepository.delete(blogEntry));

        return "redirect:/blog/view/";
    }

}
