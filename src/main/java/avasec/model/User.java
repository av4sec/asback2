package avasec.model;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@CompoundIndexes({
  @CompoundIndex(name = "user_extid", def = "{ 'extid': 1 }", unique = true)
})
public class User extends Entity {

  public User.Payload curr;
  public List<User.Payload> hist;

  public User(long extid, String name) {
    this.extid = extid;
    this.curr = new User.Payload(name, 1);
    this.hist = new ArrayList<>();
  }

  public User() { super(); }

  @Override
  public String toString() {
    return String.format(
      "User[id=%s, extid=%d, curr.name='%s', curr.version=%d]",
      id, this.extid, this.curr.name, this.curr.version);
  }

  public class Payload extends Entity.Payload {

    public List<String> role;

    public void addRole(String roleId) {
      if (!this.role.contains(roleId)) {
        this.role.add(roleId);
      }
    }

    public List<String> acode;

    public void addAcode(String acodeId) {
      if (!this.acode.contains(acodeId)) {
        this.acode.add(acodeId);
      }
    }

    public Payload(String name, int version) {
      super("", name, version);
      this.role = new ArrayList<>();
      this.acode = new ArrayList<>();
    }
  }
}
