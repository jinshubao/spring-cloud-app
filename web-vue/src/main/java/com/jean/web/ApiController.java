package com.jean.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jinshubao on 2017/6/6.
 */
@Controller
public class ApiController {
    private static Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/")
    public String toIndex() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("title", "hahahah");
        model.addAttribute("host", "host1");
        return "index";
    }
}
