# RabbitMQ Java API编程
需要在本机安装Erlang、RabbitMQ并运行。

RabbitMQ默认端口：5672。

# 目录package说明
  delay  延迟队列，对应sidekiq perform_in

  direct exchange类型为direct

  fanout 广播类型exchange

  topic 按照*或者#匹配routingKey的exchange

  utill 包括建立rabbit链接和创建生产者线程


# 插件安装
  基于延时插件实现的延时队列（需连接到Linux系统的服务），需要安装延时队列插件：

  $ cd /usr/lib/rabbitmq/lib/rabbitmq_server-3.6.12/plugins

  $ wget https://bintray.com/rabbitmq/community-plugins/rabbitmq_delayed_message_exchange/_latestVersion

  允许该插件
  
  $ rabbitmq-plugins enable rabbitmq_delayed_message_exchange