/*
 * Copyright 2006-2010 the original author or authors.
 *
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

package com.consol.citrus.ws.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;

import com.consol.citrus.context.TestContext;
import com.consol.citrus.exceptions.ValidationException;
import com.consol.citrus.validation.MessageValidator;
import com.consol.citrus.validation.context.ValidationContext;
import com.consol.citrus.validation.xml.XmlMessageValidationContext;

/**
 * Soap fault validator implementation that delegates soap fault detail validation to default XML message validator
 * in order to support XML fault detail content validation.
 * 
 * @author Christoph Deppisch
 */
public class XmlSoapFaultValidator extends AbstractFaultDetailValidator {

    @Autowired(required = false)
    @Qualifier("xmlMessageValidator")
    private MessageValidator<XmlMessageValidationContext> messageValidator;
    
    /**
     * Delegates to XML message validator for validation of fault detail.
     */
    @Override
    protected void validateFaultDetailString(String receivedDetailString, String controlDetailString, 
            TestContext context, ValidationContext validationContext) throws ValidationException {
        XmlMessageValidationContext xmlMessageValidationContext;
        
        if (validationContext instanceof XmlMessageValidationContext) {
            xmlMessageValidationContext = (XmlMessageValidationContext) validationContext;
        } else {
            xmlMessageValidationContext = new XmlMessageValidationContext();
        }
        
        Message<String> controlMessage = MessageBuilder.withPayload(controlDetailString).build();
        xmlMessageValidationContext.setControlMessage(controlMessage);

        Message<String> receivedMessage = MessageBuilder.withPayload(receivedDetailString).build();
        messageValidator.validateMessage(receivedMessage, context, xmlMessageValidationContext);
    }

    /**
     * Sets the message validator capable of validating the Soap fault detail.
     * @param validator the validator to set
     */
    public void setMessageValidator(MessageValidator<XmlMessageValidationContext> validator) {
        this.messageValidator = validator;
    }
}
