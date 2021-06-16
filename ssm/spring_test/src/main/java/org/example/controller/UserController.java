package org.example.controller;

import org.example.domain.Role;
import org.example.domain.User;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/list")
    public ModelAndView showList(ModelAndView modelAndView){
        List<User> userList = userService.list();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping(value = "/savePage")
    public ModelAndView savePage(ModelAndView modelAndView){
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    @RequestMapping(value = "/save")
    public String save(User user, @RequestParam(value = "roleIds")Long[] roleIds){
        System.out.println(user);
        userService.save(user, roleIds);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/del/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String del(@PathVariable(value = "userId") Long userId){
        System.out.println(userId);
        userService.del(userId);
        return "success";
    }
}
