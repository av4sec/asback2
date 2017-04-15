package avasec;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="element")
public class Appl extends Element {

  public Appl(long extid, String charid, String name) {
    super(extid, charid, name);
    this.type = "Appl";
  }

  public Appl() { super(); }

  @Override
  public String toString() {
    return String.format(
      "Appl[id=%s, extid=%d, curr.charid='%s', curr.name='%s', curr.version=%d]",
      id, this.extid, this.curr.charid, this.curr.name, this.curr.version);
  }
}
