===========================
Doma 2 的主要变更点
===========================

.. contents:: 目录
      :depth: 3

下面将展示从 Doma 1.36.0 开始的主要的变更点。

整体变化
====

* 面向Java 8 ,Java7以前的版本，包括Java7将无法正常使用。
* 删除了 deprecated 的 API 。

设定
====

* 追加了 ``Config`` 的实现类作为单例来使用的注解 ``@SingletonConfig`` 。
* 追加了注解 ``TransactionManager`` 来简化事务处理。
* 为了能够定制查询追加注解 ``QueryImplementors`` 和 ``CommandImplementors`` 。
* 为了能够定制结果集中包含未知列的行为添加了注解 ``UnknownColumnHandler`` 。
* 追加了注解 ``MapKeyNaming`` 为了能够解决映射键的命名规约的统一。
* 删除了注解 ``DomaAbstractConfig`` 。
  这该类提供的实现由 ``Config`` 的默认方法中提供。
* 删除了注解 ``DefaultClassHelper`` 。
  该类提供的实现由 ``ClassHelper`` 的默认方法提供。
* 删除了注解 ``NullRequiresNewController`` 。
  该类提供的实现由 ``RequiresNewController`` 默认方法提供。

基本型
======

* ``Object`` 类型现在被识别为基本类型。
* ``java.time.LocalDate`` 类型现在被识别为基本类型。
* ``java.time.LocalTime`` 类型现在被识别为基本类型。
* ``java.time.LocalDateTime`` 类型现在被识别为基本类型。

域类
==============

* 在使用注解 ``@Domain`` 编写域类的时候，默认不接受 ``null`` 。
  如果想要接受 ``null`` ，需要在 ``@Domain`` 的 ``acceptNull`` 属性上指定 ``true`` 。

实体类
==================

* 实验性质的不可变实体得到了正式支持。
* 现在可以将属性的基本型和域类的元素定义为 ``java.util.Optional`` 。
* 属性现在可以定义为 ``java.util.OptionalInt`` 。
* 属性现在可以定义为 ``java.util.OptionalLong`` 。
* 属性现在可以定义为 ``java.util.OptionalDouble`` 。

Dao接口
=================

* 现在可以将参数和返回类型定义为 ``java.util.Optional`` 。
* 现在可以将参数和返回类型定义为 ``java.util.OptionalInt`` 。
* 现在可以将参数和返回类型定义为 ``java.util.OptionalLong`` 。
* 现在可以将参数和返回类型定义为 ``java.util.OptionalDouble`` 。
* 现在可以使用 ``java.util.stream.Stream`` 来处理搜索结果。
* 现在可以使用 ``java.util.stream.Collector`` 处理搜索结果。
* 现在可以使用默认方法来代替 ``@ Delegate`` 。
* 禁止使用 ``IterationCallback`` 进行搜索。
  使用 ``java.util.stream.Stream`` 来代替。
SQL相关的变更
===============

* 列表扩展注释已被引入。

表达式语言
======

* 删除内置函数``contains``。

注释处理
========

* 给出了命名空间``doma``作为注释处理设置的名称。
  为了避免跟 Doma 以外的注释处理的设置以及键的重复。

编译
======

* 在 Maven 的中央仓库提供了 Doma 的 jar 。


