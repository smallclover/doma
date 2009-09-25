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
package org.seasar.doma.it.sqlfile;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.seasar.doma.it.dao.DepartmentDao;
import org.seasar.doma.it.dao.DepartmentDao_;
import org.seasar.doma.it.entity.Department;
import org.seasar.framework.unit.Seasar2;

@RunWith(Seasar2.class)
public class SqlFileBatchInsertTest {

    public void test() throws Exception {
        DepartmentDao dao = new DepartmentDao_();
        Department department = new Department();
        department.setDepartment_id(99);
        department.setDepartment_no(99);
        department.setDepartment_name("hoge");
        Department department2 = new Department();
        department2.setDepartment_id(98);
        department2.setDepartment_no(98);
        department2.setDepartment_name("foo");
        int[] result = dao.insertBySqlFile(Arrays.asList(department,
                department2));
        assertEquals(2, result.length);
        assertEquals(1, result[0]);
        assertEquals(1, result[1]);

        department = dao.selectById(99);
        assertEquals(new Integer(99), department.getDepartment_id());
        assertEquals(new Integer(99), department.getDepartment_no());
        department = dao.selectById(98);
        assertEquals(new Integer(98), department.getDepartment_id());
        assertEquals(new Integer(98), department.getDepartment_no());
    }

}
