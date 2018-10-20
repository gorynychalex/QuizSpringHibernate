package ru.dvfu.mrcpk.develop.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserAuth;
import ru.dvfu.mrcpk.develop.server.service.UserServiceInterface;

/**
 * Created by gorynych on 21.07.17.
 */

@Controller
@RequestMapping("${pathadminuser}")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserServiceInterface userServiceInterface;

    @RequestMapping(value = {"","list"})
    public String getUserList(ModelMap modelMap){
        modelMap.addAttribute("userlist", userServiceInterface.list());
        return "userlist";
    }

    @RequestMapping(value = "{id}")
    public String getUserById(@PathVariable("id") String userId, ModelMap modelMap){
        modelMap.addAttribute("userattr", userServiceInterface.getById(userId));
        modelMap.addAttribute("userrole", UserAuth.Authorities.values());
        return "user";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String userAddGet(ModelMap modelMap){
        modelMap.addAttribute("userattr",new UserAuth());
        modelMap.addAttribute("userrole", UserAuth.Authorities.values());
        return "useradd";
    }

    @GetMapping(value = "addauth")
    public String userAuthAddGet(ModelMap modelMap){
        modelMap.addAttribute("userattr",new UserAuth());
        return "userauthadd";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String userAddPost(@ModelAttribute("userattr") UserAuth user){
        userServiceInterface.add(user);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "{id}/edit")
    public String userEditGet(@PathVariable("id") String userId, ModelMap modelMap){
        UserAuth userAuth = userServiceInterface.getById(userId);
        modelMap.addAttribute("userattr", userAuth);
        modelMap.addAttribute("userrole", UserAuth.Authorities.values());
        return "useradd";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String userEditPost(@ModelAttribute("userattr") UserAuth user){
        userServiceInterface.update(user);
        return "redirect:/admin/users";
    }


    @RequestMapping(value = "{id}/remove", method = RequestMethod.GET)
    public String userRemoveById(@PathVariable("id") String userId){
        userServiceInterface.remove(userId);
        return "redirect:/admin/users";
    }
}
