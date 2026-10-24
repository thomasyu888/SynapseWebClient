package org.sagebionetworks.web.client.presenter;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import javax.inject.Inject;
import org.sagebionetworks.repo.model.Reference;
import org.sagebionetworks.web.client.DisplayUtils;
import org.sagebionetworks.web.client.SynapseJSNIUtils;
import org.sagebionetworks.web.client.jsinterop.React;
import org.sagebionetworks.web.client.jsinterop.SRC;
import org.sagebionetworks.web.client.jsinterop.TableLineageGraphProps;
import org.sagebionetworks.web.client.place.TableLineagePlace;
import org.sagebionetworks.web.client.widget.ReactComponent;

public class TableLineagePresenter
  extends AbstractActivity
  implements Presenter<TableLineagePlace> {

  private final ReactComponent view;
  private final SynapseJSNIUtils jsniUtils;
  private Reference reference;

  @Inject
  public TableLineagePresenter(SynapseJSNIUtils jsniUtils) {
    this.view = new ReactComponent();
    this.jsniUtils = jsniUtils;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view.asWidget());
    render();
  }

  @Override
  public void setPlace(final TableLineagePlace place) {
    this.reference = DisplayUtils.parseEntityVersionString(place.toToken());
    jsniUtils.setPageTitle("Lineage of " + reference.getTargetId());
    render();
  }

  private void render() {
    if (reference == null) {
      return;
    }
    view.render(
      React.createElementWithSynapseContext(
        SRC.SynapseComponents.TableLineagePage,
        TableLineageGraphProps.create(
          reference.getTargetId(),
          reference.getTargetVersionNumber()
        )
      )
    );
  }
}
