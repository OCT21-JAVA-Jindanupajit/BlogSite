package jbc.oct21.jindanupajit.blogapplication.controller;

import jbc.oct21.jindanupajit.blogapplication.component.CloudinaryImageUploader;
import jbc.oct21.jindanupajit.blogapplication.model.CloudinaryImage;
import jbc.oct21.jindanupajit.blogapplication.model.User;
import jbc.oct21.jindanupajit.blogapplication.repository.CategoryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.CloudinaryImageRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.UserRepository;
import jbc.oct21.jindanupajit.blogapplication.service.UserDetailsServiceImpl;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.NavbarViewModel;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProfileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CloudinaryImageRepository cloudinaryImageRepository;

    @Autowired
    CloudinaryImageUploader cloudinaryImageUploader;

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
        model.addAttribute("UserViewModel", new ViewModel<User>("fragment/profile :: page", user));
        return "profile_view_id";
    }

    @GetMapping(value = {"/profile/edit/{idString}"})
    public String profileEdit(Model model, @PathVariable String idString) {
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
        model.addAttribute("UserViewModel", new ViewModel<User>("fragment/profile :: edit", user));
        model.addAttribute("newUser",  user);
        return "profile_view_id";
    }

    @PostMapping(value = {"/profile/edit/process"})
    public String profileEditProcess(@ModelAttribute User user, @RequestParam("imageFile") MultipartFile imageFile) {

        User authenticatedUser = userRepository.findByName(UserDetailsServiceImpl.getAuthentication().getName());

        if ((authenticatedUser == null)||(user.getId() != authenticatedUser.getId()))
            return "redirect:/";


        user.setSocialMedia(authenticatedUser.getSocialMedia());
        user.setPhoto(authenticatedUser.getPhoto());


        userRepository.save(user);

        if (!imageFile.isEmpty()) {
            CloudinaryImage cloudinaryImage =
                    cloudinaryImageUploader.upload("profile",
                            String.format("avatar-%09d",user.getId()), imageFile);
            if (cloudinaryImage != null) {
                try {
                    cloudinaryImageRepository.save(cloudinaryImage);
                    user.setPhoto(cloudinaryImage);
                    userRepository.save(user);
                } catch (Exception e) {
                    System.out.println("*********** ProfileController *********");
                    System.out.println("User ID "+user.getId());
                    System.out.println("Image ID "+cloudinaryImage.getId());
                    e.printStackTrace();
                    System.out.println("***************************************");
                }
            }
        }

        return "redirect:/profile/view/"+user.getId();
    }
}
