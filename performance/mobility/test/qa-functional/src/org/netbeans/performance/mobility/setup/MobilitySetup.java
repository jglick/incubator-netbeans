/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.performance.mobility.setup;

import java.io.IOException;
import org.netbeans.modules.performance.utilities.CommonUtilities;
import org.netbeans.modules.performance.utilities.PerformanceTestCase2;
import org.openide.util.Exceptions;

/**
 * Test suite that actually does not perform any test but sets up user directory
 * for UI responsiveness tests
 *
 * @author  mmirilovic@netbeans.org, mrkam@netbeans.org
 */
public class MobilitySetup extends PerformanceTestCase2 {
    
    public MobilitySetup(java.lang.String testName) {
        super(testName);
    }

    public void testCloseMemoryToolbar() {
        CommonUtilities.closeMemoryToolbar();
    }

    public void testCleanTempDir() throws IOException {
        CommonUtilities.cleanTempDir();
    }

    public void testOpenMobilityMIDletProject() {

        try {
            this.openDataProjects("MobileApplicationVisualMIDlet");
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void testOpenMobilitySwitchProject() {

        try {
            this.openDataProjects("MobileApplicationSwitchConfiguration");
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
