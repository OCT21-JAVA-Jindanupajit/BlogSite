package jbc.oct21.jindanupajit.blogapplication.viewmodel.component;

import java.util.ArrayList;

public class Navs {
    private static long autoId = 0;
    private String id;
    private ArrayList<NavItem> navItemCollection;

    public Navs() {
        this.id = String.format("%s-%06d",this.getClass().getSimpleName(),++autoId);
        this.navItemCollection = new ArrayList<>();
    }

    public Navs(String id, ArrayList<NavItem> navItemCollection) {
        ++autoId;
        this.id = id;
        this.navItemCollection = navItemCollection;
    }

    public static long getAutoId() {
        return ++autoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<NavItem> getNavItemCollection() {
        return navItemCollection;
    }

    public void setNavItemCollection(ArrayList<NavItem> navItemCollection) {
        this.navItemCollection = navItemCollection;
    }

}
