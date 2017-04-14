package avasec;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/element")
public class ElementController {

  @Autowired
  private ElementRepository elementRepository;

  @RequestMapping(value = "/{extid}", method = GET)
  public Element getElementByExtid(@PathVariable Long extid) {

    Element element = elementRepository.findByExtid(extid);
    return element;
  }

  @RequestMapping(method = GET)
  Collection<Element> allElements() {
    return elementRepository.findAll();
  }

}
