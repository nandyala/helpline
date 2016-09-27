package com.ashakiran.service;

import java.util.List;

import com.ashakiran.model.LabelValue;


/**
 * Business Service Interface to talk to persistence layer and retrieve values for drop-down choice lists.
 *
 * @author   <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version  $Revision$, $Date$
 */
public interface LookupManager {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * Retrieves all possible roles from persistence layer.
   *
   * @return  List of LabelValue objects
   */
  List<LabelValue> getAllRoles();
}
