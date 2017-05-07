package avasec.controller;

import java.util.Collection;

import avasec.model.Role;
import avasec.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/role")
public class RoleController {

  @Autowired
  private RoleRepository roleRepository;

  @GetMapping(value = "/{extid}")
  public Role getRoleByExtid(@PathVariable Long extid) {

    Role role = roleRepository.findByExtid(extid);
    return role;
  }

  @GetMapping
  Collection<Role> allRoles() {
    return roleRepository.findAll();
  }

}
