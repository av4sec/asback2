package avasec.controller;

import avasec.model.*;
import avasec.repository.AcodeRepository;
import avasec.repository.ElementRepository;
import avasec.repository.RoleRepository;
import avasec.storage.StorageFileNotFoundException;
import avasec.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.*;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private AcodeRepository acodeRepository;

  @Autowired
  private ElementRepository elementRepository;

  private final StorageService storageService;

  @Autowired
  public FileUploadController(StorageService storageService) {
    this.storageService = storageService;
  }

  @RequestMapping(method = GET)
  public String listUploadedFiles() throws IOException {

    return storageService
      .loadAll()
      .map(path ->
        MvcUriComponentsBuilder
          .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
          .build().toString())
      .collect(Collectors.toList())
      .toString();
 }

  @GetMapping("/file/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

    Resource file = storageService.loadAsResource(filename);
    return ResponseEntity
      .ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
      .body(file);
  }

  @PostMapping("/file/role")
  public String handleRoleUpload(@RequestParam("file") MultipartFile file) {

    storageService.store(file);

    try (InputStream stream = file.getInputStream()) {

      roleRepository.deleteAll();

      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader(). parse(reader);
      for (CSVRecord record : records) {
        long extid = Long.valueOf(record.get("extid"));
        String charid = record.get("charid");
        String name = record.get("name");
        String acode = record.get("acode");
        Role role = new Role(extid, charid, name);
        for (String acode_extid: acode.split("\\|")) {
          try {
            role.curr.addAcode(acodeRepository.findByExtid(Long.valueOf(acode_extid)));
          } catch (NumberFormatException e) {
            System.out.println("role error: " + record.get("extid"));
          }
        }
        roleRepository.save(role);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return "";
  }

  @PostMapping("/file/acode")
  public String handleAcodeUpload(@RequestParam("file") MultipartFile file) {

    storageService.store(file);

    try (InputStream stream = file.getInputStream()) {

      acodeRepository.deleteAll();

      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader(). parse(reader);
      for (CSVRecord record : records) {
        long extid = Long.valueOf(record.get("extid"));
        String charid = record.get("charid");
        String name = record.get("name");
        acodeRepository.save(new Acode(extid, charid, name));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return "";
  }

  public void addAcode(Element element, String acode) {
    for (String acode_extid: acode.split("\\|")) {
      try {
        element.curr.addAcode(acodeRepository.findByExtid(Long.valueOf(acode_extid)));
      } catch (NumberFormatException e) {
        System.out.println("element error: " + element.extid);
      }
    }
  }

  @PostMapping("/file/element/appl")
  public String handleApplUpload(@RequestParam("file") MultipartFile file) {
    handleElementUpload(file, "Appl", (record -> {
      try {
        long extid = Long.valueOf(record.get("extid"));
        String charid = record.get("charid");
        String name = record.get("name");
        String acode = record.get("acode");
        Appl appl = new Appl(extid, charid, name);
        addAcode(appl, acode);
        elementRepository.save(appl);
      } catch (Exception e) {
        System.out.println("appl error: " + e.toString());
      }
    }));
    return "";
  }

  @PostMapping("/file/element/ctx")
  public String handleCtxUpload(@RequestParam("file") MultipartFile file) {
    handleElementUpload(file, "Ctx", (record -> {
      try {
        long extid = Long.valueOf(record.get("extid"));
        String charid = record.get("charid");
        String name = record.get("name");
        String acode = record.get("acode");
        Ctx ctx = new Ctx(extid, charid, name);
        addAcode(ctx, acode);
        elementRepository.save(ctx);
      } catch (Exception e) {
        System.out.println("ctx error: " + e.toString());
      }
    }));
    return "";
  }

  @PostMapping("/file/element/mtyp")
  public String handleMtypUpload(@RequestParam("file") MultipartFile file) {
    handleElementUpload(file, "Mtyp", (record -> {
      try {
        long extid = Long.valueOf(record.get("extid"));
        String charid = record.get("charid");
        String name = record.get("name");
        String acode = record.get("acode");
        Mtyp mtyp = new Mtyp(extid, charid, name);
        addAcode(mtyp, acode);
        elementRepository.save(mtyp);
      } catch (Exception e) {
        System.out.println("mtyp error: " + e.toString());
      }
    }));
    return "";
  }

  @PostMapping("/file/element/task")
  public String handleTaskUpload(@RequestParam("file") MultipartFile file) {
    handleElementUpload(file, "Task", (record -> {
      try {
        long extid = Long.valueOf(record.get("extid"));
        String charid = record.get("charid");
        String name = record.get("name");
        String acode = record.get("acode");
        Task task = new Task(extid, charid, name);
        addAcode(task, acode);
        elementRepository.save(task);
      } catch (Exception e) {
        System.out.println("task error: " + e.toString());
      }
    }));
    return "";
  }

  @PostMapping("/file/element/wfc")
  public String handleWfcUpload(@RequestParam("file") MultipartFile file) {
    handleElementUpload(file, "Wfc", (record -> {
      try {
        long extid = Long.valueOf(record.get("extid"));
        String charid = record.get("charid");
        String name = record.get("name");
        String acode = record.get("acode");
        Wfc wfc = new Wfc(extid, charid, name);
        addAcode(wfc, acode);
        elementRepository.save(wfc);
      } catch (Exception e) {
        System.out.println("wfc error: " + e.toString());
      }
    }));
    return "";
  }

  public interface RecordHandler {
    public void process(CSVRecord record);
  }

  public void handleElementUpload(MultipartFile file, String type, RecordHandler handler) {
    storageService.store(file);

    try (InputStream stream = file.getInputStream()) {

      elementRepository.deleteByType(type);

      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader(). parse(reader);
      for (CSVRecord record : records) {
        handler.process(record);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @ExceptionHandler(StorageFileNotFoundException.class)
  public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
    return ResponseEntity.notFound().build();
  }

}