package avasec.repository;

import avasec.model.Element;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ElementRepository extends MongoRepository<Element, String> {

  Element findByExtid(long extid);

  Long deleteByType(String type);
}
