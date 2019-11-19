package jbc.oct21.jindanupajit.blogapplication.controller;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import jbc.oct21.jindanupajit.blogapplication.model.Category;
import jbc.oct21.jindanupajit.blogapplication.model.User;
import jbc.oct21.jindanupajit.blogapplication.repository.BlogEntryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.CategoryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.UserRepository;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.NavbarViewModel;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.ViewModel;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.NavItem;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Navbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = {"/"})
    public String pageBlogView() {
        return "redirect:/blog/view";
    }

    @GetMapping(value = {"/blog/view"})
    public String pageBlogView(Model model) {

        NavbarViewModel navbarViewModel = new NavbarViewModel(NavbarViewModel.navbarDefault());
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItem("Category", categoryRepository));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItem("Article", (BlogEntry) null));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().get(0).setActive(true);
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryListViewModel",
                new ViewModel<Iterable<BlogEntry>>("fragment/blogentry :: list", blogEntryRepository.findAllByOrderByTimestampDesc()));

        return "blog_view";
    }



    @GetMapping(value = {"/blog/view/{idString}"})
    public String pageBlogView(Model model, @PathVariable String idString) {
        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            id = 0;
        }
        Optional<BlogEntry> blog = blogEntryRepository.findById(id);

        NavItem articleNavItem = NavbarViewModel.navItem("Article", blog.orElse(new BlogEntry()));
        articleNavItem.setActive(true);

        NavItem categoryNavItem = NavbarViewModel.navItem("Category", categoryRepository);
        for (Category category : categoryRepository.findAll())
            if (category.getId() == blog.orElse(new BlogEntry()).getCategory().getId()) {
                categoryNavItem.setLabel(category.getName());
                categoryNavItem.setActive(true);
                break;
            }

        NavbarViewModel navbarViewModel = new NavbarViewModel(NavbarViewModel.navbarDefault());
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(categoryNavItem);
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(articleNavItem);
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryViewModel",
                new ViewModel<BlogEntry>("fragment/blogentry :: page", blog.orElse(new BlogEntry())));


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

        NavItem categoryNavItem = NavbarViewModel.navItem("Category", categoryRepository);
        for (Category category : categoryRepository.findAll()) {
            if (category.getId() == id)
                categoryNavItem.setLabel(category.getName());
        }
        categoryNavItem.setActive(true);
        NavbarViewModel navbarViewModel = new NavbarViewModel(NavbarViewModel.navbarDefault());
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(categoryNavItem);
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItem("Article",
                (BlogEntry) null));
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryListViewModel",
                new ViewModel<Iterable<BlogEntry>>("fragment/blogentry :: list",
                        blogEntryRepository.findAllByCategoryIdOrderByTimestampDesc(id)));


        return "blog_view";
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
