package org.sagebionetworks.web.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * The lineage of a table or view. The token is its id, with an optional version (e.g. syn123 or syn123.4).
 */
public class TableLineagePlace extends Place {

  private String token;

  public TableLineagePlace(String token) {
    this.token = token;
  }

  public String toToken() {
    return token;
  }

  @Prefix("Lineage")
  public static class Tokenizer implements PlaceTokenizer<TableLineagePlace> {

    @Override
    public String getToken(TableLineagePlace place) {
      return place.toToken();
    }

    @Override
    public TableLineagePlace getPlace(String token) {
      return new TableLineagePlace(token);
    }
  }
}
