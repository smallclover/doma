==================
设定
==================

.. contents:: 目录
      :depth: 3

Domaに対する設定は、 ``Confing`` インタフェースの実装クラスで表現します。
Doma的设置，通过 ``Confing`` 接口的实现类来表示。

设置项目
=================

对于必须的配置和未明确指示的配置将会使用默认值

数据源
----------------

``DataSource`` を ``getDataSource`` メソッドで返してください。
ローカルトランザクションを利用する場合は、 ``LocalTransactionDataSource`` を返してください。

.. note::

   この項目は設定必須です。

データソースの名前
------------------

データソース名をあらわす ``String`` を ``getDataSourceName`` メソッドで返してください。
データソース名は、複数のデータソースを利用する環境で重要です。
データソース名はデータソースごとに自動生成される識別子を区別するために使用されます。
複数データソースを利用する場合は、それぞれ異なる名前を返すようにしてください。

デフォルトの実装では、 ``Config`` の実装クラスの完全修飾名が使用されます。

データベースの方言
--------------------------

``Dialect`` を ``getDialect`` メソッドで返してください。
``Dialect`` はRDBMSの方言を表すインタフェースです。
``Dialect`` には次のものがあります。

+----------------------------+------------------+--------------------------------------+
| データベース               | Dialect          | 説明                                 |
+============================+==================+======================================+
| DB2                        | Db2Dialect       |                                      |
+----------------------------+------------------+--------------------------------------+
| H2 Database Engine 1.2.126 | H212126Dialect   | H2 Database Engine 1.2.126で稼動     |
+----------------------------+------------------+--------------------------------------+
| H2 Database                | H2Dialect        | H2 Database Engine 1.3.171以降に対応 |
+----------------------------+------------------+--------------------------------------+
| HSQLDB                     | HsqldbDialect    |                                      |
+----------------------------+------------------+--------------------------------------+
| Microsoft SQL Server 2008  | Mssql2008Dialect | Microsoft SQL Server 2008に対応      |
+----------------------------+------------------+--------------------------------------+
| Microsoft SQL Server       | MssqlDialect     | Microsoft SQL Server 2012以降に対応  |
+----------------------------+------------------+--------------------------------------+
| MySQL                      | MySqlDialect     |                                      |
+----------------------------+------------------+--------------------------------------+
| Oracle Database            | OracleDialect    |                                      |
+----------------------------+------------------+--------------------------------------+
| PostgreSQL                 | PostgresDialect  |                                      |
+----------------------------+------------------+--------------------------------------+
| SQLite                     | SqliteDialect    |                                      |
+----------------------------+------------------+--------------------------------------+

.. note::

   この項目は設定必須です。

ログ出力ライブラリへのアダプタ
------------------------------

``JdbcLogger`` を ``getJdbcLogger`` メソッドで返してください。
``JdbcLogger`` はデータベースアクセスに関するログを扱うインタフェースです。
実装クラスには次のものがあります。

* org.seasar.doma.jdbc.UtilLoggingJdbcLogger

``UtilLoggingJdbcLogger`` は ``java.util.logging`` のロガーを使用する実装で、
デフォルトで使用されます。

SQLファイルのリポジトリ
-----------------------

``SqlFileRepository`` を ``getSqlFileRepository`` メソッドで返してください。
``SqlFileRepository`` は SQL ファイルのリポジトリを扱うインタフェースです。
実装クラスには次のものがあります。

* org.seasar.doma.jdbc.GreedyCacheSqlFileRepository
* org.seasar.doma.jdbc.NoCacheSqlFileRepository

``GreedyCacheSqlFileRepository`` は、読み込んだSQLファイルの内容をパースし、
その結果をメモリが許す限り最大限にキャッシュします。

``NoCacheSqlFileRepository`` は、一切キャッシュを行いません。
毎回、SQLファイルからSQLを読み取りパースします。

メモリの利用に厳しい制限がある環境や、扱うSQLファイルが膨大にある環境では、
適切なキャッシュアルゴリズムをもった実装クラスを作成し使用してください。

デフォルトでは ``GreedyCacheSqlFileRepository`` が使用されます。

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

``MapKeyNaming`` を ``getMapKeyNaming`` メソッドで返してください。
``MapKeyNaming`` は検索結果を ``java.util.Map<String, Object>`` にマッピングする場合に実行されます。

デフォルトでは、 ``@Select`` などの ``mapKeyNaming`` 要素に指定された規約を適用します。

ローカルトランザクションマネージャー
------------------------------------

``LocalTransactionManager`` を ``getTransactionManager`` メソッドで返してください。
``getTransactionManager`` メソッドは、デフォルトで
``UnsupportedOperationException`` をスローします。

.. note::

  この項目は設定必須ではありませんが、
  ``org.seasar.doma.jdbc.tx.TransactionManager`` のインタフェースでトランザクションを利用したい場合は設定してください。
  設定方法については :doc:`transaction` を参照してください。

SQLの識別子の追記
------------------------------------

``Commenter`` を ``getCommenter`` メソッドで返してください。
``Commenter`` はSQLの識別子（SQLの発行箇所等を特定するための文字列）をSQLコメントとして追記するためのインタフェースです。

実装クラスには次のものがあります。

* org.seasar.doma.jdbc.CallerCommenter

``CallerCommenter`` は、SQLの呼び出し元のクラス名とメソッド名を識別子として使用します。

デフォルトの実装では、 識別子を追記しません。

Command の実装
--------------

``CommandImplementors`` を ``getCommandImplementors`` メソッドで返してください。
``CommandImplementors`` を実装すると :doc:`query/index` の実行をカスタマイズできます。

たとえば、 JDBC の API を直接呼び出すことができます。

Query の実装
------------

``QueryImplementors`` を ``getQueryImplementors`` メソッドで返してください。
``QueryImplementors`` を実装すると :doc:`query/index` の内容をカスタマイズできます。

たとえば、自動生成される SQL の一部を書き換え可能です。

タイムアウト
------------

クエリタイムアウト（秒）をあらわす ``int`` を ``getQueryTimeout`` メソッドで返してください。
この値はすべての :doc:`query/index` においてデフォルト値として使われます。

最大件数
--------

SELECT時の最大行数をあらわす ``int`` を ``getMaxRows`` メソッドで返します。
この値はすべての :doc:`query/select` においてデフォルト値として使われます。

フェッチサイズ
--------------

SELECT時のフェッチサイズをあらわす ``int`` を ``getFetchSize`` メソッドで返します。
この値はすべての :doc:`query/select` においてデフォルト値として使われます。

バッチサイズ
------------

バッチサイズをあらわす ``int`` を ``getBatchSize`` メソッドで返します。
この値は :doc:`query/batch-insert` 、:doc:`query/batch-update` 、:doc:`query/batch-delete`
においてデフォルト値として使われます。

エンティティリスナーの取得
--------------------------

``EntityListenerProvider`` を ``getEntityListenerProvider`` メソッドで返して下さい。

``EntityListenerProvider`` の ``get`` メソッドは ``EntityListener`` 実装クラスの ``Class`` と ``EntityListener`` 実装クラスのインスタンスを返す ``Supplier``
を引数に取り ``EntityListener`` のインスタンスを返します。
デフォルトの実装では ``Supplier.get`` メソッドを実行して得たインスタンスを返します。

``EntityListener`` 実装クラスのインスタンスをDIコンテナから取得したいなど、
インスタンス取得方法をカスタマイズする場合は ``EntityListenerProvider`` を実装したクラスを作成し、
``getEntityListenerProvider`` メソッドでそのインスタンスを返すよう設定してください。

JDBC ドライバのロード
=====================

.. _service provider: http://docs.oracle.com/javase/7/docs/technotes/guides/jar/jar.html#Service%20Provider
.. _tomcat driver: http://tomcat.apache.org/tomcat-7.0-doc/jndi-datasource-examples-howto.html#DriverManager,_the_service_provider_mechanism_and_memory_leaks

クラスパスが通っていれば JDBC ドライバは
`サービスプロバイダメカニズム <service provider_>`_ により自動でロードされます。

.. warning::

  実行環境によっては、 JDBC ドライバが自動でロードされないことがあります。
  たとえば Tomcat 上では WEB-INF/lib に配置された
  `JDBC ドライバは自動でロードされません <tomcat driver_>`_ 。
  そのような環境においては、その環境に応じた適切は方法を採ってください。
  たとえば Tomcat 上で動作させるためのには、上記のリンク先の指示に従って
  ``ServletContextListener`` を利用したロードとアンロードを行ってください。

定義と利用例
============

シンプル
--------

シンプルな定義は次の場合に適しています。

* DIコンテナで管理しない
* ローカルトランザクションを使用する

実装例です。

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

  クラスに ``@SingletonConfig`` を注釈するのを忘れないようにしてください。

利用例です。
定義した設定クラスは、@Daoに指定します。

.. code-block:: java

  @Dao(config = AppConfig.class)
  public interface EmployeeDao {

      @Select
      Employee selectById(Integer id);
  }


アドバンスド
------------------

アドバンスドな定義は次の場合に適しています。

* DIコンテナでシングルトンとして管理する
* DIコンテナやアプリケーションサーバーが提供するトランザクション管理機能を使う

実装例です。
``dialect`` と ``dataSource`` はDIコンテナによってインジェクションされることを想定しています。

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

利用例です。
定義した設定クラスのインスタンスがDIコンテナによってインジェクトされるようにします。

.. code-block:: java

  @Dao
  @AnnotateWith(annotations = {
      @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = javax.inject.Inject.class),
      @Annotation(target = AnnotationTarget.CONSTRUCTOR_PARAMETER, type = javax.inject.Named.class, elements = "\"config\"") })
  public interface EmployeeDao {

      @Select
      Employee selectById(Integer id);
  }

上記の例では ``@AnnotateWith`` の記述をDaoごとに繰り返し記述する必要があります。
繰り返しを避けたい場合は、任意のアノテーションに一度だけ ``@AnnotateWith`` を記述し、
Daoにはそのアノテーションを注釈してください。

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


