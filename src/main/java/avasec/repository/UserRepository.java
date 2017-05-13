package avasec.repository;

import avasec.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  User findByExtid(long extid);
}
