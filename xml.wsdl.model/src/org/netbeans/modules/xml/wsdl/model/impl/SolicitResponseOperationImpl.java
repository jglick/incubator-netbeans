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

package org.netbeans.modules.xml.wsdl.model.impl;

import org.netbeans.modules.xml.wsdl.model.Input;
import org.netbeans.modules.xml.wsdl.model.Output;
import org.netbeans.modules.xml.wsdl.model.SolicitResponseOperation;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;
import org.netbeans.modules.xml.wsdl.model.visitor.WSDLVisitor;
import org.w3c.dom.Element;

/**
 *
 * @author Nam Nguyen
 */
public class SolicitResponseOperationImpl extends OperationImpl implements SolicitResponseOperation {
    
    /** Creates a new instance of SolicitResponseOperationImpl */
    public SolicitResponseOperationImpl(WSDLModel model, Element e) {
        super(model, e);
    }
    
    public SolicitResponseOperationImpl(WSDLModel model) {
        this(model, createNewElement(WSDLQNames.OPERATION.getQName(), model));
    }
    
    public Output getOutput() {
        return getChild(Output.class);
    }

    public void setOutput(Output output) {
        setChildAfter(Output.class, OUTPUT_PROPERTY, output, TypeCollection.DOCUMENTATION.types());
    }

    public Input getInput() {
        return getChild(Input.class);
    }

    public void setInput(Input input) {
        setChildAfter(Input.class, INPUT_PROPERTY, input, TypeCollection.DOCUMENTATION_OUTPUT.types());
    }

    public void accept(WSDLVisitor visitor) {
        visitor.visit(this);
    }
}
