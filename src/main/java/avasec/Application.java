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

    // save a couple of securable elements
    elementRepository.save(new Wfc(1001, "wfc_1", "Wfc 1"));
    elementRepository.save(new Wfc(1002, "wfc_2", "Wfc 2"));
    elementRepository.save(new Wfc(1003, "wfc_3", "Wfc 3"));
    elementRepository.save(new Wfc(1004, "wfc_4", "Wfc 4"));
    elementRepository.save(new Task(1005, "task_1", "Task 1"));
    elementRepository.save(new Task(1006, "task_2", "Task 2"));
    elementRepository.save(new Task(1007, "task_3", "Task 3"));
    elementRepository.save(new Ctx(1008, "ctx_1", "Ctx 1"));
    elementRepository.save(new Ctx(1009, "ctx_2", "Ctx 2"));
    elementRepository.save(new Ctx(1010, "ctx_3", "Ctx 3"));
    elementRepository.save(new Ctx(1011, "ctx_4", "Ctx 4"));
    elementRepository.save(new Appl(1012, "appl_1", "Appl 1"));
    elementRepository.save(new Appl(1013, "appl_2", "Appl 2"));
    elementRepository.save(new Mtyp(1014, "mtyp_2", "Mtyp 2"));
    elementRepository.save(new Mtyp(1015, "mtyp_3", "Mtyp 3"));
    elementRepository.save(new Mtyp(1016, "mtyp_4", "Mtyp 4"));

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

    Wfc wfc;
    wfc = (Wfc)elementRepository.findByExtid(1003);
    wfc.curr.acode.add(acodeRepository.findByExtid(101));
    wfc.curr.acode.add(acodeRepository.findByExtid(103));
    elementRepository.save(wfc);

    wfc = (Wfc)elementRepository.findByExtid(1004);
    wfc.curr.acode.add(acodeRepository.findByExtid(102));
    wfc.curr.acode.add(acodeRepository.findByExtid(104));
    elementRepository.save(wfc);
  }

  @Bean
  CommandLineRunner init(StorageService storageService) {
    return (args) -> {
      storageService.deleteAll();
      storageService.init();
    };
  }
}
