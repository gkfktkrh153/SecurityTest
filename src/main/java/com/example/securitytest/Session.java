package com.example.securitytest;

import java.util.HashMap;

public class Session {
    private final HashMap<String, Object> store = new HashMap<>();

    public Object getAttribute(String key){
        return store.get(key);
    }
    public void setAttribute(String key, Object value){
        store.put(key, value);
    }
}
