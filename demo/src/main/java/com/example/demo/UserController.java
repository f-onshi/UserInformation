package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/")
public class UserController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/index")
    public String index(Model model) {

        String sql = "SELECT * FROM userInformation";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        model.addAttribute("userList", list);
        return "index";
    }
    
}
