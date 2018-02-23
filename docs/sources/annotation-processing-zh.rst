============================
注释处理
============================

.. contents:: 目录
   :depth: 3

通过利用 `Pluggable Annotation Processing API <https://www.jcp.org/en/jsr/detail?id=269>`_ 
能够在 **编译时** 进行源代码的生成和校验。

在Doma里通过利用该API（JSR-269），使我们能够处理被 ``@Entity`` 或 ``@Dao`` 等注解的class或者是interface，或者生成必要的class，
又或者，对被注释的class或者interface进行验证，如果源代码没有遵守Doma的约定，那么会在IDE（eclipse等）的editor里表示出error message，或者在javac执行的时候。在console上表示出来。

现在对Doma提供的option类型和build tool的设置方法进行说明。

option
==================

doma.dao.package
  ``@Dao`` が注釈されたインタフェースの実装クラスが生成されるパッケージ。
  何らかの値を指定した場合、doma.dao.subpackageの指定よりも優先される。
  デフォルトの値は、 ``@Dao`` が注釈されたインタフェースと同じパッケージ。

doma.dao.subpackage
  ``@Dao`` が注釈されたインタフェースの実装クラスが生成されるサブパッケージ。
  doma.dao.packageに値を指定していない場合にのみ有効。
  ``@Dao`` が注釈されたインタフェースのパッケージが ``example.dao`` で、ここに指定した値が ``impl`` の場合、
  生成されるクラスのパッケージは ``example.dao.impl`` となる。

doma.dao.suffix
  ``@Dao`` が注釈されたインタフェースの実装クラスの名前のサフィックス。
  ``@Dao`` が注釈されたインタフェースの単純名が ``EmployeeDao`` で、ここに指定した値が ``Bean`` の場合、
  生成されるクラスの単純名は ``EmployeeDaoBean`` となる。
  デフォルトの値は ``Impl`` 。

doma.debug
  注釈処理のデバッグ情報をログ出力するかどうか。
  ``true`` の場合、ログ出力を行う。
  デフォルトの値は、 ``false`` 。

doma.domain.converters
  任意の型と基本型を相互変換する ``DomainConverter`` のプロバイダとなるクラスの完全修飾名のカンマ区切り。
  クラスは ``org.seasar.doma.DomainConverters`` によって注釈されていないければいけない。

doma.entity.field.prefix
  ``@Entity`` が注釈されたクラスごとに生成されるタイプクラスで使用される。
  タイプクラスのpublicなフィールド名のプレフィックス。
  ``none`` を指定するとプレフィックスを使用しないという意味になる。
  デフォルトの値は、 ``$`` 。

doma.expr.functions
  式コメントで利用可能な関数群を表すクラスの完全修飾名。
  ``org.seasar.doma.expr.ExpressionFunctions`` のサブタイプでなければいけない。
  デフォルトの値は、 ``org.seasar.doma.expr.ExpressionFunctions`` 。

doma.resources.dir
  SQLファイルなどリソースファイルの出力先ディレクトリ。
  絶対パスで指定する。
  指定しない場合はクラスファイルの出力先ディレクトリが使われる。

doma.sql.validation
  SQLファイルの存在チェックとSQLコメントの文法チェックを行う場合は ``true`` 。
  行わない場合は ``false`` 。
  デフォルトの値は、 ``true`` 。

doma.version.validation
  注釈処理によるソースコード生成で利用したDomaのバージョンと実行時のDomaのバージョンが同じであることを
  チェックする場合は ``true`` 。
  しない場合は ``false`` 。
  Domaのあるバージョンで生成されたコードを含むライブラリを作成する場合に ``false`` を指定してビルドすると、
  そのライブラリの再利用性が高まります。
  ライブラリが依存するDomaのバージョンとは異なるバージョンのDomaで実行できるからです
  （Domaのバージョンに互換性がある限りにおいて）。
  デフォルトの値は、 ``true`` 。

doma.config.path
  オプションの設定ファイルを置く場所の指定。
  デフォルトの値は、 ``doma.compile.config``。

Eclipse
=======

プロジェクトの「Properties」-「Java Compiler」-「Annotation Processing」の項目でオプションを登録します。

javac
=====

-Aオプションにより登録します。
詳細はjavacのドキュメントを参照してください。

Gradle
======

``compileJava.options.compilerArgs`` に指定します。

.. code-block:: groovy

  compileJava.options.compilerArgs = ['-Adoma.dao.subpackage=impl', '-Adoma.dao.suffix=Impl']

配置文件
==================
描述
默认在 ``main/resources/doma.compile.config`` 里进行了配置的描述，所以不需要对build tool的每个option进行设定。
描述的格式和properties的格式是相同的。
デフォルトでは ``main/resources/doma.compile.config`` ファイルにオプションを記述しておくことで、
ビルドツールごとのオプションの設定を利用する必要がなくなります。
記述形式はプロパティファイルと同様です。
なお、設定がバッティングした場合、ビルドツールごとのオプションの設定が優先されます。
