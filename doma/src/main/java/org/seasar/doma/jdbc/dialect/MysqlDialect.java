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
package org.seasar.doma.jdbc.dialect;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.seasar.doma.DomaIllegalArgumentException;
import org.seasar.doma.internal.jdbc.dialect.MysqlPagingTransformer;
import org.seasar.doma.jdbc.SelectForUpdateType;
import org.seasar.doma.jdbc.SqlNode;


/**
 * @author taedium
 * 
 */
public class MysqlDialect extends StandardDialect {

    protected static final Set<Integer> UNIQUE_CONSTRAINT_VIOLATION_ERROR_CODES = new HashSet<Integer>(
            Arrays.asList(1022, 1062));

    @Override
    public String getName() {
        return "mysql";
    }

    @Override
    public boolean isUniqueConstraintViolated(SQLException sqlException) {
        if (sqlException == null) {
            throw new DomaIllegalArgumentException("sqlException", sqlException);
        }
        int code = getErrorCode(sqlException);
        return UNIQUE_CONSTRAINT_VIOLATION_ERROR_CODES.contains(code);
    }

    @Override
    public boolean supportsAutoGeneratedKeys() {
        return true;
    }

    @Override
    public boolean supportsIdentity() {
        return true;
    }

    @Override
    public boolean supportsSelectForUpdate(SelectForUpdateType type,
            boolean withTargets) {
        return type == SelectForUpdateType.NORMAL && !withTargets;
    }

    @Override
    protected SqlNode toPagingSqlNode(SqlNode sqlNode, int offset, int limit) {
        MysqlPagingTransformer transformer = new MysqlPagingTransformer(offset,
                limit);
        return transformer.transform(sqlNode);
    }

}