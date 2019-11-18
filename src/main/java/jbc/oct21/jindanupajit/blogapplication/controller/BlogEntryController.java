package jbc.oct21.jindanupajit.blogapplication.controller;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import jbc.oct21.jindanupajit.blogapplication.repository.BlogEntryRepository;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.NavbarViewModel;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.ViewModel;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Link;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.NavItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@Controller
public class BlogEntryController {

    @Autowired
    BlogEntryRepository blogEntryRepository;

    @GetMapping(value = {"/"})
    public String pageBlogView() {
        return "redirect:/blog/view";
    }

    @GetMapping(value = {"/blog/view"})
    public String pageBlogView(Model model) {
        model.addAttribute("BlogEntryListViewModel",
                new ViewModel<Iterable<BlogEntry>>("fragment/blogentry :: list", blogEntryRepository.findAll()));
        model.addAttribute("NavbarViewModel", new NavbarViewModel());
        return "blog_view";
    }

    @GetMapping(value = {"/blog/view/{id}"})
    public String pageBlogView(Model model, @PathVariable String id) {
        Optional<BlogEntry> blog = blogEntryRepository.findById(Long.parseLong(id));


        NavItem navItem = new NavItem("Article","#");
        navItem.getLinkCollection().set(0, new Link("Edit","/blog/edit/"+id));
        Collections.addAll(navItem.getLinkCollection(),
            new Link("Delete","/blog/delete/"+id),
            new Link("-","-"),
            new Link("New Article","/blog/create")
        );

        NavbarViewModel navbarViewModel = new NavbarViewModel();
        navbarViewModel.getViewModel().getNavs().getNavItemCollection().add(navItem);

        model.addAttribute("BlogEntryViewModel",
                new ViewModel<BlogEntry>("fragment/blog_view_id", blog.orElse(new BlogEntry())));
        model.addAttribute("NavbarViewModel", navbarViewModel);

        return "blog_view_id";
    }

}
