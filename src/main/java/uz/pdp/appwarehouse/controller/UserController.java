package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDTO;
import uz.pdp.appwarehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Result addUser(@RequestBody UserDTO userDTO) {

        return userService.add(userDTO);
    }

    @GetMapping
    public List<User> getALl() {

        return userService.get();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {

        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {

        return userService.delete(id);
    }

    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id, @RequestBody UserDTO userDTO) {

        return userService.edit(id, userDTO);
    }
}
