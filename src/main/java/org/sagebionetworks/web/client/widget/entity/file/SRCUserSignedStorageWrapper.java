package org.sagebionetworks.web.client.widget.entity.file;

import elemental2.promise.Promise;

/**
 * Abstraction for SRC.UserSignedStorage, which downloads files in user-signed S3 storage with credentials from the
 * identity broker.
 */
public interface SRCUserSignedStorageWrapper {
  /**
   * @return a Promise that resolves to false if the FileEntity's file is not in user-signed storage
   */
  Promise<Boolean> downloadUserSignedFile(
    String fileEntityId,
    String fileHandleId,
    String accessToken
  );
}
