==================
Dao 接口
==================

.. contents:: 目录
      :depth: 3

Data Access Object（Dao）是数据库访问的接口。

Dao 定义
==================

通过 ``@Dao`` 注释的接口来定义 Dao 。
接口实现类是在编译时由 apt 自动生成的。

query 定义
==================

可以使用注释定义 :doc:`query/index` 。

如果想要使用 Java 代码组合任意的 query ,请在 `默认方法`_ 中使用 :doc:`query-builder/index` 。

.. _dao-default-method:

默认方法
==================

在默认方法里可以描述任何处理。

将Dao实例传递给 ``Config.get`` 将会得到与Dao相关的 ``Config`` 实例。

.. code-block:: java

  @Dao(config = AppConfig.class)
  public interface EmployeeDao {

      default int count() {
          Config config = Config.get(this);
          SelectBuilder builder = SelectBuilder.newInstance(config);
          builder.sql("select count(*) from employee");
          return builder.getScalarSingleResult(int.class);
      }
  }

应用示例
==================

编译时会根据注释来生成相应的实现类
实例化实现类然后使用它。
但是，在使用DI容器来管理配置类时，请使用DI容器来控制实例化。

.. code-block:: java

  EmployeeDao employeeDao = new EmployeeDaoImpl();
  Employee employee = employeeDao.selectById(1);

默认情况下，实现类的名字会在接口的名字后面添加后缀 ``Impl`` 。
要更改包和后缀请参考 :doc:`annotation-processing` 。

使用默认的构造函数的时候， ``DataSource`` 的确定由``@Dao`` 的 ``config`` 属性来指定，
也可以通过指定特定的 ``DataSource`` 来实例化。

.. code-block:: java

  DataSource dataSource = ...;
  EmployeeDao employeeDao = new EmployeeDaoImpl(dataSource);
  Employee employee = employeeDao.selectById(1);

同样，也可以通过指定特定的 ``Connection`` 来实例化。

.. code-block:: java

  Connection connection = ...;
  EmployeeDao employeeDao = new EmployeeDaoImpl(connection);
  Employee employee = employeeDao.selectById(1);

Dao接口并不与实体类一对一绑定。
一个Dao接口可以处理多个实体类。

.. code-block:: java

  @Dao(config = AppConfig.class)
  public interface MyDao {

      @Select
      Employee selectEmployeeById(int id);

      @Select
      Department selectDepartmentByName(String name);

      @Update
      int updateAddress(Address address);
  }

