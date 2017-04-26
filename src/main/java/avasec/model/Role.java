package avasec.model;

import avasec.repository.AcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

public class Role extends Entity {

  @Autowired
  private AcodeRepository acodeRepository;

  public Role.Payload curr;
  public List<Role.Payload> hist;

  public Role(long extid, String charid, String name) {
    this.extid = extid;
    this.curr = new Role.Payload(charid, name, 1);
    this.hist = new ArrayList<>();
  }

  public Role() {
    super();
  }

  @Override
  public String toString() {
    return String.format(
      "Role[id=%s, extid=%d, curr.charid='%s', curr.name='%s', curr.version=%d]",
      id, this.extid, this.curr.charid, this.curr.name, this.curr.version);
  }

  public class Payload extends Entity.Payload {

    @DBRef
    private List<Role> parent;

    public void addParent(Role parent) {
      if (!this.parent.contains(parent)) {
        this.parent.add(parent);
      }
    }

    @DBRef
    private List<Acode> acode;

    public void addAcode(Acode acode) {
      if (!this.acode.contains(acode)) {
        this.acode.add(acode);
        // acode.curr.addRoleId(id);
        // acodeRepository.save(acode);
      }
    }

    public Payload(String charid, String name, int version) {
      super(charid, name, version);
      this.acode = new ArrayList<>();
      this.parent = new ArrayList<>();
    }
  }
}
