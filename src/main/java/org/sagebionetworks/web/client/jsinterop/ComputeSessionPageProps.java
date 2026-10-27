package org.sagebionetworks.web.client.jsinterop;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class ComputeSessionPageProps extends ReactComponentProps {

  double taskId;

  @JsOverlay
  public static ComputeSessionPageProps create(double taskId) {
    ComputeSessionPageProps props = new ComputeSessionPageProps();
    props.taskId = taskId;
    return props;
  }
}
