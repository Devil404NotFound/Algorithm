package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.ListNode;

import java.util.HashSet;
import java.util.Set;

/** 每日一题 2020.10.10
 142. 环形链表 II
 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。

 说明：不允许修改给定的链表。

 进阶：

 你是否可以不用额外空间解决此题？


 示例 1：



 输入：head = [3,2,0,-4], pos = 1
 输出：返回索引为 1 的链表节点
 解释：链表中有一个环，其尾部连接到第二个节点。
 示例 2：



 输入：head = [1,2], pos = 0
 输出：返回索引为 0 的链表节点
 解释：链表中有一个环，其尾部连接到第一个节点。
 示例 3：



 输入：head = [1], pos = -1
 输出：返回 null
 解释：链表中没有环。


 提示：

 链表中节点的数目范围在范围 [0, 104] 内
 -105 <= Node.val <= 105
 pos 的值为 -1 或者链表中的一个有效索引
 */
public class _中等_142_环形链表II {
    /**
     *哈希表
     * @param head
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了24.40%的用户
    内存消耗：
    39.8 MB, 在所有 Java 提交中击败了14.90%的用户
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        while(p != null){
            if(!set.add(p)){
                return p;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 官方题解二：快慢指针
     * 通过数学推导
     * a+n(b+c)+b=a+(n+1)b+nca+n(b+c)+b=a+(n+1)b+nc
     * a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)
     * a=c+(n-1)(b+c)a=c+(n−1)(b+c)
     * @param head
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了38.00%的用户
    内存消耗：
    39 MB, 在所有 Java 提交中击败了43.76%的用户
     */
    public ListNode detectCycle2(ListNode head){
        if(head == null || head.next == null){
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p = head;
        while(p != slow){
            p = p.next;
            slow = slow.next;
        }
        return p;
    }

    /**
     * 对方法二的优化
     * @param head
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39 MB, 在所有 Java 提交中击败了52.14%的用户
     */
    public ListNode detectCycle3(ListNode head){
        ListNode slow, fast;
        slow = fast = head;
        do{
            if(fast == null || fast.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }while(slow != fast);
        ListNode p = head;
        while(p != slow){
            p = p.next;
            slow = slow.next;
        }
        return p;
    }

}
