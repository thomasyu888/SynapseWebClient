package org.sagebionetworks.web.client.presenter;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import javax.inject.Inject;
import org.sagebionetworks.web.client.SynapseJSNIUtils;
import org.sagebionetworks.web.client.jsinterop.React;
import org.sagebionetworks.web.client.jsinterop.SRC;
import org.sagebionetworks.web.client.place.GaasPlace;
import org.sagebionetworks.web.client.widget.ReactComponent;

/**
 * Mounts the React GaaS dashboard. It does not require sign-in, because the Data Use Register is public.
 */
public class GaasPresenter
  extends AbstractActivity
  implements Presenter<GaasPlace> {

  private final ReactComponent view;

  @Inject
  public GaasPresenter(SynapseJSNIUtils jsniUtils) {
    this.view = new ReactComponent();

    jsniUtils.setPageTitle("Data Governance");
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view.asWidget());
    render();
  }

  @Override
  public void setPlace(final GaasPlace place) {
    render();
  }

  private void render() {
    view.render(
      React.createElementWithSynapseContext(SRC.SynapseComponents.GaasDashboard)
    );
  }
}
