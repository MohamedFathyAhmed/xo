/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.utlis;

/**
 *
 * @author mohamed
 */
public class NavigationDestination {
    private final String destination;
    private final String title;

    public NavigationDestination(String name, String title) {
        this.destination = name;
        this.title = title;
    }

    public String getDestination() {
        return destination;
    }

    public String getTitle() {
        return title;
    }

    
}
