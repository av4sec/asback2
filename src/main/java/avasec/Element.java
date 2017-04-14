package avasec;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

public class Element extends Entity {

  public Element.Payload curr;
  public List<Element.Payload> hist;

  public Element(long extid, String charid, String name) {
    this.extid = extid;
    this.curr = new Element.Payload(charid, name, 1);
    this.hist = new ArrayList<>();
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

    @DBRef
    public List<Acode> acode;

    public Payload(String charid, String name, int version) {
      super(charid, name, version);
      this.acode = new ArrayList<>();
    }
  }
}
