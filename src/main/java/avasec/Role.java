package avasec;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Role {

  class Payload {

    public String charid;
    public String name;
    public int version;

    public Payload(String charid, String name, int version) {
      this.charid = charid;
      this.name = name;
      this.version = version;
    }
  }

  @Id
  public String id;
  public long extid;
  public Payload curr;
  public List<Payload> hist;

  public Role() {
  }

  public Role(long extid, String charid, String name) {
    this.extid = extid;
    this.curr = new Payload(charid, name, 1);
    this.hist = new ArrayList<>();
  }

  @Override
  public String toString() {
    return String.format(
      "Role[id=%s, extid=%d, curr.charid='%s', curr.name='%s', curr.version=%d]",
      id, this.extid, this.curr.charid, this.curr.name, this.curr.version);
  }

}
