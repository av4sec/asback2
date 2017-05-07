package avasec.service.impl;

import avasec.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avasec.repository.ElementRepository;
import avasec.repository.AcodeRepository;
import avasec.model.Element;
import avasec.model.Acode;

@Service
public class ElementServiceImpl implements ElementService {

  @Autowired
  private ElementRepository elementRepository;

  @Autowired
  private AcodeRepository acodeRepository;

  @Override
  public void addAcodeToElement(long extid_element, long extid_acode) {
    Element element = elementRepository.findByExtid(extid_element);
    Acode acode = acodeRepository.findByExtid(extid_acode);
    element.curr.addAcode(acode.id);
    acode.curr.addElementId(element.id);
    acodeRepository.save(acode);
    elementRepository.save(element);
  }
}
