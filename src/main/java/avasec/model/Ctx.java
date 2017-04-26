package avasec.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="element")
public class Ctx extends Element {

  public Ctx(long extid, String charid, String name) {
    super(extid, charid, name);
    this.type = "Ctx";
  }

  public Ctx() { super(); }

  @Override
  public String toString() {
    return String.format(
      "Ctx[id=%s, extid=%d, curr.charid='%s', curr.name='%s', curr.version=%d]",
      id, this.extid, this.curr.charid, this.curr.name, this.curr.version);
  }
}
