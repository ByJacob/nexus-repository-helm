package org.sonatype.repository.helm.internal.cleanup;

import static org.sonatype.nexus.repository.search.DefaultComponentMetadataProducer.IS_PRERELEASE_KEY;
import static org.sonatype.nexus.repository.search.DefaultComponentMetadataProducer.LAST_BLOB_UPDATED_KEY;
import static org.sonatype.nexus.repository.search.DefaultComponentMetadataProducer.LAST_DOWNLOADED_KEY;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.inject.Named;
import javax.inject.Singleton;
import org.sonatype.nexus.cleanup.config.CleanupPolicyConfiguration;
import org.sonatype.repository.helm.internal.HelmFormat;

@Named(HelmFormat.NAME)
@Singleton
public class HelmCleanupPolicyConfiguration implements CleanupPolicyConfiguration {

  @Override
  public Map<String, Boolean> getConfiguration() {
    return ImmutableMap.of(LAST_BLOB_UPDATED_KEY, true,
        LAST_DOWNLOADED_KEY, true,
        IS_PRERELEASE_KEY, false);
  }
}
