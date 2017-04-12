package avasec;

public class Role extends Entity {

  public Role(long extid, String charid, String name) {
    super(extid, charid, name);
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

}
