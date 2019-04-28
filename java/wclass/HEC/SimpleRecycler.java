package wclass.HEC;


import wclass.y_marks.Ugly_ToString;

/**
 * 完成于 2019年1月25日22:57:00
 * @作者 做就行了！
 * @时间 2018-10-31下午 2:29
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 */
@SuppressWarnings("WeakerAccess,unused")
public class SimpleRecycler<T> {
//public class SimpleRecycler<T extends Reuse> {
    private static final String SIMPLE_NAME = SimpleRecycler.class.getSimpleName();
    private static final String LF = "\n";
    private static final int LIMIT_SIZE = 5;
    //----------------------------------------------------------------------
    private int capacity;//总容量
    //----------------------------------------------------------------------
    private TempBox<T> tempBox;//缓存容器
    private OnCreateListener<T> onCreateListener;
    //////////////////////////////////////////////////////////////////////

    /**
     * {@link #SimpleRecycler(int)}
     */
    public SimpleRecycler() {
        this(LIMIT_SIZE);
    }

    /**
     * 构造方法：创建一个有大小限制的循环池。
     *
     * @param capacity 备用item数量的限制大小
     */
    public SimpleRecycler(int capacity) {
        tempBox = new TempBox<>();
        this.capacity = capacity;
    }
    //////////////////////////////////////////////////////////////////////

    @Ugly_ToString
    @Override
    public String toString() {
        return "[#" + SIMPLE_NAME + ": "
                + "int capacity = " + capacity
                + LF + "TempBox<T> tempBox = " + tempBox.toString()
                + " #" + SIMPLE_NAME + "]";
    }
    //////////////////////////////////////////////////////////////////////

    public void setOnCreateListener(OnCreateListener<T> listener) {
        if (onCreateListener != null) {
            throw new IllegalStateException("不能重复设置OnCreateListener。");
        }
        onCreateListener = listener;
    }

    public interface OnCreateListener<T> {
        T onCreate();
    }
    //////////////////////////////////////////////////////////////////////

    /**
     * 执行循环操作。
     *
     * @param item 将被循环的item
     */
    public void recycle(T item) {
        //不能循环操作，则返回。
        if (item == null || !isFree()) {
            return;
        }
        //复位、循环该item。
//        item.onReset();
        tempBox.put(item);
    }

    /**
     * 获取一个备用item。
     *
     * @return 备用item
     */
    public T get() {
        try {
            //没有，则创建一个。
            if (isEmpty()) {
                return onCreateListener.onCreate();
            }
            //有，则取出一个
            return tempBox.poll();
        } catch (NullPointerException e) {
            throw new IllegalStateException("请调用方法“setOnCreateListener()”。");
        }
    }

    //----------------------------------------------------------------------

    /**
     * {@link  TempBox#recapacity(int)}
     */
    public void recapacity(int capacity) {
        tempBox.recapacity(capacity);
        this.capacity = capacity;
    }

    /**
     * 恢复初始化状态。
     */
    public void clear() {
        tempBox.clear();
    }

    /**
     * 备用item数量是否为空。
     *
     * @return true：数量为空。false：数量不为空。
     */
    public boolean isEmpty() {
        return tempBox.isEmpty();
    }

    /**
     * 容量在限制范围内，则可以继续执行循环操作。
     *
     * @return true：可以继续循环操作。false：不能再循环操作了。
     */
    public boolean isFree() {
        return tempBox.size() < capacity;
    }

    /**
     * 获取当前容量大小。
     */
    public int size() {
        return tempBox.size();
    }

    /**
     * 获取总容量。
     */
    public int capacity() {
        return capacity;
    }

}
