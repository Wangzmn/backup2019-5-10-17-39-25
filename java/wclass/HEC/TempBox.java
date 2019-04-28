package wclass.HEC;

import java.util.LinkedList;

import wclass.y_marks.Ugly_ToString;

/**
 * 检查于 2018年11月24日23:03:03
 *
 * @作者 做就行了！
 * @时间 2018-11-18下午 6:10
 * @该类用途： -
 * 1、“E”作为备用，需要时 随便取一个出来。
 * 2、该类 类似{@link LinkedList}，
 * 区别是：不需要每次“new”创建node对象。
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class TempBox<E> {
    private static final String SIMPLE_NAME = TempBox.class.getSimpleName();
    private static final String LF = "\n";
    //----------------------------------------------------------------------
    private static final int DEFAULT_CAPACITY = 5;//默认容量大小。
    //----------------------------------------------------------------------
    private Node zero;//该节点作为原点。不保存任何数据。
    private Node last;//最后一个节点。
    private Node lastVal;//保存最后一个数据的节点。

    //////////////////////////////////////////////////////////////////////

    /**
     * 构造方法。
     */
    public TempBox() {
        init();
    }

    /**
     * 初始化。
     */
    private void init() {
        zero = last = lastVal = new Node(0);
    }
    //////////////////////////////////////////////////////////////////////
    /*step DEBUG*/

    @Ugly_ToString
    @Override
    public String toString() {
        return "[#TempBox: zero = " + zero.infoToStr()
                + LF + "last = " + last.infoToStr()
                + LF + "lastVal = " + lastVal.infoToStr()
                + " #TempBox]";
    }
    //////////////////////////////////////////////////////////////////////

    /**
     * 检查于 2019年1月7日22:42:55
     * <p>
     * 扩充存储容量！！！
     */
    private void increaseCapacity() {
        last.putNext(new Node(last.number + 1));
        last = last.next;
    }
    //////////////////////////////////////////////////////////////////////

    /**
     * 检查于 2019年1月7日22:42:55
     * <p>
     * 放数据。
     * <p>
     * 友情提示：满了则扩充。
     */
    public void put(E val) {
        if (!isFree()) {
            increaseCapacity();
        }
        lastVal = lastVal.next;
        lastVal.val = val;
    }

    /**
     * 检查于 2019年1月7日22:42:55
     * <p>
     * 取数据。
     * <p>
     * 友情提示：没有则返回null。
     */
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E val = lastVal.pollVal();
        lastVal = lastVal.pre;
        return val;
    }

    //////////////////////////////////////////////////////////////////////

    /**
     * 运行于 2018年11月25日00:26:43
     * <p>
     * 重新调整大小。
     * <p>
     * 友情提示：超出capacity的数据将会被删除。
     *
     * @param capacity 设置为该值的大小！！！
     */
    public void recapacity(int capacity) {
        checkCapacity(capacity);

        //大于请求容量，缩小。
        if (last.number > capacity) {
            Node futureLast = last.pre;
            while (futureLast.number > capacity) {
                futureLast = futureLast.pre;
            }
            if (futureLast.number < lastVal.number) {
                lastVal = futureLast;
            }
            futureLast.removeNext();
            last = futureLast;
        }
        //小于请求容量，扩充。
        else if (last.number < capacity) {
            do {
                increaseCapacity();
            }
            while (last.number < capacity);
        }
        //等于请求容量，不需要调整，直接返回。
    }

    //////////////////////////////////////////////////////////////////////

    /**
     * 运行于 2018年11月25日00:54:16
     * <p>
     * 清空所有缓存数据。
     */
    public void clear() {
        init();
    }

    /**
     * 运行于 2018年11月25日00:54:16
     *
     * @return 容量。
     */
    public int capacity() {
        return last.number;
    }

    /**
     * 运行于 2018年11月25日00:54:16
     *
     * @return 缓存数据 的数量。
     */
    public int size() {
        return lastVal.number;
    }

    /**
     * 运行于 2018年11月25日00:54:16
     *
     * @return true：缓存数据 数量为0。
     */
    public boolean isEmpty() {
        return lastVal.number == 0;
    }

    /**
     * 运行于 2018年11月25日00:54:16
     *
     * @return true：有剩余空间。
     */
    public boolean isFree() {
        return lastVal.next != null;
    }
    //////////////////////////////////////////////////////////////////////

    /**
     * 检查于 2019年1月7日22:15:38
     * <p>
     * 检查请求容量，适当时抛出异常。
     *
     * @param capacity 用户请求的容量
     */
    private void checkCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("容量只能大于0 。");
        }
    }

    //////////////////////////////////////////////////////////////////////

    /**
     * 检查于 2019年1月7日22:16:35
     */
    class Node {
        private final String SIMPLE_NAME = Node.class.getSimpleName();
        int number;//编号。代表第几个数据。
        Node pre;//前一个
        Node next;//后一个
        E val;//携带的数据
        //////////////////////////////////////////////////////////////////////

        Node(int number) {
            this.number = number;
        }

        //////////////////////////////////////////////////////////////////////
        /*step DEBUG*/
        public String infoToStr() {
            String preStr = "";
            String nextStr = "";
            String meValStr;
            String temp = "";
            if (pre != null) {
                if (pre.val != null) {
                    temp = "have";
                } else {
                    temp = "null";
                }
                preStr = "have -- pre.val = " + temp;
            } else {
                preStr = "null";
            }

            if (next != null) {
                if (next.val != null) {
                    temp = "have";
                } else {
                    temp = "null";
                }
                nextStr = "have -- next.val = " + temp;
            } else {
                nextStr = "null";
            }

            if (val != null) {
                meValStr = "have";
            } else {
                meValStr = "null";
            }
            return "[#" + SIMPLE_NAME + ": " +
                    "int number = " + number
                    + ", Node pre = " + preStr
                    + ", Node next = " + nextStr
                    + ", E val = " + meValStr
                    + " #" + SIMPLE_NAME + "]";
        }
        //////////////////////////////////////////////////////////////////////

        /**
         * 删除后一个节点。
         */
        void removeNext() {
            next = null;
        }

        /**
         * 添加后一个节点。
         *
         * @param node 后一个节点。
         */
        void putNext(Node node) {
            next = node;
            node.pre = this;
        }

        /**
         * 取出数据，并清空。
         *
         * @return 数据
         */
        E pollVal() {
            E val = this.val;
            this.val = null;
            return val;
        }

    }
}
