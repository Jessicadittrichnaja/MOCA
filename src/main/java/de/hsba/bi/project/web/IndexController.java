package de.hsba.bi.project.web;

import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {
    @RequestMapping(path = "/Overview")
    public String getGreeting() {
        return "Overview";
    }
}
