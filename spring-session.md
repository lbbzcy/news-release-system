#使用Spring Session实现分布式的Session共享
1. 添加依赖

        <dependency>
    		<groupId>org.springframework.session</groupId>
    		<artifactId>spring-session-data-redis</artifactId>
    		<version>1.2.0.RELEASE</version>
        </dependency>
2. spring-mvc.xml配置文件添加：
	
	    <!-- 将session放入redis -->
    	<context:annotation-config/>
    	<bean id="redisHttpSessionConfiguration"  class="org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration" >
    	<property name="maxInactiveIntervalInSeconds" value="1800" />
     	</bean>
    	<bean class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
    	<!-- redis 配置 -->
	    <property name="hostName" value="127.0.0.1" />
	    <property name="port" value="6379" />
	    </bean>
3. web.xml添加
    
    	<context-param>
	    <param-name>contextConfigLocation</param-name>
	    <param-value>classpath:spring-mvc.xml</param-value>
	    </context-param>
	    <!-- 分布式Session共享Filter -->
	    <filter>
	    <filter-name>springSessionRepositoryFilter</filter-name>
	    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	    </filter>
	    <filter-mapping>
	    <filter-name>springSessionRepositoryFilter</filter-name>
	    <url-pattern>/*</url-pattern>
	    </filter-mapping>
这样就可以实现分布式Session了。

注意：
 
1. spring的这个配置文件一定要写在web.xml的<context-param>部分，写在其他地方不行。
2. filter的名字必须是springSessionRepositoryFilter
3. 如果使用了shiro，web.xml中一定要把<context-param>放在最前面，然后写shiro的Filter配置，再写spring-session的Filter配置。后面就是其他的编码和servlet配置了。
