package jbc.oct21.jindanupajit.blogapplication.service;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import jbc.oct21.jindanupajit.blogapplication.model.Category;
import jbc.oct21.jindanupajit.blogapplication.model.User;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Link;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.NavItem;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Navbar;

import java.util.ArrayList;
import java.util.Collections;


public class NavbarViewModel extends  ViewModel<Navbar> {



    public NavbarViewModel() {
    }


    public NavbarViewModel(Navbar navbar) {

        super("fragment/navbar :: navbar", navbar);

    }

    // default navbar
    public static Navbar navbarDefault() {
        Navbar navbar = new Navbar();
        navbar.getBrand().setLabel("JBC Blog");
        navbar.getBrand().setLinkUrl("/");

        NavItem navItem;

        navItem = new NavItem();
        navItem.setLabel("Home");
        navItem.getLinkCollection().get(0).setUrl("/blogEntry/view");
        navbar.getNavs().getNavItemCollection().add(navItem);

        return navbar;
    }

    public static NavItem navItemProfile(User user) {

        if (user == null)
            return new NavItem("Login","/login");;

        NavItem navItem = new NavItem(user.getName(),"/logout");

        navItem.getLinkCollection().get(0).setLabel("Logout");

        Collections.addAll(navItem.getLinkCollection(),
                new Link("-","-"),
                new Link("My Profile","/profile/view/"+user.getId())
        );

        return navItem;
    }

    public static NavItem navItemCategory(String label, Iterable<Category> categoryDatabase) {
        NavbarViewModel navbarViewModel = new NavbarViewModel();
        NavItem navItem = new NavItem();
        navItem.setLabel(label);
        ArrayList<Link> linkCollection = new ArrayList<>();

        for (Category category : categoryDatabase) {
            linkCollection.add(new Link(category.getName(),"/category/view/"+category.getId()));
        }
        navItem.setLinkCollection(linkCollection);
        return navItem;
    }

    public static NavItem navItemBlogEntry(BlogEntry blogEntry) {

        if ((blogEntry == null)||(blogEntry.getId() == 0))
            return new NavItem("New Article","/blogEntry/edit/0");

        NavItem navItem = new NavItem("Article","#");
        ArrayList<Link> linkCollection = new ArrayList<>();
        navItem.setLinkCollection(linkCollection);

        navItem.getLinkCollection().add(new Link("New Article","/blogEntry/edit/0"));


        Collections.addAll(navItem.getLinkCollection(),
                new Link("-","-"),
                new Link("Edit This Article","/blogEntry/edit/"+blogEntry.getId()),
                new Link("Delete This Article","javascript:$('#delete').modal('show');")
        );
        return navItem;
    }


}
