package view_pager;

import android.content.Context;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import wclass.android.ui.view.base_view.UsefulViewGroup;

/**
 * @作者 做就行了！
 * @时间 2019/4/28 0028
 * @使用说明：
 */
@SuppressWarnings("unchecked")
public class ViewPager extends UsefulViewGroup {

    private static final boolean DEBUG = true;
    //--------------------------------------------------
    /**
     * todo 问题枚举：
     * 1、超出指定数量时，Detach最早的item。
     * ①解决：
     * ·加载哪个方向，detach另一个方向。
     * //     * 2、通过速率fling。
     * //     * ①记录触摸的最大坐标。
     */
    //////////////////////////////////////////////////
    boolean init = false;
    private int w;
    private int h;
    private Context context;
    //////////////////////////////////////////////////

    public ViewPager(Context context) {
        super(context);
        this.context = context;

        if (DEBUG) {

        }
    }
    //////////////////////////////////////////////////

    @Override
    protected boolean needScroll() {
        return true;
    }
    //////////////////////////////////////////////////

    @Override
    protected void onSizeChangedSafely(int w, int h) {
        this.w = w;
        this.h = h;
        super.onSizeChangedSafely(w, h);

        if (!init) {
            init = true;
            init();
        }
    }

    Adapter adapter;
    SparseArray<ItemInfo> items = new SparseArray<>();

    private void init() {
        if (showPosition == -1) {
            showPosition = 0;
        }
        attachItem(showPosition);
        if (autoLoad) {
            attachItem(showPosition - 1);
            attachItem(showPosition + 1);
        }
    }

    private void attachItem(int position) {
        if (!verifyPosition(position)) {
            return;
        }
        View view = getview(position);
        addView(view);
        measureChild(view);
        adapter.onLayoutView(view, position, this);
        items.put(position, new ItemInfo(view, position));
    }

    private boolean verifyPosition(int position) {
        return position >= 0 && position < adapter.getItemCount();
    }

    boolean autoLoad = true;

    class ItemInfo {
        View view;

        ItemInfo(View view, int position) {
            this.view = view;
        }
    }

    private View getview(int position) {
        return adapter.getView(context, position, w, h);
    }

    int showPosition = -1;

    public abstract class Adapter<T extends View> {
        /**
         * 该变量只能在{@link Adapter}的方法中使用。
         */
        ViewPager mViewPager;

        public final ViewPager getViewPager() {
            return mViewPager;
        }

        private void setViewPager(ViewPager viewPager) {
            if (mViewPager != null) {
                throw new IllegalStateException("不能重复设置adapter。");
            }
            mViewPager = viewPager;
        }
        //////////////////////////////////////////////////

        public abstract T getView(Context context, int position, int pageW, int pageH);

        public void onLayoutView(T view, int position, ViewPager viewPager) {
            view.layout(getScrollXForPosition(position, viewPager), 0,
                    view.getMeasuredWidth(), view.getMeasuredHeight());
        }

        public abstract void onDetachView(T view, int position);

        //////////////////////////////////////////////////
        /*domain 自己看着办吧，重写吧。*/

        /**
         * 获取指定下标的view所在的滑动值。
         *
         * @param position view的下标。
         * @return view所在的滑动值。
         */
        public int getScrollXForPosition(int position, ViewPager viewPager) {
            return position * viewPager.getWidth();
        }

        public abstract int getItemCount();

        public boolean lastAskChildNeedEventBeforeScroll(MotionEvent ev) {
            return false;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
