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

  @Autowired
  private ElementRepository elementRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    // ROLES
    // ------------------------------------------------------------------------
    roleRepository.deleteAll();

    // save a couple of roles
    roleRepository.save(new Role(11, "role_1", "Role 1"));
    roleRepository.save(new Role(12, "role_2", "Role 2"));
    roleRepository.save(new Role(13, "role_3", "Role 3"));
    roleRepository.save(new Role(14, "role_4", "Role 4"));

    System.out.println("-------------------------------");
    for (Role role : roleRepository.findAll()) {
      System.out.println(role);
    }
    System.out.println();

    // ACCESS CODES
    // ------------------------------------------------------------------------
    acodeRepository.deleteAll();

    // save a couple of access codes
    acodeRepository.save(new Acode(101, "acode_1", "Acode 1"));
    acodeRepository.save(new Acode(102, "acode_2", "Acode 2"));
    acodeRepository.save(new Acode(103, "acode_3", "Acode 3"));
    acodeRepository.save(new Acode(104, "acode_4", "Acode 4"));

    System.out.println("-------------------------------");
    for (Acode acode : acodeRepository.findAll()) {
      System.out.println(acode);
    }
    System.out.println();

    // SECURABLE ELEMENTS
    // ------------------------------------------------------------------------
    elementRepository.deleteAll();

    // save a couple of access codes
    elementRepository.save(new Element(1001, "element_1", "Element 1"));
    elementRepository.save(new Element(1002, "element_2", "Element 2"));
    elementRepository.save(new Element(1003, "element_3", "Element 3"));
    elementRepository.save(new Element(1004, "element_4", "Element 4"));

    System.out.println("-------------------------------");
    for (Element element : elementRepository.findAll()) {
      System.out.println(element);
    }
    System.out.println();

    // ------------------------------------------------------------------------

    Role role;
    role = roleRepository.findByExtid(14);
    role.curr.acode.add(acodeRepository.findByExtid(101));
    role.curr.acode.add(acodeRepository.findByExtid(102));
    roleRepository.save(role);

    Element element;
    element = elementRepository.findByExtid(1003);
    element.curr.acode.add(acodeRepository.findByExtid(101));
    element.curr.acode.add(acodeRepository.findByExtid(103));
    elementRepository.save(element);

    element = elementRepository.findByExtid(1004);
    element.curr.acode.add(acodeRepository.findByExtid(102));
    element.curr.acode.add(acodeRepository.findByExtid(104));
    elementRepository.save(element);
  }

  @Bean
  CommandLineRunner init(StorageService storageService) {
    return (args) -> {
      storageService.deleteAll();
      storageService.init();
    };
  }
}
