package com.fireworks.kundalini;

import java.util.HashMap;
import java.util.Map;

class User {
    private Map<String, MyObject> user = new HashMap<String, MyObject>();

    public Map<String, MyObject> getUser() {
        return user;
    }

    public void setUser(Map<String, MyObject> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "User{" +
                "user=" + user +
                '}';
    }
}