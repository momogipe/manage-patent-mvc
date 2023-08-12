package com.momo.momo.UserControllers;

import com.momo.momo.EntitiesUser.User;
import com.momo.momo.UserServices.UserNotFoundException;
import com.momo.momo.UserServices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @GetMapping("/users")
    public String showList(Model model){
        List<User> listusers=userService.userList();
        model.addAttribute("listusers",listusers);
        return "users";
    }
    @GetMapping("users/new")
    public String showform(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add New Patient");
        return "user_form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        ra.addFlashAttribute("message","the user has been saved successfuly");
        userService.save(user);
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
        public String showEditForm(@PathVariable("id") Integer id, Model model,RedirectAttributes ra) {
        try {
            User user=userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User(ID:" + id + ")");
            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }

    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
             userService.delete(id);
            ra.addFlashAttribute("message","the user with ID"+ id+"has been deleted successfuly");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/users";
    }
}
