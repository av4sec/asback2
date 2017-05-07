package avasec.service.impl;

import avasec.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avasec.repository.RoleRepository;
import avasec.repository.AcodeRepository;
import avasec.model.Role;
import avasec.model.Acode;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private AcodeRepository acodeRepository;

  @Override
  public void createRole(long extid, String charid, String name) {
    Role role = new Role(extid, charid, name);
    roleRepository.save(role);
  }

  @Override
  public void addAcodeToRole(long extid_role, long extid_acode) {
    Role role = roleRepository.findByExtid(extid_role);
    Acode acode = acodeRepository.findByExtid(extid_acode);
    role.curr.addAcode(acode.id);
    acode.curr.addRoleId(role.id);
    acodeRepository.save(acode);
    roleRepository.save(role);
  }

  @Override
  public void addParentToRole(long extid_role, long extid_parent) {
    Role role = roleRepository.findByExtid(extid_role);
    role.curr.addParent(roleRepository.findByExtid(extid_parent).id);
    roleRepository.save(role);
  }
}
