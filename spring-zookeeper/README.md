## 环境准备

```shell
podman  network create --driver bridge --subnet 100.100.1.0/24 --gateway 100.100.1.1 zk_network

podman run --privileged=true -d --net zk_network -v ~/opt/zookeeper/logs:/logs -v ~/opt/zookeeper/conf:/conf -v ~/opt/zookeeper/data:/data -v ~/opt/zookeeper/datalog:/datalog --name zookeeper_1 -p 2181:2181 -p 2888:2888 -p 3888:3888 -e ZOO_LOG4J_PROP="INFO,ROLLINGFILE" -e ZOO_MY_ID="0" -e ZOO_SERVERS="server.1=zookeeper_1:2888:3888 server.2=zookeeper_2:2888:3888 server.3=zookeeper_3:2888:3888" zookeeper:3.4.13;
podman run --privileged=true -d --net zk_network -v ~/opt/zookeeper/logs:/logs -v ~/opt/zookeeper/conf:/conf -v ~/opt/zookeeper/data:/data -v ~/opt/zookeeper/datalog:/datalog --name zookeeper_2 -p 2181:2181 -p 2888:2888 -p 3888:3888 -e ZOO_LOG4J_PROP="INFO,ROLLINGFILE" -e ZOO_MY_ID="0" -e ZOO_SERVERS="server.1=zookeeper_1:2888:3888 server.2=zookeeper_2:2888:3888 server.3=zookeeper_3:2888:3888" zookeeper:3.4.13;
podman run --privileged=true -d --net zk_network -v ~/opt/zookeeper/logs:/logs -v ~/opt/zookeeper/conf:/conf -v ~/opt/zookeeper/data:/data -v ~/opt/zookeeper/datalog:/datalog --name zookeeper_3 -p 2181:2181 -p 2888:2888 -p 3888:3888 -e ZOO_LOG4J_PROP="INFO,ROLLINGFILE" -e ZOO_MY_ID="0" -e ZOO_SERVERS="server.1=zookeeper_1:2888:3888 server.2=zookeeper_2:2888:3888 server.3=zookeeper_3:2888:3888" zookeeper:3.4.13;

```