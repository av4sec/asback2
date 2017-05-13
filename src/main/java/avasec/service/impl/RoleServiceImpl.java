package avasec.service.impl;

import avasec.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avasec.repository.RoleRepository;
import avasec.repository.AcodeRepository;
import avasec.model.Role;
import avasec.model.Acode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  @Override
  public void recalc(Role role) {

    List<String> elementList = getElementList(role);

    List<Role> roleList = collectRoles(role);
    for (Role parentRole: roleList.stream().distinct().collect(Collectors.toList())) {
      role.curr.addParentAll(parentRole.id);
      elementList.addAll(getElementList(parentRole));
    }

    for (String elementId: elementList)
      role.curr.addElement(elementId);

    roleRepository.save(role);
  }

  private List<Role> collectRoles(Role role) {
    List<Role> roleList = new ArrayList<>();
    for (String parentId: role.curr.parent) {
      Role parentRole = roleRepository.findOne(parentId);
      roleList.add(parentRole);
      roleList.addAll(collectRoles(parentRole));
    }
    return roleList;
  }

  private List<String> getElementList(Role role) {
    List <String> elementList = new ArrayList<>();
    for (String acodeId: role.curr.acode) {
      Acode acode = acodeRepository.findOne(acodeId);
      elementList.addAll(acode.curr.element);
    }
    return elementList;
  }
}
