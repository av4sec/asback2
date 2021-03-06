package avasec.model;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@CompoundIndexes({
  @CompoundIndex(name = "element_extid", def = "{ 'extid': 1 }", unique = true)
})
public class Element extends Entity {

  public Element.Payload curr;
  public List<Element.Payload> hist;
  public String type;

  public Element(long extid, String charid, String name) {
    this.extid = extid;
    this.curr = new Element.Payload(charid, name, 1);
    this.hist = new ArrayList<>();
    this.type = "Element";
  }

  public Element() {
    super();
  }

  @Override
  public String toString() {
    return String.format(
      "Element[id=%s, extid=%d, curr.charid='%s', curr.name='%s', curr.version=%d]",
      id, this.extid, this.curr.charid, this.curr.name, this.curr.version);
  }

  public class Payload extends Entity.Payload {

    public List<String> acode;

    public void addAcode(String acode) {
      if (!this.acode.contains(acode)) {
        this.acode.add(acode);
      }
    }

    public List<String> role;

    public void addRole(String role) {
      if (!this.role.contains(role)) {
        this.role.add(role);
      }
    }

    public Payload(String charid, String name, int version) {
      super(charid, name, version);
      this.acode = new ArrayList<>();
      this.role = new ArrayList<>();
    }
  }
}
