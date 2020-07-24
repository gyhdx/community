## community
社区项目，使用SpringBoot就行构建环境，使用thymeleaf、bootstrap和jQuery做了前端页面的展示

## 如果想要部署
1.先配置数据库，数据库的sql文件在项目的sql目录下，并在application.properties中对数据库的连接配置

2.先要安装redis，并修改application.properties中有关redis的相关配置

3.在安装kafka到本地，并修改kafka目录的config目录下zookeeper.properties和server.properties中的路径，在通过命令启动（要确保命令行所在的位置是kafka的根目录，即输入b后能按tab键自动补全bin）
* 启动zookeeper：bin\windows\zookeeper-server-start.bat config\zookeeper.properties
* 启动kafka：bin\windows\kafka-server-start.bat config\server.properties

然后将项目导入idea，就可以运行了
项目启动成功后访问http://localhost:8080/community/index 即可
