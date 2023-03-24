package docrob.springdemo1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@ResponseBody
public class HomeController {

    @GetMapping("/home/{personName}")
    public String welcome(@RequestParam(name = "age") int age
            , @PathVariable String personName
            , Model model) {

        model.addAttribute("name", "Bob Smith");
        model.addAttribute("personName", personName);
        model.addAttribute("personAge", age);

        return "home/home";
    }
}
