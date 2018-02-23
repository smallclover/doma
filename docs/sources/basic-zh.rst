==================
基本类型
==================

.. contents:: 目录
:depth: 3

在Doma里把数据库列能够映射到的Java类型称为基本类型。

种类
==================

基本类型有以下几种。

* 原始类型以及对应的包装类型（但是 ``char`` 和 ``java.lang.Character`` 除外）
* 枚举类型
* byte[]
* java.lang.String
* java.lang.Object
* java.math.BigDecimal
* java.math.BigInteger
* java.time.LocalDate
* java.time.LocalTime
* java.time.LocalDateTime
* java.sql.Date
* java.sql.Time
* java.sql.Timestamp
* java.sql.Array
* java.sql.Blob
* java.sql.Clob
* java.sql.SQLXML
* java.util.Date

日期/时间类型
------------------

现在解释说明一下日期与时间类型的不同。

java.time.LocalDate
  表示SQL标准的DATE类型(仅限日期)。

java.time.LocalTime
  表示SQL标准的TIME类型 (仅限时间)。

java.time.LocalDateTime
  表示SQL标准的TIMESTAMP类型 (时期和时间)。如果RDBMS支持，将会在数据库里保持为纳秒。

java.sql.Date
  表示SQL标准的DATE类型 (仅限日期)。

java.sql.Time
  表示SQL标准的TIME类型 (仅限时间)。

java.sql.Timestamp
  表示SQL标准的TIMESTAMP类型 (日期和时间)。如果RDBMS支持，将会在数据库里保持为纳秒。

java.util.Date
  表示SQL标准的TIMESTAMP类型 (日期和时间)。不能保持为纳秒。

应用示例
==================

实体类
------------------

.. code-block:: java

  @Entity
  public class Employee {

      @Id
      Integer employeeId;

      Optional<String> employeeName;

      @Version
      Long versionNo;

      ...
  }


域类
------------------

.. code-block:: java

  @Domain(valueType = String.class)
  public class PhoneNumber {

      private final String value;

      public PhoneNumber(String value) {
          this.value = value;
      }

      public String getValue() {
          return value;
      }
  }

Dao类
------------------

.. code-block:: java

  @Dao(config = AppConfig.class)
  public interface EmployeeDao {

      @Select
      Employee selectById(Integer employeeId);

      @Select
      List<String> selectAllName();
  }
