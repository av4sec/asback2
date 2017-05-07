package avasec.controller;

import avasec.model.ApplUser;
import avasec.service.ApplUserService;
import avasec.service.auth.JwtSecured;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by martinlinha on 07.05.17.
 */
@RestController
@RequestMapping("/user")
public class LoginController {

  @Autowired
  private ApplUserService applUserService;

  @Value("${avasec.jwt.secretkey}")
  private String secretkey;

  @Value("${avasec.jwt.sessionTtl}")
  private Long sessionTtl;

  @PostMapping
  public ResponseEntity<LoginResponse> login(@RequestBody ApplUser userDetail) {
    ApplUser fromDb = applUserService.findByEmail(userDetail.getEmail());
    if (fromDb != null
      && userDetail.getPassword() != null
      && userDetail.getEmail() != null
      && BCrypt.checkpw(userDetail.getPassword(), fromDb.getPassword())) {

      ZonedDateTime createdDate = ZonedDateTime.now();

      String jwtToken = Jwts.builder()
        .setSubject(userDetail.getEmail())
        .signWith(SignatureAlgorithm.HS512, secretkey)
        .setIssuedAt(Date.from(createdDate.toInstant()))
        .setExpiration(Date.from(createdDate.plusMinutes(sessionTtl).toInstant()))
        .compact();
      return new ResponseEntity<>(new LoginResponse(jwtToken), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  }

  /**
   * If the user is not logged in, method returns 401
   */
  @JwtSecured
  @GetMapping("/logged")
  public HttpStatus isUserAuthenticated() {
    return HttpStatus.OK;
  }

  private static class LoginResponse {
    public String token;

    public LoginResponse(final String token) {
      this.token = token;
    }
  }
}

