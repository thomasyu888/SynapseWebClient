package org.sagebionetworks.web.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * The page of an interactive session (enclave) task, #!Compute:{taskId}: the task and its running session.
 */
public class ComputePlace extends Place {

  private final String taskId;

  public ComputePlace(String token) {
    this.taskId = token;
  }

  public String getTaskId() {
    return taskId;
  }

  @Prefix("Compute")
  public static class Tokenizer implements PlaceTokenizer<ComputePlace> {

    @Override
    public String getToken(ComputePlace place) {
      return place.getTaskId();
    }

    @Override
    public ComputePlace getPlace(String token) {
      return new ComputePlace(token);
    }
  }
}
