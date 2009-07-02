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
package org.seasar.doma.internal.expr;

import static org.seasar.doma.internal.util.Assertions.*;

/**
 * @author taedium
 * 
 */
public class EvaluationResult {

    protected final Object value;

    protected final Class<?> valueClass;

    public EvaluationResult(Object value, Class<?> valueClass) {
        assertNotNull(valueClass);
        this.value = value;
        this.valueClass = valueClass;
    }

    public Class<?> getValueClass() {
        return valueClass;
    }

    public Object getValue() {
        return value;
    }

    public boolean getBooleanValue() {
        if (Boolean.class.isInstance(value)) {
            return Boolean.class.cast(value).booleanValue();
        }
        return false;
    }

    @Override
    public String toString() {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}