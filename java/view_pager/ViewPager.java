package view_pager;

import android.content.Context;
import android.view.View;

import wclass.HEC.IntMap;
import wclass.android.ui.view.base_view.UsefulViewGroup;

/**
 * @作者 做就行了！
 * @时间 2019/4/28 0028
 * @使用说明：
 */
public class ViewPager extends UsefulViewGroup {

    private static final boolean DEBUG = true;
    //--------------------------------------------------
    /**
     * todo 问题枚举：
     * 1、超出指定数量时，Detach最早的item。
     * ①解决：
     * ·加载哪个方向，detach另一个方向。
     */
    //////////////////////////////////////////////////
    boolean init = false;
    private int w;
    private int h;
    private Context context;
    //--------------------------------------------------
    IntMap<View>items = new IntMap<>();
    //////////////////////////////////////////////////

    public ViewPager(Context context) {
        super(context);
        this.context = context;
        if(DEBUG){

        }
    }

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

    private void init() {
        if (showPosition == -1) {
            showPosition = 0;
        }
        View view = getview();
        items.put()
    }

    private View getview() {
        return adapter.getView(context, showPosition, w, h);
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

        public void onLayoutView(T view, int position,ViewPager viewPager) {
            view.layout(getScrollXForPosition(position,viewPager),0,
                    view.getMeasuredWidth(),view.getMeasuredHeight());
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
        public int getScrollXForPosition(int position,ViewPager viewPager) {
            return position * viewPager.getWidth();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
