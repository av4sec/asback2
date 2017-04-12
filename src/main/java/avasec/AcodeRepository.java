package avasec;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AcodeRepository extends MongoRepository<Acode, String> {

  public Acode findByExtid(long extid);
}
