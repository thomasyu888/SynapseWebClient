package org.sagebionetworks.web.client.presenter;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import javax.inject.Inject;
import org.sagebionetworks.web.client.SynapseJSNIUtils;
import org.sagebionetworks.web.client.jsinterop.ComputeSessionPageProps;
import org.sagebionetworks.web.client.jsinterop.React;
import org.sagebionetworks.web.client.jsinterop.SRC;
import org.sagebionetworks.web.client.place.ComputePlace;
import org.sagebionetworks.web.client.widget.ReactComponent;

public class ComputePresenter
  extends AbstractActivity
  implements Presenter<ComputePlace> {

  private final ReactComponent view;
  private ComputePlace place;

  @Inject
  public ComputePresenter(SynapseJSNIUtils jsniUtils) {
    this.view = new ReactComponent();

    jsniUtils.setPageTitle("Compute");
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view.asWidget());
    render();
  }

  @Override
  public void setPlace(final ComputePlace place) {
    this.place = place;
    render();
  }

  private void render() {
    if (place == null) {
      return;
    }
    view.render(
      React.createElementWithSynapseContext(
        SRC.SynapseComponents.ComputeSessionPage,
        ComputeSessionPageProps.create(Double.parseDouble(place.getTaskId()))
      )
    );
  }
}
