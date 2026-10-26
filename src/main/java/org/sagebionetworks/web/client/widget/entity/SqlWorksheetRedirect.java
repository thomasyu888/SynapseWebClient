package org.sagebionetworks.web.client.widget.entity;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.Collections;
import org.sagebionetworks.repo.model.EntityHeader;
import org.sagebionetworks.web.client.PlaceChanger;
import org.sagebionetworks.web.client.SynapseJavascriptClient;
import org.sagebionetworks.web.client.place.SqlWorksheetPlace;
import org.sagebionetworks.web.client.utils.Callback;

/**
 * This client is built with entity classes that do not yet include the SQL worksheet, so a worksheet cannot be read
 * as an entity here: wherever loading an entity fails, a worksheet opens in the SQL worksheet page instead. Only a
 * failed load pays for the extra lookup.
 */
public class SqlWorksheetRedirect {

  public static final String SQL_WORKSHEET_CONCRETE_TYPE =
    "org.sagebionetworks.repo.model.table.SqlWorksheet";

  /**
   * After loading the given entity failed, open it in the SQL worksheet page if it is a worksheet.
   *
   * @param notAWorksheet Called when the entity is not a worksheet, or its type cannot be looked up
   */
  public static void openIfSqlWorksheet(
    SynapseJavascriptClient jsClient,
    final PlaceChanger placeChanger,
    final String entityId,
    final Callback notAWorksheet
  ) {
    jsClient.getEntityHeaderBatch(
      Collections.singletonList(entityId),
      new AsyncCallback<ArrayList<EntityHeader>>() {
        @Override
        public void onSuccess(ArrayList<EntityHeader> headers) {
          if (
            !headers.isEmpty() &&
            SQL_WORKSHEET_CONCRETE_TYPE.equals(headers.get(0).getType())
          ) {
            placeChanger.goTo(new SqlWorksheetPlace(entityId));
          } else {
            notAWorksheet.invoke();
          }
        }

        @Override
        public void onFailure(Throwable headerError) {
          notAWorksheet.invoke();
        }
      }
    );
  }
}
