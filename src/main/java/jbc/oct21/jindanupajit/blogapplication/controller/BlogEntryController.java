package jbc.oct21.jindanupajit.blogapplication.controller;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import jbc.oct21.jindanupajit.blogapplication.model.Category;
import jbc.oct21.jindanupajit.blogapplication.model.User;
import jbc.oct21.jindanupajit.blogapplication.repository.BlogEntryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.CategoryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.UserRepository;
import jbc.oct21.jindanupajit.blogapplication.service.UserDetailsServiceImpl;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.ViewModel;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.NavbarViewModel;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.NavItem;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Navbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.Optional;

@Controller
public class BlogEntryController {

    @Autowired
    BlogEntryRepository blogEntryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @ModelAttribute
    public void globalModelAttribute(Model model) {
        User user = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());
        model.addAttribute("authenticatedUser", user);
    }

    @GetMapping(value = {"/"})
    public String pageBlogView() {
        return "redirect:/blogEntry/view";
    }

    @GetMapping(value = {"/blogEntry/view"})
    public String pageBlogView(Model model) {
        User user = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());

        NavbarViewModel navbarViewModel = new NavbarViewModel(NavbarViewModel.navbarDefault());
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemProfile(user));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemCategory("Category" , categoryRepository.findAll()));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemBlogEntry(null));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().get(0).setActive(true);
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryListViewModel",
                new ViewModel<Iterable<BlogEntry>>("fragment/blogentry :: list", blogEntryRepository.findAllByOrderByTimestampDesc()));

        return "blog_view";
    }




    @GetMapping(value = {"/blogEntry/view/{idString}"})
    public String pageBlogView(Model model, @PathVariable String idString) {
        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            id = 0;
        }
        Optional<BlogEntry> blog = blogEntryRepository.findById(id);
        User user = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());

        if (!blog.isPresent())
            return "redirect:/";

        BlogEntry blogEntry = blog.get();
        NavItem articleNavItem;

        if ((user != null)&&(blogEntry.getUser().getId() == user.getId())) {
            articleNavItem = NavbarViewModel.navItemBlogEntry(blogEntry);
            articleNavItem.setActive(true);
        }
        else {
            articleNavItem = NavbarViewModel.navItemBlogEntry(null);
        }
        NavItem categoryNavItem = NavbarViewModel.navItemCategory("Category", categoryRepository.findAll());
        for (Category category : categoryRepository.findAll())
            if (category.getId() == blogEntry.getCategory().getId()) {
                categoryNavItem.setLabel(category.getName());
                categoryNavItem.setActive(true);
                break;
            }

        NavbarViewModel navbarViewModel = new NavbarViewModel(NavbarViewModel.navbarDefault());
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemProfile(user));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(categoryNavItem);
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(articleNavItem);
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryViewModel",
                new ViewModel<BlogEntry>("fragment/blogentry :: page", blogEntry));


        return "blog_view_id";
    }

    @GetMapping(value = {"/category/view/{idString}"})
    public String pageCategoryView(Model model, @PathVariable String idString) {
        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            id = 0;
        }

        NavItem categoryNavItem = NavbarViewModel.navItemCategory("Category", categoryRepository.findAll());
        for (Category category : categoryRepository.findAll()) {
            if (category.getId() == id)
                categoryNavItem.setLabel(category.getName());
        }
        categoryNavItem.setActive(true);

        User user = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());

        NavbarViewModel navbarViewModel = new NavbarViewModel(NavbarViewModel.navbarDefault());
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemProfile(user));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(categoryNavItem);
        navbarViewModel.getViewModel().getNavs().getNavItemCollection()
                .add(NavbarViewModel.navItemBlogEntry(null));
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryListViewModel",
                new ViewModel<Iterable<BlogEntry>>("fragment/blogentry :: list",
                        blogEntryRepository.findAllByCategoryIdOrderByTimestampDesc(id)));


        return "blog_view";
    }

    @GetMapping(value = {"/blogEntry/edit/{idString}"})
    public String pageBlogEdit(Model model, @PathVariable String idString) {

        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            id = 0;
        }

        Optional<BlogEntry> blog = (id == 0)?Optional.of(new BlogEntry()):blogEntryRepository.findById(id);


        NavbarViewModel navbarViewModel = new NavbarViewModel(new Navbar());
        navbarViewModel.getViewModel().getBrand().setLabel("Edit");
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryViewModel",
                new ViewModel<BlogEntry>("fragment/blogentry :: edit", blog.orElse(new BlogEntry())));


        model.addAttribute("allCategory", categoryRepository.findAll());


        model.addAttribute("newBlogEntry", blog.orElse(new BlogEntry()));

        return "blog_view_id";
    }

    @PostMapping(value = {"/blogEntry/edit/process"})
    public String pageBlogEdit(BlogEntry blogEntry) {

        blogEntry.setTimestamp(new Timestamp(System.currentTimeMillis()));
        User user = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());
        if ((blogEntry.getId() == 0)||(blogEntry.getUser().getId() == user.getId())) {
            blogEntry.setUser(user);
            blogEntryRepository.save(blogEntry);
        }

        return "redirect:/blogEntry/view/"+blogEntry.getId();
    }

    @GetMapping(value = {"/blogEntry/delete/{idString}"})
    public String pageBlogDelete(@PathVariable String idString) {

        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            id = 0;
        }
        Optional<BlogEntry> blog = (id == 0)?Optional.of(new BlogEntry()):blogEntryRepository.findById(id);
        User user = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());

        blog.ifPresent(blogEntry -> {
            if (user.getId() == blogEntry.getUser().getId())
                blogEntryRepository.delete(blogEntry);});

        return "redirect:/blogEntry/view/";
    }

}
