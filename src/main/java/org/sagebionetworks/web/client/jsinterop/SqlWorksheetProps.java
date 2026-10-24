package org.sagebionetworks.web.client.jsinterop;

import jsinterop.annotations.JsNullable;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class SqlWorksheetProps extends ReactComponentProps {

  @JsNullable
  public String entityId;

  @JsOverlay
  public static SqlWorksheetProps create(String entityId) {
    SqlWorksheetProps props = new SqlWorksheetProps();
    props.entityId = entityId;
    return props;
  }
}
