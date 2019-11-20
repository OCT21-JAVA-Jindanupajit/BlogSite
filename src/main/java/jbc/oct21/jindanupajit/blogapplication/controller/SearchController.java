package jbc.oct21.jindanupajit.blogapplication.controller;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import jbc.oct21.jindanupajit.blogapplication.model.User;
import jbc.oct21.jindanupajit.blogapplication.repository.BlogEntryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.CategoryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.UserRepository;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.NavbarViewModel;
import jbc.oct21.jindanupajit.blogapplication.service.UserDetailsServiceImpl;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogEntryRepository blogEntryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @ModelAttribute
    public void globalModelAttribute(Model model) {
        User user = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());
        model.addAttribute("authenticatedUser", user);
    }
    @GetMapping(value = {"/search"})
    public String profileView(Model model, @RequestParam("q") String toSearch) {

        User authenticatedUser = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());

        Iterable<BlogEntry> blogEntryCollection = blogEntryRepository
                .findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByTimestampDesc(toSearch, toSearch);

        NavbarViewModel navbarViewModel = new NavbarViewModel(NavbarViewModel.navbarDefault());
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemProfile(authenticatedUser));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemCategory("Category" , categoryRepository.findAll()));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemBlogEntry(null));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().get(0).setActive(true);
        model.addAttribute("NavbarViewModel", navbarViewModel);

        model.addAttribute("BlogEntryListViewModel",
                new ViewModel<Iterable<BlogEntry>>("fragment/blogentry :: list", blogEntryCollection));

        return "blog_view";


    }
}
