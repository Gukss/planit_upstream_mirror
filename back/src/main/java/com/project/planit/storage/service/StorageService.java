package com.project.planit.storage.service;

import com.project.planit.storage.dto.CreateStorageRequest;
import com.project.planit.storage.dto.UpdateStorageRequest;
import com.project.planit.storage.entity.Storage;
import java.util.List;

/**
 * packageName    : com.project.planit.storage.service fileName       : StorageService author
 *  : SSAFY date           : 2023-01-31 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-31        SSAFY       최초
 * 생성
 */
public interface StorageService {
<<<<<<< HEAD
  Storage createStorage(CreateStorageRequest request, Long id);
  Storage updateStorage(UpdateStorageRequest request,Long memberId);
=======
  void createStorage(CreateStorageRequest request,Long id);
  void updateStorage(UpdateStorageRequest request,Long memberId);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  List<Storage> findStorageList(Long storageId);
}
