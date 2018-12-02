# HashMap 底层分析

## 原理
HashMap底层是基于**数组和链表**实现的。两个重要参数：
* 容量capacity
* 负载因子factor

容量默认是16（1<4）,负载因子是0.75，当hashmap size > 12 发送扩容

## put方法

## get方法