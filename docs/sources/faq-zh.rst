==================
FAQ
==================

.. contents:: 目录
   :depth: 3

一般问题
============

Doma是什么意思？
------------------------------

**D** omain **O** riented Database **MA** pping Framework 的缩写。

注释处理是什么？
------------------------

使用 Java 6 引入的 Pluggable Annotation Processing API （可插入注释处理API）进行处理，
可以在编译的时候进行源代码的验证或者是源代码的生成。

Doma使用注释处理来实现以下功能。

* 生成实体类和域类的元信息（metadata）
* 根据Dao接口实现该接口的实现类。
* 对SQL文件进行验证

关于运行环境的问题
====================

支持哪个版本的JRE？
-------------------------------------------

支持 JER 8 及其以上版本。

运行Doma所必须的库有哪些？
------------------------------------------------

Doma不依赖JRE之外的任何库。

关于开发环境的问题
====================

支持哪个版本的JDK？
-------------------------------------------

支持 JDK 8 及其以上版本。

有推荐的IDE（集成开发环境）吗？
-------------------------------------------

Eclipse 。

已经将Doma的jar包设置到Eclipse构建路径（build path）了，但是无法执行注释处理。
------------------------------------------------------------------------

Factory Path 上也需要设置Doma的jar包。

Factory Path的设置画面，从项目的属性菜单开始，依次选择
Java - Compiler - Annotation Processing - Factory Path。
Annotation Processing和Factory Path的画面上，
选中选择框「Enable project specific settings」。

注释处理生成的代码会输出到哪里？
--------------------------------------------------

使用Eclipse的情况下，
默认情况下，它会直接输出到项目下的 ``.apt_generated`` 目录。

虽然使用了Eclipse， 但是没有找到 .apt_generated 目录。
----------------------------------------------------------------------------------------

可能只是因为Package Explorer 的视图上不表示 ``.apt_generated`` 而已。
因为 ``.apt_generated`` 文件的名称是以 ``.`` 开头的，所以Package Explorer 不会表示出该对象。
以下三种方法请任意选择一种进行尝试。


* 在Nivigator视图中确认 ``.apt_generated`` 目录
* 改变Package Explorer视图的の文件夹链接的设定，显示 ``.apt_generated`` 目录
* 变更输出目录，目录的名称不以 ``.`` 开始

找不到SQL文件
-----------------------------

尽管存在SQL文件，但可能会有以下错误消息输出。
::

  [DOMA4019] 在类路径[META-INF /../ select.sql]中找不到SQL文件

SQL文件是从类文件的输出目录中开始搜索的。
确保SQL文件的输出目录与类文件的输出目录相同。

使用Eclipse的情况下，在项目属性的“Java Build Path”设置界面上，
可以为每个源文件夹变更输出的目录。

对该数据访问框架功能的提问
============================================

有自动生成SQL文件的功能吗？
-----------------------------------

有。

更新类型，存储过程/函数调用相关的SQL可以自动生成。
对于检索类型的SQL无法自动生成，但是可以执行外部SQL文件，然后将结果自动跟Java的对象进行映射。

详细情况请参考 :doc:`query/index` 。

对于动态变化的SQL是如何去执行的？
-------------------------------------------------

可以使用SQL注释在SQL文件中指定条件。
SQL注释在运行时被解析，并且根据条件生成不同的SQL。

详细请参考 :doc:`sql` 。

可以将数据库关系（如一对一和一对多）映射到Java对象上吗？
---------------------------------------------------------------------------------------------

对不起，不能。

对于Doma来说、会将SQL的结果集中的一行与一个实体类的实例进行映射。
我认为这样更简单易于理解。

有提供连接池的功能吗？
------------------------------------------

不提供。

请使用提供连接池的应用服务器，框架，类库进行组合。


