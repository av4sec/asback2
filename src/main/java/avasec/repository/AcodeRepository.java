package avasec.repository;

import avasec.model.Acode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AcodeRepository extends MongoRepository<Acode, String> {

  Acode findByExtid(long extid);
}
