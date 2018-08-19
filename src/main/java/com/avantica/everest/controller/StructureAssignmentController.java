package com.avantica.everest.controller;

import com.avantica.everest.domain.OkResponse;
import com.avantica.everest.domain.StructureAssignmentRequest;
import com.avantica.everest.service.StructureAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/***
 * This controller class is used to assign
 * the DataStructureType with the TransactionType.
 */
@RestController
public class StructureAssignmentController {

  private static final Logger logger =
      LoggerFactory.getLogger(StructureAssignmentController.class);

  @Autowired
  private StructureAssignmentService structureAssignmentService;

  /***
   * This method is used to create new StructureAsignment,
   * new Transaction type is assignment to one DataStructureType.
   * @param request
   * @return
   */
  public @ResponseBody OkResponse create(StructureAssignmentRequest request) {
    logger.debug("New StructureAssignment, TransactionType: {}, DataStructureType: {}.",
        request.getTransactionType(), request.getStructureType());

    structureAssignmentService.create(request.getTransactionType(), request.getStructureType());
    return new OkResponse("Structure assignment was created successfully.");
  }
}