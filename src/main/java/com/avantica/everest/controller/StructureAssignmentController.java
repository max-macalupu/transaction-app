package com.avantica.everest.controller;

import com.avantica.everest.domain.OkResponse;
import com.avantica.everest.domain.StructureAssignmentRequest;
import com.avantica.everest.domain.StructureAssignmentResponse;
import com.avantica.everest.service.StructureAssignmentService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/***
 * This controller class is used to assign
 * the DataStructureType with the TransactionType.
 */
@RestController
@RequestMapping("/v1/structure/assignment")
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
  @PostMapping
  public @ResponseBody OkResponse create(@RequestBody StructureAssignmentRequest request) {
    logger.debug("New StructureAssignment, TransactionType: {}, DataStructureType: {}.",
        request.getTransactionType(), request.getStructureType());

    structureAssignmentService.create(request.getTransactionType(), request.getStructureType());
    return new OkResponse("Structure assignment was created successfully.");
  }

  /***
   * This class is used to update the structure assignment
   * with one data structure type.
   * @param request
   * @return
   */
  @PutMapping
  public @ResponseBody OkResponse update(@RequestBody StructureAssignmentRequest request) {
    structureAssignmentService.update(request.getTransactionType(),
        request.getStructureType());

    return new OkResponse("Structure assignment was updated successfully.");
  }

  /***
   * This class is used to delete the structure assignment.
   * @param request
   * @return
   */
  @DeleteMapping
  public @ResponseBody OkResponse delete(@RequestBody StructureAssignmentRequest request) {
    structureAssignmentService.delete(request.getTransactionType());
    return new OkResponse("Structure assignment was deleted successfully.");
  }

  /***
   * This class is used to get all structure assignment.
   * @return
   */
  @GetMapping
  public @ResponseBody Object getAll() {
    List<StructureAssignmentResponse> list =
        structureAssignmentService.list();
    if (CollectionUtils.isEmpty(list)) {
      return new OkResponse("Structure assignment is empty.");
    }
    return list;
  }
}