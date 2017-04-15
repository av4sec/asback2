package avasec;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="element")
public class Task extends Element {

  public Task(long extid, String charid, String name) {
    super(extid, charid, name);
    this.type = "Task";
  }

  public Task() { super(); }

  @Override
  public String toString() {
    return String.format(
      "Task[id=%s, extid=%d, curr.charid='%s', curr.name='%s', curr.version=%d]",
      id, this.extid, this.curr.charid, this.curr.name, this.curr.version);
  }
}
