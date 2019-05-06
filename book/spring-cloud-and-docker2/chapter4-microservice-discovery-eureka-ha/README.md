#[idea 配置启动参数](https://blog.csdn.net/weixin_37490221/article/details/82967526)
那么现在有这几种解决方案：

直接在本地配置一套，使用本地配置（根据属性加载顺序，远程加载不到，那么才到本地加载）
使用1~6优先级的形式设置所缺的属性
第一种方案可以，但是git在提交时会提示更改，而配置文件不应该添加到.gitignore，所以，对不起，我不能接受代码的凌乱感。我选择第二钟，并且将配置属性通过启动参数的形式注入到项目中，下次我不想要个直接删了即可，同时也不影响git的status，干净。

IDEA项目启动参数配置
点击项目下拉按钮后选择"Edit Configurations"
![image.png](https://img-blog.csdn.net/20181008144540339?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zNzQ5MDIyMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

在"Configuration"下的VM options中填入需要的属性值
![image.png](https://img-blog.csdn.net/20181008144644520?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zNzQ5MDIyMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)


填写的格式如下：

-Dserver.port=8888 -Dspring.redis.port=6378 -D"你想配置的参数名"="参数值"
1
多个参数之间使用空格隔开。当然你也可以使用环境变量（Environment variables）和 Program arguments添加。

-Dspring.profiles.active=peer1
--spring.profiles.active=peer1