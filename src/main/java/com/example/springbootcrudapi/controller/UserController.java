package com.example.springbootcrudapi.controller;

import com.example.springbootcrudapi.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    List<User> listUser = new ArrayList<User>();

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ResponseEntity<List<User>> Init(){
        listUser.add(new User(1, "Bùi Oanh 1", "Hà Nội 1"));
        listUser.add(new User(2, "Bùi Oanh 2", "Hà Nội 2"));
        listUser.add(new User(3, "Bùi Oanh 3", "Hà Nội 3"));
        listUser.add(new User(4, "Bùi Oanh 4", "Hà Nội 4"));
        listUser.add(new User(5, "Bùi Oanh 5", "Hà Nội 5"));

        return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/User", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUser(){
        if(listUser.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/User/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findUser(@PathVariable("id") long id) {
        User user;
        for(User u : listUser) {
            if(u.getId() == id) {
                return new ResponseEntity<User>(u, HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/User", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@Validated @RequestBody User user) {
        listUser.add(user);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/User", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestParam(value = "id") Long UserId,
                                           @Validated @RequestBody User UserForm) {
        User user;
        for(User u : listUser) {
            if(u.getId() == UserId.longValue()) {
                u.setAddress(UserForm.getAddress());
                u.setName(UserForm.getName());
                return new ResponseEntity<User>(u, HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/User/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {

        for(int i = 0; i < listUser.size(); i++) {
            User user = listUser.get(i);
            if(user.getId() == id.longValue()) {
                listUser.remove(i);
                return new ResponseEntity<User>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
