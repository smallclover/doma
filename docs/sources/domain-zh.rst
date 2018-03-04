==================
Domain 类
==================

.. contents:: 目录
      :depth: 3

描述一下Domain （域）类的定义方法。

在Doma中，可以使用称为** domain **的Java对象处理数据库中表的列的值。
所谓域就是值能够取到的范围，也就是定义域。

通过利用域类使得我们可以用不同于数据库中列的类型的java的类型来表现，因为有的时候即使用了与数据库相同类型的java类型，在应用上的意思也可能有差异。
ドメインクラスを利用することで、データベース上のカラムの型が同じあっても
アプリケーション上意味が異なるものを別のJavaの型で表現できます。
通过利用域类来明确所要表达的意思，更容易预防编程错误。
而且根据域类所持有的行为，是我们可以编写更加容易理解的程序。

域类的创建和使用是可选的。
即使不使用域类，也可以只使用诸如``Integer`` 和 ``String``等基本类型来进行数据库的访问。

域根据其定义的方式分为内部域和外部域。

内部域
======================

将定义直接写入要视为域的目标类的源代码中。

要定义一个内部域，请用 ``@Domain`` 注释该类。

``@Domain`` 的 ``valueType`` 属性的值请参考 :doc:`basic` 。

使用构造函数生成的方式
-----------------------------------------------

``@Domain`` 的 ``factoryMethod`` 属性的默认值是 ``new`` 的场合、
表示该域类通过非private的构造函数实例化生成。
所以，如果是通过构造函数生成的场合，可以省略 ``factoryMethod`` 属性。
下面这个例子是一个拥有 ``public`` 类型的构造函数的域类。
这个类表示电话号码。

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

      public String getAreaCode() {
         //可以用来描述域的特定行为
         ...
      }
  }

通过factorymethod生成的方式
-----------------------------------------------

构造函数是private并且想要使用factorymethod来生成实例的情况下，
定义一个static且不是private类型的方法。并且在 ``@Domain`` 的 ``factoryMethod`` 属性中指定该方法的名字。
下个例子是通过一个public类型的factorymethod方法来生成实例。
该类表示电话号码。

.. code-block:: java

  @Domain(valueType = String.class, factoryMethod = "of")
  public class PhoneNumber {

      private final String value;

      private PhoneNumber(String value) {
          this.value = value;
      }

      public String getValue() {
          return value;
      }

      public String getAreaCode() {
         //可以描述域所特有的行为
         ...
      }

      public static PhoneNumber of(String value) {
          return new PhoneNumber(value);
      }
  }

下面的例子是一个拥有 ``public`` 类型的factorymethod的枚举类型的域类。
该域类表示工作的类型。

.. code-block:: java

  @Domain(valueType = String.class, factoryMethod = "of")
  public enum JobType {
      SALESMAN("10"),
      MANAGER("20"),
      ANALYST("30"),
      PRESIDENT("40"),
      CLERK("50");

      private final String value;

      private JobType(String value) {
          this.value = value;
      }

      public static JobType of(String value) {
          for (JobType jobType : JobType.values()) {
              if (jobType.value.equals(value)) {
                  return jobType;
              }
          }
          throw new IllegalArgumentException(value);
      }

      public String getValue() {
          return value;
      }
  }

类型参数的使用方法
-----------------------------------------------

域类可以声明任意数量的类型参数
下面这个例子创建了一个拥有一个类型参数以及一个 ``public`` 类型的构造函数。
该域类表示标识符。

.. code-block:: java

  @Domain(valueType = int.class)
  public class Identity<T> {

      private final int value;

      public Identity(int value) {
          this.value = value;
      }

      public int getValue() {
          return value;
      }
  }

带有类型参数的域类也可以使用工厂方法创建。
在这种情况下factorymethod也需要声明类型参数，域域类一样。

.. code-block:: java

  @Domain(valueType = int.class, factoryMethod = "of")
  public class Identity<T> {

      private final int value;

      private Identity(int value) {
          this.value = value;
      }

      public int getValue() {
          return value;
      }

      public static <T> Identity<T> of(int value) {
          return new Identity<T>(value);
      }
  }

外部域
======================

将定义写入与要视为域的目标类不同的类中。

如果你不想将域加入源码，不想依赖Doma，在这种情况下使用外部域会比较有效果。
要定义一个外部域，你需要实现类 ``DomainConverter`` ，并且在类上使用
``@ExternalDomain`` 注释。


比如，下面这个 ``PhoneNumber`` 类，现在不想修改源码，添加与域有关的代码。

.. code-block:: java

  public class PhoneNumber {

      private final String value;

      public PhoneNumber(String value) {
          this.value = value;
      }

      public String getValue() {
          return value;
      }

      public String getAreaCode() {
         ...
      }
  }

如果想要将上面的 ``PhoneNumber`` 类作为域来处理，请添加如下类。

.. code-block:: java

  @ExternalDomain
  public class PhoneNumberConverter implements DomainConverter<PhoneNumber, String> {

      public String fromDomainToValue(PhoneNumber domain) {
          return domain.getValue();
      }

      public PhoneNumber fromValueToDomain(String value) {
          if (value == null) {
              return null;
          }
          return new PhoneNumber(value);
      }
  }

到此为止外部域已经定义完毕，但是仅仅这样还是不能使用。
我们需要将定义的外部域在 ``@DomainConverters`` 中注册一下。
``@DomainConverters`` 中可以注册多个外部域的定义。

.. code-block:: java

  @DomainConverters({ PhoneNumberConverter.class })
  public class DomainConvertersProvider {
  }

最后需要在:doc:`annotation-processing` 的选项中指定在  ``@DomainConverters`` 中
添加的类的全限定名。
选项的key是 ``doma.domain.converters`` 。

类型变量使用的方式
----------------------------------------

域类可以拥有任意数量的类型参数。
.. code-block:: java

  public class Identity<T> {

      private final int value;

      public Identity(int value) {
          this.value = value;
      }

      public int getValue() {
          return value;
      }
  }

想要使用上述的 ``Identity`` 域类，你还需要创建一下代码。
你必须指定 ``Identity`` 的类型变量的通配符为 ``?`` 。

.. code-block:: java

  @ExternalDomain
  public class IdentityConverter implements DomainConverter<Identity<?>, String> {

      public String fromDomainToValue(Identity<?> domain) {
          return domain.getValue();
      }

      @SuppressWarnings("rawtypes")
      public Identity<?> fromValueToDomain(String value) {
          if (value == null) {
              return null;
          }
          return new Identity(value);
      }
  }

其他设置方法与不使用类型参数时相同。

应用实例
==================



如果域类持有类型参数，则类型参数需要具体类型。
不支持通配符“`？”或类型变量。

.. code-block:: java

  @Entity
  public class Employee {

      @Id
      Identity<Employee> employeeId;

      String employeeName;

      PhoneNumber phoneNumber;

      JobType jobType;

      @Version
      Integer versionNo();

      ...
  }

.. code-block:: java

  @Dao(config = AppConfig.class)
  public interface EmployeeDao {

      @Select
      Employee selectById(Identity<Employee> employeeId);

      @Select
      Employee selectByPhoneNumber(PhoneNumber phoneNumber);

      @Select
      List<PhoneNumber> selectAllPhoneNumber();

      @Select
      Employee selectByJobType(JobType jobType);

      @Select
      List<JobType> selectAllJobTypes();
  }




















