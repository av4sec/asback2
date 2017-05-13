package avasec.service;

import avasec.model.User;

public interface UserService {

  void create(long extid, String name);
  void addAcode(long extidUser, long extidAcode);
  void addRole(long extidUser, long extidRole);
  void recalc(User user);
}
