/*
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
package org.netbeans.modules.websvc.saas.codegen;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.text.Document;
//import org.apache.tools.ant.module.api.support.ActionUtils;
import org.netbeans.junit.NbTestCase;
import org.netbeans.modules.websvc.saas.codegen.util.Util;
import org.netbeans.modules.websvc.saas.model.*;
import org.netbeans.modules.websvc.saas.model.wadl.Method;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

/**
 *
 * @author ayubkhan
 */
public class JavaCodeGenerationTest extends NbTestCase {

    private static final List<String> nonhyperlinkedOut = new ArrayList<String>();
    private static final List<String> nonhyperlinkedErr = new ArrayList<String>();
    private static final List<String> hyperlinkedOut = new ArrayList<String>();
    private static final List<String> hyperlinkedErr = new ArrayList<String>();
    private FileObject targetFolder;
    private Properties props;

    public JavaCodeGenerationTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        targetFolder = FileUtil.createFolder(new File("/tmp/testuserdir"));
        nonhyperlinkedOut.clear();
        nonhyperlinkedErr.clear();
        hyperlinkedOut.clear();
        hyperlinkedErr.clear();
//        String junitJarS = System.getProperty("test.junit.jar");
//        assertNotNull("defined test.junit.jar", junitJarS);
//        File junitJar = new File(junitJarS);
//        assertTrue("file " + junitJar + " exists", junitJar.isFile());
//        props = new Properties();
//        props.setProperty("libs.junit.classpath", junitJar.getAbsolutePath()); // #50261

    }

    @Override
    protected void tearDown() throws Exception {
    }

    public void testWadlDragnDrop() {
        try {
            SetupUtil.commonSetUp(super.getWorkDir());
            InputStream is = this.getClass().getResourceAsStream("JavaApplication.zip");
            TestUtils.unZipFile(is, targetFolder);
        } catch (Exception ex) {
            assertFalse("Failed with exception: "+ex.getMessage(), ex != null);
        }
        FileObject targetFO = targetFolder.getFileObject("JavaApplication/src/javaapplication/Main.java");
        assertTrue("Target File is null", targetFO != null);

        SaasServicesModel instance = SaasServicesModel.getInstance();
        SaasGroup group = instance.getRootGroup().getChildGroup("Delicious");
        WadlSaas saas = (WadlSaas) group.getServices().get(0);
        assertEquals("Bookmarking Service", saas.getDisplayName());
        WadlSaasMethod method = saas.getResources().get(0).getChildResources().get(0).getMethods().get(0);
        Method m = method.getWadlMethod();
        if (m == null) {
            Exceptions.printStackTrace(new IOException("Wadl method not found"));
        }

        try {
            Document doc = Util.getDocument(targetFO);
            SaasClientCodeGenerator codegen = 
                    (SaasClientCodeGenerator) SaasClientCodeGenerationManager.lookup(method, doc);

            codegen.generate();
            
            FileObject buildXml = targetFolder.getFileObject("JavaApplication/build.xml");
            assertNotNull("have build.xml as a FileObject", buildXml);
//            ActionUtils.runTarget(buildXml, new String[]{"clean", "run"}, props).result();
            //System.out.println("nonhyperlinkedOut=" + nonhyperlinkedOut + " nonhyperlinkedErr=" + nonhyperlinkedErr + " hyperlinkedOut=" + hyperlinkedOut + " hyperlinkedErr=" + hyperlinkedErr);
            assertTrue("hyperlinkedErr found", hyperlinkedErr.size() == 0);
            assertTrue("hyperlinkedOut found ", hyperlinkedOut.size() == 0);
            assertTrue("nonhyperlinkedErr found", nonhyperlinkedErr.size() == 0);
            assertTrue("nonhyperlinkedErr found", nonhyperlinkedErr.size() == 0);

        } catch (Exception ex) {
            assertFalse("Failed with exception: "+ex.getMessage(), ex != null);
        }
    }
}
