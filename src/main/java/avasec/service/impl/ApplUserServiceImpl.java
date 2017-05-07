package avasec.service.impl;

import avasec.model.ApplUser;
import avasec.repository.ApplUserRepository;
import avasec.service.ApplUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by martinlinha on 07.05.17.
 */
public class ApplUserServiceImpl implements ApplUserService {

  @Autowired
  private ApplUserRepository applUserRepository;

  @Override
  public ApplUser findByEmail(String email) {
    return applUserRepository.findByEmail(email);
  }

  @Override
  public ApplUser save(ApplUser applUser) {
    return applUserRepository.save(applUser);
  }
}
