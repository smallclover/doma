=======================
Getting Started
=======================

.. contents:: 目录
:depth: 3

概要
====

介绍设置开发环境的方法，以及如何执行基本的数据库访问
.. _IntelliJ Doma support plugin: https://github.com/siosio/DomaSupport

.. note::

  本文档使用 Eclipse 作为 IDE ，但是使用 IntelliJ IDEA 代替 Eclipse 进行开发也是可以的。
  使用 IntelliJ IDEA 的情况下，建议和 `IntelliJ Doma support plugin`_ 一起使用。

JDK 的安装
==================

.. _JDK 8: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

根据链接地址安装 `JDK 8`_ 。

Eclipse 的安装
======================

.. _Eclipse Standard 4.4: http://www.eclipse.org/downloads/

根据链接地址安装 `Eclipse Standard 4.4`_ 。

.. note::

  虽然本文档面向的是 Eclipse Standard 软件包，但是诸如 Eclipse IDE for Java EE Developers 等其他的软件包也是可以正常工作的。

安装 Eclipse插件 Doma Tools
============================================

Doma Tools 插件可以用来实现 Java 文件和 SQL 文件之间的转换
在 Doma 框架中该插件不是必须使用的，只不过使用之后能提高一些生产性。

Eclipse 从菜单栏开始，点击 Help > Install New Software... ，
然后点击文本框'Work With' ，输入以下URL。

::

  http://dl.bintray.com/domaframework/eclipse/

如下图所示显示的可安装的插件候选，请检查最新版本的 Doma Tools 并继续进行其他步骤完成安装。

.. image:: images/install-doma-tools.png

文件的关联
------------------

Doma Tools 将更新挂钩到SQL文件并执行注释处理。
因此，需要能偶在Eclipse中打开SQL文件。

从菜单开始选择 Eclipse > 环境设定... 或者 Window > Preference ，然后打开设置画面。

如下图所示的那样，将 Text Editor 与扩展名为 ``.sql`` 文件关联起来。

.. image:: images/sql-file-association.png
:width: 80 %

同样的，将 Text Editor 与扩展名为 ``.script`` 的文件关联起来。

.. image:: images/script-file-association.png
:width: 80 %

.. note::

   如果使用的是 Eclipse IDE for Java EE Developers 的情况下、
   SQL文件默认是和专用的编辑器关联在一起了，所以该步骤可以跳过。

.. _Oracle SQL Developer: http://www.oracle.com/technetwork/developer-tools/sql-developer/overview/index.html
.. _pgAdmin: http://www.pgadmin.org/

.. note::

  推荐的开发方式是使用 RDBMS 所对应的工具（`Oracle SQL Developer`_ 或者 `pgAdmin`_）来编写 SQL，
  然后将写完的 SQL 文件拷贝到 Eclipse 的编辑器中

导入示例的原型项目
============================

从 GitHub clone项目 simple-boilerplate 。

.. code-block:: bash

  $ git clone https://github.com/domaframework/simple-boilerplate.git

移动到 clone 的目录。

.. code-block:: bash

  $ cd simple-boilerplate

使用如下命令来生成 Eclipse 用的配置文件。

.. code-block:: bash

  $ ./gradlew eclipse

.. note::

  如果是 Windows 系统请使用 ``gradlew eclipse`` 来代替 ``./gradlew eclipse`` 。

.. note::

  请务必在环境变量 ``JAVA_HOME`` 中指定JDK8的安装目录。
  那是执行 gradlew 命令所必须的条件。


在 Eclipse 的菜单中选择 File > Import...
找到 'Existing Projects into Workspace' 选择项目 simple-boilerplate 导入。

.. image:: images/import.png
:width: 80 %

为了确保导入成功，选择该项目，执行 JUnit 测试。
如果测试显示1件成功，说明导入正常。

原型项目的构成
======================

项目源代码的构成如下所示。

::

  ─ src
    ├── main
    │   ├── java
    │   │   └── boilerplate
    │   │       ├── AppConfig.java
    │   │       ├── dao
    │   │       │   ├── AppDao.java
    │   │       │   └── EmployeeDao.java
    │   │       └── entity
    │   │           └── Employee.java
    │   └── resources
    │       └── META-INF
    │           └── boilerplate
    │               └── dao
    │                   ├── AppDao
    │                   │   ├── create.script
    │                   │   └── drop.script
    │                   └── EmployeeDao
    │                       ├── selectAll.sql
    │                       └── selectById.sql
    └── test
        ├── java
        │   └── boilerplate
        │       ├── DbResource.java
        │       └── dao
        │           └── EmployeeDaoTest.java
        └── resources

想在对主要的部分进行解释说明。

AppConfig.java
  运行 Doma 所必要的 :doc:`config` 。

AppDao.java
  用来在该应用中运行时创建/销毁数据库模式。
  真实环境是不需要的。
  模式的创建/销毁使用以下目录下的脚本文件 ``META-INF/boilerplate/dao/AppDao/`` 。

Employee.java
  数据库中表 `EMPLOYEE` 对应的 :doc:`entity` 。

EmployeeDao.java
  ``Employee`` 类使用用来执行更新和获取的 :doc:`dao` 。
  使用以下目录 ``META-INF/boilerplate/dao/EmployeeDao/`` 中的 SQL文件 。

EmployeeDaoTest.java
  ``EmployeeDao`` 使用的测试用例。
  可以一边在这个文件中追加测试项目，一边学习 Doma 。
  因为所有的测试方法都会执行对数据库模式的创建和销毁
  所以数据的更新不会受到其他测试的影响。

Java 和 SQL 的相互转移
======================

``EmployeeDao.java`` 的定义如下。

.. code-block:: java

  @Dao(config = AppConfig.class)
  public interface EmployeeDao {

      @Select
      List<Employee> selectAll();

      @Select
      Employee selectById(Integer id);

      @Insert
      int insert(Employee employee);

      @Update
      int update(Employee employee);

      @Delete
      int delete(Employee employee);

  }

在 Eclipse 编辑器上的 ``selectById`` 方法上右键单击，
在弹出的菜单中选择 Doma > Jump to SQL 然后会跳转到文件
``META-INF/boilerplate/dao/EmployeeDao/selectById.sql`` 。

接下来在 ``META-INF/boilerplate/dao/EmployeeDao/selectById.sql`` 文件的任意地方
右键，在弹出的菜单中选择  Doma > Jump to Java ，然后会返回文件 ``EmployeeDao.java`` 。

SQL 文件
============

打开文件 ``META-INF/boilerplate/dao/EmployeeDao/selectById.sql`` 。
文件记载的内容如下所示。

.. code-block:: sql

  select
      /*%expand*/*
  from
      employee
  where
      id = /* id */0

``/*%expand*/`` 表示使用 Java 方法将实体类映射到数据库中表对应的列的展开。

``/* id */`` 表示将 Java 方法的参数的值绑定到 SQL 。

它后面的 ``0`` 是测试数据。
如果包含了这个测试数据，那么在 SQL 工具运行的上下文里
可以轻易的确认是否有语法错误。

测试用的数据在 Java 程序运行的时候不会使用。

详细请参照 :doc:`sql` 。

检索
====

:doc:`query/select` 处理运行的时候，将会调用 ``@Select`` 注释的 Dao 方法。

添加检索处理
--------------

接下来展示一个检索小于一定年龄的员工的处理过程。

在 ``EmployeeDao`` 中追加以下代码。

.. code-block:: java

   @Select
   List<Employee> selectByAge(Integer age);

这个时候根据注释处理，将会在 Eclipse 上展示以下的错误信息。

::

  [DOMA4019] ファイル[META-INF/boilerplate/dao/EmployeeDao/selectByAge.sql]が
  クラスパスから見つかりませんでした。


::

  [DOMA4019] 没有找到[META-INF/boilerplate/dao/EmployeeDao/selectByAge.sql]文件。


将光标放在 Eclipse 编辑器的 ``selectByAge`` 方法上并右键单击
在弹出的菜单中选择 Doma > Jump to SQL。

用于创建新SQL文件的对话框如下所示。

.. image:: images/new-sql-file.png
:width: 80 %

点击 'Finish' 生成文件。

文件生成之后，保存空文件，返回 ``EmployeeDao``
这是错误信息将会改变。
エラーメッセージの内容が変わります。

::

  [DOMA4020] SQLファイル[META-INF/boilerplate/dao/EmployeeDao/selectByAge.sql]が空です。

::

  [DOMA4020] SQL文件[META-INF/boilerplate/dao/EmployeeDao/selectByAge.sql]是空的。

返回 ``selectByAge.sql`` 文件，编写如下 SQL。

.. code-block:: sql

  select
      /*%expand*/*
  from
      employee
  where
      age < /* age  */0

然后错误就会消失。


运行检索处理
--------------

对上面编写检索处理的代码进行实际的运行。

在 ``EmployeeDaoTest`` 文件中追加以下代码。

.. code-block:: java

  @Test
  public void testSelectByAge() {
      TransactionManager tm = AppConfig.singleton().getTransactionManager();
      tm.required(() -> {
          List<Employee> employees = dao.selectByAge(35);
          assertEquals(2, employees.size());
      });
  }

运行 JUnit 测试，确认一下代码的行为。

此时控制台打印出的搜索 SQL 如下所示。

.. code-block:: sql

  select
      age, id, name, version
  from
      employee
  where
      age < 35

插入
====

:doc:`query/insert` 运行的时候将会调用、 ``@Insert`` 注释的 Dao 方法。

运行插入处理
--------------

确认类 ``EmployeeDao`` 中存在以下代码。

.. code-block:: java

  @Insert
  int insert(Employee employee);

使用以上代码进行插入处理。

在类 ``EmployeeDaoTest`` 中添加以下代码。

.. code-block:: java

  @Test
  public void testInsert() {
      TransactionManager tm = AppConfig.singleton().getTransactionManager();

      Employee employee = new Employee();

      // 第一次事务
      // 执行插入
      tm.required(() -> {
          employee.name = "HOGE";
          employee.age = 20;
          dao.insert(employee);
          assertNotNull(employee.id);
      });

      // 第二次事务
      // 确认插入成功
      tm.required(() -> {
          Employee employee2 = dao.selectById(employee.id);
          assertEquals("HOGE", employee2.name);
          assertEquals(Integer.valueOf(20), employee2.age);
          assertEquals(Integer.valueOf(1), employee2.version);
      });
  }

运行 JUnit 测试，确认一下代码的行为。

此时控制台打印出的插入 SQL 如下所示。

.. code-block:: sql

  insert into Employee (age, id, name, version) values (20, 100, 'HOGE', 1)

唯一标识(id)和版本号(version)将会自动生成。

更新
====

:doc:`query/update` 运行的时候将会调用、 ``@Update`` 注释的 Dao 方法。

运行更新处理
--------------

确认类 ``EmployeeDao`` 中存在以下代码。

.. code-block:: java

  @Update
  int update(Employee employee);

利用以上代码进行更新处理。

在类 ``EmployeeDaoTest`` 中添加以下代码。

.. code-block:: java

  @Test
  public void testUpdate() {
      TransactionManager tm = AppConfig.singleton().getTransactionManager();

      // 第一次事务
      // 搜索并更新 age 字段
      tm.required(() -> {
          Employee employee = dao.selectById(1);
          assertEquals("ALLEN", employee.name);
          assertEquals(Integer.valueOf(30), employee.age);
          assertEquals(Integer.valueOf(0), employee.version);
          employee.age = 50;
          dao.update(employee);
          assertEquals(Integer.valueOf(1), employee.version);
      });

      // 第二次事务
      // 确认更新成功
      tm.required(() -> {
          Employee employee = dao.selectById(1);
          assertEquals("ALLEN", employee.name);
          assertEquals(Integer.valueOf(50), employee.age);
          assertEquals(Integer.valueOf(1), employee.version);
      });
  }

运行 JUnit 测试，确认一下代码的行为。

此时控制台打印出的更新 SQL 如下所示。

.. code-block:: sql

  update Employee set age = 50, name = 'ALLEN', version = 0 + 1 where id = 1 and version = 0

因为乐观排他的原因，版本番号会自动增长。

删除
====

:doc:`query/delete` 运行的时候会调用、 ``@Delete`` 注释的 Dao 方法。

执行删除处理
--------------

确认在类 ``EmployeeDao`` 中存在以下代码。

.. code-block:: java

  @Delete
  int delete(Employee employee);

使用以上代码进行删除处理。

在类 ``EmployeeDaoTest`` 中添加以下代码。

.. code-block:: java

  @Test
  public void testDelete() {
      TransactionManager tm = AppConfig.singleton().getTransactionManager();

      // 第一次事务
      // 执行删除处理
      tm.required(() -> {
          Employee employee = dao.selectById(1);
          dao.delete(employee);
      });

      // 第二次事务
      // 确认删除成功
      tm.required(() -> {
          Employee employee = dao.selectById(1);
          assertNull(employee);
      });
  }


运行 JUnit 测试，确认一下代码的行为。

此时控制台打印出的删除 SQL 如下所示。

.. code-block:: sql

  delete from Employee where id = 1 and version = 0

除了标识符(id)之外，也在搜索条件中指定版本号(version)。

