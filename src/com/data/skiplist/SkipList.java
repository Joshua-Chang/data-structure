package com.data.skiplist;

/**
 * 跳表中存储的是正整数，并且存储的是不重复的。
 */
public class SkipList {

    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    private Node head = new Node();  // 带头链表

    public class Node {
        private int data = -1;
        private Node forwards[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }

    /**
     * 每个节点的forward里存的是当前节点的所有索引层的下一跳，
     * forward[ 0 ]对应的是原链表里的下一跳，forward[ 1 ]是最后一层节点的下一跳位置，
     * 以此类推，也就是说访问head的forward[ levelCount-1 ]表示第一层索引的头结点。
     * head是一个头结点，它的forward里存的是原链表以及索引层的头结点。
     * <p>
     * 1.每次插入数据的时候随机产生的level:决定了新节点的层数；
     * 2.数组update的作用：用以存储新节点所有层数上，各自的前一个节点的信息；
     * 3.节点内的forwards数组：用以存储该节点所有层的下一个节点的信息；
     * 4.当所有节点的最大层级变量maxlevel=1的时候，跳表退化成一个普通链表
     */
    public Node find(int value) {
        Node p = head;//最左上角
        for (int i = levelCount - 1; i >= 0; i--) {/*从最顶层level下降到0层*/
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                //p在第i层的下一个,在下一个... 直到大于等于value之前退出。然后i--去下一层
                p = p.forwards[i];/*i再外层每变化，横向下一个*/
            }
        }
        //forwards[n] n表示层数，0层为原始链表：head.forwards[n] 即head在n层的下一个
        if (p.forwards[0] != null && p.forwards[0].data == value) return p.forwards[0];
        else return null;
    }

    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node[] updateRoute = new Node[level];//
        for (int i = 0; i < level; i++) updateRoute[i] = head;/*初始值*/

        Node p = head;
        for (int i = level - 1; i >= 0; i--) {/*查找*/
            /*遍历i层的下一个，直到value不大于value,记录在下该层的位置，然后去下一层*/
            while (p.forwards[i] != null && p.forwards[i].data < value) p = p.forwards[i];
            updateRoute[i] = p;//记录在该层中，作为往下查找路径的node
        }
        for (int i = 0; i < level; i++) {//更新从原始链表到随机level层的索引，这几层都要新加一个node
            //即在每层路径节点的后方都插入一个新节点
            newNode.forwards[i] = updateRoute[i].forwards[i];//新节点每层的下一个节点，都是路径节点的下一个节点
            updateRoute[i].forwards[i] = newNode;//原路径节点的下个节点是新节点
        }
        if (levelCount < level) levelCount = level;
    }

    /**
     * 要删除的值为value的所有节点，
     * 原始链表中必然有值为value的节点，索引层中也有可能存在值为value的索引节点，要一并删除。
     */
    public void delete(int value) {
        Node[] updateRoute = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {/*查找*/
            /*遍历i层的下一个，直到value不大于value,记录在下该层的位置，然后去下一层*/
            while (p.forwards[i] != null && p.forwards[i].data < value) p = p.forwards[i];
            updateRoute[i] = p;//记录在该层中，作为往下查找路径的node
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {/*找到*/
            //updateRoute[i]节点<value       updateRoute[i].forward[i]>=value
            //当=value就找到了值等于value索引节点，要和原始节点一起删除
            for (int i = levelCount - 1; i >= 0; i--) {/*遍历删除每层的节点*/
                if (updateRoute[i].forwards[i] != null && updateRoute[i].forwards[i].data == value)/*路径上*/
                    updateRoute[i].forwards[i] = updateRoute[i].forwards[i].forwards[i];//#value=#value.next
            }
        }

        while (levelCount > 1 && head.forwards[levelCount] == null) levelCount--;//顶行为空则行数减一
    }

    // 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
    // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        50%的概率返回 1
    //        25%的概率返回 2
    //      12.5%的概率返回 3 ...
    private int randomLevel() {
        int level = 1;
        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) level += 1;
        //Math.random() 取值在[0.0,1.0]之间，
        return level;
    }
}
