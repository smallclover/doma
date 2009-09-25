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
package org.seasar.doma.internal.apt.meta;

import static org.seasar.doma.internal.util.AssertionUtil.*;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import org.seasar.doma.Select;
import org.seasar.doma.internal.apt.AptException;
import org.seasar.doma.internal.apt.meta.type.AnyType;
import org.seasar.doma.internal.apt.meta.type.CollectionType;
import org.seasar.doma.internal.apt.meta.type.IterationCallbackType;
import org.seasar.doma.internal.apt.meta.type.ValueType;
import org.seasar.doma.message.DomaMessageCode;

/**
 * @author taedium
 * 
 */
public class SqlFileSelectQueryMetaFactory extends
        AbstractSqlFileQueryMetaFactory<SqlFileSelectQueryMeta> {

    public SqlFileSelectQueryMetaFactory(ProcessingEnvironment env) {
        super(env);
    }

    @Override
    public QueryMeta createQueryMeta(ExecutableElement method, DaoMeta daoMeta) {
        assertNotNull(method, daoMeta);
        SqlFileSelectQueryMeta queryMeta = createSqlFileSelectQueryMeta(method,
                daoMeta);
        if (queryMeta == null) {
            return null;
        }
        doTypeParameters(queryMeta, method, daoMeta);
        doParameters(queryMeta, method, daoMeta);
        doReturnType(queryMeta, method, daoMeta);
        doThrowTypes(queryMeta, method, daoMeta);
        doSqlFile(queryMeta, method, daoMeta);
        return queryMeta;
    }

    protected SqlFileSelectQueryMeta createSqlFileSelectQueryMeta(
            ExecutableElement method, DaoMeta daoMeta) {
        Select select = method.getAnnotation(Select.class);
        if (select == null) {
            return null;
        }
        SqlFileSelectQueryMeta queryMeta = new SqlFileSelectQueryMeta();
        queryMeta.setQueryTimeout(select.queryTimeout());
        queryMeta.setFetchSize(select.fetchSize());
        queryMeta.setMaxRows(select.maxRows());
        queryMeta.setIterated(select.iterate());
        queryMeta.setQueryKind(QueryKind.SQLFILE_SELECT);
        queryMeta.setName(method.getSimpleName().toString());
        queryMeta.setExecutableElement(method);
        return queryMeta;
    }

    @Override
    protected void doReturnType(SqlFileSelectQueryMeta queryMeta,
            ExecutableElement method, DaoMeta daoMeta) {
        QueryReturnMeta returnMeta = createReturnMeta(method);
        queryMeta.setReturnMeta(returnMeta);
        if (queryMeta.isIterated()) {
            IterationCallbackType iterationCallbackType = queryMeta
                    .getIterationCallbackType();
            AnyType callbackReturnType = iterationCallbackType.getReturnType();
            if (callbackReturnType == null
                    || !env.getTypeUtils().isSameType(returnMeta.getType(),
                            callbackReturnType.getType())) {
                throw new AptException(DomaMessageCode.DOMA4055, env, method,
                        returnMeta.getType(), callbackReturnType);
            }
        } else {
            if (!returnMeta.isSupportedType()) {
                throw new AptException(DomaMessageCode.DOMA4008, env,
                        returnMeta.getElement(), returnMeta.getType());
            }
            CollectionType collectionType = returnMeta.getCollectionType();
            if (collectionType != null) {
                if (!collectionType.hasSupportedElementType()) {
                    throw new AptException(DomaMessageCode.DOMA4007, env,
                            returnMeta.getElement(), collectionType
                                    .getEntityType());
                }
            }
        }
    }

    @Override
    protected void doParameters(SqlFileSelectQueryMeta queryMeta,
            ExecutableElement method, DaoMeta daoMeta) {
        for (VariableElement parameter : method.getParameters()) {
            QueryParameterMeta parameterMeta = createParameterMeta(parameter);
            if (parameterMeta.getCollectionType() != null) {
                CollectionType collectionType = parameterMeta
                        .getCollectionType();
                ValueType valueType = collectionType.getValueType();
                if (valueType == null) {
                    throw new AptException(DomaMessageCode.DOMA4028, env,
                            parameterMeta.getElement());
                }
            } else if (parameterMeta.getEntityType() != null) {
            } else if (parameterMeta.getValueType() != null) {
            } else if (parameterMeta.getSelectOptionsType() != null) {
                if (queryMeta.getSelectOptionsType() != null) {
                    throw new AptException(DomaMessageCode.DOMA4053, env,
                            parameterMeta.getElement());
                }
                queryMeta.setSelectOptionsType(parameterMeta
                        .getSelectOptionsType());
                queryMeta
                        .setSelectOptionsParameterName(parameterMeta.getName());
            } else if (parameterMeta.getIterationCallbackType() != null) {
                if (queryMeta.getIterationCallbackType() != null) {
                    throw new AptException(DomaMessageCode.DOMA4054, env,
                            parameterMeta.getElement());
                }
                queryMeta.setIterationCallbackType(parameterMeta
                        .getIterationCallbackType());
                queryMeta.setIterationCallbackPrameterName(parameterMeta
                        .getName());
            } else {
                throw new AptException(DomaMessageCode.DOMA4008, env,
                        parameterMeta.getElement(), parameterMeta.getType());
            }
            queryMeta.addParameterMetas(parameterMeta);
            queryMeta.addExpressionParameterType(parameterMeta.getName(),
                    parameterMeta.getType());
        }
        if (queryMeta.isIterated()
                && queryMeta.getIterationCallbackType() == null) {
            throw new AptException(DomaMessageCode.DOMA4056, env, method);
        }
        if (!queryMeta.isIterated()
                && queryMeta.getIterationCallbackType() != null) {
            throw new AptException(DomaMessageCode.DOMA4057, env, method);
        }
    }

}
