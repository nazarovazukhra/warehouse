package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDTO;
import uz.pdp.appwarehouse.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Result add(UserDTO userDTO) {

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCode(userDTO.getCode());
        user.setActive(userDTO.getActive());
        user.setWarehouses(userDTO.getWarehouseSet());

        userRepository.save(user);
        return new Result("User added", true);
    }

    public List<User> get() {

        return userRepository.findAll();
    }

    public User getById(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = userRepository.existsById(id);
        if (exists) {
            userRepository.deleteById(id);
            return new Result("User deleted", true);
        }
        return new Result("Such user not found", false);
    }

    public Result edit(Integer id, UserDTO userDTO) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User editingUser = optionalUser.get();
            editingUser.setFirstName(userDTO.getFirstName());
            editingUser.setLastName(userDTO.getLastName());
            editingUser.setPhoneNumber(userDTO.getPhoneNumber());
            editingUser.setCode(userDTO.getCode());
            editingUser.setPassword(userDTO.getPassword());
            editingUser.setActive(userDTO.getActive());
            editingUser.setWarehouses(userDTO.getWarehouseSet());

            userRepository.save(editingUser);
            return new Result("User edited", true);
        }
        return new Result("Such user not found", true);
    }
}
