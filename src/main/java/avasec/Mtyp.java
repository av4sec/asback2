package avasec;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="element")
public class Mtyp extends Element {

  public Mtyp(long extid, String charid, String name) {
    super(extid, charid, name);
    this.type = "Mtyp";
  }

  public Mtyp() { super(); }

  @Override
  public String toString() {
    return String.format(
      "Mtyp[id=%s, extid=%d, curr.charid='%s', curr.name='%s', curr.version=%d]",
      id, this.extid, this.curr.charid, this.curr.name, this.curr.version);
  }
}
