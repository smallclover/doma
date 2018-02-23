.. Doma documentation master file, created by
sphinx-quickstart on Thu Feb 13 12:43:15 2014.
You can adapt this file completely to your liking, but it should at least
contain the root `toctree` directive.

.. image:: images/doma.png
:height: 200px
   :width: 200px
       :align: right
       :target: https://github.com/domaframework/doma

Welcome to Doma
===============

Doma 是一个控制Java DB访问的框架。

虽然现在Doma的版本有版本 1（Doma） 和版本 2（Doma2），
但是这个文档面向的是 ** 版本 2** 即Doma2。

Doma 2 具有一下特征。

* 通过使用注释处理在 **编译时** 进行代码的生成和校验。
* 可以对操作数据库上列的值的Java对象进行映射处理。
* 弄偶使用被称为2-way SQL SQL模板。
* 使用Java 8 的 ``java.time.LocalDate`` 和  ``java.util.Optional`` 以及 ``java.util.stream.Stream`` 。
* 不依赖JRE 以外的库。

本文档由多个章节构成。

* `User Documentation`_
* `Developer Documentation`_
* `About Doma`_
* `Links`_

User Documentation
==================

.. toctree::
:maxdepth: 2

       getting-started
       config
       basic
       domain
       embeddable
       entity
       dao
       query/index
       query-builder/index
       sql
       expression
       transaction
       annotation-processing
       build
       lombok-support
       kotlin-support

Developer Documentation
=======================

.. toctree::
:maxdepth: 2

       development
       integration-test

About Doma
==========

.. toctree::
:maxdepth: 1

       release-notes
       changelog
       faq

Links
=====

* `GitHub repository <https://github.com/domaframework/doma>`_
* `JavaDoc <http://www.javadoc.io/doc/org.seasar.doma/doma>`_
* `Examples <https://github.com/domaframework/simple-examples>`_
* `Doma-Gen <http://doma-gen.readthedocs.org/>`_
* `Doma 1 <http://doma.seasar.org/>`_

