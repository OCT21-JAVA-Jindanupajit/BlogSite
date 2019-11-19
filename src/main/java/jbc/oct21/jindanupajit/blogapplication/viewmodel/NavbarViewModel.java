package jbc.oct21.jindanupajit.blogapplication.viewmodel;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import jbc.oct21.jindanupajit.blogapplication.model.Category;
import jbc.oct21.jindanupajit.blogapplication.repository.CategoryRepository;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Link;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.NavItem;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Navbar;


import java.util.ArrayList;
import java.util.Collections;


public class NavbarViewModel extends  ViewModel<Navbar> {


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
        navItem.getLinkCollection().get(0).setUrl("/blog/view");
        navbar.getNavs().getNavItemCollection().add(navItem);

        navItem = new NavItem();
        navItem.setLabel("My Profile");
        navbar.getNavs().getNavItemCollection().add(navItem);

        return navbar;
    }

    public static NavItem navItem(String label, CategoryRepository categoryRepository) {
        NavItem navItem = new NavItem();
        navItem.setLabel(label);
        ArrayList<Link> linkCollection = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            linkCollection.add(new Link(category.getName(),"/category/view/"+category.getId()));
        }
        navItem.setLinkCollection(linkCollection);
        return navItem;
    }

    public static NavItem navItem(String label, BlogEntry blogEntry) {
        NavItem navItem = new NavItem();
        navItem.setLabel(label);
        ArrayList<Link> linkCollection = new ArrayList<>();
        navItem.setLinkCollection(linkCollection);

        navItem.getLinkCollection().add(new Link("New Article","/blog/edit/0"));

        if ((blogEntry == null)||(blogEntry.getId() == 0)) {
            navItem.setLabel("New Article");
            return navItem;
        }
        Collections.addAll(navItem.getLinkCollection(),
                new Link("-","-"),
                new Link("Edit This Article","/blog/edit/"+blogEntry.getId()),
                new Link("Delete This Article","javascript:$('#delete').modal('show');")
        );
        return navItem;
    }


}
