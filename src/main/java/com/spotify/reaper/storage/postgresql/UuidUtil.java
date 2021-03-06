package com.spotify.reaper.storage.postgresql;

import java.util.UUID;

public final class UuidUtil {
  public static UUID fromSequenceId(long insertedId) {
    return new UUID(insertedId, 0L);
  }

  public static long toSequenceId(UUID id) {
    return id.getMostSignificantBits();
  }

  private UuidUtil() {}
}
