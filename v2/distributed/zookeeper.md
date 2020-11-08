# zookeeper
# 一、初步认识zookeeper
## 1.1、安装
### 1.1.1 下载 ：
https://www.apache.org/dyn/closer.cgi/zookeeper/  
http://mirror.bit.edu.cn/apache/zookeeper/

### 1.1.2 解压 
tar -zxvf zookeeper-3.4.14.tar.gz  

### 1.1.3 运行
* 配置config文件 
> cd zookeeper-3.4.14  
cd conf/  
cp zoo_sample.cfg zoo.cfg  
vim zoo.cfg  

* 启动
> cd zookeeper-3.4.14  
cd bin/  
sh zkServer.sh start  
>> 打印的信息  :  
ZooKeeper JMX enabled by default  
 Using config: /opt/middleware/zookeeper/zookeeper-3.4.14/bin/../conf/zoo.cfg  
 Starting zookeeper ... STARTED  
 
 
 * 使用客户端工具连接
> cd zookeeper-3.4.14  
cd bin/  
sh zkCli.sh  

>> help 可以查看 zookeeper 提供的命令  
命令 节点名称 值  
例：
>>> create /orderservice 0  创建节点名为orderservice值为0的节点  
通过 ls / 查看当前目录下的节点  
ls /orderservice  没有节点  
创建orderservice下的节点
create /orderservice/select 1
 删除 必须一层层删除   
 获取 get /orderservice/select  
 
### 1.1.4 常用命令
* 启动 ZK 服务: bin/zkServer.sh start
* 查看 ZK 服务状态: bin/zkServer.sh status
* 停止 ZK 服务: bin/zkServer.sh stop
* 重启 ZK 服务: bin/zkServer.sh restart
* 连接服务器: zkCli.sh -timeout 0 -r -server ip:port

### 1.1.5 zookeeper 特性
* 同级节点唯一性
* 有临时节点和持久节点
* 有序节点和无序节点
* 临时节点下不能存在节点

## 1.2 集群搭建
### 1.2.1 修改配置文件 zoo.cfg   
格式： server.id=ip:port:port  
第一个端口是数据通信端口，第二个端口是重新选举端口  
每台机器的配置都是一样的。
> server.1=192.168.202.115:2888:3888  
server.2=192.168.202.114:2888:3888  
server.3=192.168.202.113:2888:3888

### 1.2.2 修改id
dataDir=/tmp/zookeeper    
在 dataDir 目录下 新建 myid 然后写上对应的id  
id 的范围是 1~255

### 1.2.3 启动
按顺序分别启动上面的zookeeper  
一开始启动的时候会报3888端口错误、需要启动两台及以上就ok  

### 1.2.4 查看主从关系
> sh zkServer.sh status  
Mode: leader  
Mode: follower  
Mode: follower 

还有一个角色：Observer。用于检测整个集群环境的信息。   

### 1.2.5 数据同步
在一台机上新增一个节点、然后再其他两台机上都可以看到新增的数据通过过来。  

## 1.3 了解zookeeper各项参数
### 1.3.1 zoo.cfg
* tickTime:ZK中的一个时间单元。ZK中所有时间都是以这个时间单元为基础，进行整数倍配置的。例如，session的最小超时时间是2*tickTime。
* initLimit:Follower在启动过程中，会从Leader同步所有最新数据，然后确定自己能够对外服务的起始状态。Leader允许F在 initLimit 时间内完成这个工作。通常情况下，我们不用太在意这个参数的设置。如果ZK集群的数据量确实很大了，F在启动的时候，从Leader上同步数据的时间也会相应变长，因此在这种情况下，有必要适当调大这个参数了。(No Java system property)
* syncLimit:在运行过程中，Leader负责与ZK集群中所有机器进行通信，例如通过一些心跳检测机制，来检测机器的存活状态。如果L发出心跳包在syncLimit之后，还没有从F那里收到响应，那么就认为这个F已经不在线了。注意：不要把这个参数设置得过大，否则可能会掩盖一些问题。(No Java system property)
* dataDir:存储快照文件snapshot的目录。默认情况下，事务日志也会存储在这里。建议同时配置参数dataLogDir, 事务日志的写性能直接影响zk性能。
* dataLogDir:事务日志输出目录。尽量给事务日志的输出配置单独的磁盘或是挂载点，这将极大的提升ZK性能。  
* clientPort:客户端连接server的端口，即对外服务端口，一般设置为2181吧。
* clientPortAddress:对于多网卡的机器，可以为每个IP指定不同的监听端口。默认情况是所有IP都监听 clientPort 指定的端口。  New in 3.3.0
* globalOutstandingLimit:最大请求堆积数。默认是1000。ZK运行的时候， 尽管server已经没有空闲来处理更多的客户端请求了，但是还是允许客户端将请求提交到服务器上来，以提高吞吐性能。当然，为了防止Server内存溢出，这个请求堆积数还是需要限制下的。  
* preAllocSize:预先开辟磁盘空间，用于后续写入事务日志。默认是64M，每个事务日志大小就是64M。如果ZK的快照频率较大的话，建议适当减小这个参数。(Java system property:zookeeper.preAllocSize )
* snapCount:每进行snapCount次事务日志输出后，触发一次快照(snapshot), 此时，ZK会生成一个snapshot.*文件，同时创建一个新的事务日志文件log.*。默认是100000.（真正的代码实现中，会进行一定的随机数处理，以避免所有服务器在同一时间进行快照而影响性能）(Java system property:zookeeper.snapCount )
* traceFile:用于记录所有请求的log，一般调试过程中可以使用，但是生产环境不建议使用，会严重影响性能。(Java system property:? requestTraceFile )
* maxClientCnxns:单个客户端与单台服务器之间的连接数的限制，是ip级别的，默认是60，如果设置为0，那么表明不作任何限制。请注意这个限制的使用范围，仅仅是单台客户端机器与单台ZK服务器之间的连接数限制，不是针对指定客户端IP，也不是ZK集群的连接数限制，也不是单台ZK对所有客户端的连接数限制。指定客户端IP的限制策略，这里有一个patch，可以尝试一下：http://rdc.taobao.com/team/jm/archives/1334（No Java system property）
* minSessionTimeoutmaxSessionTimeout:Session超时时间限制，如果客户端设置的超时时间不在这个范围，那么会被强制设置为最大或最小时间。默认的Session超时时间是在2 *  tickTime ~ 20 * tickTime 这个范围 New in 3.3.0
* fsync.warningthresholdms:事务日志输出时，如果调用fsync方法超过指定的超时时间，那么会在日志中输出警告信息。默认是1000ms。(Java system property:  fsync.warningthresholdms )New in 3.3.4
* autopurge.purgeInterval:在上文中已经提到，3.4.0及之后版本，ZK提供了自动清理事务日志和快照文件的功能，这个参数指定了清理频率，单位是小时，需要配置一个1或更大的整数，默认是0，表示不开启自动清理功能。(No Java system property)  New in 3.4.0
* autopurge.snapRetainCount:这个参数和上面的参数搭配使用，这个参数指定了需要保留的文件数目。默认是保留3个。(No Java system property)  New in 3.4.0
* electionAlg:在之前的版本中， 这个参数配置是允许我们选择leader选举算法，但是由于在以后的版本中，只会留下一种“TCP-based version of fast leader election”算法，所以这个参数目前看来没有用了，这里也不详细展开说了。(No Java system property)
* leaderServes:默认情况下，Leader是会接受客户端连接，并提供正常的读写服务。但是，如果你想让Leader专注于集群中机器的协调，那么可以将这个参数设置为no，这样一来，会大大提高写操作的性能。(Java system property: zookeeper. leaderServes )。
* server.x=[hostname]:nnnnn[:nnnnn]:这里的x是一个数字，与myid文件中的id是一致的。右边可以配置两个端口，第一个端口用于F和L之间的数据同步和其它通信，第二个端口用于Leader选举过程中投票通信。  
* group.x=nnnnn[:nnnnn]weight.x=nnnnn:对机器分组和权重设置，可以  参见这里(No Java system property)
* cnxTimeout:Leader选举过程中，打开一次连接的超时时间，默认是5s。(Java system property: zookeeper.  cnxTimeout )
* zookeeper.DigestAuthenticationProvider.superDigest:ZK权限设置相关，具体参见  《  使用super  身份对有权限的节点进行操作 》  和  《 ZooKeeper   权限控制 》
* skipACL:对所有客户端请求都不作ACL检查。如果之前节点上设置有权限限制，一旦服务器上打开这个开头，那么也将失效。(Java system property:  zookeeper.skipACL )
* forceSync:这个参数确定了是否需要在事务日志提交的时候调用 FileChannel .force来保证数据完全同步到磁盘。(Java system property: zookeeper.forceSync )
* jute.maxbuffer:每个节点最大数据量，是默认是1M。这个限制必须在server和client端都进行设置才会生效。(Java system property: jute.maxbuffer )

### 1.3.2 节点状态
* cZxid:Created ZXID表示该数据节点被创建时的事务ID
* ctime:Created Time表示节点被创建的时间
* mZxid:Modified ZXID 表示该节点最后一次被更新时的事务ID
* mtime:Modified Time表示节点最后一次被更新的时间
* pZxid:表示该节点的子节点列表最后一次被修改时的事务ID。只有子节点列表变更了才会变更pZxid,子节点内容变更不会影响pZxid
* cversion:子节点的版本号
* dataVersion:数据节点版本号
* aclVersion:节点的ACL版本号
* ephemeralOwner:创建该临时节点的会话的SessionID。如果节点是持久节点，这个属性为0
* dataLength:数据内容的长度
* numChildren:当前节点的子节点个数

### 1.3.3 ACL 权限控制
五种权限：create/read/write/delete/admin

## 1.4 zookeeper角色
### 1.4.1、leader
处理事务请求：所有的新增、修改、删除都走leader  
如果事务请求落到了follower节点、则follower节点将请求转发到leader节点。  

### 1.4.2、follower
类似读写分离 事务请求的其他请求  

### 1.4.3、observer
监视  
不会参与选举  
数据同步不会做到实时性  

## 5 zookeeper 其余介绍
### 1.5.1 zookeeper 数据模型
* zookeeper维护着一个树形层次结构，树中的节点被称为znode。Znode可以用于存储数据，并且有一个与之关联的ACL（access control list，访问控制列表）。Zookeeper被设计为用来实现协调服务(通常使用小数据文件)，而不是用于大容量数据存储。因此一个znode能存储的数据被限制在1MB以内。
* zookeeper的数据访问具有原子性。客户端在读取一个znode的数据时，要么读到所有的数据，要么读操作失败，不会只读到部分数据。同样，写操作将替换znode存储的所有数据。Zookeeper会保证写操作不成功就失败，不会出现部分写之类的情况。
* zookeeper中的路径必须是绝对路径，即每条路径必须从一个斜杠开始。所有路径必须是规范的，即每条路径只有唯一的一种表示方式，不支持路径解析。/zookeeper是一个保留词，不能用作一个路径组件。Zookeeper使用/zookeeper来保存管理信息
* 最小节点：znode

### 1.5.2 zookeeper 会话生命周期
NOT_CONNECTED、CONNECTING、CONNECTED、CLOSE

### 1.5.3 watcher 机制
* watcher 监听机制是 zookeeper 中非常重要的特性，我们基于 zookeeper 上创建的节点，可以对这些节点绑定监听事件，比如可以监听节点数据变更、节点删除、子节点状态变更等事件，通过这个事件机制，可以基于 zookeeper实现分布式锁、集群管理等功能。  
* watcher 特性：当数据发生变化的时候， zookeeper 会产生一个 watcher 事件，并且会发送到客户端。但是客户端只会收到一次通知。如果后续这个节点再次发生变化，那么之前设置 watcher 的客户端不会再次收到消息。（watcher 是一次性的操作）。 可以通过循环监听去达到永久监听效果

### 1.5.4 zookeeper 应用场景
* 注册中心
* 配置中心
    * 动态感知、不需要去拿、通过watcher机制、自动更新配置
    * 节点的特性
    * 安全性
    * 性能搞

### 1.5.5 实现负载均衡
