package avasec.service;

import avasec.model.Role;

public interface RoleService {

  void createRole(long extid, String charid, String name);
  void addAcodeToRole(long extid_role, long extid_acode);
  void addParentToRole(long extid_role, long extid_parent);
  void recalc(Role role);
}
