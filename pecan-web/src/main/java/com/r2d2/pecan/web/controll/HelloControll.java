package com.r2d2.pecan.web.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hello")
public class HelloControll {

    @RequestMapping
    public String hello() {
        return "Hello Page";
    }

    @RequestMapping("/info")
    public Map<String, String> getInfo(@RequestParam String name) {
        Map<String, String> map = new HashMap();
        map.put("name", name);
        return map;
    }

    @RequestMapping("/list")
    public List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList();
        Map<String, String> map;
        for (int i = 1; i <= 5; i++) {
            map = new HashMap<String, String>();
            map.put("name", "Shanhy-" + i);
            list.add(map);
        }
        return list;
    }
}