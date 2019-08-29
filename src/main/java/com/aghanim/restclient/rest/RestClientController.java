package com.aghanim.restclient.rest;

import com.aghanim.restclient.model.User;
import com.aghanim.restclient.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
public class RestClientController {

    @Autowired
    private RestService restService;

    @GetMapping(value = "/users")
    public String getUsers(Model model){
        List<User> users = restService.getUsers();
        model.addAttribute("list", users);
        return "users";
    }

    @GetMapping(value = "/add")
    public String userFormForAdd (Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }

    @GetMapping(value = "/edit/{id}")
    public String userFormForUpdate(@PathVariable("id") long id, Model model){
        User user = restService.getUser(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user){
        restService.addUser(user);
        return "redirect:/users";
    }

    @PutMapping(value = "/edit/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute("user") User user){
        restService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        restService.deleteUser(id);
        return "redirect:/users";
    }
}
