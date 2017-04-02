package avasec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired
  private RoleRepository roleRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

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

  }
}
