# 实现一个简单的RPC框架：Simple-RPC

## 0-quick start

1. 首先克隆此项目到本地

2. 打开`simple-rpc-demo`首先启动`Server`（本地实现了加减法demo，接口为`CalcService`和对应实现类`CalcServiceImpl`）

   ![image-20211224155916322](https://s2.loli.net/2021/12/24/M6rNjbIB4culdTQ.png)

![image-20211224140937440](https://s2.loli.net/2021/12/24/lA9uKQb1FZhTyoP.png)

3. 确认服务注册成功后，启动`Client`，运行注册好的方法。

启动`Client`后，`Server`日志：

<img src="https://s2.loli.net/2021/12/24/UWtaSoPGLFvmDkJ.png" alt="image-20211224141915654" style="zoom: 200%;" />

启动`Client`后，`Client`日志：

![image-20211224142308511](https://s2.loli.net/2021/12/24/rfQoe2wyk94Vi7g.png)

## 2-技术栈

- Java高阶特性：反射
- Java高阶特性：动态代理
- 包管理：Maven
- 序列化：fastjson
- 网络通信：jetty
- 传输协议：HTTP（有待改进）

## 3-实现模块

- 客户端
- 服务端
- 序列化模块
- 网络模块（负责信息传输类）
- 传输协议（客户端与服务端如何通信）

## 4-模块详览

![Simple-RPC (1)](https://s2.loli.net/2021/12/24/p4vAHSny7QciRCz.png)

## 5-逻辑流程

- 流程逻辑，图画的一般般吧

![Simple-RPC (2)](https://s2.loli.net/2021/12/24/iW8SwCNejgIQKcn.png)

- 结合文字说明与执行流程图更快理解

<img src="https://s2.loli.net/2021/12/24/3syWDrQvikBT7XL.png" alt="未命名文件 (4)" style="zoom:150%;" />



