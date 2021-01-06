# Git learning note
---
## Git 简介
* ### 版本控制系统：
  |集中式|CVS|SVN|
  |:-:|:-:|:-:|
  |分布式|Git|
* ### 安装及更新
  * #### linux：
  ```
  $ git
  $ sudo apt-get install git
  ```
  * #### windows
  ```
  //通过命令更新git for windows
  git update-git-for-windows
  ```
* ### 设置
  * #### git全局设置
  ```
  $ git config --global user.name "your name"
  $ git config --global user.email "your email"
  ```
  * #### ssh密钥生成
  ```
  $ ssh-keygen -t rsa -C "your email"
  ```