package com.toasttab.toastexplorer;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Room {

    private String imagePath;
    private String name;
    private Integer id;
    private HashSet<Route> routes;

    public Room (String imagePath, String name) {
        this.imagePath = imagePath;
        this.name = name;
        this.routes = new HashSet<>();
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public Set<Route> getRoutes() {
        return routes;
    }

}
