package docrob.springdemo1.controllers;

import docrob.springdemo1.models.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/dogs")
public class DogController {

    @GetMapping
    public String all(Model model) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog(1, "Spot"));
        dogs.add(new Dog(2, "Barfy"));

        model.addAttribute("dogs", dogs);

        return "dogs";
    }

    @GetMapping("/{dogId}")
    @ResponseBody
    public String viewDog(@PathVariable long dogId) {
        return "show page for a dog w id " + dogId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
