==================
设定
==================

.. contents:: 目录
      :depth: 3

对于Doma的设置，通过 ``Confing`` 接口的实现类来表示。

设置项目
=================

对于必须的配置以及未明确指定的配置将会使用默认值

数据源
----------------

通过 ``getDataSource`` 方法来获得 ``DataSource`` 。
在使用本地事务的场合，使用 ``LocalTransactionDataSource`` 方法获得数据源。
.. note::

   该项目是必须设置的。

数据源的名称
------------------

数据源的名称通过 ``getDataSourceName`` 方法返回。
在有多个数据源的环境中，数据源的名称是非常重要的。
通过使用数据名称来区别每个数据源自动生成的标识符。
如果是多个数据源的情况下，请保证返回的名字是不同的。

默认的实现使用 ``Config`` 实现类的全限定名。

数据库方言
--------------------------

通过方法 ``getDialect`` 来获得 ``Dialect`` 。
``Dialect`` 是表示RDBMS方言的接口。
``Dialect`` 有如下实现。

+----------------------------+------------------+------------------------------------------+
| 数据库              | Dialect          | 说明                                 |
+============================+==================+==========================================+
| DB2                        | Db2Dialect       |                                          |
+----------------------------+------------------+------------------------------------------+
| H2 Database Engine 1.2.126 | H212126Dialect   | 在H2 Database Engine 1.2.126上运行        |
+----------------------------+------------------+------------------------------------------+
| H2 Database                | H2Dialect        | 对应H2 Database Engine 1.3.171或者更高版本 |
+----------------------------+------------------+------------------------------------------+
| HSQLDB                     | HsqldbDialect    |                                          |
+----------------------------+------------------+------------------------------------------+
| Microsoft SQL Server 2008  | Mssql2008Dialect | 对应Microsoft SQL Server 2008版本         |
+----------------------------+------------------+------------------------------------------+
| Microsoft SQL Server       | MssqlDialect     | 对应Microsoft SQL Server 2012或者更高版本  |
+----------------------------+------------------+------------------------------------------+
| MySQL                      | MySqlDialect     |                                          |
+----------------------------+------------------+------------------------------------------+
| Oracle Database            | OracleDialect    |                                          |
+----------------------------+------------------+------------------------------------------+
| PostgreSQL                 | PostgresDialect  |                                          |
+----------------------------+------------------+------------------------------------------+
| SQLite                     | SqliteDialect    |                                          |
+----------------------------+------------------+------------------------------------------+

.. note::

   该项目是必须设定的。

面向日志输出库的适配器
------------------------------

通过  ``getJdbcLogger`` 返回 ``JdbcLogger`` 。
 ``JdbcLogger`` 是一个处理与数据库访问相关的日志的接口。。
实现类如下。

* org.seasar.doma.jdbc.UtilLoggingJdbcLogger

``UtilLoggingJdbcLogger`` 使用 ``java.util.logging`` 记录器实现、
默认使用该日志处理。

SQL文件的仓库
-----------------------

通过  ``getSqlFileRepository`` 方法来返回 ``SqlFileRepository`` 。
``SqlFileRepository`` 是一个处理 SQL 文件存储库的接口。。
实现类如下。

* org.seasar.doma.jdbc.GreedyCacheSqlFileRepository
* org.seasar.doma.jdbc.NoCacheSqlFileRepository

``GreedyCacheSqlFileRepository`` 分析读取的SQL文件的内容，
在内存允许的范围内最大限度缓存分析的内容。

``NoCacheSqlFileRepository`` 会在每次读取时解析，不会缓存到内存中。

在内存使用严重受限或有大量 SQL 文件处理的环境中，
选择合适的使用缓存算法创建的实现类。

默认情况下使用 ``GreedyCacheSqlFileRepository`` 。

关联属性为REQUIRES的事物
-------------------------------------------

通过 ``getRequiresNewController`` 方法来获得 ``RequiresNewController``。
 ``RequiresNewController`` 接口用来 控制拥有 REQUIRES_NEW 属性的事务。

该接口只在使用 ``@TableGenerator`` 自动生成标识符的时候使用。
不使用 ``@TableGenerator`` 的时候，不需要考虑该项目。
又或者使用的事务数量不会产生分配标识符的更新锁问题的时候，也不需要设定。


默认的实现不会做任何处理。

类的加载方法
------------------

``ClassHelper`` 通过 ``getClassHelper`` 方法得到。

``ClassHelper`` 是一个对于应用服务器和框架在类加载相关的部分的差异进行抽象化的接口。

默认的实现使用 ``java.lang.Class.forName(name)``  来加载类。

异常信息中包含的SQL类型
-------------------------------

请使用 ``getExceptionSqlLogType`` 方法返回 ``SqlLogType``，
它表示异常信息中包含的SQL类型。
该值决定了Doma抛出的异常中包含哪种类型的SQL。

默认的实现包含了已经格式化的 SQL。

未知列的处理器
----------------------

请使用 ``getUnknownColumnHandler`` 方法来返回 ``UnknownColumnHandler`` 。
 ``UnknownColumnHandler`` 处理器在将 :doc:`query/select` 的结果映射到 :doc:`entity` 的时候存在实体类不知道的列的时候执行。

默认抛出 ``UnknownColumnException`` 异常。

表和列名的命名规约的控制
--------------------------------------------
请使用``getNaming`` 方法来返回 ``Naming`` 。

``Naming`` 使用来控制在 ``@Entity`` 的name属性中指定（或者不指定）的 ``NamingType``是如何应用的接口。

使用该接口，即使没有给各个实体类指定 ``NamingType`` 
也可以从实体类和属性名称中解析数据库表名称和列名称。

使用 ``Naming`` 条件如下。

* 没有指定 ``@Table`` 和 ``@Column`` 的 ``name`` 属性的值。

为了实现一般用例的实现，是在 ``Naming`` 的 ``static`` 成员里定义。

默认使用、 ``Naming.NONE`` 。
该实现使用实体类中指定的 ``NamingType`` ， 
如果没有指定，则不会适用任何规约。

比如说，在没有指定却又想使用蛇形大写的方式来表现的时候，请使用 ``Naming.SNAKE_UPPER_CASE`` 。

映射的键的命名规约的控制
----------------------------------

请使用  ``getMapKeyNaming`` 方法来获得 ``MapKeyNaming`` 。

在将检索结果映射到  ``java.util.Map<String, Object>`` 的场合，执行 ``MapKeyNaming`` 。

默认会适用 ``@Select`` 等属性 ``mapKeyNaming`` 指定的规约。

本地事务管理器
------------------------------------

请使用 ``getTransactionManager`` 方法来获得 ``LocalTransactionManager`` 。
``getTransactionManager`` 方法默认会抛出异常
``UnsupportedOperationException`` 。

.. note::

  虽然该项目不是必须设定的，但是如果想要使用
  ``org.seasar.doma.jdbc.tx.TransactionManager`` 接口的事务的时候请务必设定。
  设定的方法请参照 :doc:`transaction` 。

SQL标识符的追加
------------------------------------

请使用 ``getCommenter`` 方法来获得 ``Commenter`` 。
``Commenter`` 是用来将SQL的识别符（用于指定发布SQL的位置的字符串）作为SQL注释追加的接口。

有如下实现类。

* org.seasar.doma.jdbc.CallerCommenter

``CallerCommenter`` 使用SQL的调用者的类名称和方法名称作为标识符。

默认实现不会追加标识符

Command 的实现
--------------

请使用  ``getCommandImplementors`` 方法来获得 ``CommandImplementors`` 。
实现 ``CommandImplementors`` 类，可以定制 :doc:`query/index` 的执行方式。

比如说，直接调用JDBC的API。

Query 的实现
------------

请使用  ``QueryImplementors`` 方法来获得 ``getQueryImplementors`` 。
实现``QueryImplementors`` 类，可以定制 :doc:`query/index` 的内容。

比如说，重写一部分自动生成的SQL。

超时
------------

请用 ``getQueryTimeout`` 方法获得表示查询超时（秒）的 ``int`` 数。
该值作为所有 :doc:`query/index` 的默认值来使用。

最大件数
--------

使用 ``getMaxRows`` 方法获得表示SELECT能够获得最大行数的 ``int`` 数。
该值作为所有 :doc:`query/select` 的默认值来使用。

捕获数量
--------------

使用 ``getFetchSize`` 方法获得表示SELECT能够获得最大行数的 ``int`` 数。
该值作为所有 :doc:`query/select` 的默认值来使用。

批处理数量
------------

使用 ``getBatchSize`` 方法来获得表示批处理数量的 ``int`` 。
该值作为 :doc:`query/batch-insert` 、:doc:`query/batch-update` 、:doc:`query/batch-delete`
的默认值来使用。

获取实体监听器
--------------------------

请使用 ``getEntityListenerProvider`` 方法 来获得 ``EntityListenerProvider`` 。

``EntityListenerProvider`` 的 ``get`` 方法是用来返回 ``EntityListener`` 实现类的 ``Class`` 和 ``EntityListener`` 实现类的实例的 ``Supplier`` 作为参数的 ``EntityListener`` 的实例。默认的实现通过执行 ``Supplier.get`` 方法来获得实例。


如果想要定制从DI容器中获取 ``EntityListener`` 实现类的实例的时候，请实现 ``EntityListenerProvider`` 类
将其设置为使用 ``getEntityListenerProvider`` 方法来获得它的实例。


加载JDBC驱动程序
=====================

.. _service provider: http://docs.oracle.com/javase/7/docs/technotes/guides/jar/jar.html#Service%20Provider
.. _tomcat driver: http://tomcat.apache.org/tomcat-7.0-doc/jndi-datasource-examples-howto.html#DriverManager,_the_service_provider_mechanism_and_memory_leaks

如果传递了类路径，则JDBC驱动程序将根据
`服务提供者机制 <service provider_>`_ 自动加载。

.. warning::

  根据执行环境的不同，JDBC 驱动程序可能不会自动加载。
  例如，在Tomcat上，放置在WEB-INF / lib中的
  `JDBC 驱动程序不会自动加载 <tomcat driver_>`_ 。
  在这样的环境下，请采用适合该环境的方法。
  例如，为了在Tomcat上运行，根据上面链接里的操作使用
  ``ServletContextListener`` 进行加载和卸载。

定义和用法示例
============

简单
--------

一个简单的定义适用于以下情况。

* 不使用DI容器管理
* 使用本地事务

实现的示例。

.. code-block:: java

  @SingletonConfig
  public class AppConfig implements Config {

      private static final AppConfig CONFIG = new AppConfig();

      private final Dialect dialect;

      private final LocalTransactionDataSource dataSource;

      private final TransactionManager transactionManager;

      private AppConfig() {
          dialect = new H2Dialect();
          dataSource = new LocalTransactionDataSource(
                  "jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null);
          transactionManager = new LocalTransactionManager(
                  dataSource.getLocalTransaction(getJdbcLogger()));
      }

      @Override
      public Dialect getDialect() {
          return dialect;
      }

      @Override
      public DataSource getDataSource() {
          return dataSource;
      }

      @Override
      public TransactionManager getTransactionManager() {
          return transactionManager;
      }

      public static AppConfig singleton() {
          return CONFIG;
      }
  }

.. note::

  请不要忘了在类上使用注释 ``@SingletonConfig`` 。

使用的例子。
将已经定义的设定类指定为@Dao。

.. code-block:: java

  @Dao(config = AppConfig.class)
  public interface EmployeeDao {

      @Select
      Employee selectById(Integer id);
  }


高级
------------------

高级的定义适用于以下场合。

* 使用DI容器来管理单例
* 用DI容器和应用服务器提供的事务管理功能

实现的示例。
``dialect`` 和 ``dataSource`` 应该由DI容器注入。

.. code-block:: java

  public class AppConfig implements Config {

      private Dialect dialect;

      private DataSource dataSource;

      @Override
      public Dialect getDialect() {
          return dialect;
      }

      public void setDialect(Dialect dialect) {
          this.dialect = dialect;
      }

      @Override
      public DataSource getDataSource() {
          return dataSource;
      }

      public void setDataSource(DataSource dataSource) {
          this.dataSource = dataSource;
      }
  }

使用的例子。
通过DI容器注入定义的配置类的实例。

.. code-block:: java

  @Dao
  @AnnotateWith(annotations = {
      @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = javax.inject.Inject.class),
      @Annotation(target = AnnotationTarget.CONSTRUCTOR_PARAMETER, type = javax.inject.Named.class, elements = "\"config\"") })
  public interface EmployeeDao {

      @Select
      Employee selectById(Integer id);
  }

在上面的例子中 ``@AnnotateWith`` 注释中的描述，需要在每个Dao里进行重复描述。
如果不想重复描述，请首先在任意的一个注释上使用 ``@AnnotateWith`` 进行描述，然后在Dao上使用该
注释即可。

.. code-block:: java

  @AnnotateWith(annotations = {
      @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = javax.inject.Inject.class),
      @Annotation(target = AnnotationTarget.CONSTRUCTOR_PARAMETER, type = javax.inject.Named.class, elements = "\"config\"") })
  public @interface InjectConfig {
  }

.. code-block:: java

  @Dao
  @InjectConfig
  public interface EmployeeDao {

      @Select
      Employee selectById(Integer id);
  }


