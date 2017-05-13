package avasec.controller;

import java.util.Collection;

import avasec.model.User;
import avasec.repository.UserRepository;
import avasec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @GetMapping(value = "/{extid}")
  public User getUserByExtid(@PathVariable Long extid) {

    User user = userRepository.findByExtid(extid);
    return user;
  }

  @GetMapping
  Collection<User> allUsers() { return userRepository.findAll(); }

  @RequestMapping(value = "/recalc", method = GET)
  public void recalc() {

  }

}
