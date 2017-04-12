package avasec;

public class Acode extends Entity {

  public Acode(long extid, String charid, String name) {
    super(extid, charid, name);
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

}
