package jbc.oct21.jindanupajit.blogapplication.viewmodel;

import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Link;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.NavItem;
import jbc.oct21.jindanupajit.blogapplication.viewmodel.component.Navbar;

public class NavbarViewModel extends  ViewModel<Navbar> {

    public NavbarViewModel() {

        super("fragment/navbar :: navbar", new Navbar());

        getViewModel().getBrand().setLabel("JBC Blog");
        getViewModel().getBrand().setLinkUrl("/");

        NavItem navItem;

        navItem = new NavItem();
        navItem.setLabel("Home");
        navItem.getLinkCollection().get(0).setUrl("/blog/view");
        navItem.setActive(true);
        getViewModel().getNavs().getNavItemCollection().add(navItem);

        navItem = new NavItem();
        navItem.setLabel("My Profile");
        getViewModel().getNavs().getNavItemCollection().add(navItem);

        navItem = new NavItem();
        navItem.setLabel("Category");
            navItem.getLinkCollection().get(0).setLabel("Menu 1");
            navItem.getLinkCollection().get(0).setUrl("/link1");
            navItem.getLinkCollection().add(new Link());
            navItem.getLinkCollection().get(1).setLabel("Menu 2");
            navItem.getLinkCollection().get(1).setUrl("/link2");
            navItem.getLinkCollection().add(new Link());
            navItem.getLinkCollection().get(2).setLabel("-");
            navItem.getLinkCollection().get(2).setUrl("-");
            navItem.getLinkCollection().add(new Link());
            navItem.getLinkCollection().get(3).setLabel("Menu 3");
            navItem.getLinkCollection().get(3).setUrl("/link3");
        getViewModel().getNavs().getNavItemCollection().add(navItem);


    }


}
