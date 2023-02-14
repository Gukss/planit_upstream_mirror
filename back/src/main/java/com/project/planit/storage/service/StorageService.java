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
  Storage createStorage(CreateStorageRequest request, Long id);
  Storage updateStorage(UpdateStorageRequest request,Long memberId);
  List<Storage> findStorageList(Long storageId, Long reqestMemberId);
}
