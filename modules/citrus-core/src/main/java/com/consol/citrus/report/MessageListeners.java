/*
 * Copyright 2006-2013 the original author or authors.
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

package com.consol.citrus.report;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christoph Deppisch
 */
public class MessageListeners implements MessageListener {

    /** List of message listener known to Spring application context */
    @Autowired
    private List<MessageListener> messageListener = new ArrayList<MessageListener>();

    /**
     * Delegate to all known message listener instances.
     * @param message
     */
    public void onInboundMessage(String message) {
        for (MessageListener listener : messageListener) {
            listener.onInboundMessage(message);
        }
    }

    /**
     * Delegate to all known message listener instances.
     * @param message
     */
    public void onOutboundMessage(String message) {
        for (MessageListener listener : messageListener) {
            listener.onOutboundMessage(message);
        }
    }

    /**
     * Adds a new message listener.
     * @param listener
     */
    public void addMessageListener(MessageListener listener) {
        this.messageListener.add(listener);
    }

}
