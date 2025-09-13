package academy.kata.controller;

import academy.kata.model.User;
import academy.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        if (userService.getAllUsers().isEmpty()) {
            userService.saveUser(new User("John", 18, true));
            userService.saveUser(new User("Danny", 15, false));
            userService.saveUser(new User("Tom", 37, true));
            userService.saveUser(new User("Jack", 23, false));
        }
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "main-page";
    }

    @PostMapping("/add-user")
    public String addUser(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            return "main-page";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping("/update-user")
    public String updateUser(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam(defaultValue = "false") boolean hasCar) {
        userService.updateUser(id, name, age, hasCar);
        return "redirect:/";
    }

}
