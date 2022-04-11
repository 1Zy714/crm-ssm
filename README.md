# 1.0 搭建开发环境

1. 创建空项目crm-project

   空项目当中创建模块module，补全目录结构

   设置编码格式utf-8：settings-Editor-FileEncodings

   ![1](C:\Users\player\Desktop\感恩有你\SSM框架\SSM\src\1.png)

2. 添加支持jar包和pom依赖

   **依赖：**

   ```xml
   <dependencies>
     <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.11</version>
       <scope>test</scope>
     </dependency>
   
     <!--mysql驱动-->
     <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.25</version>
     </dependency>
     <!--阿里数据库连接池-->
     <dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>druid</artifactId>
       <version>1.2.8</version>
     </dependency>
     <!--mybatis依赖-->
     <dependency>
       <groupId>org.mybatis</groupId>
       <artifactId>mybatis</artifactId>
       <version>3.5.9</version>
     </dependency>
     <!--spring依赖-->
     <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-context</artifactId>
     <version>5.3.16</version>
     </dependency>
     <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-aop</artifactId>
       <version>5.3.16</version>
     </dependency>
     <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-core</artifactId>
       <version>5.3.16</version>
     </dependency>
     <!--加入监听器依赖-->
     <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-web</artifactId>
       <version>5.3.16</version>
     </dependency>
     <!--加入springmvc依赖-->
     <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-webmvc</artifactId>
       <version>5.3.16</version>
       <scope>compile</scope>
     </dependency>
     <!--spring事务依赖-->
     <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-tx</artifactId>
       <version>5.3.16</version>
     </dependency>
     <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-jdbc</artifactId>
       <version>5.3.16</version>
     </dependency>
     <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-oxm</artifactId>
       <version>5.3.16</version>
     </dependency>
     <!--加入aspectJ依赖-->
     <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-aspects</artifactId>
       <version>5.3.16</version>
     </dependency>
     <!--spring mybatis集成依赖-->
     <dependency>
       <groupId>org.singledog</groupId>
       <artifactId>mybatis-spring</artifactId>
       <version>1.3.3</version>
     </dependency>
     <!--加入servlet依赖-->
     <dependency>
       <groupId>javax.servlet</groupId>
       <artifactId>javax.servlet-api</artifactId>
       <version>4.0.1</version>
       <scope>provided</scope>
     </dependency>
     <!--加入jsp依赖-->
     <dependency>
       <groupId>javax.servlet.jsp</groupId>
       <artifactId>jsp-api</artifactId>
       <version>2.1.3-b06</version>
       <scope>provided</scope>
     </dependency>
     <!--jstl依赖-->
     <dependency>
       <groupId>javax.servlet.jsp.jstl</groupId>
       <artifactId>jstl-api</artifactId>
       <version>1.2</version>
     </dependency>
     <!--taglibs-->
     <dependency>
       <groupId>org.apache.taglibs</groupId>
       <artifactId>taglibs-standard-spec</artifactId>
       <version>1.2.1</version>
     </dependency>
     <dependency>
       <groupId>org.apache.taglibs</groupId>
       <artifactId>taglibs-standard-impl</artifactId>
       <version>1.2.1</version>
     </dependency>
     <!--jackson依赖-->
     <dependency>
       <groupId>com.fasterxml.jackson.core</groupId>
       <artifactId>jackson-core</artifactId>
       <version>2.9.0</version>
     </dependency>
     <dependency>
       <groupId>com.fasterxml.jackson.core</groupId>
       <artifactId>jackson-databind</artifactId>
       <version>2.9.0</version>
     </dependency>
     <dependency>
       <groupId>com.fasterxml.jackson.core</groupId>
       <artifactId>jackson-annotations</artifactId>
       <version>2.9.0</version>
     </dependency>
     <!--poi依赖-->
     <dependency>
       <groupId>org.apache.poi</groupId>
       <artifactId>poi</artifactId>
       <version>5.2.2</version>
     </dependency>
     <!--文件上传依赖-->
     <dependency>
       <groupId>commons-fileupload</groupId>
       <artifactId>commons-fileupload</artifactId>
       <version>1.4</version>
     </dependency>
     <!--日志依赖-->
     <dependency>
       <groupId>org.apache.logging.log4j</groupId>
       <artifactId>log4j-api</artifactId>
       <version>2.17.1</version>
     </dependency>
     <dependency>
       <groupId>org.apache.logging.log4j</groupId>
       <artifactId>log4j-core</artifactId>
       <version>2.17.1</version>
     </dependency>
     <dependency>
       <groupId>org.apache.logging.log4j</groupId>
       <artifactId>log4j-jcl</artifactId>
       <version>2.17.1</version>
     </dependency>
   </dependencies>
   ```

3. 添加配置文件

   - mybatis配置文件mybatis-config.xml

   - spring配置文件applicationContext.xml

     将dataSource独立出来，单独创建一个配置文件，在spring主配置文件中导入

     **applicationContext.xml**

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xmlns:p="http://www.springframework.org/schema/p"
            xmlns:aop="http://www.springframework.org/schema/aop"
            xmlns:tx="http://www.springframework.org/schema/tx"
            xmlns:task="http://www.springframework.org/schema/task"
            xsi:schemaLocation="
     http://www.springframework.org/schema/beans
     http://www.springframework.org/schema/beans/spring-beans.xsd
     http://www.springframework.org/schema/context
     http://www.springframework.org/schema/context/spring-context.xsd
     http://www.springframework.org/schema/tx
     http://www.springframework.org/schema/tx/spring-tx.xsd
     http://www.springframework.org/schema/aop
     http://www.springframework.org/schema/aop/spring-aop.xsd">
         <!-- 加载系统配置文件
         <context:property-placeholder location="classpath:*.properties" />-->
         <!-- 扫描注解 -->
         <context:component-scan base-package="com.bjpowernode.crm.service" />
         <!-- 导入数据相关配置 -->
         <import resource="applicationContext-datasource.xml" />
     </beans>
     ```

     **applicationContext-datasource.xml**

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:aop="http://www.springframework.org/schema/aop"
            xmlns:context="http://www.springframework.org/schema/context"
            xmlns:tx="http://www.springframework.org/schema/tx"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
           http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">
         <!-- 配置数据源 -->
         <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
             <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
             <property name="username" value="root"/>
             <property name="password" value="979979"/>
             <!--                                                        查数据、登录、输入数据都是用utf-8-->
             <property name="url" value="jdbc:mysql://127.0.0.1:3306/crm?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
         </bean>
         <!-- 配置SqlSessionFactory -->
         <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
             <!-- 必须注入属性dataSource -->
             <property name="dataSource" ref="dataSource"/>
             <!-- 如果mybatis没有特殊的配置(比如别名等)，configLocation可以省去 ;否则，不能省略-->
             <property name="configLocation" value="classpath:mybatis-config.xml"/>
         </bean>
         <!-- mapper注解扫描器配置,扫描@MapperScan注解,自动生成代码对象 -->
         <bean id="mapperScanner" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
             <property name="basePackage" value="com.bjpowernode.crm.mapper"/>
             <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
         </bean>
     
         <!-- 配置事务管理器 -->
         <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
             <property name="dataSource" ref="dataSource"/>
         </bean>
         <!-- 配置事务 -->
         <aop:config>
             <aop:pointcut expression="execution(* com.bjpowernode.crm..service.*.*(..))" id="allMethodPointcut"/>
             <aop:advisor advice-ref="txAdvice" pointcut-ref="allMethodPointcut"/>
         </aop:config>
         <tx:advice id="txAdvice" transaction-manager="transactionManager">
             <tx:attributes>
                 <tx:method name="add*" propagation="REQUIRED" rollback-for="Exception"/>
                 <tx:method name="save*" propagation="REQUIRED" rollback-for="Exception"/>
                 <tx:method name="edit*" propagation="REQUIRED" rollback-for="Exception"/>
                 <tx:method name="update*" propagation="REQUIRED" rollback-for="Exception"/>
                 <tx:method name="delete*" propagation="REQUIRED" rollback-for="Exception"/>
                 <tx:method name="do*" propagation="REQUIRED" rollback-for="Exception"/>
                 <tx:method name="*" propagation="REQUIRED" read-only="true"/>
             </tx:attributes>
         </tx:advice>
     </beans>
     
     
     ```

     

   - springmvc配置文件

     **applicationContext-mvc.xml**

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xmlns:p="http://www.springframework.org/schema/p"
            xmlns:util="http://www.springframework.org/schema/util"
            xmlns:aop="http://www.springframework.org/schema/aop"
            xmlns:tx="http://www.springframework.org/schema/tx"
            xmlns:mvc="http://www.springframework.org/schema/mvc"
            xsi:schemaLocation="
                http://www.springframework.org/schema/beans
                http://www.springframework.org/schema/beans/spring-beans.xsd
                http://www.springframework.org/schema/context
                http://www.springframework.org/schema/context/spring-context.xsd
                http://www.springframework.org/schema/tx
                http://www.springframework.org/schema/tx/spring-tx.xsd
                http://www.springframework.org/schema/aop
                http://www.springframework.org/schema/aop/spring-aop.xsd
                http://www.springframework.org/schema/mvc
                http://www.springframework.org/schema/mvc/spring-mvc.xsd
                http://www.springframework.org/schema/util
                http://www.springframework.org/schema/util/spring-util.xsd">
         <!-- dispatcherServlet截获所有URL请求 -->
         <mvc:default-servlet-handler />
         <!-- spring mvc 扫描包下的controller -->
         <context:component-scan base-package="com.bjpowernode.crm.web.controller"/>
         <!-- 配置注解驱动 -->
         <mvc:annotation-driven/>
         <!-- 配置视图解析器 -->
         <bean id="viewResolver"
               class="org.springframework.web.servlet.view.InternalResourceViewResolver">
             <property name="prefix" value="/WEB-INF/pages/"/>
             <property name="suffix" value=".jsp"/>
         </bean>
         <!-- 配置文件上传解析器 id:必须是multipartResolver-->
         <!--<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
             <property name="maxUploadSize" value="#{1024*1024*80}"/>
             <property name="defaultEncoding" value="utf-8"/>
         </bean>-->
     </beans>
     
     ```

   - web.xml配置文件

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xmlns="http://java.sun.com/xml/ns/javaee"
              xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
     http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
              id="dataservice" version="3.0">
       <display-name>dataservice application</display-name>
       <!-- spring监听器加载applicationContext.xml配置文件 -->
       <context-param>
         <param-name>contextConfigLocation</param-name>
         <param-value>classpath:applicationContext.xml</param-value>
       </context-param>
       <listener>
         <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
       </listener>
       <!-- spring字符过滤器 -->
       <filter>
         <filter-name>encodingFilter</filter-name>
         <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
         <init-param>
           <param-name>encoding</param-name>
           <param-value>UTF-8</param-value>
         </init-param>
       </filter>
       <filter-mapping>
         <filter-name>encodingFilter</filter-name>
         <url-pattern>/*</url-pattern>
       </filter-mapping>
       <!-- Spring mvc分发servlet -->
       <servlet>
         <servlet-name>dispatcher</servlet-name>
         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
         <init-param>
           <param-name>contextConfigLocation</param-name>
           <param-value>classpath:applicationContext-mvc.xml</param-value>
         </init-param>
         <load-on-startup>1</load-on-startup>
       </servlet>
       <servlet-mapping>
         <servlet-name>dispatcher</servlet-name>
         <url-pattern>/</url-pattern>
       </servlet-mapping>
       <servlet-mapping>
         <servlet-name>dispatcher</servlet-name>
         <url-pattern>*.do</url-pattern>
       </servlet-mapping>
       <!-- 欢迎页，默认进入index controller -->
       <welcome-file-list>
         <welcome-file>/</welcome-file>
       </welcome-file-list>
     </web-app>
     
     
     ```

   - 将静态资源放置到目录中

## 问题

**Error creating bean with name 'sqlSessionFactory' defined in class path resource [applicationContext.xml]: Invocation of init method failed;**

启动服务器，对各部件进行初始化时出现的错误，该错误出现的原因是，没有对mybatis配置文件的mapper进行赋值

```xml
<!--dao和mapper所在包-->
<mappers>
    <package name="com.bjpowernode.crm.mapper"/>
</mappers>
```

不论是否建立了对应的包，必须先赋值，否则初始化bean,sqlSessionFactory会失败

# 2.0 设置首页

1. 需求分析

   用户访问项目时，跳转到首页index.html

2. 分析与设计

   - index.html页面在WEB-INF下，用户无法直接访问，需要通过controller跳转

   - 创建IndexController，接收到请求后，跳转首页

     - 创建IndexController，通过@Controller注解，创建index()方法，返回index.jsp路径

       ```java
       @Controller
       public class IndexController {
           /**
            * @RequestMapping: value = "http://127.0.0.1:8080/crm/*",前面一段项目的地址可以省略
            *                          直接用"/*"表示
            *              如下的value意思是进入项目就访问controller的方法index()
            * */
           @RequestMapping("/")
           public String index(){
               //请求转发
               return "index";
           }
           
       }
       ```

     - index.jsp向controller发送请求跳转到login.jsp

   - 要跳转user下的login.jsp，需要创建对于user目录的Controller——userController

     ```java
     @Controller
     public class userController {
         /**
          * 跳转登陆页面
          * */
         @RequestMapping("/settings/qx/user/toLogin.do")
         public String toLogin(){
             return "settings/qx/user/login";
         }
     }
     ```

     

     - login.html转换成jsp文件，需要注意，对应的资源文件定位方式要更改

       通过代码块获取项目绝对路径

       ```java
       String basePath = request.getScheme()+"://"+request.getServerName()+":"
       	+request.getServerPort()+request.getContextPath()+"/";
       
       getSheme：http+"://"+getServerName:127.0.0.1+":"+
           getServerPort:8080+getContextPath:/crm+"/"
       
       http://127.0.0.1:8080/crm/
       ```

       赋值给base标签

       ```html
       <base href="<%=basePath%>">
       ```

3. 编码实现

4. 测试

# 3.0 用户登录

1. 需求分析：用户输入用户名密码，点击登录，跳转到UserController，判断登录信息并响应
2. 分析与设计
   1. 用户输入用户名密码，点击登录或回车，ajax请求发送到UserController
   2. UserController将前端传递的数据封装map(？)，向后台数据库获取数据，判断用户信息是否正确
   3. 如果请求通过，UserController直接跳转进入系统，否则将错误信息返回页面
   4. 页面根据传递过来的错误信息，处理页面

## **3.1 mybatis逆向工程**

- 通过数据库表，生成mapper层三部分代码：实体类，mapper接口，映射文件

- 使用mybatis逆向工程：

  1. 新创建一个模块：crm-mybatis-generator

  2. 添加插件：

     ```xml
     <!--mybatis逆向工程插件-->
     <plugin>
         <groupId>org.mybatis.generator</groupId>
         <artifactId>mybatis-generator-maven-plugin</artifactId>
         <version>1.4.1</version>
         <configuration>
             <verbose>true</verbose>
             <overwrite>true</overwrite>
         </configuration>
     </plugin>
     ```

     **添加的信息：**

     - 数据库连接信息
     - 代码保存的目录
     - 表的信息

     配置文件：

     generator.properties

     ```properties
     jdbc.driverLocation=D:/Maven/mvn_repository/mysql/mysql-connector-java/8.0.25/mysql-connector-java-8.0.25.jar
     jdbc.driverClass=com.mysql.cj.jdbc.Driver
     jdbc.connectionURL=jdbc:mysql://127.0.0.1:3306/crm
     jdbc.userId=root
     jdbc.password=979979
     ```

     generatorConfig.xml

     ```xml
             <!-- Model模型生成器,用来生成含有主键key的类，记录类 以及查询Example类
                 targetPackage     指定生成的model生成所在的包名
                 targetProject     指定在该项目下所在的路径|指定生成到的工程名称
             -->
             <javaModelGenerator targetPackage="com.bjpowernode.crm.settings.domain"
     targetProject="C:/Users/player/Desktop/感恩有你/SSM框架/2022-04/crm-project/crm-ssm/crm/src/main/java">
     
                 <!-- 是否允许子包，即targetPackage.schemaName.tableName -->
                 <property name="enableSubPackages" value="false"/>
                 <!-- 是否对model添加 构造函数 true添加，false不添加-->
                 <property name="constructorBased" value="false"/>
                 <!-- 是否对类CHAR类型的列的数据进行trim操作 -->
                 <property name="trimStrings" value="true"/>
                 <!-- 建立的Model对象是否 不可改变  即生成的Model对象不会有 setter方法，只有构造方法 -->
                 <property name="immutable" value="false"/>
             </javaModelGenerator>
     
             <!--Mapper映射文件生成所在的目录 为每一个数据库的表生成对应的SqlMap文件 -->
             <sqlMapGenerator targetPackage="com.bjpowernode.crm.settings.mapper"
                              targetProject="C:/Users/player/Desktop/感恩有你/SSM框架/2022-04/crm-project/crm-ssm/crm/src/main/java">
                 <property name="enableSubPackages" value="false"/>
             </sqlMapGenerator>
     
             <!-- 客户端代码，生成易于使用的针对Model对象和XML配置文件 的代码
                     type="ANNOTATEDMAPPER",生成Java Model 和基于注解的Mapper对象
                     type="MIXEDMAPPER",生成基于注解的Java Model 和相应的Mapper对象
                     type="XMLMAPPER",生成SQLMap XML文件和独立的Mapper接口
             -->
             <javaClientGenerator targetPackage="com.bjpowernode.crm.settings.mapper"
                                  targetProject="C:/Users/player/Desktop/感恩有你/SSM框架/2022-04/crm-project/crm-ssm/crm/src/main/java" type="XMLMAPPER">
                 <property name="enableSubPackages" value="true"/>
             </javaClientGenerator>
     
     
             <table tableName="tbl_user" domainObjectName="User"
                    enableCountByExample="false" enableUpdateByExample="false"
                    enableDeleteByExample="false" enableSelectByExample="false"
                    selectByExampleQueryId="false">
             </table>
     
     
         </context>
     </generatorConfiguration>
     ```

     **需要注意**

     实体类配置

     ```
      <javaModelGenerator
      //生成实体类存放的包
      targetPackage="com.bjpowernode.crm.settings.domain"
      //生成实体类存放的位置
     targetProject="C:/Users/player/Desktop/感恩有你/SSM框架/2022-04/crm-project/crm-ssm/crm/src/main/java">
     ```

     mapper配置

     ```xml
      <!--Mapper映射文件生成所在的目录 为每一个数据库的表生成对应的SqlMap文件 -->
     <sqlMapGenerator
     //生成mapper文件所在包
     targetPackage="com.bjpowernode.crm.settings.mapper"
     //生成mapper文件所在位置
     targetProject="C:/Users/player/Desktop/感恩有你/SSM框架/2022-04/crm-project/crm-ssm/crm/src/main/java">
     <property name="enableSubPackages" value="false"/>
     </sqlMapGenerator>
     ```

     mapper对应的接口

     ```xml
         <javaClientGenerator
     //生成mapper接口所在包
     targetPackage="com.bjpowernode.crm.settings.mapper"
     //生成mapper接口所在位置
     targetProject="C:/Users/player/Desktop/感恩有你/SSM框架/2022-04/crm-project/crm-ssm/crm/src/main/java" type="XMLMAPPER">
             <property name="enableSubPackages" value="true"/>
         </javaClientGenerator>
     ```

     运行mybatis逆向工程，根据指定的表生成java代码，并且保存到指定目录

  3. 在前端通过jquery获取对应的值，如果是获取元素的指定属性值，主要有两个方法

     - 选择器.attr("属性名")：用来获取不是true/false的属性
     - 选择器.prop("属性名")：用来获取true/false属性的值
     - 这里需要获取的是十天免登录的checkBox所以使用prop("checked");

### **修改：**

1. 对于时间的格式，封装成一个工具类
2. 对于成功失败的标志，封装到一个常量类当中，保证一个项目的标准一致

```java
else if(DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime())>0) {
    //登陆失败，账号已过期
    ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
    ro.setMsg("账号过期");

}
```

## **3.2 把登陆后的用户信息放到每一个页面：**

1. 把控制层代码处理好的数据传递到视图层，使用作用域传递：

   - pageContext：同一个页面不同标签之间传递数据
   - request：同一个请求之间传递数据，请求结束失效
   - session：同一个会话当中，不同请求之间传递数据，浏览器关闭则失效
   - application：所有用户共享数据，并且长久频繁使用，服务器关闭失效

   ```java
   else {
           //登陆成功
           //保存user到session域
           session.setAttribute(Constants.SESSION_USER,user);
           ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
           }
   ```

## **3.3 回车键绑定登陆事件**

```js
//给整个浏览器窗口判定回车事件
$(window).keydown(function (event){
   if(event.key == "Enter"){
      login();
      //让登录按钮触发单击事件
      //$("#loginBtn").click();
   }
})
```

对于选择器.click()等事件，如果click附带函数参数，则代表给事件绑定函数

如果没有函数，则代表触发事件

## **3.3 实现记住密码**

1. 需求：如果用户登录时，点击了十天免登录，则再后续登录过程中，后台自动填上账号密码

2. 分析与设计

   - 如果用户登录成功时，判断用户是否需要记住密码。如果需要，则生成cookie
   - 下次登录时，判断该页面是否有用户的cookie，如果有则自动输入密码

   ```java
   //判断是否需要填写cookie
               if("true".equals(isRemPwd)){
                  Cookie c1 =  new Cookie("loginAct",loginAct);
                  c1.setMaxAge(10*24*60*60);
                   response.addCookie(c1);
                  Cookie c2 = new Cookie("loginPwd",loginAct);
                  c2.setMaxAge(10*24*60*60);
                  response.addCookie(c2);
               }else {
                   //如果不需要记录密码，删除cookie:age设为0，则删除
                   Cookie c1 =  new Cookie("loginAct","1");
                   c1.setMaxAge(0);
                   response.addCookie(c1);
                   Cookie c2 = new Cookie("loginPwd","1");
                   c2.setMaxAge(0);
                   response.addCookie(c2);
               }
   ```

   - 为免登录框增加判断事件

     ```html
     <c:if test="${not empty cookie.loginAct and not empty cookie.loginPwd}">
     <input type="checkbox" id="isRemPwd" checked>
     	</c:if>
     <c:if test="${ empty cookie.loginAct and  empty cookie.loginPwd}">
     <input type="checkbox" id="isRemPwd">
     	</c:if>
     ```

     利用jstl标签，判断cookie中的数据是否存在，如果存在则勾选，否则不勾选

     ### **以下错误，因为只要checkbox有了checked属性，那么就会默认选中**

     这里感觉用jstl太麻烦了，我实在cookie当中再增加了一个关于checked的对象

     ```java
     Cookie c3 = new Cookie("checked","checked");
     c2.setMaxAge(10*24*60*60);
     response.addCookie(c3);
     ```

     只需要在原来的html标签当中为checked属性赋值即可

     ```html
     <input type="checkbox" id="isRemPwd" checked="${cookie.checked.value}"> 十天内记住账号
     ```

## **3.4 安全退出功能**

1. 需求分析：用户点击退出，点击确定后，向后台发出退出请求，清空cookie，销毁session，跳转到首页

2. 分析与设计：

   - 用户选择安全退出，点击确定跳转控制器，给确定按钮绑定单击事件

     ```js
     $("#logoutBtn").click(function (){
     			window.location.href='settings/qx/user/logout.do';
     		})
     ```

   - 请求发送到UserController，UserController进行session销毁和cookie清空

     ```java
     @RequestMapping("logout.do")
     public String logout(HttpSession session,HttpServletResponse response){
         //清空cookie
         Cookie c1 =  new Cookie("loginAct","0");
         c1.setMaxAge(0);
         response.addCookie(c1);
         Cookie c2 = new Cookie("loginPwd","0");
         c2.setMaxAge(0);
     
         //销毁session
         session.invalidate();
         //跳转到项目首页
         /*
         * 注意这里不能定位index所在，因为重定向redirect不能访问WEB-INF目录的资源
         * */
         return "redirect:/";
     }
     ```

## 3.5 登录验证功能

1. 需求分析：用户访问任何业务资源都要进行登陆验证，如果验证不通过的用户访问任何业务资源都会跳转登陆页面

2. 分析与设计：

   - 用户每发起一个请求，都要通过过滤器过滤或者拦截器拦截，但是过滤器功能不如拦截器，而且框架大多支持拦截器，所以我们使用拦截器

3. 实现

   - 过滤器

     Implements Filter{

     --init

     --doFilter

     --destroy

     }

     配置web.xml

   - 拦截器

     Implements HandlerInterceptor{

     --pre

     --post

     --after

     }

     ```java
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过session判断用户是否登录
         HttpSession session = request.getSession();
         if(null==session.getAttribute(Constants.SESSION_USER)){
             //手动重定向要带上项目名称
             String path = request.getContextPath();
             response.sendRedirect(path);
             return false;
         }else
             return true;
     
          }
     ```

     拦截时间选择在控制器方法执行前

     配置springmvc.xml

     ```xml
     <mvc:interceptors>
         <mvc:interceptor>
             <mvc:mapping path="/settings/**"/>
             <mvc:mapping path="/workbench/**"/>
             <!--排除拦截请求-->
             <mvc:exclude-mapping path="/settings/qx/user/toLogin.do"/>
             <mvc:exclude-mapping path="/settings/qx/user/login.do"/>
             <!--拦截器所在类-->
             <bean class="com.bjpowernode.crm.web.interceptor.LoginInterceptor"/>
         </mvc:interceptor>
     </mvc:interceptors>
     ```

     需要注意，除了设置拦截的path意外，还要对登陆页面以及登录请求进行排除，否则无法进入系统

## 页面切割技术

1. frameSet和frame；效率慢，用户体验不好

   - <frameSet>：用来切割页面

     <frmaeset cols="20%.60%,20%" rows="10%,80%,10%">

   - <frame>：显示页面

     <frame src="url">

     每一个frame标签都是一个独立的浏览器窗口

   - 两者搭配

   - <frameset cols="20%,60%,20%">

     ​		<frame src="url1">

     ​		<frame src="url2">

     ​		<frame src="url3">

     ​	</frameset>

2. (<div>)和(<frame>)标签

   - div切割页面：

     (<div style="hight:10%;width=20%">)

   - iframe显示页面

     (<iframe href="url"/>)

   - div当中嵌套iframe即可

# 4.0 工作台页面分析

- 跳转主页面index，由于浏览器无法直接跳转WEB-INF目录下的资源，所以跳转请求需要以controller为桥梁

**模态窗口：**

- 模态窗口：本质上是原来页面的一个div，通过设置z-index的大小实现，通过增加class modal fade 来由bootstrap控制
  - 初始状态，z-index的参数小于0，所以不显示
  - 需要显示时，框架修改z-index的值，模态窗口即可显示
  - **如何控制模态窗口显示与隐藏**
    - 给按钮增加 data-toggle="modal" data-target=""，单击按钮时即可控制对应target的显示、隐藏
    - 通过js函数控制：选择器选中div模态窗口，使用modal(hide/show)
    - 通过标签的属性 data-dismiss=""，用户单击标签时会自动关闭标签所在窗口(关闭按钮)
- 传统窗口：window.open("url","_blank")，将url对应的页面弹出新窗口
  - 这样弹出的网页是相互独立的，两个窗口是父子关系，需要获取相互需要document.parentElement/children

# 5.0 市场活动模块实现

## 5.1 创建市场活动相关

### **5.1.1 需求分析：**

- 用户在市场活动主页面，点击创建市场活动会弹出创建市场活动模态窗口

- 用户输入信息后，点击保存，将数据传递到后台处理

  特殊需求

  - 所有者是通过从后台数据库获取的动态列表
  - 所有者和名称不能为空
  - 结束日期不能比开始日期小
  - 成本只能为非负整数
  - 创建成功时关闭模态窗口，刷新市场活动列表，显示第一页数据，保持每页显示条数不变
  - 创建失败时，提示信息，模态窗口不关闭，市场活动列表不刷新

### **5.1.2 分析与设计：**

- 用户点击市场活动菜单，跳转请求到ActivityController

- controller向后台发出请求，获取用户列表，调用UserService

- UserSerivce增加获取用户列表方法，mapper增加查询所有用户方法，返回userList到上层

- 最上层jindex.jsp对传递过来的数据进行处理

  ```js
  <%--给模态窗口用户下拉列表赋值--%>
  		var html="";
  		<c:forEach items="${users}" var="u">
  		html += "<option value='${u.id}'>${u.name}</option>"
  		</c:forEach>
  		$("#create-marketActivityOwner").html(html);
  		$("#edit-marketActivityOwner").html(html);
  
  ```

  我将jstl以及对下拉列表的赋值放到了js代码块当中，通过jstl标签对得到的数据进行循环，加入html当中，再给模块赋值

- 用户点击创建弹出创建活动模态窗口，输入信息点击保存，将保存请求添加到后台

- ActivityController将数据传递到ActivityService，调用创建市场活动方法

- service向mapper传递数据，请求插入数据insert语句

- mapper将插入情况返回，依次返回到最上层，生成响应信息，生成json穿返回给前端

- 前端根据返回的json串更新页面：code：1/0 message：xxxx

### **5.1.3 编码设计**

- 通过mybatis逆向工程生成表单对应的mapper

  1. 将三个包名的settings更改为workbench
  2. 将选中的表注释掉，修改为现阶段需要的activity表

- 将生成的workbench目录下的mapper添加到spring配置文件扫描

  ```xml
  <!-- mapper注解扫描器配置,扫描@MapperScan注解,自动生成代码对象 -->
  <bean id="mapperScanner" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
      <property name="basePackage" 
                value="com.bjpowernode.crm.settings.mapper,
                com.bjpowernode.crm.workbench.mapper,
                                             "/>
      <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
  </bean>
  ```

- 创建service业务类，并将spring对于service的扫描增加一个workbench下的service包

- controller类，返回json串，可以封装一个Object，再以@ResponseBody注释即可，同时接收数据可以直接接收实体类对象Activity，接收过来的数据提前封装好

- 编辑index页面请求，js代码：

  每次打开模态窗口清空数据，需要获取到表单的DOM元素，通过jq对象的[0]或者get(0)获得

  ```js
  $("#createBtn").click(function(){
     //清空模态窗口数据
     $("#create-form")[0].reset();
     //清空提示信息
     $(".msg").text("");
     //显示模态窗口
     $("#createActivityModal").modal("show");
  })
  $("#saveBtn").click(function(){
     $(".msg").text("");
     var owner=$("#create-marketActivityOwner").val();
     var name = $.trim($("#create-marketActivityName").val());
     var startDate=$("#create-startDate").val();
     var endDate = $("#create-endDate").val();
     var cost = $.trim($("#create-cost").val());
     var description = $.trim($("#create-description").val());
     //判断数据是否符合要求
     if(owner == ""){
        $("#owner-msg").text("所有者不可为空");
        return;
     }if(name == ""){
        $("#name-msg").text("名称不可为空");
        return;
     }if(startDate!==""&&endDate!=""){
        if(startDate > endDate){
           $("#date-msg").text("结束日期应该在开始日期之后");
           return;
        }
     }
     /*
     * 正则表达式：
     * 1.正则表达式是一种语言，有自己的语法，可以用来判断字符串是否符合规定的格式
     * 2.var reqExp = /^(([1-9]\d*)|0)$/; 表示1-9匹配多个或者0
     * */
     var reqExp = /^(([1-9]\d*)|0)$/;
     if(!(cost==""||reqExp.test(cost))){
        $("#cost-msg").text("成本应该为非负整数");
        return;
     }
      //下面位ajax请求
  ```

**正则表达式：**

- 语法通则：

  1. //：表示在js当中定义一个正则表达式 var reqExp = /...../;

  2. []：表示匹配指定字符集中的一位字符 var reqExp = /[abc]/;表示这个变量只能定义a或者b或者c

  3. ^：表示匹配字符串的开头位置

  4. $：表示匹配字符串的结尾var reqExp = /^[abc]$/，26个字符 a~z

  5. {}：表示匹配次数，var reqExp = /^[a~z0~9]{5}$/，表示a-z 0-9匹配5位

     ​		{m}:匹配m次

     ​		{m,n}匹配m到n次

     ​		{m,}表示匹配至少m次

  6. 特殊符号：

     - \d：匹配一位数字
     - \D：匹配以为非数字
     - \w：表示匹配所有字符，包括字母(所有语言)、数字、下划线
     - \W：表示匹配所有非字符
     - *：表示匹配0次或者多次
     - +：匹配1次或者多次
     - ？：表示匹配0次或者1次

  对于cost限定非负整数：

  ```js
   /*
     * 正则表达式：
     * 1.正则表达式是一种语言，有自己的语法，可以用来判断字符串是否符合规定的格式
     * 2.var reqExp = /^(([1-9]\d*)|0)$/; 表示1-9匹配多个或者0
     * */
     var reqExp = /^(([1-9]\d*)|0)$/;
     if(!(cost==""||reqExp.test(cost))){
        $("#cost-msg").text("成本应该为非负整数");
        r
  ```

**对时间输入增加日历插件**

这里我们使用：bootstrap——datetimepicker

**使用步骤**

1. 引入开发包：下载并拷贝开发包到webapp目录下

   <link>/<script>

   ```html
   <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
   
   <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
   <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
   ```

2. 根据大小创建容器<input>/<div>，将插件运行结果输出到容器标签

3. 加载完成容器之后，对容器调用工具函数

   - 对需要用到的容器赋予class = "time"

     ```html
     <input type="text" class="form-control time" id="create-startDate">
     ```

     

   - 在入口函数当中对class为time的容器进调用datetimepicker()方法，并设置参数

     ```js
     /*
        *  设置time类属性 年月日
        * */
     $(".time").datetimepicker({
        minView: "month",
        language:  'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left"
     });
     ```

   - 如果对时间输入框设置disable，则无法修改也无法提交

   - 如果对时间输入框设置为readonly，则选择后无法删除

   - 可以在增加插件参数，clearBtn：true

## 5.2 查询市场活动

**需求分析：**

- 当市场活动页面加载完毕后，显示第一页数据
- 用户市场活动主页填写查询条件后，显示符合条件的活动列表第一页数据
- 保持每页显示条数不变
- 实现翻页功能
  - 市场活动主页面，显示市场活动列表和记录的总条数
  - 默认每页显示10条

**分析与设计：**

- 用户发送请求，转到市场活动主页面index.jsp，页面加载完毕后，通过ajax请求到后台市场活动控制器ActivityController，控制器将查询活动的数据(每页显示数量，开始页码),获取第一页市场活动的列表
- 前端接收到市场活动列表，通过分页插件显示在页面
- 用户点击查询按钮、点击分页插件，或者切换显示条数都会向后台发送ajax请求，查询符合条件的数据，传递到前端分页处理
- 前端ajax接收响应，处理页面

**编码设计：**

- 通过一句sql向后台获取符合条件的数据项

  ```sql
   <select id="selectActivityList" parameterType="map" resultMap="BaseResultMap">
      select a.id,u1.name as owner,a.name,a.start_date,a.end_date,a.cost,
                  a.description,a.create_time,u2.name as create_by,
                  a.edit_time,u3.name as edit_by
              from tbl_activity a
                  join tbl_user u1
                   on     u1.id = a.owner
                  join tbl_user u2
                   on     u2.id = a.create_By
                  left join tbl_user u3
                   on     u3.id = a.edit_By
                      <where>
                          <if test="name!=null and name!=''">
                             a.name like '%' #{name} '%'
                          </if>
                          <if test="owner!=null and owner!=''">
                            and u1.name like '%' #{name} '%'
                          </if>
                          <if test="startDate!=null and startDate!=''">
                            and a.start_date &gt;= #{startDate}
                          </if>
                          <if test="endDate!=null and endDate!=''">
                            and a.end_date &lt;= #{endDate}
                          </if>
                      </where>
                          order by a.create_time desc
                          limit #{beginNo} , #{pageSize}
    </select>
  ```

  注意，还需要获取符合条件的数据项总个数，除了不需要分页和排序，其他都和查询数据一致

  **此处的错误**：没有使用where标签导致的语法出错

- **前端通过分页插件进行分页操作**

  **使用步骤：**

  1. 引用开发包：插件基于jquery，所以前提是需要引用jquery

     ```js
     <!--分页插件--><link rel="stylesheet" type="text/css" href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css"><script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script><script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>
     ```

  2. 创建容器：创建div用来放置生成的卡片

  3. 容器加载完成后，对容器调用工具函数，在页面加载完成后调用，因为需要一些ajax返回的参数，所以放在success方法当中

     ```js
     success:function(data){				var html ="";				$.each(data.activityList,function(i,n){					html +='<tr class="active">';					html +='	<td><input type="checkbox" value="'+n.id+'"/></td>';					html +='	<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'detail.html\';">'+n.name+'</a></td>';					html +='	<td>'+n.owner+'</td>';					html +='	<td>'+n.startDate+'</td>';					html +='	<td>'+n.endDate+'</td>';					html +='</tr>';				})				$("#activityBody").html(html);				//计算总页数				var totalPages = 1;				if(data.totalRows%pageSize==0){					totalPages = data.totalRows/pageSize;				}else{					totalPages = parseInt(data.totalRows/pageSize)+1;				}				//分页插件函数				$("#page").bs_pagination({					currentPage:pageNo,						//当前页号					rowsPerPage:pageSize,					//每页显示条数					totalRows:data.totalRows,				//总条数					totalPages:totalPages,					//总页数					visiblePageLinks:5,						//最多显示卡片数					showGoToPage:true,						//控制是否显示跳转页面,默认为true					showRowsPerPage:true,					//是否显示每页显示条数，默认是true					showRowsInfo:true,						//是否显示记录信息，默认是true					onChangePage:function(event,pageObj){	//用户切换页面时发生的事件，每次切换页面都返回切换页号之后的pageNo和pageSize						pageList(pageObj.currentPage,pageObj.rowsPerPage);					}				})			}
     ```

     

### 前端部分知识

**js当中的遍历方法**

- jstl标签：遍历作用域当中的变量
- jquery方法each：用来遍历js变量
- 页面加载完成后，入口函数当中调用方法

**给标签增加页面片段**

- 选择器.html("")：覆盖原有html
- 选择器.text("")：覆盖标签原有文本
- 选择器.append("")：标签内部最后增加
- 选择器.after("")：标签后面追加
- 选择器.before("")：标签前面追加

**js常用系统函数**

- eval("")：模拟执行字符串当中的代码，eval("var a=100")，可以在外面alert(a)

- parseInt()，获取数值的整数部分，这里通过该函数获取分页数的整数部分

  ```js
  //计算总页数var totalPages = 1;if(data.totalRos%pageSize==0){   totalPages = data.totalRows/pageSize;}else{   totalPages = parseInt(data.totalRows/pageSize)+1;}
  ```

### **错误**

- jquery-1.11.1-min.js:2 Uncaught RangeError: Maximum call stack size exceeded

  这是由于jquery在获取参数时无法获取，导致陷入死循环而出现的堆栈溢出，我错的原因是在获取元素value时，忘记了增加.val()，加上val()即可

## 5.3 删除市场活动

**需求分析：**

- 用户在市场活动主页面选择要删除的市场活动，点击删除按钮，弹出确认窗口
- 用户点击确认，完成删除市场活动删除功能
- 每次至少删除一条市场活动
- 批量删除
- 删除成功之后刷新市场活动列表
- 删除失败提示失败信息，列表不刷新

**分析与设计：**

- 用户在市场活动主页面，选择市场活动(勾选复选框)，点击删除按钮触发事件
- 弹出确认删除对话框，confirm方法，如果点击确定进入ajax请求向后台发起请求
- 后台接收前端传递过来的id数组，向后台发起删除数据请求
- 返回删除结果，删除成功则调用获取市场活动列表方法，否则弹出提示

### 前端部分知识

**在页面给元素添加事件的语法**

1. 使用元素的时间属性on***("function()")

2. 通过jquery选择器.**给元素增加事件

   只能给固有元素添加事件，不能给动态生成的元素添加事件，因为执行的是ajax请求，所以执行的时候不会等待其他函数执行，所以选择器无法获得动态元素

3. jquery的on标签，先获取动态元素的固定父元素

   夫选择器.on('事件类型',子选择器,事件函数(){})

   ```
   $("#activityBody").on("click",$("input[name=xz]"),function () { $("#qx").prop("checked",$("input[name=xz]").length==$("input[name=xz]:checked").length);})
   ```

### 错误

- jquery-1.11.1-min.js:4 Uncaught TypeError: Illegal invocation

  由于ajax请求当中把获取的jquery对象当成了字符串用来赋值

- index.do:55 Uncaught TypeError: $(...).modal is not a function

  原因是bootstrap的引入在jquery之前，导致无法获取jquery支持

## 5.4 修改市场活动

**需求分析**

- 用户选择一个要修改的市场活动，点击更新按钮，弹出相关市场活动的模态窗口
- 用户在模态窗口填写表单，单击更新按钮完成修改
- 修改成功之后关闭模态窗口并刷新市场活动列表， 但是保证页号和显示条数不变
- 修改失败提示失败信息，不关闭模态窗口，不刷新列表

**分析与设计**

- 用户对一个活动点击修改按钮，发起ajax请求，从后台获取对应活动的信息
- 后台返回对应的市场活动信息，回调函数处理页面，将数据放到表单
- 用户修改表单信息，点击更新，将修改情况保存到后台



### 错误

1. Cause: java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed
   ; Connection is read-only. Queries leading to data modification are not allowed; nested exception is java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed

   **错误原因：**事务配置中，没有service当中的方法(我创建的方法modify开头，事务没有配置)

   还有就是，异常返回的code设置错误，返回了正确的code，导致不知道发生了异常， 后续调试才发现有异常发生

   

## 5.5 导出市场活动列表

**需求分析：**

- 用户在市场活动主页面，点击批量导出按钮，把所有市场活动生成一个excel文件，弹出文件下载对话框
- 用户选择保存的目录，完成到处市场活动功能
- 不需要刷新页面

**分析与设计：**

- 用户单击导出按钮，发起ajax请求到后台控制器
- controller向后台获取所有市场活动信息
- 生成一个excel文件，将查询出来的市场活动写入，将文件输出到浏览器
- 用户选择文件下载目录，保存到用户本机

**技术准备：**

1. java生成excel文件：io流（X）无法处理有格式的文件，需要图形化api：iText,poi(免费)

   - 关于办公文档插件使用的基本思想：

   ​	把办公文档的所有元素封装成普通的java类，程序员通过操作这些类，达到操作办公文档的目的

   - **excel文件的元素：**

     - 文件：HSSFWorkbook
     - 页：HSSFSheet
     - 行：HSSFRow
     - 列：HSSFCell
     - 样式：HSSFCellStyle

   - 使用apache-poi生成excel：

     1. 添加依赖

        ```xml
        <!--poi依赖--><dependency>  <groupId>org.apache.poi</groupId>  <artifactId>poi</artifactId>  <version>5.2.2</version></dependency>
        ```

     2. 使用封装类生成excel文件并且写入数据

        ```java
          public void textPoi() throws IOException {        //创建HSSFWorkbook文件对应一个excel文件        HSSFWorkbook wb = new HSSFWorkbook();        //在对应的文件创建页        HSSFSheet sheet = wb.createSheet("user");        //对应页创建行        HSSFRow row = sheet.createRow(0);//行号，从0开始，依次递增        HSSFRow row1 = sheet.createRow(1);        //行中创建列        HSSFCell cell = row.createCell(0);//列号，从0开始        //设置样式,创建HSSFCellStyle        HSSFCellStyle style = wb.createCellStyle();        //居中        style.setAlignment(HorizontalAlignment.CENTER);        cell.setCellStyle(style);        cell.setCellValue("学号");        row.createCell(1).setCellValue("姓名");        row.createCell(2).setCellValue("班别");        row1.createCell(0).setCellValue("3119005194");        row1.createCell(1).setCellValue("廖振宇");        row1.createCell(2).setCellValue("19软工5");        //调用工具函数生成excel文件        FileOutputStream os = new FileOutputStream("C:\\Users\\player\\Desktop\\感恩有你\\SSM框架\\SSM\\src\\user.xls");        wb.write(os);        //关闭资源        os.close();        wb.close();    }}
        ```

2. 文件下载

   所有文件下载只能发同步请求

   ```java
     //设置相应类型为excel文件        response.setContentType("application/octet-stream");        //浏览器接收到响应信息后，默认在浏览器直接打开，还会调用应用程序，是在打不开才会下载        //所以我们需要设置响应头信息，使浏览器接收到响应后直接进行下载        response.addHeader("Content-Disposition","attachment;filename=user.xls");        //通过输出流将文件写出        OutputStream out = response.getOutputStream();        ...        wb.write(out);        wb.close();        //out由tomcat自动关闭        out.flush();
   ```

   

**需求分析**

- 用户点击批量导出按钮，向后台发送获取市场活动列表请求
- 后台将市场活动信息导出到一个xls文件当中，发送到页面供下载



**分析与设计**

- 用户点击导出按钮，需要发送同步请求，使用window.location.href定位下载服务

- 请求到controller，controller通过访问service，获取所有的市场活动，返回activityList

- controller将list打包到excel文件当中，将文件通过输出流，输出到前端

  ```java
  @RequestMapping("export.do")    public void exportAllActivities(HttpServletResponse response) throws IOException {        //通过业务层获取列表        List<Activity> list = activityService.selectActivities();        //床架excel文件        HSSFWorkbook wb = new HSSFWorkbook();        //在对应的文件创建页        HSSFSheet sheet = wb.createSheet("activities");        //对应页创建行        HSSFRow row = sheet.createRow(0);//行号，从0开始，依次递增        //行中创建列        /*        * 创建表头        * */        HSSFCell cell = row.createCell(0);//列号，从0开始            cell.setCellValue(" id");        cell=row.createCell(1);            cell.setCellValue(" owner");        cell=row.createCell(2);            cell.setCellValue(" name");        cell=row.createCell(3);            cell.setCellValue(" start_date");        cell=row.createCell(4);            cell.setCellValue(" end_date");        cell=row.createCell(5);            cell.setCellValue("  cost");        cell=row.createCell(6);            cell.setCellValue(" description ");        cell=row.createCell(7);            cell.setCellValue(" create_time");        cell=row.createCell(8);            cell.setCellValue(" create_by");        cell=row.createCell(9);            cell.setCellValue(" edit_time");        cell=row.createCell(10);            cell.setCellValue(" edit_by");        //判断list是否为空        if(list!=null&&list.size()>0){        //遍历list集合，将数据写入        Activity activity = null;        /*        * 输入数据        * */        for(int i=0;i<list.size();i++) {            activity = list.get(i);            //对应页创建行            row = sheet.createRow(i+1);//行号，从0开始，依次递增            //行中创建列            cell = row.createCell(0);//列号，从0开始            cell.setCellValue(activity.getId());            cell = row.createCell(1);            cell.setCellValue(activity.getOwner());            cell = row.createCell(2);            cell.setCellValue(activity.getName());            cell = row.createCell(3);            cell.setCellValue(activity.getStartDate());            cell = row.createCell(4);            cell.setCellValue(activity.getEndDate());            cell = row.createCell(5);            cell.setCellValue(activity.getCost());            cell = row.createCell(6);            cell.setCellValue(activity.getDescription());            cell = row.createCell(7);            cell.setCellValue(activity.getCreateTime());            cell = row.createCell(8);            cell.setCellValue(activity.getCreateBy());            cell = row.createCell(9);            cell.setCellValue(activity.getCreateTime());            cell = row.createCell(10);            cell.setCellValue(activity.getCreateBy());        }        }        //将生成的xls文件,导出        OutputStream os = new FileOutputStream("C:\\Users\\player\\Desktop\\感恩有你\\SSM框架\\SSM\\src\\activity.xls");        wb.write(os);        wb.close();        os.close();        response.setContentType("application/octet-stream");        //浏览器接收到响应信息后，默认在浏览器直接打开，还会调用应用程序，是在打不开才会下载        //所以我们需要设置响应头信息，使浏览器接收到响应后直接进行下载        response.addHeader("Content-Disposition","attachment;filename=user.xls");        //通过输出流将文件写出        OutputStream out = response.getOutputStream();        //读excel文件        /*        * 通过输入流读取文件再写道输出流        * */        InputStream is = new FileInputStream("C:\\Users\\player\\Desktop\\感恩有你\\SSM框架\\SSM\\src\\activity.xls");        byte[] buff = new byte[256];        int len=0;        while((len=is.read(buff))!=-1){            out.write(buff,0,len);        }        is.close();        wb.write(out);        out.flush();    }
  ```

- 代码优化

  由于wb对象将数据写入磁盘，后续还需要将文件从磁盘读出，需要浪费大量时间，代码也冗长

  相当于把数据从内存放到磁盘，再读到内存，再传到输出流

  将把文件输出到磁盘过程省略，直接从workbook输出到响应流即可

  ```java
  response.setContentType("application/octet-stream");//浏览器接收到响应信息后，默认在浏览器直接打开，还会调用应用程序，是在打不开才会下载//所以我们需要设置响应头信息，使浏览器接收到响应后直接进行下载response.addHeader("Content-Disposition","attachment;filename=user.xls");//通过输出流将文件写出OutputStream out = response.getOutputStream();wb.write(out);
  ```

  这样，就相当于直接把数据从内存传递到内存当中

## 5.6 导入市场活动

**需求分析**

- 用户上传规定格式的数据列表文件，服务器将文件传到后台处理
- 后台将文件存储到数据库，将处理结果发送到前端
- 前端根据传递的导入结果，处理页面

**技术准备：**

- 文件上传

  - 文件上传表单需要满足三个条件

    1. 表单组件标签只能使用<input type="file" name=""/>
    2. 请求方式只能用post不能用get
       - get只能提交文本，post可以提交各种媒体
       - get参数通过请求头提交到后台，长度要求不能太长；post参数放在请求体当中，长度可以比get方式长的多，理论上可以无限制
    3. 表单编码格式之呢个使用multipart/form-data
       - Http协议规定，浏览器每次向后台提交参数时，都会对参数进行统一编码；默认采用编码格式是urlencoded，这种编码格式只能对文本数据进行编码
       - 浏览器每次向后台提交参数，都会先把所有参数转换成字符串，然后对这些数据统一进行urlencoded编码
       - multipart/form-data的目的就是为了阻止上述行为的发生

  - 配置springmvc文件解析器

    ```xml
    <!-- 配置文件上传解析器 id:必须是multipartResolver--><bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">    <property name="maxUploadSize" value="#{1024*1024*5}"/>    <property name="defaultEncoding" value="utf-8"/></bean>
    ```

  - 前端通过input上传文件

    ```html
    <form action="workbench/activity/upload.do" method="post" enctype="multipart/form-data">    <input type="file" name="myFile"/>    <input type="text" name="userName"/>    <input type="submit" value="提交"/></form>
    ```

  - 后台使用MultipartFile接收文件

    ```java
    @RequestMapping("upload.do")public void upload(String userName, MultipartFile myFile) throws IOException {    //文本数据打印    System.out.println("username:"+userName);    //将文件内容读出    File file = new File("C:\\Users\\player\\Desktop\\感恩有你\\SSM框架\\SSM\\src\\",myFile.getName()+myFile.getOriginalFilename());   myFile.transferTo(file);   byte[] bytes = myFile.getBytes();   for(byte b:bytes){       System.out.print(b+" ");   }
    ```

- excel文件解析

  - 使用图形化api：apache-poi解析文件

    - 获取HSSFWorkbook对象，通过输入流：

      ```
      	HSSFWorkbook wb = new HSSFWorkbook(InputStream);​	InputStream is = myFile.getInputStream();
      ```

      

    - 获取页HSSFSheet对象

      ```
      	HSSFSheet sheet = wb.getSheetAt(index);
      ```

      

    - 通过页获取行

      ```
      	HSSFRow row = sheet.getRow(index);
      ```

      

    - 遍历每行数据

      ```
      	for(int i=0;i<sheet.getLastRowNum()+1;i++){//获取的最后一行下标		row=sheet.getRow(i);		...	}
      ```

    - 行循环当中遍历获取列，获取列中数据

      ```
      for(int j=0;j<row.getLastCellNum();j++){//获取的是最后一列下标+1		cell = row.getCell(j);		cell.}
      ```

  - ```java
    HSSFWorkbook wb = new HSSFWorkbook(myFile.getInputStream());HSSFSheet sheet = wb.getSheetAt(0);HSSFRow row = null;HSSFCell cell = null;String type;for(int i = 0;i<sheet.getLastRowNum()+1;i++){    row = sheet.getRow(i);    for(int j=0;j<row.getLastCellNum();j++){        cell = row.getCell(j);       System.out.println(HSSFUtils.getCellValueForStr(cell)+" ");    }    //每一行所有列都打完了，执行换行    System.out.println();}
    ```

    - 关于获取列中值的工具方法

      ```java
      /** * excel文件操作工具类 * */public class HSSFUtils {    public static String getCellValueForStr(HSSFCell cell){        String ret="";        switch (cell.getCellType()){            case STRING :                ret = cell.getStringCellValue();                break;            case NUMERIC:                ret = cell.getNumericCellValue()+"";                break;            case BOOLEAN:                ret = cell.getBooleanCellValue()+"";                break;            case FORMULA:                ret = cell.getCellFormula();                break;            default:                break;        }        return ret;    }}
      ```

**分析与设计**

- 用户点击导入功能，弹出模态窗口

- 模态窗口中用户选择上传文件，点击导入触发单击事件，向后台发送异步请求，将文件传递到后台controller

- controller将文件数据解析，封装到对象当中，传递到业务层调用dao将数据存储到数据库，返回存储结果

- 关于上传文件：

  要提前约定好文件的格式，最好在前端提供一个模板给用户下载

  js截取字符串

  str.substr(startIndex,length)

  str.substring(startIndex,endIndex)或者只有start，截取到最后

**编码实现：**

控制层代码

```java
/**     * 上传excel文件并处理     * */    @RequestMapping("importActivity.do")    @ResponseBody    public Object importActivity(MultipartFile activityFile,HttpSession session){        HSSFWorkbook wb = null;        User user = (User) session.getAttribute(Constants.SESSION_USER);        String owner = user.getId();        ReturnObject ro = new ReturnObject();        try {            wb = new HSSFWorkbook(activityFile.getInputStream());            //上传文件只有1页，如果多页则需要多加一个循环            HSSFSheet sheet = wb.getSheetAt(0);            HSSFRow row = null;            HSSFCell cell = null;            //第一行是属性名，无需解析            Activity activity  = null;            List<Activity> activityList = new ArrayList<>();            for(int i = 1;i<sheet.getLastRowNum()+1;i++){                //每一行对应一个市场活动记录                activity = new Activity();                activity.setId(UUIDUtils.getUUID());                activity.setOwner(owner);                activity.setCreateTime(DateUtils.formatDateTime(new Date()));                activity.setCreateBy(owner);                row = sheet.getRow(i);                for(int j=0;j<row.getLastCellNum();j++){                    cell = row.getCell(j);                    String value = HSSFUtils.getCellValueForStr(cell);                    switch (j){                        case 0:                            activity.setName(value);                            break;                        case 1:                            activity.setStartDate(value);                            break;                        case 2:                            activity.setEndDate(value);                            break;                        case 3:                            activity.setCost(value);                            break;                        case 4:                            activity.setDescription(value);                            break;                        default:                            break;                    }                }                activityList.add(activity);            }                //向业务层发起请求            int flag = activityService.saveActivityByList(activityList);            //判断是否插入成功            ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);            ro.setRetData(flag);        } catch (IOException e) {            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);            ro.setMsg("导入失败");        }        return ro;    }
```

前端代码

```js
	//导入按钮单击事件		$("#importActivityBtn").click(function(){			$("#importMsg").text("");			/*验证文件名*/			var name = $("#activityFile").val();			var suffix = name.substr(name.lastIndexOf(".")+1).toLowerCase();//获取最后一个.后面的后缀,转成小写			//判断后缀名是否符合要求			if(suffix != "xls"){				$("#importMsg").text("请选择xls文件");				return;			}			//获取文件DOM对象的文件，浏览器只支持选一个			var activityFile = $("#activityFile")[0].files[0];			/*验证文件大小:判断文件大小是否为5M				*/			if(activityFile.size>5*1024*1024){				$("#importMsg").text("文件过大，请选择小于5MB的文件");				return;			}			/*			* 发送异步请求			* FromData是ajax提供的接口			* FromData可以模拟键值对，向后台提交参数，不但能提交文本数据，还能提交二进制数据			* */			var fromData = new FormData();			fromData.append("activityFile",activityFile);			$.ajax({				url:"workbench/activity/importActivity.do",				data:fromData,				processData:false,//设置ajax提交数据前，是否把参数转为字符串，默认是true				contentType:false,//设置ajax提交数据前，是否把所有参数统一按urlencoded编码，默认为true				type:"post",				dataType:"json",				success:function(data){					if(data.code=='1'){						$("#importActivityModal").modal('hide');						alert("成功导入"+data.retData+"条记录！");						pageList(1,$("#pageSize").val());					}else {						alert(data.msg);					}				}			})		})
```

**主要涉及重要技术**

1. 后台关于excel解析插件的使用

   ```java
   wb = new HSSFWorkbook(activityFile.getInputStream());            //上传文件只有1页，如果多页则需要多加一个循环            HSSFSheet sheet = wb.getSheetAt(0);            HSSFRow row = null;            HSSFCell cell = null;            //第一行是属性名，无需解析            Activity activity  = null;            List<Activity> activityList = new ArrayList<>();            for(int i = 1;i<sheet.getLastRowNum()+1;i++){                //每一行对应一个市场活动记录                activity = new Activity();                activity.setId(UUIDUtils.getUUID());                activity.setOwner(owner);                activity.setCreateTime(DateUtils.formatDateTime(new Date()));                activity.setCreateBy(owner);                row = sheet.getRow(i);                for(int j=0;j<row.getLastCellNum();j++){                    cell = row.getCell(j);                    String value = HSSFUtils.getCellValueForStr(cell);                    switch (j){                       ...                    }                }                activityList.add(activity);
   ```

   需要注意，如果是有多页数据，也需要对页进行循环处理，同理可能会有多个excel文件的时候，则是对文件wb进行循环处理

2. dao Sql语句

   ```sql
   <insert id="insertActivityByList" parameterType="com.bjpowernode.crm.workbench.domain.Activity">    insert into tbl_activity(id, owner, name, start_date,end_date, cost, description,create_time, create_by)        values            <foreach collection="list" item="obj" separator=",">              (#{obj.id},#{obj.owner},#{obj.name},#{obj.startDate},#{obj.endDate},#{obj.cost},#{obj.description},#{obj.createTime},#{obj.createBy})            </foreach>  </insert>
   ```

3. ajax提供的fromData接口，用来实现字节文件的传输

   ```js
   /** 发送异步请求* FromData是ajax提供的接口* FromData可以模拟键值对，向后台提交参数，不但能提交文本数据，还能提交二进制数据* */var fromData = new FormData();fromData.append("activityFile",activityFile);
   ```

   这种传递方式与ajax当中data的键值对类似，最后将fromData放到ajax data属性即可。可以将多个属性append到fromData中

   ```js
   $.ajax({   url:"workbench/activity/importActivity.do",   data:fromData,   processData:false,//设置ajax提交数据前，是否把参数转为字符串，默认是true   contentType:false,//设置ajax提交数据前，是否把所有参数统一按urlencoded编码，默认为true   type:"post",   dataType:"json",
   ```

4. 文件类型、大小的判断

   ```js
   /*验证文件名*/var name = $("#activityFile").val();var suffix = name.substr(name.lastIndexOf(".")+1).toLowerCase();//获取最后一个.后面的后缀,转成小写//判断后缀名是否符合要求if(suffix != "xls"){   $("#importMsg").text("请选择xls文件");   return;}//获取文件DOM对象的文件，浏览器只支持选一个var activityFile = $("#activityFile")[0].files[0];/*验证文件大小:判断文件大小是否为5M   */if(activityFile.size>5*1024*1024){   $("#importMsg").text("文件过大，请选择小于5MB的文件");   return;}
   ```

   文件类型判断，是通过获得上传的文件名最后一个.后面的扩展名判断的

   而文件大小判断则是，通过获取文件的dom对象的files属性的第一个对象，规定上可以有多个对象，但是浏览器只支持单个文件上传
