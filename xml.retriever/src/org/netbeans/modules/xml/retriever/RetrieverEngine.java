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

package org.netbeans.modules.xml.retriever;

import org.netbeans.modules.xml.retriever.impl.RetrieverEngineImpl;

/**
 * Utility class used to retrieve resource(s) in a thread.
 *
 * @author girix
 */
public abstract class RetrieverEngine implements Runnable {
    
    public abstract void addResourceToRetrieve(RetrieveEntry rent);
    
    public abstract void setFileOverwrite(boolean fileOverwrite);
    
    public abstract void start();
    
    public static RetrieverEngine getRetrieverEngine(java.io.File fixedSaveRootFolder) {
        return new RetrieverEngineImpl(fixedSaveRootFolder);
    }

    public static RetrieverEngine getRetrieverEngine(java.io.File fixedSaveRootFolder,
            boolean startNewThread) {
        return new RetrieverEngineImpl(fixedSaveRootFolder, startNewThread);
    }
}
