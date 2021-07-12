#zeo-online-judge
## 总体思想

1. 系统采用模块化结构（方便维护，组装，也可以尝试不同的技术路线）
2. 此项目主要为学习性质，会尝试使用不同的技术路线进行开发，技术路线的选择主要依据：
    1. 流行指数/普及率（主要参考：Stack Overflow Developer Survey 2020: [https://insights.stackoverflow.com/survey/2020](https://insights.stackoverflow.com/survey/2020)）
    2. 技术类型（关系型数据库与非关系型数据库；Mybatis 与 Hibernate）
    3. 学习成本影响学习的优先级。

## 系统需求

1. 高性能：尤其是判题系统对于性能效率的要求。
2. 高并发：可以处理瞬时的大规模判题以及查询请求。
3. 高可靠：包括放置网络攻击，以及系统稳定性，尽量有备用技术。

## 系统架构

1. 项目采用 `MVVM` 架构（相比起 `MVC`，View 不依赖于 Model，可以专注于界面设计；相比于 `MVP` 简化了手动配置大量 View 与 Model 之间同步的问题）
2. ## 总体思想

1. 系统采用模块化结构（方便维护，组装，也可以尝试不同的技术路线）
2. 此项目主要为学习性质，会尝试使用不同的技术路线进行开发，技术路线的选择主要依据：
    1. 流行指数/普及率（主要参考：Stack Overflow Developer Survey 2020: [https://insights.stackoverflow.com/survey/2020](https://insights.stackoverflow.com/survey/2020)）
    2. 技术类型（关系型数据库与非关系型数据库；Mybatis 与 Hibernate）
    3. 学习成本影响学习的优先级。

## 系统架构

1. 项目采用 `MVVM` 架构（相比起 `MVC`，View 不依赖于 Model，可以专注于界面设计；相比于 `MVP` 简化了手动配置大量 View 与 Model 之间同步的问题）
2. 具体技术路线采用：Vue+Nginx+Nodejs+spring boot（参考：https://www.zhihu.com/question/33578075/answer/56951771；https://www.zhihu.com/question/294219455/answer/496326925）
   * [ ] 尝试一：仅使用 Spring boot
3. 账号类型包括：管理员，学生，教师（其中，管理员：由标签进行权限分类；班级，专业，院级，校级等具备不同的权限）
4. 教师，学生，管理员 为三个独立部署的子系统，发生故障时可以降低系统间的影响。

作为学习：可以尝试 Ngix, Nodejs, Spring Boot 进行单应用开发测试，得出优缺点之后进行搭配优化。

### 三者的比较学习
> 网络上的资料有些都想到老，nodejs 在七八年前都还没成熟，辩证的看待，具体还要自己检验。
> 用node做服务器了，还需要nginx吗？ - CNode技术社区: [https://cnodejs.org/topic/5411035ea0c965223b99d7d1](https://cnodejs.org/topic/5411035ea0c965223b99d7d1)
> nginx与Node.js的优缺点是什么？ - 「已注销」的回答 - 知乎
https://www.zhihu.com/question/20757944/answer/451331270

Nginx 相比 Nodejs：适合处理静态资源，以及反向代理，另外重写，压缩，缓存，日志等（但 Nodejs 直接调用库，更加便捷）；错误处理更完备
Nodejs：最为明显的优势就是 Non-blocking I/O




## 访问权限设计

1. 子系统间数据库相互独立，不可进行交叉访问
2. 学生子系统无法访问教师子系统
3. 教师可以推送试题或公告给学生子系统（但不可访问数据库）

## 文件输入输出

1. 关于 EXCEL 文件的操作选用 Apache POI （原本考虑：alibaba/easyexcel : [https://github.com/alibaba/easyexcel](https://github.com/alibaba/easyexcel) 但考虑到稳定性，以及文档的完善，项目成熟度，就放弃了）
2. 只支持 Microsoft Excel XML (2007+) file 格式（参考：Apache POI - Component Overview: [http://poi.apache.org/components/](http://poi.apache.org/components/)）
    ```xml
    <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.0.0</version>
    </dependency>
    ```
### 文件读取
1. EXCEL 文件读取：Busy Developers' Guide to HSSF and XSSF Features: [https://poi.apache.org/components/spreadsheet/quick-guide.html](https://poi.apache.org/components/spreadsheet/quick-guide.html)

## 数据库设计

> 【参考】database design - MySQL: multiple tables or one table with many columns? - Stack Overflow: [https://stackoverflow.com/questions/9774715/mysql-multiple-tables-or-one-table-with-many-columns](https://stackoverflow.com/questions/9774715/mysql-multiple-tables-or-one-table-with-many-columns)

1. 关系型数据库选择：MySQL, PostgreSQL; 非关系型数据库选择：MongoDB
2. ORM 框架选择：Mybatis, Hibernate


### 数据库设计 - 题目

1. 所有题目放置在同一个表里
    1. 方便管理员检索
    2. 之后若要拆分也非常方便
    3. 标签（10*20）（容量2^8）（存放数值，使用映射）
2. 教师子系统无法直接访问数据库，需提供账户信息通过系统调用

|字段|类型|输入限制|容量分配|
|--|--|--|--|
|ID|整数|10000000|4|
|学科|文本|
|类型|文本|5个字符|16|
|内容|文本(支持latex)|200个字符|512|
|答案|文本|10个字符|32|
|标签|数字|6个|1|
|创建者姓名|文本|10个字符|32|
|创建者ID|10000000|4|
|拥有者姓名|文本|10个字符|32|
|拥有者ID|10000000|4|
|日志|10000000|4|
