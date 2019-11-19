package jbc.oct21.jindanupajit.blogapplication.controller;

import jbc.oct21.jindanupajit.blogapplication.model.User;
import jbc.oct21.jindanupajit.blogapplication.repository.CategoryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.UserRepository;
import jbc.oct21.jindanupajit.blogapplication.service.NavbarViewModel;
import jbc.oct21.jindanupajit.blogapplication.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import sun.plugin.util.UserProfile;

@Controller
public class ProfileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @ModelAttribute
    public void globalModelAttribute(Model model) {
        User user = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());
        model.addAttribute("authenticatedUser", user);
    }

    @GetMapping(value = {"/profile/view/{idString}"})
    public String profileView(Model model, @PathVariable String idString) {
        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            id = 0;
        }
        User authenticatedUser = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());
        User user = userRepository.findById(id).orElse(new User());
        NavbarViewModel navbarViewModel = new NavbarViewModel(NavbarViewModel.navbarDefault());
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemProfile(authenticatedUser ));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemCategory("Category" , categoryRepository.findAll()));
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(NavbarViewModel.navItemBlogEntry(null));

        navbarViewModel.getViewModel().getNavs().getNavItemCollection().get(1).setActive(true);
        model.addAttribute("NavbarViewModel", navbarViewModel);
        model.addAttribute("user", user);
        return "profile_view_id";
    }
}
