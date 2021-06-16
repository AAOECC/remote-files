package org.example.controller;

import org.example.domain.Role;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/list")
    public ModelAndView showList(ModelAndView modelAndView) {
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList", roleList);
//        System.out.println(roleList);
        modelAndView.setViewName("role-list");

        return modelAndView;
    }

    @RequestMapping(value = "/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/list";
    }
}
