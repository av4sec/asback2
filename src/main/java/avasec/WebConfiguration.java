package avasec;

import avasec.service.auth.SpringJwtMvcInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by martinlinha on 07.05.17.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

  //@Value("${bloggito.jwt.secretkey}")
  private String secretkey;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new SpringJwtMvcInterceptor(secretkey));
  }
}
