
> 当你的才华支撑不起你的野心的时候，你应该静下心来学习

内容很简单，来自《数据结构与算法分析Java语言描述》
不断更新~~
##单链表
- 带头结点的单链表SingleLinkWithHead
    - 判断链表是否为空
    -  添加一个结点
    -  在指定位置添加一个结点
     - 删除一个结点（循环法）
     - 删除指定位置的结点
     - 删除结点时间复杂度O(1)
     - 用栈的方式删除指定位置结点
     - 删除链表中的重复数据
     - 删除链表中倒数第K个结点
     - 反转链表
     - 查找一个未知长度链表的中间项
 - 不带头结点的单链表SingleLinkWithoutHead
    - 判断链表是否为空
    -  获取位置结点
    -  添加结点
    
 **对带不带头结点一直比较困惑，查找若干资料以后，总结如下：在带头结点的链表中，head表示头结点；
在不带头结点的链表中head表示第一个结点。头结点，一定不为空，在构造函数中已经初始化，头结点的值可
以为空，初始化的时候头结点的next为空，但是头结点本身不为空！！！可以用指针的含义来理解，在带头节
点的链表中，头指针指向头结点，在不带头结点的链表中，头指针指向第一个结点。头结点可以不在，但是头
指针在两种链表中都必须存在。**


##双向链表
- 带头结点的双向链表DoubleLink
    - 获取指定位置结点
    -  在指定位置插入值
    -  移除指定结点
## 栈的实现
- 用数组模拟实现出栈和入栈的过程
- 用双向链表模拟实现入栈和出栈的过程    

##队列的实现
- 用数组模拟实现入队和出队的过程
- 用两个栈实现入队和出队的过程