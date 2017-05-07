package avasec.model;

import java.util.ArrayList;
import java.util.List;

public class Role extends Entity {

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

    public List<String> parent;

    public void addParent(String parent) {
      if (!this.parent.contains(parent)) {
        this.parent.add(parent);
      }
    }

    public List<String> acode;

    public void addAcode(String acode) {
      if (!this.acode.contains(acode)) {
        this.acode.add(acode);
      }
    }

    public List<String> element;

    public void addElement(String element) {
      if (!this.element.contains(element)) {
        this.element.add(element);
      }
    }

    public Payload(String charid, String name, int version) {
      super(charid, name, version);
      this.acode = new ArrayList<>();
      this.parent = new ArrayList<>();
      this.element = new ArrayList<>();
    }
  }
}
