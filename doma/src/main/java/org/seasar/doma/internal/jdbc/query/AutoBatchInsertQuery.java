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
package org.seasar.doma.internal.jdbc.query;

import static org.seasar.doma.internal.util.Assertions.*;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.doma.internal.jdbc.Entity;
import org.seasar.doma.internal.jdbc.GeneratedIdProperty;
import org.seasar.doma.internal.jdbc.Property;
import org.seasar.doma.internal.jdbc.id.IdGenerationConfig;
import org.seasar.doma.internal.jdbc.sql.PreparedSql;
import org.seasar.doma.internal.jdbc.sql.PreparedSqlBuilder;
import org.seasar.doma.jdbc.JdbcException;
import org.seasar.doma.message.MessageCode;


/**
 * @author taedium
 * 
 */
public class AutoBatchInsertQuery<I, E extends Entity<I>> extends
        AutoBatchModifyQuery<I, E> implements BatchInsertQuery {

    protected GeneratedIdProperty<?> generatedIdProperty;

    protected final List<GeneratedIdProperty<?>> generatedIdProperties = new ArrayList<GeneratedIdProperty<?>>();

    protected IdGenerationConfig idGenerationConfig;

    protected boolean batchSupported = true;

    public AutoBatchInsertQuery(Class<E> entityClass) {
        super(entityClass);
    }

    public void compile() {
        assertNotNull(config, entities);
        Iterator<? extends E> it = entities.iterator();
        if (it.hasNext()) {
            executable = true;
            entity = it.next();
            entity.__preInsert();
            prepareTableAndColumnNames();
            prepareIdAndVersionProperties();
            prepareOptions();
            prepareTargetProperties();
            prepareVersionValue();
            prepareSql();
        } else {
            return;
        }
        while (it.hasNext()) {
            idProperties.clear();
            generatedIdProperty = null;
            versionProperty = null;
            targetProperties.clear();
            this.entity = it.next();
            entity.__preInsert();
            prepareIdAndVersionProperties();
            prepareTargetProperties();
            prepareVersionValue();
            prepareSql();
        }
        assertEquals(entities.size(), sqls.size());
    }

    @Override
    protected void prepareIdAndVersionProperties() {
        super.prepareIdAndVersionProperties();
        generatedIdProperty = entity.__getGeneratedIdProperty();
        generatedIdProperties.add(generatedIdProperty);
        if (generatedIdProperty != null) {
            if (idGenerationConfig == null) {
                String idColumnName = columnNameMap.get(generatedIdProperty
                        .getName());
                idGenerationConfig = new IdGenerationConfig(config, entity,
                        tableName, idColumnName);
                generatedIdProperty
                        .validateGenerationStrategy(idGenerationConfig);
                autoGeneratedKeysSupported = generatedIdProperty
                        .isAutoGeneratedKeysSupported(idGenerationConfig);
                batchSupported = generatedIdProperty
                        .isBatchSupported(idGenerationConfig);
            }
            generatedIdProperty.preInsert(idGenerationConfig);
        }
    }

    protected void prepareTargetProperties() {
        for (Property<?> p : entity.__getProperties()) {
            if (!p.isInsertable()) {
                continue;
            }
            if (p.isId()) {
                if (p != generatedIdProperty
                        || generatedIdProperty.isIncluded(idGenerationConfig)) {
                    targetProperties.add(p);
                }
                if (generatedIdProperty == null && p.getDomain().isNull()) {
                    throw new JdbcException(MessageCode.DOMA2020, entity
                            .__getName(), p.getName());
                }
                continue;
            }
            targetProperties.add(p);
        }
    }

    protected void prepareVersionValue() {
        if (versionProperty != null) {
            versionProperty.setIfNecessary(1);
        }
    }

    protected void prepareSql() {
        PreparedSqlBuilder builder = new PreparedSqlBuilder(config
                .sqlLogFormattingVisitor());
        builder.appendSql("insert into ");
        builder.appendSql(tableName);
        builder.appendSql(" (");
        for (Property<?> p : targetProperties) {
            builder.appendSql(columnNameMap.get(p.getName()));
            builder.appendSql(", ");
        }
        builder.cutBackSql(2);
        builder.appendSql(") values (");
        for (Property<?> p : targetProperties) {
            builder.appendDomain(p.getDomain());
            builder.appendSql(", ");
        }
        builder.cutBackSql(2);
        builder.appendSql(")");
        PreparedSql sql = builder.build();
        sqls.add(sql);
    }

    @Override
    public boolean isBatchSupported() {
        return batchSupported;
    }

    @Override
    public void generateId(Statement statement, int index) {
        GeneratedIdProperty<?> generatedIdProperty = generatedIdProperties
                .get(index);
        generatedIdProperty.postInsert(idGenerationConfig, statement);
    }

}