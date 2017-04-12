package avasec;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/acode")
public class AcodeController {

  @Autowired
  private AcodeRepository acodeRepository;

  @RequestMapping(value = "/{extid}", method = GET)
  public Acode getAcodeByExtid(@PathVariable Long extid) {

    Acode acode = acodeRepository.findByExtid(extid);
    return acode;
  }

  @RequestMapping(method = GET)
  Collection<Acode> allAcodes() {
    return acodeRepository.findAll();
  }

}
