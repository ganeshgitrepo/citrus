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

package com.consol.citrus.ws.message.callback;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.springframework.integration.Message;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;

import com.consol.citrus.ws.message.converter.SoapMessageConverter;

/**
 * Receiver callback invoked by framework on response message. Callback fills an internal message representation with
 * the response information for further message processing.
 * 
 * @author Christoph Deppisch
 */
public class SoapResponseMessageCallback implements WebServiceMessageCallback {

    /** The response message built from WebService response message */
    private Message<?> response;

    /** Message converter */
    private SoapMessageConverter messageConverter = new SoapMessageConverter();
    
    /**
     * Callback method called with actual web service response message. Method constructs a Spring Integration
     * message from this web service message for further processing.
     */
    public void doWithMessage(WebServiceMessage responseMessage) throws IOException, TransformerException {
        // convert and set response for later access via getResponse():
        response = messageConverter.convert(responseMessage);
    }
    
    /**
     * Gets the constructed Spring Integration response message object.
     * @return the response message.
     */
    public Message<?> getResponse() {
        return response;
    }
}
