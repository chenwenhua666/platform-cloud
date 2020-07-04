/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : platform_nacos_v2

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 13/05/2020 17:35:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (8, 'platform-gateway-dev.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 8301\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      globalcors:\r\n        corsConfigurations:\r\n          \'[/**]\':\r\n            allowedOrigins: \"*\"\r\n            allowedMethods: \"*\"\r\n            allowedHeaders: \"*\"\r\n            allowCredentials: true\r\n      routes:\r\n        - id: platform-auth-social\r\n          uri: lb://platform-auth\r\n          predicates:\r\n            - Path=/auth/social/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: socialfallback\r\n                fallbackUri: forward:/fallback/platform-auth\r\n        - id: platform-auth\r\n          uri: lb://platform-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: authfallback\r\n                fallbackUri: forward:/fallback/platform-auth\r\n        - id: platform-server-system\r\n          uri: lb://platform-server-system\r\n          predicates:\r\n            - Path=/system/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: systemfallback\r\n                fallbackUri: forward:/fallback/platform-server-system\r\n        - id: platform-server-generator\r\n          uri: lb://platform-server-generator\r\n          predicates:\r\n            - Path=/generator/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: generatorfallback\r\n                fallbackUri: forward:/fallback/platform-server-generator\r\n        - id: platform-server-job\r\n          uri: lb://platform-server-job\r\n          predicates:\r\n            - Path=/job/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: jobfallback\r\n                fallbackUri: forward:/fallback/platform-server-job\r\n        - id: platform-server-foundation\r\n          uri: lb://platform-server-foundation\r\n          predicates:\r\n            - Path=/foundation/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: foundationfallback\r\n                fallbackUri: forward:/fallback/platform-server-foundation\r\n        - id: platform-server-statistics\r\n          uri: lb://platform-server-statistics\r\n          predicates:\r\n            - Path=/statistics/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: statisticsfallback\r\n                fallbackUri: forward:/fallback/platform-server-statistics\r\n      loadbalancer:\r\n        use404: true\r\n      default-filters:\r\n        - StripPrefix=1\r\n        - PlatformDocGatewayHeaderFilter\r\n  # autoconfigure:\r\n    # exclude: org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration,org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration,org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration\r\n\r\n  data:\r\n    mongodb:\r\n      host: ${mongo.url}\r\n      port: 27017\r\n      database: platform_cloud_route_v2\r\n\r\n  redis:\r\n    database: 3\r\n    host: ${redis.url}\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n\r\nplatform:\r\n  gateway:\r\n    enhance: true\r\n    jwt:\r\n      secret: 123456788\r\n      expiration: 36000\r\n  doc:\r\n    gateway:\r\n      enable: true\r\n      resources: \"platform-server-system,platform-server-foundation,platform-auth,platform-server-generator,platform-server-job\"\r\n\r\nhystrix:\r\n  command:\r\n    default:\r\n      execution:\r\n        isolation:\r\n          thread:\r\n            timeoutInMilliseconds: 10000\r\n    socialfallback:\r\n      execution:\r\n        isolation:\r\n          thread:\r\n            timeoutInMilliseconds: 60000\r\n\r\nribbon:\r\n  eager-load:\r\n    enabled: true\r\n\r\nmanagement:\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: health,info,gateway', 'da21021b762aa763342d3db0010a87cb', '2019-09-18 10:37:02', '2020-05-12 11:45:00', NULL, '127.0.0.1', '', '', 'platform-gateway-dev网关配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (24, 'platform-server-foundation.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 8202\r\nspring:\r\n  datasource:\r\n    dynamic:\r\n      p6spy: true\r\n      hikari:\r\n        connection-timeout: 30000\r\n        max-lifetime: 1800000\r\n        max-pool-size: 15\r\n        min-idle: 5\r\n        connection-test-query: select 1\r\n        pool-name: PlatformHikariCP\r\n      primary: base\r\n      datasource:\r\n        base:\r\n          username: root\r\n          password: 123456\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${mysql.url}:3306/platform_cloud_base_v2?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://${platform-admin}:8401\r\n        username: platform\r\n        password: 123456a_\r\n        instance:\r\n          prefer-ip: true\r\n\r\nfeign:\r\n  hystrix:\r\n    enabled: true\r\n\r\nhystrix:\r\n  shareSecurityContext: true\r\n  command:\r\n    default:\r\n      execution:\r\n        isolation:\r\n          thread:\r\n            timeoutInMilliseconds: 3000\r\nmybatis-plus:\r\n  type-aliases-package: com.plm.platform.common.core.entity.system\r\n  mapper-locations: classpath:mapper/*.xml\r\n  configuration:\r\n    jdbc-type-for-null: null\r\n  global-config:\r\n    banner: false\r\n\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      id: ${spring.application.name}\r\n      user-info-uri: http://${platform-gateway}:8301/auth/user\r\n\r\ntx-lcn:\r\n  client:\r\n    manager-address: ${platform-tx-manager}:8888\r\n\r\ninfo:\r\n  app:\r\n    name: ${spring.application.name}\r\n    description: \"@project.description@\"\r\n    version: \"@project.version@\"\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\nplatform:\r\n  doc:\r\n    enable: true\r\n    title: ${spring.application.name}文档\r\n    base-package: com.plm.platform.server.foundation.controller\r\n    description: ${platform.doc.title}\r\n    name: crystal\r\n    email: 764471698@qq.com\r\n    url: http://47.105.198.196\r\n    version: 2.0-RELEASE\r\n  cloud:\r\n    security:\r\n      enable: true\r\n      anon-uris: /actuator/**,/v2/api-docs,/v2/api-docs-ext', '7ce9d29eb8807799a79a5898c19c7461', '2019-09-19 10:17:25', '2020-05-05 14:20:00', NULL, '127.0.0.1', '', '', 'platform-server-foundation微服务配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (29, 'platform-tx-manager.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 8501\r\nspring:\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://${platform-admin}:8401\r\n        username: platform\r\n        password: 123456a_\r\n        instance:\r\n          prefer-ip: true\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://${mysql.url}:3306/platform_cloud_base_v2?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\r\n    username: root\r\n    password: 123456\r\n  redis:\r\n    database: 0\r\n    host: ${redis.url}\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n\r\ninfo:\r\n  app:\r\n    name: ${spring.application.name}\r\n    description: \"@project.description@\"\r\n    version: \"@project.version@\"\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\n\r\ntx-lcn:\r\n  manager:\r\n    host: 127.0.0.1\r\n    # TM监听Socket端口.\r\n    port: 8888\r\n    # TM控制台密码\r\n    admin-key: 123456a_\r\n  logger:\r\n    # 开启日志记录\r\n    enabled: true\r\n    driver-class-name: ${spring.datasource.driver-class-name}\r\n    jdbc-url: ${spring.datasource.url}\r\n    username: ${spring.datasource.username}\r\n    password: ${spring.datasource.password}', '874a21785c1200d56bdfdf0070f8f20f', '2020-04-16 09:12:35', '2020-05-11 11:11:37', NULL, '127.0.0.1', '', '', 'tx-lcn管理端配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (33, 'platform-server-generator.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 8203\r\nspring:\r\n  datasource:\r\n    dynamic:\r\n      p6spy: true\r\n      hikari:\r\n        connection-timeout: 30000\r\n        max-lifetime: 1800000\r\n        max-pool-size: 15\r\n        min-idle: 5\r\n        connection-test-query: select 1\r\n        pool-name: PlatformHikariCP\r\n      primary: base\r\n      datasource:\r\n        base:\r\n          username: root\r\n          password: 123456\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${mysql.url}:3306/platform_cloud_base_v2?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://${platform-admin}:8401\r\n        username: platform\r\n        password: 123456a_\r\n        instance:\r\n          prefer-ip: true\r\n\r\nmybatis-plus:\r\n  type-aliases-package: com.plm.platform.common.core.entity.system\r\n  mapper-locations: classpath:mapper/*.xml\r\n  configuration:\r\n    jdbc-type-for-null: null\r\n  global-config:\r\n    banner: false\r\n\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      id: ${spring.application.name}\r\n      user-info-uri: http://${platform-gateway}:8301/auth/user\r\n\r\ninfo:\r\n  app:\r\n    name: ${spring.application.name}\r\n    description: \"@project.description@\"\r\n    version: \"@project.version@\"\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\n\r\nplatform:\r\n  doc:\r\n    enable: true\r\n    title: ${spring.application.name}文档\r\n    base-package: com.plm.platform.server.generator.controller\r\n    description: ${platform.doc.title}\r\n    name: crystal\r\n    email: 764471698@qq.com\r\n    url: http:47.105.198.196\r\n    version: 2.0-RELEASE\r\n  cloud:\r\n    security:\r\n      enable: true\r\n      anon-uris: /actuator/**,/v2/api-docs,/v2/api-docs-ext', '23dd2080ac3dae10ead6e38cd7ed4f0e', '2020-04-16 11:03:11', '2020-05-05 14:21:26', NULL, '127.0.0.1', '', '', '代码生成配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (35, 'platform-server-job.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 8204\r\nspring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n  datasource:\r\n    dynamic:\r\n      # 是否开启 SQL日志输出，生产环境建议关闭，有性能损耗\r\n      p6spy: true\r\n      hikari:\r\n        connection-timeout: 30000\r\n        max-lifetime: 1800000\r\n        max-pool-size: 15\r\n        min-idle: 5\r\n        connection-test-query: select 1\r\n        pool-name: PlatformHikariCP\r\n      # 配置默认数据源\r\n      primary: base\r\n      datasource:\r\n        # 数据源-1，名称为 base\r\n        base:\r\n          username: root\r\n          password: 123456\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${mysql.url}:3306/platform_cloud_base_v2?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\r\n        # 数据源-2，名称为 job\r\n        job:\r\n          username: root\r\n          password: 123456\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${mysql.url}:3306/platform_cloud_job_v2?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\r\n\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://${platform-admin}:8401\r\n        username: platform\r\n        password: 123456a_\r\n        instance:\r\n          prefer-ip: true\r\n\r\nmybatis-plus:\r\n  type-aliases-package: com.plm.platform.server.job.entity\r\n  mapper-locations: classpath:mapper/*.xml\r\n  configuration:\r\n    jdbc-type-for-null: null\r\n  global-config:\r\n    banner: false\r\n\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      id: ${spring.application.name}\r\n      user-info-uri: http://${platform-gateway}:8301/auth/user\r\n\r\ninfo:\r\n  app:\r\n    name: ${spring.application.name}\r\n    description: \"@project.description@\"\r\n    version: \"@project.version@\"\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\nplatform:\r\n  doc:\r\n    enable: true\r\n    title: ${spring.application.name}文档\r\n    base-package: com.plm.platform.server.job.controller\r\n    description: ${platform.doc.title}\r\n    name: crystal\r\n    email: 764471698@qq.com\r\n    url: http://47.105.198.196\r\n    version: 2.0-RELEASE\r\n  cloud:\r\n    security:\r\n      enable: true\r\n      anon-uris: /actuator/**,/v2/api-docs,/v2/api-docs-ext', '6b5e8756e70170955b00943deb4f293f', '2020-04-16 11:31:54', '2020-05-05 14:22:16', NULL, '127.0.0.1', '', '', '定时任务配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (38, 'platform-admin.yaml', 'DEFAULT_GROUP', 'server:\n  port: 8401\n\nspring:\n  security:\n    user:\n      name: platform\n      password: 123456a_\n  boot:\n    admin:\n      ui:\n        title: ${spring.application.name}', 'bf2f89f2455702ef119c3cab9f2a0e3e', '2020-04-16 13:56:54', '2020-05-03 18:06:38', NULL, '127.0.0.1', '', '', 'sba监控', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (82, 'platform-server-system.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 8201\r\n    \r\nspring:\r\n  aop:\r\n    proxy-target-class: true\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://${platform-admin}:8401\r\n        username: platform\r\n        password: 123456a_\r\n        instance:\r\n          prefer-ip: true\r\n  freemarker:\r\n    check-template-location: false\r\n\r\n  datasource:\r\n    dynamic:\r\n      p6spy: true\r\n      hikari:\r\n        connection-timeout: 30000\r\n        max-lifetime: 1800000\r\n        max-pool-size: 15\r\n        min-idle: 5\r\n        connection-test-query: select 1\r\n        pool-name: PlatformHikariCP\r\n      primary: base\r\n      datasource:\r\n        base:\r\n          username: root\r\n          password: 123456\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${mysql.url}:3306/platform_cloud_base_v2?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\r\n\r\nmybatis-plus:\r\n  type-aliases-package: com.plm.platform.common.core.entity.system\r\n  mapper-locations: classpath:mapper/*.xml\r\n  configuration:\r\n    jdbc-type-for-null: null\r\n  global-config:\r\n    banner: false\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      id: ${spring.application.name}\r\n      user-info-uri: http://${platform-gateway}:8301/auth/user\r\n\r\ninfo:\r\n  app:\r\n    name: ${spring.application.name}\r\n    description: \"@project.description@\"\r\n    version: \"@project.version@\"\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\nplatform:\r\n  doc:\r\n    enable: true\r\n    title: ${spring.application.name}文档\r\n    base-package: com.plm.platform.server.system.controller\r\n    description: ${platform.doc.title}\r\n    name: crystal\r\n    email: 764471698@qq.com\r\n    url: https://47.105.198.196\r\n    version: 2.0-RELEASE\r\n  cloud:\r\n    security:\r\n      enable: true\r\n      anon-uris: /actuator/**,/v2/api-docs,/v2/api-docs-ext\r\ntx-lcn:\r\n  client:\r\n    manager-address: ${platform-tx-manager}:8888', 'feb04476d6b98ca495e66dde64bc43aa', '2020-04-18 10:00:56', '2020-05-05 14:09:43', NULL, '127.0.0.1', '', '', 'platform-server-system微服务配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (83, 'platform-auth.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 8101\r\nspring:\r\n  datasource:\r\n    dynamic:\r\n      hikari:\r\n        connection-timeout: 30000\r\n        max-lifetime: 1800000\r\n        max-pool-size: 15\r\n        min-idle: 5\r\n        connection-test-query: select 1\r\n        pool-name: PlatformHikariCP\r\n      primary: base\r\n      datasource:\r\n        base:\r\n          username: root\r\n          password: 123456\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${mysql.url}:3306/platform_cloud_base_v2?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\r\n\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://${platform-admin}:8401\r\n        username: platform\r\n        password: 123456a_\r\n        instance:\r\n          prefer-ip: true\r\n\r\n  redis:\r\n    database: 0\r\n    host: ${redis.url}\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\nmybatis-plus:\r\n  type-aliases-package: com.plm.platform.common.core.entity.system\r\n  mapper-locations: classpath:mapper/*.xml\r\n  configuration:\r\n    jdbc-type-for-null: null\r\n  global-config:\r\n    banner: false\r\ninfo:\r\n  app:\r\n    name: ${spring.application.name}\r\n    description: \"@project.description@\"\r\n    version: \"@project.version@\"\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\n\r\njustauth:\r\n  enabled: true\r\n  type:\r\n    gitee:\r\n      client-id: d04d3e900d1d8d851ccafd215f434f7881162b2e92980687c6de6e82470df7ba\r\n      client-secret: 20fd9f035b113b9a126f405f1864ad893e8d9b65e00b447249dae269ceae4164\r\n      redirect-uri: http://cloud.cwh.com:8301/auth/social/gitee/callback\r\n    github:\r\n      client-id: a4b007459505a76bb564\r\n      client-secret: b5f36b777e58a4bdb7f1452b68cf151ec734b4c0\r\n      redirect-uri: http://cloud.cwh.com:8301/auth/social/github/callback\r\n    qq:\r\n      client-id: \r\n      client-secret: \r\n      redirect-uri: \r\n    wechat:\r\n      client-id: \r\n      client-secret: \r\n      redirect-uri: \r\n    weibo:\r\n      client-id: 767698939\r\n      client-secret: 410463de444e76432f1ad6605f92bad5\r\n      redirect-uri: http://cloud.cwh.com:8301/auth/social/weibo/callback\r\n    douyin:\r\n      client-id: \r\n      client-secret: \r\n      redirect-uri: \r\n  cache:\r\n    type: redis\r\n    prefix: \'PLM::CLOUD::SOCIAL::STATE::\'\r\n    timeout: 1h\r\n\r\nplatform:\r\n  frontUrl: \'http://127.0.0.1:9527\'\r\n  doc:\r\n    enable: true\r\n    title: ${spring.application.name}文档\r\n    base-package: com.plm.platform.auth.controller\r\n    description: ${platform.doc.title}\r\n    name: crystal\r\n    email: 764471698@qq.com\r\n    url: http://47.105.198.196\r\n    version: 2.0-RELEASE\r\n  cloud:\r\n    security:\r\n      enable: true\r\n      only-fetch-by-gateway: false\r\n      anon-uris: /actuator/**,/captcha,/social/**,/v2/api-docs,/v2/api-docs-ext,/login,/resource/**', 'c35efb5f62dbbb32b954c09c1cff4f0a', '2020-04-18 10:01:55', '2020-05-13 11:54:42', NULL, '127.0.0.1', '', '', 'platform-auth微服务配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (89, 'platform-gateway-prod.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 8301\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      globalcors:\r\n        corsConfigurations:\r\n          \'[/**]\':\r\n            allowedOrigins: \"*\"\r\n            allowedMethods: \"*\"\r\n            allowedHeaders: \"*\"\r\n            allowCredentials: true\r\n      routes:\r\n        - id: platform-auth-social\r\n          uri: lb://platform-auth\r\n          predicates:\r\n            - Path=/auth/social/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: socialfallback\r\n                fallbackUri: forward:/fallback/platform-auth\r\n        - id: platform-auth\r\n          uri: lb://platform-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: authfallback\r\n                fallbackUri: forward:/fallback/platform-auth\r\n        - id: platform-server-system\r\n          uri: lb://platform-server-system\r\n          predicates:\r\n            - Path=/system/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: systemfallback\r\n                fallbackUri: forward:/fallback/platform-server-system\r\n        - id: platform-server-generator\r\n          uri: lb://platform-server-generator\r\n          predicates:\r\n            - Path=/generator/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: generatorfallback\r\n                fallbackUri: forward:/fallback/platform-server-generator\r\n        - id: platform-server-job\r\n          uri: lb://platform-server-job\r\n          predicates:\r\n            - Path=/job/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: jobfallback\r\n                fallbackUri: forward:/fallback/platform-server-job\r\n        - id: platform-server-foundation\r\n          uri: lb://platform-server-foundation\r\n          predicates:\r\n            - Path=/foundation/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: foundationfallback\r\n                fallbackUri: forward:/fallback/platform-server-foundation\r\n        - id: platform-server-statistics\r\n          uri: lb://platform-server-statistics\r\n          predicates:\r\n            - Path=/statistics/**\r\n          filters:\r\n            - name: Hystrix\r\n              args:\r\n                name: statisticsfallback\r\n                fallbackUri: forward:/fallback/platform-server-statistics\r\n      loadbalancer:\r\n        use404: true\r\n      default-filters:\r\n        - StripPrefix=1\r\n        - PlatformDocGatewayHeaderFilter\r\n  autoconfigure:\r\n    exclude: org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration,org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration,org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration\r\n\r\n  # data:\r\n    # mongodb:\r\n      # host: ${mongo.url}\r\n      # port: 27017\r\n      # database: platform_cloud_route_v2\r\n\r\n  redis:\r\n    database: 3\r\n    host: ${redis.url}\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n\r\nplatform:\r\n  gateway:\r\n    enhance: false\r\n    jwt:\r\n      secret: 123456788\r\n      expiration: 36000\r\n  doc:\r\n    gateway:\r\n      enable: true\r\n      resources: \"platform-server-system,platform-server-foundation,platform-auth,platform-server-generator,platform-server-job\"\r\n\r\nhystrix:\r\n  command:\r\n    default:\r\n      execution:\r\n        isolation:\r\n          thread:\r\n            timeoutInMilliseconds: 10000\r\n    socialfallback:\r\n      execution:\r\n        isolation:\r\n          thread:\r\n            timeoutInMilliseconds: 60000\r\n\r\nribbon:\r\n  eager-load:\r\n    enabled: true\r\n\r\nmanagement:\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: health,info,gateway', '48c7e4ff2ec374cb0170236fc10c43c1', '2020-05-05 11:54:41', '2020-05-12 11:45:29', NULL, '127.0.0.1', '', '', 'platform-gateway-prod网关配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (92, 'platform-server-statistics.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 8205\r\nspring:\r\n  aop:\r\n    proxy-target-class: true\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://${platform-admin}:8401\r\n        username: platform\r\n        password: 123456a_\r\n        instance:\r\n          prefer-ip: true\r\n  freemarker:\r\n    check-template-location: false\r\n  mail:\r\n    host: smtp.qq.com\r\n    username: 764471698@qq.com\r\n    password: xagwinywfwjsbfjc\r\n    properties:\r\n      mail:\r\n        smtp:\r\n          auth: true\r\n          starttls:\r\n            enable: true\r\n            required: true\r\n  datasource:\r\n    dynamic:\r\n      p6spy: true\r\n      hikari:\r\n        connection-timeout: 30000\r\n        max-lifetime: 1800000\r\n        max-pool-size: 15\r\n        min-idle: 5\r\n        connection-test-query: select 1\r\n        pool-name: PlatformHikariCP\r\n      primary: base\r\n      datasource:\r\n        base:\r\n          username: root\r\n          password: 123456\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${mysql.url}:3306/platform_cloud_base_v2?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\r\n  redis:\r\n    database: 4\r\n    host: ${redis.url}\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\nmybatis-plus:\r\n  type-aliases-package: com.plm.platform.server.statistics.entity\r\n  mapper-locations: classpath:mapper/*.xml\r\n  configuration:\r\n    jdbc-type-for-null: null\r\n  global-config:\r\n    banner: false\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      id: ${spring.application.name}\r\n      user-info-uri: http://${platform-gateway}:8301/auth/user\r\n\r\ninfo:\r\n  app:\r\n    name: ${spring.application.name}\r\n    description: \"@project.description@\"\r\n    version: \"@project.version@\"\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: ALWAYS\r\nplatform:\r\n  cloud:\r\n    security:\r\n      enable: true\r\n      anon-uris: /actuator/**,/v2/api-docs,/v2/api-docs-ext\r\nfeign:\r\n  hystrix:\r\n    enabled: true\r\n\r\nhystrix:\r\n  shareSecurityContext: true\r\n  command:\r\n    default:\r\n      execution:\r\n        isolation:\r\n          thread:\r\n            timeoutInMilliseconds: 4000', '9d47ec07a2738b41e246e8814a2bb732', '2020-05-12 11:18:23', '2020-07-04 11:21:14', NULL, '127.0.0.1', '', '', '统计分析', 'null', 'null', 'yaml', 'null');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('platform', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('platform', '$2a$10$l0ckwTquYsxTvAUb6zRMx.V5G8goi6/ICTubt.Ysnm2e9NM0eAhLy', 1);

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
                               `role` varchar(50) NOT NULL,
                               `resource` varchar(512) NOT NULL,
                               `action` varchar(8) NOT NULL,
                               UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
