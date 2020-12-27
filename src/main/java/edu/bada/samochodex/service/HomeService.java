package edu.bada.samochodex.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {

    // Returns home.html
    public String greet() {
        return "home";
    }
}
