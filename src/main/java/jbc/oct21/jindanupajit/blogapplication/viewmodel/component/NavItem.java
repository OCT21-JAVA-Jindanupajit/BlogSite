package jbc.oct21.jindanupajit.blogapplication.viewmodel.component;

import java.util.ArrayList;

public class NavItem {
    private static long autoId = 0;
    private String id;
    private String label;
    private ArrayList<Link> linkCollection;
    private int linkCount;
    private boolean isActive;
    private boolean isDisabled;


    public NavItem() {
        this.id = String.format("%s-%06d",this.getClass().getSimpleName(),++autoId);
        this.label = this.id;
        this.linkCollection = new ArrayList<>();
            this.linkCollection.add(new Link());
            this.linkCollection.get(0).setLabel(this.label);
            this.linkCollection.get(0).setUrl("#");
        this.isActive = false;
        this.isDisabled = false;
    }
    public NavItem(String label, String url) {
        this.id = String.format("%s-%06d",this.getClass().getSimpleName(),++autoId);
        this.label = label;
        this.linkCollection = new ArrayList<Link>();
        this.linkCollection.add(new Link());
        this.linkCollection.get(0).setLabel(label);
        this.linkCollection.get(0).setUrl(url);
        this.isActive = false;
        this.isDisabled = false;
    }
    public NavItem(String id, String label, String url, boolean isActive, boolean isDisabled) {
        ++autoId;
        this.id = id;
        this.label = label;
        this.linkCollection = new ArrayList<Link>();
            this.linkCollection.add(new Link());
            this.linkCollection.get(0).setLabel(label);
            this.linkCollection.get(0).setUrl(url);
        this.isActive = isActive;
        this.isDisabled = isDisabled;
    }

    public NavItem(String id, String label, ArrayList<Link> linkCollection, boolean isActive, boolean isDisabled) {
        ++autoId;
        this.id = id;
        this.label = label;
        this.linkCollection = linkCollection;
        this.isActive = isActive;
        this.isDisabled = isDisabled;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<Link> getLinkCollection() {
        return linkCollection;
    }

    public void setLinkCollection(ArrayList<Link> linkCollection) {
        this.linkCollection = linkCollection;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
