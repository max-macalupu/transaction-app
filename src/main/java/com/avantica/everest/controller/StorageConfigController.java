package com.avantica.everest.controller;

import com.avantica.everest.domain.OkResponse;
import com.avantica.everest.domain.StorageConfigRequest;
import com.avantica.everest.domain.StorageConfigResponse;
import com.avantica.everest.service.StorageConfigService;
import com.avantica.everest.service.StorageConfigService.StorageType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/configuration/storage")
public class StorageConfigController {

  @Autowired
  private StorageConfigService storageConfigService;

  /***
   * This method is used to return all storage type
   * and the user is be able to select one.
   * @return
   */
  @GetMapping
  public @ResponseBody
  List<StorageType> storageTypeList() {
    return storageConfigService.getStorageList();
  }

  /***
   * This method is used to update the
   * storage type.
   * @param request
   * @return
   */
  @PutMapping
  public OkResponse updateStorageType(@RequestBody StorageConfigRequest request) {
    storageConfigService.update(request.getStorageType());
    return new OkResponse("StorageType was updated successfully.");
  }

  /***
   * This method is used to get the current storage type.
   * @return
   */
  @GetMapping("/current")
  public Object getCurrentStorageType() {
    StorageType storageType = storageConfigService.getCurrentStorage();
    if (storageType != null) {
      return new StorageConfigResponse(storageType);
    }
    return new OkResponse("The storage type have not been update yet.");
  }

  /***
   * This method is used to clean the storage configuration.
   * @return
   */
  @PutMapping("/clean")
  public OkResponse cleanStorageConfiguration() {
    storageConfigService.cleanStorage();
    return new OkResponse("Storage was clean up successfully.");
  }
}
