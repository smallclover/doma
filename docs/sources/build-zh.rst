==================
编译
==================

.. contents:: 目录
:depth: 3

Maven Central Repository
========================

Doma 的 jar 包可以从 Maven Central Repository 里下载获得。
GroupId 和 ArtifactId 的名称如下所示。

:GroupId: org.seasar.doma
:ArtifactId: doma

使用 Eclipse 进行编译
======================

使用 Eclipse 进行编译的时候请注意一下几点。

* 在项目设置中启用注释处理
* Build Path 和 Factory Path 需要设定为 Doma 的 jar 包存在的路径。

.. note::

  比起手动进行设定，推荐使用 Gradle 的 eclipse task 的自动设定。
  详细请参考一下实例程序中包含的 build.gradle 和 eclipse.gradle 。
  `domaframework/simple-boilerplate <https://github.com/domaframework/simple-boilerplate>`_

启用注释处理
----------------

为了启用注释处理，从菜单开始选择 Project > Properties 然后在弹出的菜单中，在左侧的栏目中选择
 Java Compiler > Annotation Processing 。

然后参考下图，选中所需要的选择框。

.. image:: images/annotation-processing.png
:width: 80 %

|

设定 Factory Path
-------------------

为了启用注释处理，从菜单开始选择 Project > Properties 然后在弹出的菜单中，在左侧的栏目中选择
 Java Compiler > Annotation Processing > Factory Path 。

然后参考下图，选中所需要的选择框，
注册与编译路径相同版本的 Doma 的 jar 包。

.. image:: images/factory-path.png
:width: 80 %

|

通过 Gradle 进行编译
=====================

使用 Gradle 进行编译的时候注意一下几点。

* Java类和SQL文件的输出目录需要一致。
* 在编译之前将SQL文件复制到目标目录
* 在依赖关系设置中指定对doma的依赖性

以下是示例程序中的build.gradle。

.. code-block:: groovy

  apply plugin: 'java'

  // Java类和SQL文件的输出目录需要一致
  processResources.destinationDir = compileJava.destinationDir
  // 在编译之前，反转依赖关系将SQL文件复制到目标目录
  compileJava.dependsOn processResources

  repositories {
      mavenCentral()
      maven {url 'https://oss.sonatype.org/content/repositories/snapshots/'}
  }

  dependencies {
      compile "org.seasar.doma:doma:2.20.0-SNAPSHOT"
  }

.. note::

  仓库中 https://oss.sonatype.org/content/repositories/snapshots/ 的设定
  只有你需要引用 Doma 的 SNAPSHOT 的时候才是必要的。

  Doma 的 SNAPSHOT 在 `Travis-CI <https://travis-ci.org/domaframework/doma>`_
  每次构建成功时，都将被创建并放置在仓库中。

更详细的 Gradle 构建脚本请参照一下示例。
`domaframework/simple-boilerplate <https://github.com/domaframework/simple-boilerplate>`_
