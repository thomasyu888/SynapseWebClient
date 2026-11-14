package org.sagebionetworks.web.client.widget.entity.file;

import elemental2.promise.Promise;
import javax.inject.Inject;
import org.sagebionetworks.web.client.jsinterop.DownloadUserSignedFileArgs;
import org.sagebionetworks.web.client.jsinterop.SRC;

public class SRCUserSignedStorageWrapperImpl
  implements SRCUserSignedStorageWrapper {

  @Inject
  public SRCUserSignedStorageWrapperImpl() {}

  @Override
  public Promise<Boolean> downloadUserSignedFile(
    String fileEntityId,
    String fileHandleId,
    String accessToken
  ) {
    return SRC.UserSignedStorage.downloadUserSignedFile(
      DownloadUserSignedFileArgs.create(fileEntityId, fileHandleId, accessToken)
    );
  }
}
