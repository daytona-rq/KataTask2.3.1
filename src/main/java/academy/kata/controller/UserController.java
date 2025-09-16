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
        model.addAttribute("users", userService.getAllUsers());
        return "main-page";
    }

    @GetMapping("/add-user")
    public String addUser(@ModelAttribute("user") User user) {
        return "add-user";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit-user")
    public String updateUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id).orElseThrow());
        return "edit-user";
    }

    @PostMapping("/edit-user")
    public String edit(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-user";
        }
        userService.updateUser(user.getId(), user);
        return "redirect:/";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
