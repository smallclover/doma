==================
Lombok 支持
==================

.. contents:: 目录
:depth: 3

Doma 支持 `Lombok <https://projectlombok.org/>`_  1.16.12 以上的版本。

.. note::

  IDE として Eclipse を利用する場合はバージョン 4.5 以上を使ってください。
  请使用4.5版本以上的 Eclipse 作为IDE。
  如果使用4.5 以下版本的 Eclipse 的时候，通过注释处理器获得的类中定义的字段等的顺序与源码中描述的属性不同，无法正常工作。

Lombok 支持概要
================================

Lombok 和 Doma 都提供 JSR 269 中指定的注释处理器，但是 Lombok 和 Doma 同时使用的时候，注释处理器的处理顺序可能会成为问题。
例如，Lombok的注释处理器会生成一个构造函数，然后Doma的注释处理器会读取其构造函数。
如果Doma的注释处理器被首先执行，那么它的行为就好像构造器没有被定义一样，编译将失败。

如果能够指定注释处理器的处理顺序，那么问题将得到解决，
不幸的是，事实上我们没有找到解决该问题的方法来保证处理的顺序。

在Doma，为了解决这个问题，我们通过辨识Lombok注释是否存在，使得动作不依赖于处理顺序。
在上面的例子中，如果有一个类似“lombok.Value”的Lombok注释创建了一个构造函数，
实际上，这意味着即使你没有读取构造函数，它也会以构造函数存在的情况下执行动作。

Lombok 使用最佳实践
================================

解释说明使用Lombok 注释类的推荐方法

实体类
-------------------

immutable（不可变）
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

定义不可变实体类时，请注意以下几点。

* ``@Entity`` 的 ``immutable`` 属性设置为 ``true``
* 使用 ``@lombok.Value`` 或者 ``@lombok.AllArgsConstructor`` 进行注释
* 选择 ``@lombok.AllArgsConstructor`` 的时候、为了生成getter请使用 ``@lombok.Getter`` 注释。

.. code-block:: java

  @Entity(immutable = true)
  @Value
  public class Employee {
    @Id
    Integer id;
    String name;
    Age age;
  }

.. code-block:: java

  @Entity(immutable = true)
  @AllArgsConstructor
  @Getter
  public class Worker {
    @Id
    private final Integer id;
    private final String name;
    private final Age age;
  }

mutable（可变）
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

定义可变类型的变量的时候请注意以下几点。

* 定义默认构造函数（不要禁止生成默认构造函数）
* 需要 getter / setter 生成的情况下，使用注释 ``lombok.Data`` 或者 ``lombok.Getter`` /``lombok.Setter``

.. code-block:: java

  @Entity
  @Data
  public class Person {
    @Id
    private Integer id;
    private String name;
    private Age age;
  }

.. code-block:: java

  @Entity
  @Getter
  @Setter
  public class Businessman {
    @Id
    private Integer id;
    private String name;
    private Age age;
  }

域类
-------------------

定义域类的时候请注意一下几点

* 使用注释 ``@lombok.Value``
* 只定义一个实例字段的时候，将其命名为 ``value``

.. code-block:: java

  @Domain(valueType = Integer.class)
  @Value
  public class Age {
    Integer value;
  }

嵌入类
----------------------

定义嵌入类的时候请注意以下几点。

* 使用 ``@lombok.Value`` 或者 ``@lombok.AllArgsConstructor`` 注释
* 选择``@lombok.AllArgsConstructor`` 的时候、为了生成getter方法，使用 ``@lombok.Getter`` 注释

.. code-block:: java

  @Embeddable
  @Value
  public class Address {
    String street;
    String city;
  }

.. code-block:: java

  @Embeddable
  @AllArgsConstructor
  @Getter
  public class Location {
    private final String street;
    private final String city;
  }
