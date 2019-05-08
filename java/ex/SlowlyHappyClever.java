package ex;

import android.content.Context;
import android.view.View;

import wclass.android.ui.view.base_view.UsefulFrameLayout;
import wclass.android.util.ViewUT;

/**
 * @作者 做就行了！
 * @时间 2019/5/8 0008
 * @使用说明：
 */
public class SlowlyHappyClever extends UsefulFrameLayout {

    public View head;
    public View mid;
    public View tail;

    public SlowlyHappyClever(Context context) {
        super(context);
    }

    //////////////////////////////////////////////////
    boolean init;

    @Override
    protected void onSizeChangedSafely(int w, int h) {
        super.onSizeChangedSafely(w, h);
        if (!init) {
            init = true;
            init(w, h);
        }
    }

    Adapter adapter;

    /**
     * 通过adapter创建views，并添加至ViewGroup中，
     * 并调整他们的大小。
     *
     * @param w 容器宽。
     * @param h 容器高。
     */
    private void init(int w, int h) {
        head = adapter.onGetHead();
        mid = adapter.onGetMid();
        tail = adapter.onGetTail();
        addView(head);
        if (mid != null) {
            addView(mid);
            ViewUT.adjustSize(mid, w, adapter.getMidHeight());
        }
        addView(head);
        ViewUT.adjustSize(head, w, adapter.getHeadHeight());
        ViewUT.adjustSize(tail, w, adapter.getTailHeight());
    }

    /**
     * 1、创建view。
     * 2、返回view，让容器自动添加。
     */
    public static abstract class Adapter {
        /**
         * 在该方法中创建views，通过以下方法返回这些views：
         * {@link Adapter#onGetHead()}
         * {@link Adapter#onGetMid()}
         * {@link Adapter#onGetTail()}
         */
        public abstract void onCreateViews();

        public abstract View onGetHead();

        public View onGetMid() {
            return null;
        }

        public abstract View onGetTail();

        public abstract int getHeadHeight();

        public int getMidHeight() {
            return 10;
        }

        public abstract int getTailHeight();
    }
}
