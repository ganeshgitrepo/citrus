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

package com.consol.citrus.config.xml;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.consol.citrus.actions.InputAction;
import com.consol.citrus.testng.AbstractBeanDefinitionParserBaseTest;

/**
 * @author Christoph Deppisch
 */
public class InputActionParserTest extends AbstractBeanDefinitionParserBaseTest<InputAction> {

    @Test
    public void testInputActionParser() {
        assertActionCount(4);
        assertActionClassAndName(InputAction.class, "input");
        
        InputAction action = getNextTestActionFromTest();
        Assert.assertEquals(action.getMessage(), "Press return key to continue ...");
        Assert.assertNull(action.getValidAnswers());
        Assert.assertEquals(action.getVariable(), "userinput");
        
        action = getNextTestActionFromTest();
        Assert.assertEquals(action.getMessage(), "Do you want to continue?");
        Assert.assertNull(action.getValidAnswers());
        Assert.assertEquals(action.getVariable(), "userinput");
        
        action = getNextTestActionFromTest();
        Assert.assertEquals(action.getMessage(), "Do you want to continue?");
        Assert.assertEquals(action.getValidAnswers(), "yes/no");
        Assert.assertEquals(action.getVariable(), "userinput");
        
        action = getNextTestActionFromTest();
        Assert.assertEquals(action.getMessage(), "Do you want to continue?");
        Assert.assertEquals(action.getValidAnswers(), "y/n");
        Assert.assertEquals(action.getVariable(), "inputVar");
    }
}
