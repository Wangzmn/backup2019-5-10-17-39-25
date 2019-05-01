package view_pager;

import android.content.Context;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import wclass.android.ui.view.base_view.UsefulScrollViewGroup;
import wclass.enums.Orien2;
import wclass.ui.event_parser.MultiSingleParser;

/**
 * @作者 做就行了！
 * @时间 2019/4/28 0028
 * @使用说明：
 */
@SuppressWarnings("unchecked")
public class ViewPager extends UsefulScrollViewGroup {
    private static final boolean DEBUG = true;
    //--------------------------------------------------
    /**
     * todo 问题枚举：
     * 1、超出指定数量时，Detach最早的item。
     * ①解决：
     * ·加载哪个方向，detach另一个方向。
     * //     * 2、通过速率fling。
     * //     * ①记录触摸的最大坐标。
     * <p>
     * 2、设计思路：
     * ①就按“页”设计！！！
     */
    //////////////////////////////////////////////////
    boolean init = false;
    private int w;
    private int h;
    private Context context;
    //////////////////////////////////////////////////

    @Override
    protected Orien2 getScrollOrien() {
        return Orien2.HORIZONTAL;
    }

    public ViewPager(Context context) {
        super(context);
        this.context = context;

        if (DEBUG) {

        }
    }
    //--------------------------------------------------

    int startScrollX;

    @Override
    protected void onSetTouchScrollValue(MultiSingleParser parser) {
        super.onSetTouchScrollValue(parser);
    }

    @Override
    protected void onTouchScroll_start(MultiSingleParser parser, MotionEvent ev) {
        super.onTouchScroll_start(parser, ev);
        startScrollX = getScrollX();
    }

    @Override
    protected void onTouchScroll_finishAndDoFling(MultiSingleParser parser, VelocityTracker vt, MotionEvent ev) {
        super.onTouchScroll_finishAndDoFling(parser, vt, ev);
        int currScrollX = getScrollX();
        int cutScrollX = currScrollX - startScrollX;
        //往之后。
        if (cutScrollX > 0) {
//            if(cutScrollX)
            //先通过滑动距离判断。
            if(){

            }
            //再通过速率判断。
            else{
                vt.computeCurrentVelocity(1000);
                float xVelocity = vt.getXVelocity();
                if(-xVelocity>w){
                    //触发下一页。
                }
            }
        }
        //往之前。
        else {
            //先通过滑动距离判断。
            if(){

            }
            //再通过速率判断。
            else{
                vt.computeCurrentVelocity(1000);
                float xVelocity = vt.getXVelocity();
                if(xVelocity>w){
                    //触发上一页。
                }
            }

        }
        //--------------------------------------------------
        /*滑动距离触发不了页滑动时，通过速率判断。*/
        vt.computeCurrentVelocity(1000);
        float xVelocity = vt.getXVelocity();
        if (Math.abs(xVelocity )> w) {
            //往之前。
            if(xVelocity>0){

            }
            //往之后。
            else{

            }
        }
    }

    //////////////////////////////////////////////////

    public void showPrePosition(int duration) {
        int position = showPosition - 1;
        if (attachItemCheckly(position)) {
            int scrollX = getscrollX(position);
            setScrollX(scrollX);
        }
    }

    public void showNextPosition() {
        int position = showPosition + 1;
        if (attachItemCheckly(position)) {
            int scrollX = getscrollX(position);
            setScrollX(scrollX);
        }
    }

    public void scrollToPrePosition(int duration) {
        int position = showPosition - 1;
        if (attachItemCheckly(position)) {
            int scrollX = getscrollX(position);
            smoothScrollTo(scrollX, duration);
        }
    }

    public void scrollToNextPosition(int duration) {
        int position = showPosition + 1;
        if (attachItemCheckly(position)) {
            int scrollX = getscrollX(position);
            smoothScrollTo(scrollX, duration);
        }
    }

    public void showPositionDirectly(int position) {
        if (attachItemCheckly(position)) {
            int scrollX = getscrollX(position);
            setScrollX(scrollX);
        }
    }
    //////////////////////////////////////////////////

    @Override
    protected void onNoTouchScroll_finish() {
        super.onNoTouchScroll_finish();
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
        attachItemDirectly(showPosition);
        if (autoLoad) {
            attachItemDirectly(showPosition - 1);
            attachItemDirectly(showPosition + 1);
        }
    }

    private void attachItemDirectly(int position) {
        if (!verifyPosition(position)) {
            return;
        }
        View view = getview(position);
        items.put(position, new ItemInfo(view, position));
        addView(view);
        measureChild(view);
        adapter.onLayoutItem(view, position, this);
    }

    private int getscrollX(int position) {
        return adapter.getScrollXForPosition(position, this);
    }

    /**
     * 将item添加至容器。
     *
     * @param position item的下标。
     * @return true：item已经添加至容器。
     * false：item未添加至容器，item下标异常。
     */
    private boolean attachItemCheckly(int position) {
        if (!verifyPosition(position)) {
            return false;
        }
        ItemInfo itemInfo = items.get(position);
        //该position的item已经显示了。
        if (itemInfo != null) return true;

        View view = getview(position);
        items.put(position, new ItemInfo(view, position));
        addView(view);
        measureChild(view);
        adapter.onLayoutItem(view, position, this);
        return true;
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

        public void onLayoutItem(T view, int position, ViewPager viewPager) {
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
