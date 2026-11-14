package org.sagebionetworks.web.client.widget.entity.file;

import com.google.gwt.user.client.ui.IsWidget;
import org.sagebionetworks.web.client.jsinterop.ReactMouseEvent;
import org.sagebionetworks.web.client.widget.entity.menu.v3.EntityActionMenu;

public interface FileDownloadMenuItemView extends IsWidget {
  void setPresenter(Presenter presenter);

  void setActionMenu(EntityActionMenu actionMenu);

  void clear();

  void setIsDirectDownloadLink(String href);

  void setIsSFTPDownload();

  void setIsUnauthenticatedS3DirectDownload();

  void showLoginS3DirectDownloadDialog(String endpoint);

  void showS3DirectDownloadDialog();

  /**
   * Presenter interface
   */
  public interface Presenter extends S3DirectLoginDialog.Presenter {
    void onUnauthenticatedS3DirectDownloadClicked();

    /**
     * @param event the click on the direct download link; the presenter may prevent its default (following the href)
     */
    void onDirectDownloadClicked(ReactMouseEvent event);

    void onSFTPDownloadErrorClicked();
  }
}
