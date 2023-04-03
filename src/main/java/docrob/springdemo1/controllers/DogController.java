package docrob.springdemo1.controllers;

import docrob.springdemo1.models.Dog;
import docrob.springdemo1.repositories.DogRepository;
import docrob.springdemo1.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/dogs")
public class DogController {
    private final DogRepository dogDao;

    private final EmailService emailService;

    @GetMapping
    public String all(Model model) {
        List<Dog> dogs = dogDao.findAll();

        model.addAttribute("dogs", dogs);

//        model.addAttribute("msg", "hello world");
        return "dogs/index";
    }

    @GetMapping("/{dogId}")
    @ResponseBody
    public String viewDog(@PathVariable long dogId) {
        Dog dog = dogDao.findById(dogId).get();

        String newName = dog.getName() + dog.getName();
        dog.setName(newName);

        dogDao.save(dog);

        System.out.println(dog);

        return "show page for a dog w id " + dogId;
    }

    @PostMapping("/create")
    public String createDog(@ModelAttribute Dog dog) {
        System.out.println(dog);

        dogDao.save(dog);
//        emailService.prepareAndSend(dog, "A dog has been created: " + dog.getName(), dog.toString());
        return "redirect:/dogs";
    }

    @GetMapping("/create")
    public String createDog(Model model) {
        // create default values for form
        Dog dog = new Dog();
        dog.setName("Spot");
        dog.setGender("Male");
        model.addAttribute("dog", dog);
        return "/dogs/create";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
