/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spotify.reaper;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.cassandra.repair.RepairParallelism;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import systems.composable.dropwizard.cassandra.CassandraFactory;

public class ReaperApplicationConfiguration extends Configuration {

  @JsonProperty
  @NotNull
  private Integer segmentCount;

  @JsonProperty
  @NotNull
  private RepairParallelism repairParallelism;

  @JsonProperty
  @NotNull
  @DecimalMin(value = "0", inclusive = false)
  @Max(1)
  private Double repairIntensity;

  @JsonProperty
  @NotNull
  @DefaultValue("false")
  private Boolean incrementalRepair;

  @DefaultValue("7")
  private Integer scheduleDaysBetween;

  @JsonProperty
  @DefaultValue("false")
  private boolean useAddressTranslator;

  @JsonProperty
  @NotNull
  private Integer repairRunThreadCount;

  @JsonProperty
  @NotNull
  private Integer hangingRepairTimeoutMins;

  @NotEmpty
  private String storageType;

  private String enableCrossOrigin;


  @JsonProperty
  private DataSourceFactory database = new DataSourceFactory();

  @JsonProperty
  private Map<String, Integer> jmxPorts;

  @JsonProperty
  private JmxCredentials jmxAuth;

  @JsonProperty
  @DefaultValue("false")
  private Boolean allowUnreachableNodes;

  @JsonProperty
  private AutoSchedulingConfiguration autoScheduling;

  @JsonProperty
  @DefaultValue("true")
  private Boolean enableDynamicSeedList;

  @JsonProperty
  private Integer repairManagerSchedulingIntervalSeconds;

  public int getSegmentCount() {
    return segmentCount;
  }

  public void setSegmentCount(int segmentCount) {
    this.segmentCount = segmentCount;
  }

  public RepairParallelism getRepairParallelism() {
    return repairParallelism;
  }

  public void setRepairParallelism(RepairParallelism repairParallelism) {
    this.repairParallelism = repairParallelism;
  }

  public double getRepairIntensity() {
    return repairIntensity;
  }

  public void setRepairIntensity(double repairIntensity) {
    this.repairIntensity = repairIntensity;
  }

  public Boolean getIncrementalRepair() {
	    return incrementalRepair;
  }

  public void setIncrementalRepair(Boolean incrementalRepair) {
	    this.incrementalRepair = incrementalRepair;
  }

  public Integer getScheduleDaysBetween() {
    return scheduleDaysBetween;
  }

  public void setScheduleDaysBetween(int scheduleDaysBetween) {
    this.scheduleDaysBetween = scheduleDaysBetween;
  }

  public int getRepairRunThreadCount() {
    return repairRunThreadCount;
  }

  public void setRepairRunThreadCount(int repairRunThreadCount) {
    this.repairRunThreadCount = repairRunThreadCount;
  }

  public String getStorageType() {
    return storageType;
  }

  public void setEnableCrossOrigin(String enableCrossOrigin) {
    this.enableCrossOrigin = enableCrossOrigin;
  }

  public String getEnableCrossOrigin() {
    return this.enableCrossOrigin;
  }

  public boolean isEnableCrossOrigin() {
    return this.enableCrossOrigin != null && ("true").equalsIgnoreCase(this.enableCrossOrigin);
  }

  public void setStorageType(String storageType) {
    this.storageType = storageType;
  }

  public DataSourceFactory getDataSourceFactory() {
    return database;
  }

  public void setDataSourceFactory(DataSourceFactory database) {
    this.database = database;
  }

  public int getRepairManagerSchedulingIntervalSeconds() {
    return this.repairManagerSchedulingIntervalSeconds==null?30:this.repairManagerSchedulingIntervalSeconds;
  }

  @JsonProperty
  public void setRepairManagerSchedulingIntervalSeconds(int repairManagerSchedulingIntervalSeconds) {
    this.repairManagerSchedulingIntervalSeconds = repairManagerSchedulingIntervalSeconds;
  }

  public Map<String, Integer> getJmxPorts() {
    return jmxPorts;
  }

  public void setJmxPorts(Map<String, Integer> jmxPorts) {
    this.jmxPorts = jmxPorts;
  }

  public JmxCredentials getJmxAuth() {
    return jmxAuth;
  }

  public void setJmxAuth(JmxCredentials jmxAuth) {
    this.jmxAuth = jmxAuth;
  }

  public boolean hasAutoSchedulingEnabled() {
    return autoScheduling != null &&  autoScheduling.isEnabled();
  }

  public AutoSchedulingConfiguration getAutoScheduling() {
    return autoScheduling;
  }

  public void setAutoScheduling(AutoSchedulingConfiguration autoRepairScheduling) {
    this.autoScheduling = autoRepairScheduling;
  }

  public void setEnableDynamicSeedList(Boolean enableDynamicSeedList) {
    this.enableDynamicSeedList = enableDynamicSeedList;
  }

  public Boolean getEnableDynamicSeedList() {
    return this.enableDynamicSeedList==null?Boolean.TRUE:this.enableDynamicSeedList;
  }

  public void setUseAddressTranslator(Boolean useAddressTranslator) {
    this.useAddressTranslator = useAddressTranslator;
  }

  public boolean useAddressTranslator() {
    return this.useAddressTranslator;
  }

  public static class JmxCredentials {

    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    public String getUsername() {
      return username;
    }

    public String getPassword() {
      return password;
    }

  }


  private CassandraFactory cassandra = new CassandraFactory();

  @JsonProperty("cassandra")
  public CassandraFactory getCassandraFactory() {
      return cassandra;
  }

  @JsonProperty("cassandra")
  public void setCassandraFactory(CassandraFactory cassandra) {
      this.cassandra = cassandra;
  }

  public Boolean getAllowUnreachableNodes() {
    return allowUnreachableNodes != null ? allowUnreachableNodes : false;
  }

  public void setAllowUnreachableNodes(Boolean allow) {
    this.allowUnreachableNodes = allow;
  }

  public int getHangingRepairTimeoutMins() {
    return hangingRepairTimeoutMins;
  }

  @JsonProperty
  public void setHangingRepairTimeoutMins(int hangingRepairTimeoutMins) {
    this.hangingRepairTimeoutMins = hangingRepairTimeoutMins;
  }

  public static class AutoSchedulingConfiguration {

    @JsonProperty
    private Boolean enabled;

    @JsonProperty
    private Duration initialDelayPeriod;

    @JsonProperty
    private Duration periodBetweenPolls;

    @JsonProperty
    private Duration timeBeforeFirstSchedule;

    @JsonProperty
    private Duration scheduleSpreadPeriod;

    @JsonProperty
    private List<String> excludedKeyspaces = Collections.emptyList();

    public Boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(Boolean enable) {
      this.enabled = enable;
    }

    public Duration getInitialDelayPeriod() {
      return initialDelayPeriod;
    }

    public void setInitialDelayPeriod(Duration initialDelayPeriod) {
      this.initialDelayPeriod = initialDelayPeriod;
    }

    public Duration getPeriodBetweenPolls() {
      return periodBetweenPolls;
    }

    public void setPeriodBetweenPolls(Duration periodBetweenPolls) {
      this.periodBetweenPolls = periodBetweenPolls;
    }

    public Duration getTimeBeforeFirstSchedule() {
      return timeBeforeFirstSchedule;
    }

    public void setTimeBeforeFirstSchedule(Duration timeBeforeFirstSchedule) {
      this.timeBeforeFirstSchedule = timeBeforeFirstSchedule;
    }

    public Duration getScheduleSpreadPeriod() {
      return scheduleSpreadPeriod;
    }

    public void setScheduleSpreadPeriod(Duration scheduleSpreadPeriod) {
      this.scheduleSpreadPeriod = scheduleSpreadPeriod;
    }

    public boolean hasScheduleSpreadPeriod() {
      return scheduleSpreadPeriod != null;
    }

    public void setExcludedKeyspaces(List<String> excludedKeyspaces) {
      this.excludedKeyspaces = excludedKeyspaces;
    }

    public List<String> getExcludedKeyspaces() {
      return excludedKeyspaces;
    }

    @Override
    public String toString() {
      return "AutoSchedulingConfiguration{" +
          "enabled=" + enabled +
          ", initialDelayPeriod=" + initialDelayPeriod +
          ", periodBetweenPolls=" + periodBetweenPolls +
          ", timeBeforeFirstSchedule=" + timeBeforeFirstSchedule +
          ", scheduleSpreadPeriod=" + scheduleSpreadPeriod +
          '}';
    }
  }

}
