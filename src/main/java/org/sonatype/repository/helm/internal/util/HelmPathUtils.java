/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2018-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.repository.helm.internal.util;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.sonatype.goodies.common.ComponentSupport;
import org.sonatype.nexus.repository.view.Content;
import org.sonatype.nexus.repository.view.Context;
import org.sonatype.nexus.repository.view.matchers.token.TokenMatcher;
import org.sonatype.nexus.repository.view.payloads.StringPayload;
import org.sonatype.repository.helm.internal.metadata.ChartIndex;
import org.yaml.snakeyaml.Yaml;

/**
 * Utility methods for working with Helm routes and paths.
 *
 * @since 0.0.1
 *
 * Edit By Jakub Rosa © 2019 Telic AG.
 */
@Named
@Singleton
public class HelmPathUtils extends ComponentSupport {

  private final YamlParser yamlParser;

  public HelmPathUtils() {
    yamlParser = new YamlParser();
  }

  @Inject
  public HelmPathUtils(final YamlParser yamlParser) {
    this.yamlParser = checkNotNull(yamlParser);
  }

  /** Returns the filename from a {@link TokenMatcher.State}. */
  public String filename(final TokenMatcher.State state) {
    return match(state, "filename");
  }

  public String extension(final TokenMatcher.State state) {
    return match(state, "extension");
  }

  /**
   * Utility method encapsulating getting a particular token by name from a matcher, including
   * preconditions.
   */
  private String match(final TokenMatcher.State state, final String name) {
    checkNotNull(state);
    String result = state.getTokens().get(name);
    checkNotNull(result);
    return result;
  }

  /** Returns the {@link TokenMatcher.State} for the content. */
  public TokenMatcher.State matcherState(final Context context) {
    return context.getAttributes().require(TokenMatcher.State.class);
  }

  public String buildAssetPath(final TokenMatcher.State matcherState) {
    String filename = filename(matcherState);
    String extension = extension(matcherState);
    return filename + "." + extension;
  }

  public Content updateYamlUrls(Content content, String url) {
    ChartIndex index;
    try {
      index = yamlParser.loadToChartIndex(content.openInputStream());
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      return content;
    }
    log.debug("Update urls in entries");
    index
        .getEntries()
        .forEach(
            (s, chartEntries) ->
                chartEntries.forEach(
                    chartEntry ->
                        chartEntry.setUrls(
                            chartEntry.getUrls().stream()
                                .map(s1 -> url + "/" + s1)
                                .collect(Collectors.toList()))));
    final StringPayload stringPayload =
        new StringPayload(yamlParser.getString(index), content.getContentType());
    final Content newContent = new Content(stringPayload);

    content
        .getAttributes()
        .forEach(
            attributes -> newContent.getAttributes().set(attributes.getKey(), attributes.getValue()));
    return newContent;
  }
}
