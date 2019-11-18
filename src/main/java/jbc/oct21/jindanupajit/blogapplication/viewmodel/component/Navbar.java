package jbc.oct21.jindanupajit.blogapplication.viewmodel.component;

public class Navbar {
    private long autoId = 0;
    private String id;
    private Brand brand;
    private Navs navs;

    public Navbar() {
        this.id = String.format("%s-%06d",this.getClass().getSimpleName(),++autoId);
        this.brand = new Brand();
        this.navs = new Navs();
    }

    public Navbar(String id, Brand brand, Navs navs) {
        ++autoId;
        this.id = id;
        this.brand = brand;
        this.navs = navs;
    }

    public long getAutoId() {
        return ++autoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Navs getNavs() {
        return navs;
    }

    public void setNavs(Navs navs) {
        this.navs = navs;
    }
}
