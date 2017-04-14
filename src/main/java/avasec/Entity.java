package avasec;

import org.springframework.data.annotation.Id;

import java.util.List;

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

  @Id
  public String id;
  public long extid;

  public Entity() {
  }

}
