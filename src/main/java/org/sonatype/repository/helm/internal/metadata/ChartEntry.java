package org.sonatype.repository.helm.internal.metadata;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

/**
 * Represents a chart entry in index.yaml
 *
 * @since 0.0.2
 */
public class ChartEntry
{
  private String description;
  private String name;
  private String version;
  private DateTime created;
  private String appVersion;
  private String digest;
  private String icon;
  private List<String> urls;
  private List<String> sources;
  private List<Map<String, String>> maintainers;
  private String engine;
  private String kubeVersion;
  private List<String> keywords;
  private String home;
  private Boolean deprecated;
  private String tillerVersion;

  public String getName() { return this.name; }

  public void setName(final String name) { this.name = name; }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(final String version) {
    this.version = version;
  }

  public DateTime getCreated() {
    return created;
  }

  public void setCreated(final DateTime created) {
    this.created = created;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(final String appVersion) {
    this.appVersion = appVersion;
  }

  public String getDigest() {
    return digest;
  }

  public void setDigest(final String digest) {
    this.digest = digest;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(final String icon) {
    this.icon = icon;
  }

  public List<String> getUrls() {
    return urls;
  }

  public void setUrls(final List<String> urls) {
    this.urls = urls;
  }

  public List<String> getSources() {
    return sources;
  }

  public void setSources(final List<String> sources) {
    this.sources = sources;
  }

  public List<Map<String, String>> getMaintainers() {
    return maintainers;
  }

  public void setMaintainers(final List<Map<String, String>> maintainers) {
    this.maintainers = maintainers;
  }

  public String getEngine() {
    return engine;
  }

  public void setEngine(String engine) {
    this.engine = engine;
  }

  public String getKubeVersion() {
    return kubeVersion;
  }

  public void setKubeVersion(String kubeVersion) {
    this.kubeVersion = kubeVersion;
  }

  public List<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<String> keywords) {
    this.keywords = keywords;
  }

  public String getHome() {
    return home;
  }

  public void setHome(String home) {
    this.home = home;
  }

  public Boolean getDeprecated() {
    return deprecated;
  }

  public void setDeprecated(Boolean deprecated) {
    this.deprecated = deprecated;
  }

  public String getTillerVersion() {
    return tillerVersion;
  }

  public void setTillerVersion(String tillerVersion) {
    this.tillerVersion = tillerVersion;
  }
}
