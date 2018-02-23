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
  ``@Dao`` 注释的interface的实现类生成之后存放的package。
  在指定了值的场合，优先于doma.dao.subpackage指定的值。
  默认的值是、与 ``@Dao`` 注释的interface同一个package下。

doma.dao.subpackage
  ``@Dao`` 注释的interface的实现类生成之后存放的subpackage。
  只有在doma.dao.package的值没有被指定的时候。
  ``@Dao`` 注释的interface位于 ``example.dao`` 下、载这里指定的值是 ``impl`` 的场合、
  生成的实现类位于 ``example.dao.impl`` 下。

doma.dao.suffix
  ``@Dao`` 注释的interface的实现类的名字的后缀。
  ``@Dao`` 注释的interface的名字为 ``EmployeeDao`` 这里指定的值为 ``Bean`` 的场合、
  生成的类的名字为 ``EmployeeDaoBean`` 。
  默认的值是 ``Impl`` 。

doma.debug
  是否以log的形式输出注释处理的debug信息
  ``true`` 的时候，输出debug log。
  默认值是、 ``false`` 。

doma.domain.converters
  任意の型と基本型を相互変換する ``DomainConverter`` のプロバイダとなるクラスの完全修飾名のカンマ区切り。
  クラスは ``org.seasar.doma.DomainConverters`` によって注釈されていないければいけない。

doma.entity.field.prefix
  ``@Entity`` が注釈されたクラスごとに生成されるタイプクラスで使用される。
  タイプクラスのpublicなフィールド名のプレフィックス。
  ``none`` を指定するとプレフィックスを使用しないという意味になる。
  デフォルトの値は、 ``$`` 。

doma.expr.functions
  表示注释中可用函数集的类的完全限定名称。
  他它必须是``org.seasar.doma.expr.ExpressionFunctions`` 类的子类型
  默认值是、 ``org.seasar.doma.expr.ExpressionFunctions`` 。

doma.resources.dir
  SQL文件等资源文件输出的目录
  使用绝对路径
  如果没有指定，那默认使用class文件输出的目录。

doma.sql.validation
  执行SQL文件存在检查和SQL comment语法检查的时候设为 ``true`` 。
  不执行的时候设为 ``false`` 。
  默认值是、 ``true`` 。

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
  指定option设置文件的路径
  默认值、 ``doma.compile.config``。

Eclipse
=======

project的「Properties」-「Java Compiler」-「Annotation Processing」的选项里进行设置。

javac
=====

根据-A option进行设置。
详细的情况请参照javac的文档。

Gradle
======

指定为 ``compileJava.options.compilerArgs``  

.. code-block:: groovy

  compileJava.options.compilerArgs = ['-Adoma.dao.subpackage=impl', '-Adoma.dao.suffix=Impl']

配置文件
==================

默认在 ``main/resources/doma.compile.config`` 里进行了配置的描述，所以不需要对build tool的每个option进行设定。
描述的格式和properties的格式是相同的。
另外，如果配置文件发生冲突，优先使用build tool的option。
