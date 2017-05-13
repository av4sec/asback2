package avasec;

import avasec.model.*;
import avasec.repository.*;
import avasec.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import avasec.storage.StorageProperties;
import avasec.storage.StorageService;

import java.util.List;
import java.util.stream.Collectors;



@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleService roleService;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private AcodeRepository acodeRepository;

  @Autowired
  private ElementService elementService;

  @Autowired
  private ElementRepository elementRepository;

  @Autowired
  private ApplUserService applUserService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    // ROLES
    // ------------------------------------------------------------------------
    roleRepository.deleteAll();

    // save a couple of roles
    roleService.createRole(11, "role_1", "Role 1");
    roleService.createRole(12, "role_2", "Role 2");
    roleService.createRole(13, "role_3", "Role 3");
    roleService.createRole(14, "role_4", "Role 4");

    System.out.println("-------------------------------");
    for (Role role : roleRepository.findAll()) {
      System.out.println(role);
    }

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

    roleService.addAcodeToRole(14, 101);
    roleService.addAcodeToRole(14, 102);
    roleService.addAcodeToRole(14, 103);

    roleService.addParentToRole(14, 11);
    roleService.addParentToRole(12, 11);
    roleService.addParentToRole(13, 12);
    roleService.addParentToRole(13, 14);

    /*
    List<Role> role_lst =  roleService.collectRoles(roleRepository.findByExtid(13));
    List<Long> role_lst_id = role_lst.stream().distinct().map(r -> r.extid).collect(Collectors.toList());
    System.out.println(role_lst_id);
    */

    /*
    Wfc wfc;
    wfc = (Wfc)elementRepository.findByExtid(1003);
    wfc.curr.addAcode(acodeRepository.findByExtid(101));
    wfc.curr.addAcode(acodeRepository.findByExtid(103));
    elementRepository.save(wfc);

    wfc = (Wfc)elementRepository.findByExtid(1004);
    wfc.curr.addAcode(acodeRepository.findByExtid(102));
    wfc.curr.addAcode(acodeRepository.findByExtid(103));
    wfc.curr.addAcode(acodeRepository.findByExtid(104));
    wfc.curr.addAcode(acodeRepository.findByExtid(104));
    elementRepository.save(wfc);
    */

    elementService.addAcodeToElement(1003, 101);
    elementService.addAcodeToElement(1003, 103);
    elementService.addAcodeToElement(1004, 102);
    elementService.addAcodeToElement(1004, 103);

    // ------------------------------------------------------------------------

    ApplUser applUser = new ApplUser();
    applUser.setEmail("admin@admin.com");
    // admin1234
    applUser.setPassword("$2y$10$k2oEI.8QKb/gZs3BrARHOueODOMLx1k8TL4MpmB2oskh8N.AhNoye");
    applUserService.save(applUser);


    // USER
    // ------------------------------------------------------------------------
    userRepository.deleteAll();

    // save a couple of users
    userService.create(1, "User 1");
    userService.create(2, "User 2");

    userService.addAcode(1, 101);
    userService.addAcode(1, 104);
    userService.addAcode(2, 103);

    userService.addRole(1, 11);
    userService.addRole(2, 12);
    userService.addRole(2, 14);

    System.out.println("-------------------------------");
    for (User user : userRepository.findAll()) {
      System.out.println(user);
    }
    System.out.println();

    // ------------------------------------------------------------------------

    for (User user : userRepository.findAll()) {
      userService.recalc(user);
    }

    for (Role role : roleRepository.findAll()) {
      roleService.recalc(role);
    }

    for (Element element : elementRepository.findAll()) {
      elementService.recalc(element);
    }

  }

  @Bean
  CommandLineRunner init(StorageService storageService) {
    return (args) -> {
      storageService.deleteAll();
      storageService.init();
    };
  }
}
