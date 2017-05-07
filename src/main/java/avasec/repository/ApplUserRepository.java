package avasec.repository;

import avasec.model.ApplUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by martinlinha on 07.05.17.
 */
public interface ApplUserRepository extends MongoRepository<ApplUser, String> {


  ApplUser findByEmail(String email);

}
