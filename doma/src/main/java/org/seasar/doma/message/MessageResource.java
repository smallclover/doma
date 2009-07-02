/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.message;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.seasar.doma.DomaIllegalArgumentException;


/**
 * @author taedium
 * 
 */
public class MessageResource extends ResourceBundle {

    @Override
    public Enumeration<String> getKeys() {
        List<String> keys = new LinkedList<String>();
        for (MessageCode messageCode : EnumSet.allOf(MessageCode.class)) {
            keys.add(messageCode.message);
        }
        return Collections.enumeration(keys);
    }

    @Override
    protected Object handleGetObject(String key) {
        if (key == null) {
            new DomaIllegalArgumentException("key", key);
        }
        MessageCode m = Enum.valueOf(MessageCode.class, key);
        return m.message;
    }
}