package avasec;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

public class Acode extends Entity {

  public Acode.Payload curr;
  public List<Acode.Payload> hist;

  public Acode(long extid, String charid, String name) {
    this.extid = extid;
    this.curr = new Acode.Payload(charid, name, 1);
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

    @DBRef
    public List<Role> role;

    public Payload(String charid, String name, int version) {
      super(charid, name, version);
      this.role = new ArrayList<>();
    }
  }

}
