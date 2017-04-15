package avasec;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="element")
public class Wfc extends Element {

  public Wfc(long extid, String charid, String name) {
    super(extid, charid, name);
    this.type = "Wfc";
  }

  public Wfc() { super(); }

  @Override
  public String toString() {
    return String.format(
      "Wfc[id=%s, extid=%d, curr.charid='%s', curr.name='%s', curr.version=%d]",
      id, this.extid, this.curr.charid, this.curr.name, this.curr.version);
  }
}
