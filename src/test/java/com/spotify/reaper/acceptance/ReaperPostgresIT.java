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
package com.spotify.reaper.acceptance;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spotify.reaper.AppContext;
import com.spotify.reaper.acceptance.ReaperTestJettyRunner.ReaperJettyTestSupport;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:com.spotify.reaper.acceptance/integration_reaper_functionality.feature"
)
public class ReaperPostgresIT {
  private static final Logger LOG = LoggerFactory.getLogger(ReaperPostgresIT.class);
  private static ReaperJettyTestSupport runnerInstance;
  private static final String POSTGRES_CONFIG_FILE="cassandra-reaper-postgres-at.yaml";
  
  
  @BeforeClass
  public static void setUp() throws Exception {
    LOG.info("setting up testing Reaper runner with {} seed hosts defined and Postgres storage",
        TestContext.TEST_CLUSTER_SEED_HOSTS.size());
    AppContext context = new AppContext();    
    runnerInstance = ReaperTestJettyRunner.setup(context, POSTGRES_CONFIG_FILE);

    BasicSteps.setReaperClient(ReaperTestJettyRunner.getClient());
  }
  
  @AfterClass
  public static void tearDown() {
    LOG.info("Stopping reaper service...");
    runnerInstance.after();
  }
  
}
