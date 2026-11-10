package org.sagebionetworks.web.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Governance-as-a-Service: unified access requests, Federated DAC review and the public Data Use Register.
 * The React dashboard routes everything after the prefix, e.g. GaaS:default/register.
 */
public class GaasPlace extends Place {

  private String token;

  public GaasPlace(String token) {
    this.token = token;
  }

  public String toToken() {
    return token;
  }

  @Prefix("GaaS")
  public static class Tokenizer implements PlaceTokenizer<GaasPlace> {

    @Override
    public String getToken(GaasPlace place) {
      return place.toToken();
    }

    @Override
    public GaasPlace getPlace(String token) {
      return new GaasPlace(token);
    }
  }
}
