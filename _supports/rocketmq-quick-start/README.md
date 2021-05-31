# docker安装rocketmq

## 下载rocketmq
https://archive.apache.org/dist/rocketmq/4.4.0/rocketmq-all-4.4.0-bin-release.zip

## 启动
nohup sh bin/mqnamesrv &
nohup sh bin/mqbroker -n localhost:9876 &

## 停止
sh bin/mqshutdown broker
sh bin/mqshutdown namesrv