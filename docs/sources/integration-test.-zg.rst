===========
结合测试
===========

.. contents:: 目录
   :depth: 3

.. note::

  本页面是为Doma开发人员编写的。

结合测试的目的有两个

* 测试 Eclipse 和 javac 注释处理的行为
* 测试访问数据库的相关机能

结合测试使用 RDBMS 来进行测试。
默认使用的 RDBMS 是 H2 Database Engine 。

source code
============

.. code-block:: bash

  $ git clone https://github.com/domaframework/doma-it.git

build
======

.. code-block:: bash

  $ ./gradlew build

Eclipse
=======

能够生成 Eclipse 的设定文件。

.. code-block:: bash

  $ ./gradlew eclipse

工厂路径设置也已完成。（翻译有待修正）

Continuous Integration
======================

使用 `Travis CI`_ 

  https://travis-ci.org/domaframework/doma-it

使用以下的数据库进行测试

* H2 Database Engine
* HSQLDB
* MySQL
* PostgreSQL


.. _Travis CI: http://docs.travis-ci.com/
