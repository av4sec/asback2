package avasec.model;

import org.springframework.data.annotation.Id;

public class Entity {

  public class Payload {

    public String charid;
    public String name;
    public int version;

    public Payload(String charid, String name, int version) {
      this.charid = charid;
      this.name = name;
      this.version = version;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Entity entity = (Entity) o;

    return id != null ? id.equals(entity.id) : entity.id == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (int) (extid ^ (extid >>> 32));
    return result;
  }

  @Id
  public String id;
  public long extid;

  public Entity() {
  }

}
