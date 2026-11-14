package org.sagebionetworks.web.server.servlet.filter;

import static org.sagebionetworks.web.server.servlet.filter.CORSFilter.ORIGIN_HEADER;
import static org.sagebionetworks.web.server.servlet.filter.CORSFilter.isAllowedSynapseSubdomain;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * X-Frame-Options header
 */
public class XFrameOptionsFilter extends OncePerRequestFilter {

  public static final String X_FRAME_OPTIONS_HEADER = "X-Frame-Options";
  public static final String DENY = "DENY";
  public static final String SAMEORIGIN = "SAMEORIGIN";
  public static final String REFERER_HEADER = "Referer";

  /**
   * Paths that pages of this same origin may frame. synapse-react-client signs
   * in to the identity broker's login service silently (OAuth prompt=none) in a
   * hidden frame, which the login service sends back to robots.txt with the
   * answer in its URL, where the page that opened the frame reads it.
   * robots.txt has nothing to click, so framing it cannot be used for
   * clickjacking.
   */
  public static final Set<String> SAME_ORIGIN_FRAMEABLE_PATHS = Set.of(
    "/robots.txt"
  );

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    String origin = request.getHeader(ORIGIN_HEADER);
    // SWC-7536: if no origin header, use referer
    if (origin == null) {
      origin = request.getHeader(REFERER_HEADER);
    }
    if (isSameOriginFrameable(request)) {
      // pages of this origin may frame it, and no other
      response.addHeader(X_FRAME_OPTIONS_HEADER, SAMEORIGIN);
    } else if (!isAllowedSynapseSubdomain(origin)) {
      // if allowed synapse subdomain, do not add X-Frame-Options header
      response.addHeader(X_FRAME_OPTIONS_HEADER, DENY);
    } else {
      // set to deprecated ALLOW-FROM value because app server will set if empty:
      // https://github.com/Sage-Bionetworks/Synapse-Stack-Builder/blob/develop/src/main/resources/templates/repo/ebextensions/security.conf#L1
      response.addHeader(X_FRAME_OPTIONS_HEADER, "ALLOW-FROM " + origin);
    }

    filterChain.doFilter(request, response);
  }

  static boolean isSameOriginFrameable(HttpServletRequest request) {
    String path = request.getRequestURI();
    if (path == null) {
      return false;
    }
    String contextPath = request.getContextPath();
    if (contextPath != null && path.startsWith(contextPath)) {
      path = path.substring(contextPath.length());
    }
    return SAME_ORIGIN_FRAMEABLE_PATHS.contains(path);
  }

  public void testFilter(
    HttpServletRequest mockRequest,
    HttpServletResponse mockResponse,
    FilterChain mockFilterChain
  ) throws ServletException, IOException {
    doFilterInternal(mockRequest, mockResponse, mockFilterChain);
  }
}
