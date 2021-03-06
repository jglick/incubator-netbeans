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
package org.netbeans.modules.versioning.spi.testvcs;

import org.netbeans.modules.versioning.spi.VersioningSystem;
import org.netbeans.modules.versioning.spi.VCSInterceptor;
import org.netbeans.modules.versioning.spi.VCSAnnotator;

import java.io.File;
import org.netbeans.modules.versioning.spi.VCSHistoryProvider;
import org.netbeans.modules.versioning.spi.VCSVisibilityQuery;
import org.netbeans.spi.queries.CollocationQueryImplementation;

/**
 * Test versioning system.
 * 
 * @author Maros Sandor
 */
@org.openide.util.lookup.ServiceProvider(service=org.netbeans.modules.versioning.spi.VersioningSystem.class)
public class TestVCS extends VersioningSystem {

    private static TestVCS instance;
    private VCSInterceptor interceptor;
    private VCSAnnotator annotator;
    private VCSHistoryProvider historyProvider;
    private VCSVisibilityQuery vq;
    private TestVCSCollocationQuery vcq;

    public static final String VERSIONED_FOLDER_SUFFIX = "-test-versioned";

    public static TestVCS getInstance() {
        return instance;
    }
    
    public TestVCS() {
        instance = this;
        interceptor = new TestVCSInterceptor();
        annotator = new TestVCSAnnotator();
        historyProvider = new TestVCSHistoryProvider();
        vq = new TestVCSVisibilityQuery();
        vcq = new TestVCSCollocationQuery();
    }

    public File getTopmostManagedAncestor(File file) {
        File topmost = null;
        for (; file != null; file = file.getParentFile()) {
            if (file.getName().endsWith(VERSIONED_FOLDER_SUFFIX)) {
                topmost = file;
            }
        }
        return topmost;
    }

    public VCSInterceptor getVCSInterceptor() {
        return interceptor;
    }

    public VCSAnnotator getVCSAnnotator() {
        return annotator;
    }

    @Override
    public VCSVisibilityQuery getVisibilityQuery() {
        return vq;
    }

    @Override
    public CollocationQueryImplementation getCollocationQueryImplementation() {
        return vcq;
    }
    
    @Override
    public VCSHistoryProvider getVCSHistoryProvider() {
        return historyProvider;
    }
}
