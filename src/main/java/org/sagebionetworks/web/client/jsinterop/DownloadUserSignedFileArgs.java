package org.sagebionetworks.web.client.jsinterop;

import jsinterop.annotations.JsNullable;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.sagebionetworks.repo.model.file.FileHandleAssociateType;

/**
 * Arguments of SRC.UserSignedStorage.downloadUserSignedFile.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class DownloadUserSignedFileArgs {

  public FileHandleAssociationJsObject fileHandleAssociation;

  @JsNullable
  public String accessToken;

  @JsOverlay
  public static DownloadUserSignedFileArgs create(
    String fileEntityId,
    String fileHandleId,
    String accessToken
  ) {
    FileHandleAssociationJsObject fha = new FileHandleAssociationJsObject();
    fha.associateObjectId = fileEntityId;
    fha.associateObjectType = FileHandleAssociateType.FileEntity.name();
    fha.fileHandleId = fileHandleId;
    DownloadUserSignedFileArgs args = new DownloadUserSignedFileArgs();
    args.fileHandleAssociation = fha;
    args.accessToken = accessToken;
    return args;
  }

  @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
  public static class FileHandleAssociationJsObject {

    public String associateObjectId;
    public String associateObjectType;
    public String fileHandleId;
  }
}
