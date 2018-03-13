===========
Doma 的开发
===========

.. contents:: 目录
      :depth: 3

.. note::

  本页所述内容是面向 Doma 的开发者的。

源代码
============

.. code-block:: bash

  $ git clone https://github.com/domaframework/doma.git

编译
======

.. code-block:: bash

  $ ./gradlew build

Maven ローカルリポジトリへのインストール
----------------------------------------

.. code-block:: bash

  $ ./gradlew build install

.. note::

  如果想要对本地修改的代码进行 :doc:`integration-test` ，
  需要提前在本地的 Maven 仓库中提前安装 Doma 。

Eclipse
=======

能够生成 Eclipse 的配置文件。

.. code-block:: bash

  $ ./gradlew eclipse


Continuous Integration
======================

Continuous Integration 的执行需要使用 `Travis CI`_ 。

  https://travis-ci.org/domaframework/doma

文档
============

文档的制作需要使用 `Sphinx`_ 。

搭建环境
--------

.. code-block:: bash

   $ cd docs
   $ pip install -r requirements.txt

文档的生成
------------------

.. code-block:: bash

   $ make dirhtml

LiveReload
----------

在Google Chrome中安装 `LiveReload`_ 后可以
实时的使用浏览器正确检查文档。

激活此扩展后，启动服务器。

.. code-block:: bash

   $ python server.py

然后就可以在以下的 URL 确认一下文档

   http://localhost:5500/_build/dirhtml/


.. _Travis CI: http://docs.travis-ci.com/
.. _LiveReload: https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei
.. _Sphinx: http://sphinx-doc.org/

