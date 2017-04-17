package avasec;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ElementRepository extends MongoRepository<Element, String> {

  public Element findByExtid(long extid);

  Long deleteByType(String type);
}
