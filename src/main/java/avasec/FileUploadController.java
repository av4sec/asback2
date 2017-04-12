package avasec;

import avasec.storage.StorageFileNotFoundException;
import avasec.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Files;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

  @Autowired
  private RoleRepository roleRepository;

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
  public String handleFileUpload(@RequestParam("file") MultipartFile file) {

    storageService.store(file);

    try (InputStream stream = file.getInputStream()) {

      roleRepository.deleteAll();

      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader(). parse(reader);
      for (CSVRecord record : records) {
        long extid = Long.valueOf(record.get("extid"));
        String charid = record.get("charid");
        String name = record.get("name");
        roleRepository.save(new Role(extid, charid, name));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return "";
  }

  @ExceptionHandler(StorageFileNotFoundException.class)
  public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
    return ResponseEntity.notFound().build();
  }

}
