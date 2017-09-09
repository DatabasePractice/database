## 欢迎使用 webproject二次开发平台

> 使用Java语言开发的移动电信收费系统

## 部署环境前提
* eclipse
* jdk8
* git
* maven
* mysql

## 部署开发环境
1. 下载 [Eclipse IDE for Java EE Developers](http://eclipse.org/downloads/)
2. 打开eclpse,导入项目
3. File -> Import -> Git -> Projects from Git -> Clone URI
4. 然后在URI输入：https://github.com/DatabasePractice/database.git
5. 等待eclipse自动下载jar包
6. 在mysql中导入sql文件夹中的webproject.sql
7. 在application.properties中配置数据库账号密码。
8. 将DemoApplication.java run as java application
9. 部署中可能还会碰到其他问题，请联系QQ：873966702

本项目采用springboot+mybatis+shiro+thymeleaf
内含基本系统管理功能，可以用于二次开发
