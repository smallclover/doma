==================
事务(transaction)
==================

.. contents:: 目录
   :depth: 3

Doma支持本地事务。
本篇文档将会对本地事务的设置方法和利用方法进行解释说明。
グローバルトランザクションを使用したい場合は、JTA（Java Transaction API）
如果使用全局事务的时候，比如JTA（Java Transaction API），请使用实现了相关接口的框架或者应用服务器。

设定
====

执行本地事务的时候要满足以下条件。

* 通过``Config`` 的 ``getDataSource`` 方法来返回 ``LocalTransactionDataSource`` 
* 上記の ``LocalTransactionDataSource`` をコンストラクタで受けて ``LocalTransactionManager`` を生成する
* 上記の ``LocalTransactionManager`` の管理下でデータベースアクセスを行う

``LocalTransactionManager`` 的生成和取得的方法有很多种，最简单的方法是，在
``Config`` 的实现类的构造函数中生成，然后将 ``Config`` 的实现类设置为单例。

实现的例子。

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

  通过为类指定 ``@SingletonConfig`` 注解，来表示该类是单例。

使用的例子
======

`设定`_ 展示的 ``AppConfig`` 类作为下面的Dao接口的注释来使用。

.. code-block:: java

  @Dao(config = AppConfig.class)
  public interface EmployeeDao {
      ...
  }

出现在下面的代码示例中的 ``dao`` 是上面描述的类的实例。

事务的开始和结束
----------------------------

使用 ``TransactionManager`` 以下三个方法中的任意一个来开启事务。

* required
* requiresNew
* notSupported

将事务作为lambda表达式传递。

.. code-block:: java

  TransactionManager tm = AppConfig.singleton().getTransactionManager();

  tm.required(() -> {
      Employee employee = dao.selectById(1);
      employee.setName("hoge");
      employee.setJobType(JobType.PRESIDENT);
      dao.update(employee);
  });

如果lambda表达式成功，则提交事务。
如果lambda表达式抛出异常，则回滚事务。

显式回滚
--------------------

除了通过抛出意外的方法来回滚事务，你还可以通过调用 ``setRollbackOnly`` 方法来回滚事务。
.. code-block:: java

  TransactionManager tm = AppConfig.singleton().getTransactionManager();

  tm.required(() -> {
      Employee employee = dao.selectById(1);
      employee.setName("hoge");
      employee.setJobType(JobType.PRESIDENT);
      dao.update(employee);
      // 标记为回滚
      tm.setRollbackOnly();
  });

存档点（Save Point）
--------------

通过使用存档点这一机能，可以实现事务中特定变更取消这一功能。

.. code-block:: java

  TransactionManager tm = AppConfig.singleton().getTransactionManager();

  tm.required(() -> {
      // 检索以及更新
      Employee employee = dao.selectById(1);
      employee.setName("hoge");
      dao.update(employee);

      // 做成存档点
      tm.setSavepoint("beforeDelete");

      // 删除
      dao.delete(employee);

      // 返回存档点（取消上面的删除）
      tm.rollback("beforeDelete");
  });

