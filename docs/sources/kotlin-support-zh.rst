==================
Kotlin 支持
==================

.. contents:: 目录
:depth: 3

Doma 实验性的支持 `Kotlin <https://kotlinlang.org/>`_ 1.1.2版本。

使用Kotlin的最佳实践
================================

现在描述一下关于类定义和构建事项的推荐的方式。

实体类
-------------------

* 定义 Data Class
* 定义 immutable（ `@Entity` 的 `immutable` 属性设置为 `true` ）
* 只定义一个构造函数
* 不在构造函数以外定义属性
* 在构造函数里定义属性的时候使用 `val`

.. code-block:: java

  @Entity(immutable = true)
  data class Person(
        @Id
        @GeneratedValue(strategy = org.seasar.doma.GenerationType.IDENTITY)
        val id: Int? = null,
        val name: Name,
        val address: Address)

域类
-------------------

* 定义 Data Class
* 只定义一个构造函数
* 在构造函数里定义名称为 `value` 的属性
* 使用 `val` 定义构造函数里的属性

.. code-block:: java

  @Domain(valueType = String::class)
  data class Name(val value: String)

嵌入类
----------------------

* 定义 Data Class
* 只定义一个构造函数
* 不在构造函数以外定义属性
* 在构造函数里定义属性的时候使用 `val`


.. code-block:: java

  @Embeddable
  data class Address(val city: String, val street: String)

Dao 接口
-------------------

* 不使用 Kotlin 使用 Java 定义
* 使用 `org.seasar.doma.jdbc.Result` 或者 `org.seasar.doma.jdbc.BatchResult` 作为更新处理的返回值类型

.. code-block:: java

  @Dao(config = AppConfig.class)
  public interface PersonDao {
    @Select
    Person selectById(Integer id);
    @Insert
    Result<Person> insert(Person person);
  }

* 处理更新处理的返回值时，使用 `Destructuring Declarations <https://kotlinlang.org/docs/reference/multi-declarations.html>`_

.. code-block:: java

  val dao: PersonDao = ...
  val person = Person(name = Name("Jhon"), address = Address(city = "Tokyo", street = "Yaesu"))
  val (newPerson, count) = dao.insert(person)


使用kapt进行build
-------------------

使用 Kotlin 描述的类和接口的注释处理需要执行 `kapt <https://blog.jetbrains.com/kotlin/2016/12/kotlin-1-0-6-is-here/>`_
kapt 属于实验性项目，没有具体的文档。
当使用Gradle构建时，建议经常运行`clean build`来确保可靠的注释处理。

.. code-block:: sh

  ./gradlew clean build

在用Eclispe时请注意，如果设置正确完成，Java注释处理将自动完成，但是如果不执行Gradle，kapt（Kotlin的注释处理）将不会完成。

以下是build.gradle的代码片段。 请特别注意以下设置，以便在编译时引用SQL文件。

.. code-block:: groovy

  // 在编译之前，为了将 SQL 文件复制到输出方目录而逆转依赖关系
  compileJava.dependsOn processResources

  // 给 kapt 传递 SQL 文件等资源文件的输出路径
  kapt {
      arguments {
          arg("doma.resources.dir", processResources.destinationDir)
      }
  }


Java和Kotlin的混合
-------------------------

为了避免 kapt 的不确定行为，建议考虑与 Doma 有关的代码全部使用 Java 来编写。
在使用 Doma 的时候，Java 和 Kotlin 混合使用是没有问题的。

实例项目
=====================

示例代码参照一下项目。

* `kotlin-sample <https://github.com/domaframework/kotlin-sample>`_
