package com.u2tzjtne.SpringDemo.controller;

import com.u2tzjtne.SpringDemo.domain.User;
import com.u2tzjtne.SpringDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/person/save")
    public User save(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        if (userRepository.save(user)) {
            System.out.println("用户对象保存成功：" + user.toString());
        } else {
            System.out.println("用户对象保存失败！");
        }
        return user;
    }
}
