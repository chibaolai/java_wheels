# JVM调优

## 基础知识：
1. 基本数据类型，堆对象的引用，程序存储在栈中，方法以先进后出进栈，栈的起点是main方法
一个线程对应一个栈空间，栈是运行单元，**栈调优参数：-Xss**，StackOverFlowError是栈中死循环无法返回递归。
2. 对象存储在堆中，堆是存储单元。**堆调优参数：-Xms**

## 垃圾回收算法
1. 引用计数：增加一个对象引用，计数加1，减少一个对象引用，计数减1，计数为0 回收，问题：循环引用无法回收
2. 标记-清除：第一阶段从根节点开始标记对象引用，第二阶段未标记的对象清除。问题：空间碎片
3. 复制：两块内存，复制正在使用对象到另外一块内存空间。问题：两倍空间
4. 标记-整理：第一阶段从根节点开始标记对象引用，第二阶段未标记的对象清除同时整理存活对象到清除的对象空间上

## 分代回收
不同对象有不同的生命周期（人也如此），session，socket等需要长周期，String 这种不变类需要短周期

1. 分代：
* 年轻代（Eden，survivor），有意思的命名！Eden：伊甸园，亚当夏娃居住地。survivor：幸存者
* 年老代，
* 持久代（方法区），java.lang.OutOfMemoryError: PermGen space，**调优参数：-XX:MaxPermSize**。
调优经验：
问题描述：thrift定义了大量big class，尽管以及做了生产环境1024M的调优，但依旧出现PerGen space错误。
解决办法：升级到jdk8。
原理：因为jdk8已经元空间代替持久代，metaspace占用本地内存，不占用虚拟机内存。
 
2. GC类型
* scavenge（搜索） GC：主要作用于年轻代，高频清理Eden，采用并行收集器，**调优参数：XX:+UseParallelGC**
* full GC

## 配置参数
* -Xmx：JVM最大可用内存，maximum memory size for pile and heap
* -Xms：JVM初始内存，设置与-Xmx相同，以避免每次垃圾回收完成后JVM重新分配内存，minimum memory size for pile and heap
* -Xmn：年轻代内存
* -Xss：栈内存

