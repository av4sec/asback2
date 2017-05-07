package avasec.service;

import avasec.model.Element;

public interface ElementService {

  void addAcodeToElement(long extid_element, long extid_acode);
  void recalc(Element element);
}
