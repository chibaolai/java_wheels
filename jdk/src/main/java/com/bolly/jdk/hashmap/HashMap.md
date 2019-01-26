#### 知识点
数组特点：检索快（因为有索引）,插入慢
链表特点：检索慢（因为要从第一个Node开始检索）,插入快
#### HashMap存储
##### HashMap数据结构
数组+链表
##### 如何存储
**概述**：
* 初始分配16个数组空间，
* 对key取hash值，然后计算出下标，会出现如下三种情况
* 如果没有碰撞，直接放入数组中
* 如果碰撞，以链表方式链接到最后
* 如果链表长度超过8，红黑树变形
* 如果节点已经存在，替换旧值
* 如果数组超过16*0.75=12的时候，扩容一倍即32个。


1. 如何获取一个1~15的随机数？
```
n = 16
(n - 1) & Object.hashCode()
```
2. 为什么与运算可以取得1~15随机数
```
int num15 = 15;
int numRand = "zoo".hashCode();
System.out.println(Integer.toBinaryString(num15));
System.out.println(Integer.toBinaryString(numRand));
System.out.println(Integer.toBinaryString(numRand&num15));
```
```
0000000000000000000000000000**1111**
0000000000000001110101111101**1010**
----与运算结果-----
0000000000000000000000000000**1010**
```
3. 如何防止后四位二进制重复
高16bit与低16bit做异或
```
System.out.println(Integer.toBinaryString(numRand ^ numRand>>>16));
```
```
00000000000000011101011111011011
```