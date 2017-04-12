package avasec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import avasec.storage.StorageProperties;
import avasec.storage.StorageService;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application implements CommandLineRunner {

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private AcodeRepository acodeRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    // ROLES
    // ------------------------------------------------------------------------
    roleRepository.deleteAll();

    // save a couple of roles
    roleRepository.save(new Role(10001, "role_1", "Role 1"));
    roleRepository.save(new Role(10002, "role_2", "Role 2"));
    roleRepository.save(new Role(10003, "role_3", "Role 3"));
    roleRepository.save(new Role(10004, "role_4", "Role 4"));

    System.out.println("-------------------------------");
    for (Role role : roleRepository.findAll()) {
      System.out.println(role);
    }
    System.out.println();

    // ACCESS CODES
    // ------------------------------------------------------------------------
    acodeRepository.deleteAll();

    // save a couple of access codes
    acodeRepository.save(new Acode(10001, "acode_1", "Acode 1"));
    acodeRepository.save(new Acode(10002, "acode_2", "Acode 2"));
    acodeRepository.save(new Acode(10003, "acode_3", "Acode 3"));
    acodeRepository.save(new Acode(10004, "acode_4", "Acode 4"));

    System.out.println("-------------------------------");
    for (Acode acode : acodeRepository.findAll()) {
      System.out.println(acode);
    }
    System.out.println();

    // ------------------------------------------------------------------------
  }

  @Bean
  CommandLineRunner init(StorageService storageService) {
    return (args) -> {
      storageService.deleteAll();
      storageService.init();
    };
  }
}
