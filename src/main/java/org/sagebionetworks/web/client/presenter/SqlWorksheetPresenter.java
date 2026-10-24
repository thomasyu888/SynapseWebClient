package org.sagebionetworks.web.client.presenter;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import javax.inject.Inject;
import org.sagebionetworks.web.client.SynapseJSNIUtils;
import org.sagebionetworks.web.client.jsinterop.React;
import org.sagebionetworks.web.client.jsinterop.SRC;
import org.sagebionetworks.web.client.jsinterop.SqlWorksheetProps;
import org.sagebionetworks.web.client.place.SqlWorksheetPlace;
import org.sagebionetworks.web.client.widget.ReactComponent;

public class SqlWorksheetPresenter
  extends AbstractActivity
  implements Presenter<SqlWorksheetPlace> {

  private final ReactComponent view;
  private String entityId;

  @Inject
  public SqlWorksheetPresenter(SynapseJSNIUtils jsniUtils) {
    this.view = new ReactComponent();

    jsniUtils.setPageTitle("SQL Worksheet");
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view.asWidget());
    render();
  }

  @Override
  public void setPlace(final SqlWorksheetPlace place) {
    String token = place.toToken();
    this.entityId = token == null || token.trim().isEmpty() ? null : token;
    render();
  }

  private void render() {
    view.render(
      React.createElementWithSynapseContext(
        SRC.SynapseComponents.SqlWorksheetPage,
        SqlWorksheetProps.create(entityId)
      )
    );
  }
}
