package cn.tinbat.stowaway;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "world");

        return "hello";
    }
}