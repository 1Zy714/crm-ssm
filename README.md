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
