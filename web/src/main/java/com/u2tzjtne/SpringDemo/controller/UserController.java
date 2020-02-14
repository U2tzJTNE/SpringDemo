package com.u2tzjtne.SpringDemo.controller;

import com.u2tzjtne.SpringDemo.domain.User;
import com.u2tzjtne.SpringDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/person/addUser")
    public User addUser(@RequestParam String username,
                     @RequestParam String address,
                     @RequestParam String sex,
                     @RequestParam String birthday) {
        User user = new User();
        user.setUsername(username);
        user.setAddress(address);
        user.setSex(sex);
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = fmt.parse(birthday);
            user.setBirthday(date);
            if (userRepository.addUser(user)) {
                System.out.println("用户对象保存成功：" + user.toString());
            } else {
                System.out.println("用户对象保存失败！");
            }
            return user;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/person/findAll")
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
