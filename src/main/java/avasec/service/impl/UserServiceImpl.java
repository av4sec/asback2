package avasec.service.impl;

import avasec.model.Acode;
import avasec.model.Role;
import avasec.model.User;
import avasec.repository.AcodeRepository;
import avasec.repository.RoleRepository;
import avasec.repository.UserRepository;
import avasec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private AcodeRepository acodeRepository;

  @Override
  public void create(long extid, String name) {
    User user = new User(extid, name);
    userRepository.save(user);
  }

  @Override
  public void addAcode(long extidUser, long extidAcode) {
    User user = userRepository.findByExtid(extidUser);
    Acode acode = acodeRepository.findByExtid(extidAcode);
    user.curr.addAcode(acode.id);
    userRepository.save(user);
  }

  @Override
  public void addRole(long extidUser, long extidRole) {
    User user = userRepository.findByExtid(extidUser);
    Role role = roleRepository.findByExtid(extidRole);
    user.curr.addRole(role.id);
    userRepository.save(user);
  }

  @Override
  public void recalc(User user) {
    // t.b.d.
  }
}
