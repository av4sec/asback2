package avasec.service;

import avasec.model.ApplUser;

/**
 * Created by martinlinha on 07.05.17.
 */
public interface ApplUserService {

  ApplUser findByEmail(String email);

  ApplUser save(ApplUser applUser);

}
