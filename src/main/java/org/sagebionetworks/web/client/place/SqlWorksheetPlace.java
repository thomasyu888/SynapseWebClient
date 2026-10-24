package org.sagebionetworks.web.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * A SQL worksheet. The token, when not empty, is the id of a table or view to start from (e.g. syn123).
 */
public class SqlWorksheetPlace extends Place {

  private String token;

  public SqlWorksheetPlace(String token) {
    this.token = token;
  }

  public String toToken() {
    return token;
  }

  @Prefix("SqlWorksheet")
  public static class Tokenizer implements PlaceTokenizer<SqlWorksheetPlace> {

    @Override
    public String getToken(SqlWorksheetPlace place) {
      return place.toToken();
    }

    @Override
    public SqlWorksheetPlace getPlace(String token) {
      return new SqlWorksheetPlace(token);
    }
  }
}
