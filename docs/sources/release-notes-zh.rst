==============
发行说明
==============

v2.19.2: 2018-03-11
======================

* `GH220 <https://github.com/domaframework/doma/pull/220>`_
  修复损坏的本地事务（翻译有待商榷）

v2.19.1: 2018-01-08
======================

* `GH216 <https://github.com/domaframework/doma/pull/216>`_
 记录仅当标识字段为空或小于0时才设置自动生成的值（翻译待调整）
* `GH215 <https://github.com/domaframework/doma/pull/215>`_
  修复 TypeDeclaration#removeOverriddenMethodDeclarations 为了避免 IllegalStateException 异常

v2.19.0: 2017-11-19
======================

* `GH211 <https://github.com/domaframework/doma/pull/211>`_
  在表达式函数中使用 `CharSequence` 作为参数
* `GH210 <https://github.com/domaframework/doma/pull/210>`_
支持Dao类的私有方法

v2.18.0: 2017-10-28
======================

* `GH208 <https://github.com/domaframework/doma/pull/208>`_
  在 JDK 9 中使用 `javax.annotation.processing.Generated`
* `GH207 <https://github.com/domaframework/doma/pull/207>`_
  如果表达式的结果是 `Long`, 将其评估为 `Float`。（翻译待调整）
* `GH206 <https://github.com/domaframework/doma/pull/206>`_
  修复 Docs 示例中的错误注释参数。
* `GH205 <https://github.com/domaframework/doma/pull/205>`_
  从Oracle方言中移除全角通配符 `％` 和 `＿` 

v2.17.0: 2017-09-09
======================

* `GH203 <https://github.com/domaframework/doma/pull/203>`_
  增加注解 `@TenantId` 来支持多用户分区方法（翻译待调整）
* `GH202 <https://github.com/domaframework/doma/pull/202>`_
  更新 url
* `GH200 <https://github.com/domaframework/doma/pull/200>`_
  修复错别字
* `GH199 <https://github.com/domaframework/doma/pull/199>`_
  修复错误的使用了类 `java.lang.reflect.Modifier`的问题

v2.16.1: 2017-05-14
======================

* `GH196 <https://github.com/domaframework/doma/pull/196>`_
  实验性支持 Kotlin 1.1.2 
* `GH195 <https://github.com/domaframework/doma/pull/195>`_
  支持在 Oracle 12c 或更高版本中使用IDENTITY自动生成标识符
* `GH194 <https://github.com/domaframework/doma/pull/194>`_
  为类 SelectBuilder 添加了 params 方法和 literals 方法 

v2.16.0: 2017-02-19
======================

* `GH191 <https://github.com/domaframework/doma/pull/191>`_
  能够从配置文件中读取注释处理选项

v2.15.0: 2017-02-05
======================

* `GH184 <https://github.com/domaframework/doma/pull/184>`_
  长名称序列
* `GH187 <https://github.com/domaframework/doma/pull/187>`_
  修复在Eclipse 4.6.2 检测出的警告
* `GH188 <https://github.com/domaframework/doma/pull/188>`_
  现在允许继承只含有默认方法的Dao接口（翻译待确认）

v2.14.0: 2017-01-14
======================

* `GH183 <https://github.com/domaframework/doma/pull/183>`_
  添加类 BatchUpdateExecutor, BatchDeleteExecutor, MapBatchInsertBuilder 
* `GH182 <https://github.com/domaframework/doma/pull/182>`_
  修复了在Embeddable类里没有定义属性时生成的代码编译错误的问题
* `GH181 <https://github.com/domaframework/doma/pull/181>`_
  添加了 `@SqlProcessor` 来处理用SQL模板组装的SQL
* `GH180 <https://github.com/domaframework/doma/pull/180>`_
  支持 Lombok 
* `GH179 <https://github.com/domaframework/doma/pull/179>`_
  StandardExpressionFunctions#escapeが现在不使用scapeChar
* `GH177 <https://github.com/domaframework/doma/pull/177>`_
  兼容 Kotlin 1.0.6
* `GH176 <https://github.com/domaframework/doma/pull/176>`_
  添加类 BatchInsertExecutor
* `GH175 <https://github.com/domaframework/doma/pull/175>`_
  对内置函数的 LocalDate, LocalDateTime 进行对应（翻译待确认）
* `GH174 <https://github.com/domaframework/doma/pull/174>`_
  添加了MapInsertBuilder，它自动使用Map作为参数来组装Insert语句

v2.13.0: 2016-11-13
======================

* `GH170 <https://github.com/domaframework/doma/pull/170>`_
  当标识符是原始类型的时候的注意点进行说明（翻译待修中）
* `GH167 <https://github.com/domaframework/doma/pull/167>`_
  修复了 Doma 2 重大变更说明表述中类名的错误


v2.12.1: 2016-08-06
======================

* `GH165 <https://github.com/domaframework/doma/pull/165>`_
  修复了当实体类的继承关系在两层的时候出现无限循环的问题

v2.12.0: 2016-07-14
======================

* `GH161 <https://github.com/domaframework/doma/pull/161>`_
  添加删除SQL文件缓存的方法
* `GH160 <https://github.com/domaframework/doma/pull/160>`_
  支持将可嵌入类定义为嵌套类型
* `GH159 <https://github.com/domaframework/doma/pull/159>`_
  支持将实体类定义为嵌套类型
* `GH158 <https://github.com/domaframework/doma/pull/158>`_
  更新文件版权日期
* `GH156 <https://github.com/domaframework/doma/pull/156>`_
  修复SQL文件存在检查的大小写区分问题
* `GH155 <https://github.com/domaframework/doma/pull/155>`_
  修复错字

v2.11.0: 2016-06-18
======================

* `GH153 <https://github.com/domaframework/doma/pull/153>`_
  支持在QueryBuilder嵌入literal
* `GH151 <https://github.com/domaframework/doma/pull/151>`_
  修复literal变量注释之后，测试用literal校验问题
* `GH150 <https://github.com/domaframework/doma/pull/150>`_
  添加literal变量注释机能

v2.10.0: 2016-05-28
======================

* `GH146 <https://github.com/domaframework/doma/pull/146>`_
  修复了 Embeddable 对象为 null 时更新系的处理失败的问题
* `GH145 <https://github.com/domaframework/doma/pull/145>`_
  添加有关 Kotlin 支持的文档
* `GH142 <https://github.com/domaframework/doma/pull/142>`_
  添加 Embeddable 类的文档
* `GH141 <https://github.com/domaframework/doma/pull/141>`_
  继承实体类的时候，访问父类属性的方法进行了简化
* `GH140 <https://github.com/domaframework/doma/pull/140>`_
  修复了将null分配给基本类型的属性时发生异常的错误，现在将设定默认值
* `GH139 <https://github.com/domaframework/doma/pull/139>`_
  支持注解 `@Embeddable` 
* `GH138 <https://github.com/domaframework/doma/pull/138>`_
  使用 Kotlin 来定义不可变实体类的时候，无需使用 `@ParameterName` 

v2.9.0: 2016-05-16
======================

* `GH136 <https://github.com/domaframework/doma/pull/136>`_
  更新結果を表すクラスで Kotlin の Destructuring Declarations に対応
* `GH135 <https://github.com/domaframework/doma/pull/135>`_
  注釈処理で出力するメッセージに、クラス、メソッド、フィード名など出力元の情報を追加
* `GH134 <https://github.com/domaframework/doma/pull/134>`_
  支持在 `@Dao` 注解的 Singleton 属性上指定类型的 Config 
* `GH133 <https://github.com/domaframework/doma/pull/133>`_
  实验性支持kapt 1.0.1-2
* `GH132 <https://github.com/domaframework/doma/pull/132>`_
  将远程URL从SSH切换到HTTPS
* `GH131 <https://github.com/domaframework/doma/pull/131>`_
  修复了匿名包中放置的类的注释处理失败的缺陷
* `GH130 <https://github.com/domaframework/doma/pull/130>`_
  改善测试代码

v2.8.0: 2016-04-16
======================

* `GH127 <https://github.com/domaframework/doma/pull/127>`_
  支持将域类定义为嵌套类型

v2.7.0: 2016-02-27
======================

* `GH125 <https://github.com/domaframework/doma/pull/125>`_
  将SelectBuilder 的默认 FetchType 设定为 Lazy 
* `GH124 <https://github.com/domaframework/doma/pull/124>`_
  修复错误的警告信息
* `GH122 <https://github.com/domaframework/doma/pull/122>`_
  检索用的方法的返回类型时Stream
* `GH121 <https://github.com/domaframework/doma/pull/121>`_
  修复include的说明错误

v2.6.2: 2016-02-11
======================

* `GH118 <https://github.com/domaframework/doma/pull/118>`_
  SQL注释列 列表扩展注释 链接修正
* `GH117 <https://github.com/domaframework/doma/pull/117>`_
  添加了与链接相关的Javadoc生成选项
* `GH116 <https://github.com/domaframework/doma/pull/116>`_
  修复在Query Builder 调用getSql()时发生错误的问题
* `GH115 <https://github.com/domaframework/doma/pull/115>`_
  兼容Spring Boot DevTools

v2.6.1: 2016-01-11
======================

* `GH111 <https://github.com/domaframework/doma/pull/111>`_
  Revert "修复了注释处理生成的代码包含过长的泛型的问题"

v2.6.0: 2015-11-21
======================

* `GH107 <https://github.com/domaframework/doma/pull/107>`_
  修复了注释处理生成的代码包含过长的泛型的问题
* `GH105 <https://github.com/domaframework/doma/pull/105>`_
  修复在 UniqueConstraintException's 构造函数中 casue 变量的问题

v2.5.1: 2015-11-01
======================

* `GH102 <https://github.com/domaframework/doma/pull/102>`_
  修复了 UnknownColumnHandler 的 handle() 没有实现的时候发生 NullPointerException 的问题

v2.5.0: 2015-10-10
======================

* `GH99 <https://github.com/domaframework/doma/pull/99>`_
  通过预先申请数据库IDENTITY来解决批量更新中性能不佳的问题

v2.4.1: 2015-09-12
======================

* `GH96 <https://github.com/domaframework/doma/pull/96>`_
  嵌入的变量注释展开后不插入空格

v2.4.0: 2015-08-14
======================

* `GH93 <https://github.com/domaframework/doma/pull/93>`_
  修复 JdbcMappingHint#getDomainClass() 不返回 domain 类的问题
* `GH89 <https://github.com/domaframework/doma/pull/89>`_
  使PortableObjectType成为通用，以便可以将String等指定为valueType
* `GH88 <https://github.com/domaframework/doma/pull/88>`_
  JdbcLogger的方法的错别字修正。 Failuer -> Failure
* `GH87 <https://github.com/domaframework/doma/pull/87>`_
  在StandardExpressionFunctions的子类中添加public构造函数
* `GH86 <https://github.com/domaframework/doma/pull/86>`_
  版本号规范与文档不同
* `GH84 <https://github.com/domaframework/doma/pull/84>`_
  修复 populate 使用的方法里出现 DOMA4122 的问题
* `GH81 <https://github.com/domaframework/doma/pull/81>`_
  ResourceBundle无法取到的时候，回退到默认的ResourceBundle

v2.3.1: 2015-05-30
======================

* `GH79 <https://github.com/domaframework/doma/pull/79>`_
  修复了跳过使用SQL文件进行更新的问题

v2.3.0: 2015-05-23
======================

* `GH75 <https://github.com/domaframework/doma/pull/75>`_
  在SQL文件中自动生成UPDATE语句的SET子句
* `GH74 <https://github.com/domaframework/doma/pull/74>`_
  修复了PostgresDialect中发生ID生成错误的问题

v2.2.0: 2015-03-28
======================

* `GH71 <https://github.com/domaframework/doma/pull/71>`_
  现在在interface上也可以使用@Domain注解
* `GH70 <https://github.com/domaframework/doma/pull/70>`_
  通过EntityListenerProvider来获取EntityListener
* `GH67 <https://github.com/domaframework/doma/pull/67>`_
  修复 SQL Server 的 OPTION 语句存在的时候无法正常的进行分页的问题
* `GH66 <https://github.com/domaframework/doma/pull/66>`_
  将命名约定的应用程序从编译时更改为运行时
* `GH64 <https://github.com/domaframework/doma/pull/64>`_
  修复了获取不可变实体时发生 NullPointerException 的错误
* `GH61 <https://github.com/domaframework/doma/pull/61>`_
  使用从 SQL Server 2012 添加的 OFFSET-FETCH 进行分页处理
* `GH60 <https://github.com/domaframework/doma/pull/60>`_
  更改了 ssql2008Dialect 的 getName() 返回的值
* `GH59 <https://github.com/domaframework/doma/pull/59>`_
  修复在Windows环境下测试失败的问题
* `GH58 <https://github.com/domaframework/doma/pull/58>`_
  如果列名包含数字，则StringUtil的FromCamelCaseToSnakeCase不会产生预期结果

v2.1.0: 2014-12-30
======================

* `GH51 <https://github.com/domaframework/doma/issues/51>`_
  修复了LocalTransactionManager#notSupported()创建新事务的缺陷
* `GH50 <https://github.com/domaframework/doma/pull/50>`_
  现在可以使用SQL注释向SQL添加了一个标识符
* `GH49 <https://github.com/domaframework/doma/pull/49>`_
  将Gradle的projec名字设定为"doma"
* `GH48 <https://github.com/domaframework/doma/pull/48>`_
  `/*%expand` 和 `*/` 之间的宫格不会被识别为alias

v2.0.1: 2014-08-06
======================

* 修复了将 ``byte[]`` 指定为第二个类型参数时 ``DomainConverter`` 注释处理会发生编译错误的问题

v2.0.0: 2014-07-02
======================

* 删除 ``UnitOfWork`` 

v2.0-beta-5: 2014-06-07
========================

* 修复了当 Dao 方法返回值的类型为 ``List<Optional<Emp>>`` 和 ``List<Optional<Map<String, Object>>>`` 的时候注解处理发生编译错误的问题
* 修复了在 Entity 更新后更改未在 OriginalStates 中反应的问题
* 当已经设置了实体标识符的值时候，不执行自动生成过程
* 修复了在列 列表扩展注释中 导致 DOMA 4257 错误的问题
* 可以通过注释来控制SQL日志输出方法
* 从 Dao 输出的日志消息详细化
* 将 ``UtilLoggingJdbcLogger`` 的logger的名称更改外完全限定名
* 修复了执行SQL时SQL文件路径未输出到日志的问题

v2.0-beta-4: 2014-05-04
========================

* Pluggable Annotation Processing API Visitor的版本已升级到Java 8 
* 当空的 ``java.util.Iterable`` 与 IN 语句绑定的时候，将其作为 SQL 的 ``null``
* 支持 ``java.sql.SQLXML``
* 修复了 ``LocalTransaction`` 指定 save point 「以后」应该删除的地方进行了「以前」删除的缺陷
* 修复了 ``LocalTransaction`` 的 save point 删除的时候日志错误的缺陷
* 修复了如果 Entity 的属性类型设置为 byte[] 时注释处理失败的缺陷

v2.0-beta-3: 2014-04-03
========================

* 检索结果可以使用 ``java.util.stream.Collector`` 进行处理。
* 从 ``LocalTransactionManager`` 里抽出 ``TransactionManager`` 接口。
* 修复了 ``Config`` 里指定的设置一部分被忽略的缺陷
* 添加了接口 ``MapKeyNaming`` 来统一控制Map的命名规约。
* 现在 ``java.time.LocalDate`` 、 ``java.time.LocalTime`` 、 ``java.time.LocalDateTime``
  可以作为基本类型来使用。
* 添加了 ``AbstractJdbcLogger`` 来替换 ``JdbcLogger`` 的实现。
* ``SelectStrategyType`` 的名字变更为 ``SelectType`` 。
