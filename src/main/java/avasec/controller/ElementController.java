package avasec.controller;

import java.util.Collection;

import avasec.model.Element;
import avasec.repository.ElementRepository;
import avasec.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/element")
public class ElementController {

  @Autowired
  private ElementService elementService;

  @Autowired
  private ElementRepository elementRepository;

  @GetMapping(value = "/{extid}")
  public Element getElementByExtid(@PathVariable Long extid) {

    Element element = elementRepository.findByExtid(extid);
    return element;
  }

  @GetMapping()
  Collection<Element> allElements() {
    return elementRepository.findAll();
  }

  @RequestMapping(value = "/recalc", method = GET)
  public void recalc() {

    for (Element element : elementRepository.findAll()) {
      elementService.recalc(element);
    }
  }
}
