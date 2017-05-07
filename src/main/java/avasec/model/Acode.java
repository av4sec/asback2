package avasec.model;

import java.util.ArrayList;
import java.util.List;

public class Acode extends Entity {

  public Payload curr;
  public List<Payload> hist;

  public Acode(long extid, String charid, String name) {
    this.extid = extid;
    this.curr = new Payload(charid, name, 1);
    this.hist = new ArrayList<>();
  }

  public Acode() {
    super();
  }

  @Override
  public String toString() {
    return String.format(
      "Acode[id=%s, extid=%d, curr.charid='%s', curr.name='%s', curr.version=%d]",
      id, this.extid, this.curr.charid, this.curr.name, this.curr.version);
  }
  public class Payload extends Entity.Payload {

    public List<String> role;
    public List<String> element;

    public void addRoleId(String roleId) {
      if (!this.role.contains(roleId)) {
        this.role.add(roleId);
      }
    }

    public void addElementId(String elementId) {
      if (!this.element.contains(elementId)) {
        this.element.add(elementId);
      }
    }

    public Payload(String charid, String name, int version) {
      super(charid, name, version);
      this.role = new ArrayList<>();
      this.element = new ArrayList<>();
    }
  }

}
