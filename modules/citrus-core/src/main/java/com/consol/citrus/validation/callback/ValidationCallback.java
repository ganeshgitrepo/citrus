/*
 * Copyright 2006-2012 the original author or authors.
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

package com.consol.citrus.validation.callback;

import org.springframework.context.ApplicationContext;
import org.springframework.integration.Message;

/**
 * Callback called by receive message action for validation purpose. Implementations
 * to validate the received message with Java code.
 * 
 * @author Christoph Deppisch
 */
public interface ValidationCallback {

    /**
     * Validate callback method with received message.
     * 
     * @param message
     */
    void validate(Message<?> message);
    
    /**
     * Set optional application context instance on this callback.
     * @param ctx
     */
    void setApplicationContext(ApplicationContext ctx);
}
