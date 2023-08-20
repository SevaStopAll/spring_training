package com.sevastopall.spring.http.controller;

import com.sevastopall.spring.dto.PageResponse;
import com.sevastopall.spring.dto.UserCreateEditDto;
import com.sevastopall.spring.dto.UserFilter;
import com.sevastopall.spring.dto.UserReadDto;
import com.sevastopall.spring.entity.Role;
import com.sevastopall.spring.service.CompanyService;
import com.sevastopall.spring.service.UserService;
import com.sevastopall.spring.validation.group.CreateAction;
import com.sevastopall.spring.validation.group.UpdateAction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.groups.Default;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CompanyService companyService;


    @GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable) {
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return userService.findById(id)
                .map(user -> {
                        model.addAttribute("user", user);
                        model.addAttribute("roles", Role.values());
                        model.addAttribute("companies", companyService.findAll());
        return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreateEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        return "user/registration";
    }
    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute @Validated({Default.class, CreateAction.class}) UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        return "redirect:/users/" + userService.create(user).getId();
    }

    //@PutMapping("/id")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute @Validated({Default.class, UpdateAction.class}) UserCreateEditDto user) {
        return userService.update(id, user)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //@DeleteMapping("/id")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }
}
