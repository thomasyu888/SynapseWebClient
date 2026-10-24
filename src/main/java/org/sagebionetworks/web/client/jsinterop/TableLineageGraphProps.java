package org.sagebionetworks.web.client.jsinterop;

import jsinterop.annotations.JsNullable;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class TableLineageGraphProps extends ReactComponentProps {

  public String entityId;

  @JsNullable
  public Double versionNumber;

  @JsOverlay
  public static TableLineageGraphProps create(
    String entityId,
    Long versionNumber
  ) {
    TableLineageGraphProps props = new TableLineageGraphProps();
    props.entityId = entityId;
    if (versionNumber != null) {
      props.versionNumber = versionNumber.doubleValue();
    }
    return props;
  }
}
