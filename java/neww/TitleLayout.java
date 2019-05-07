package neww;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import wclass.android.ui.view.base_view.UsefulViewGroup;
import wclass.android.util.ViewUT;
import wclass.android.z_debug.LogUT;
import wclass.common.WH;

/**
 * @作者 做就行了！
 * @时间 2019-05-05下午 4:17
 * @该类描述： -
 * 1、大概样子：
 * ①横向布局的容器。
 * ②左边可以放多个view。这些view的顺序为：从左到右，下标从0递增。
 * ③右边可以放多个view。这些view的顺序为：从右到左，下标从0递增。
 * ④中间放一个view。中间的这个view，可以作为显示标题的容器。
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public class TitleLayout extends UsefulViewGroup {

    private static final boolean DEBUG = true;
    private Context context;

    public TitleLayout(Context context, Adapter adapter) {
        super(context);
        this.context = context;
        this.adapter = adapter;
    }

    /**
     * 左边的items。
     */
    List<View> lefts = new ArrayList<>();

    /**
     * 右边的items。
     */
    List<View> rights = new ArrayList<>();

    /**
     * 中间的item。
     * 友情提示：可以为null。
     */
    View mid;
    /**
     * 适配器类。{@link Adapter}
     */
    Adapter adapter;
    //////////////////////////////////////////////////

    @Override
    protected void onInit(int w, int h) {
        super.onInit(w, h);
        adapter.onCreateViews(context);
        int leftMenuCount = adapter.getLeftMenuCount();
        for (int i = 0; i < leftMenuCount; i++) {
            View leftMenu = adapter.onGetLeftMenu(context, i);
            lefts.add(leftMenu);
            addView(leftMenu);
        }
        int rightMenuCount = adapter.getRightMenuCount();
        for (int i = 0; i < rightMenuCount; i++) {
            View rightMenu = adapter.onGetRightMenu(context, i);
            rights.add(rightMenu);
            addView(rightMenu);
        }
        View midMenu = adapter.onGetMidMenu(context);
        if (midMenu != null) {
            mid = midMenu;
            addView(mid);
        }

    }

    //////////////////////////////////////////////////
    @Override
    protected void onSizeChangedSafely(int w, int h) {
        super.onSizeChangedSafely(w, h);
        adapter.onSizeChangeSafely(this, w, h);
        boolean same = adapter.leftsRightsSameSize();
        if (same) {
            WH wh = adapter.getSameSize();
            for (int i = 0; i < lefts.size(); i++) {
                View child = lefts.get(i);
                ViewUT.adjustSize(child, wh.w, wh.h);
            }
            for (int i = 0; i < rights.size(); i++) {
                View child = rights.get(i);
                ViewUT.adjustSize(child, wh.w, wh.h);
            }
        }
        measureChildrenWithMarginsSelfish();
        Vertical verti = adapter.getVerticalType();
        layoutLeftssss(verti, w, h);
        layoutRightssss(verti, w, h);
        layoutMidMenu(verti, w, h);
    }

    /**
     * 布局左边的控件。
     */
    private void layoutLeftssss(Vertical verti, int w, int h) {
        int layoutLeft = getStartLayoutLeft();
        int layoutTop;
        switch (verti) {
            case TOP:
                layoutTop = getPaddingTop();
                for (int i = 0; i < lefts.size(); i++) {
                    View child = lefts.get(i);
                    int childLayoutWidth = getLayoutWidth(child);
                    LayoutUT.layout(child, layoutLeft, layoutTop);
                    layoutLeft = getNextLayoutLeft(layoutLeft, childLayoutWidth);
                }
                break;
            case MID:
                for (int i = 0; i < lefts.size(); i++) {
                    View child = lefts.get(i);
                    int childLayoutWidth = getLayoutWidth(child);
                    int childLayoutHeight = getLayoutHeight(child);
                    layoutTop = (h - childLayoutHeight) / 2;
                    LayoutUT.layout(child, layoutLeft, layoutTop);
                    layoutLeft = getNextLayoutLeft(layoutLeft, childLayoutWidth);
                }
                break;
            case BOTTOM:
                int layoutBottom = h - getPaddingBottom();
                for (int i = 0; i < lefts.size(); i++) {
                    View child = lefts.get(i);
                    int childLayoutWidth = getLayoutWidth(child);
                    int childLayoutHeight = getLayoutHeight(child);
                    layoutTop = layoutBottom - childLayoutHeight;
                    LayoutUT.layout(child, layoutLeft, layoutTop);
                    layoutLeft = getNextLayoutLeft(layoutLeft, childLayoutWidth);
                }
                break;
            default:
                throw new IllegalStateException();
        }

        if (DEBUG) {
            for (int i = 0; i < lefts.size(); i++) {
                String str = getClass() + "#layoutLeftssss:" +
                        " child" + i + ":";
                LogUT.log_xywh(lefts.get(i), str);
            }
        }

    }


    /**
     * 布局右边的控件。
     */
    private void layoutRightssss(Vertical verti, int w, int h) {
        int layoutLeft;
        int layoutRight = getStartLayoutRight(w);
        int layoutTop;
        switch (verti) {
            case TOP:
                layoutTop = getPaddingTop();
                for (int i = 0; i < rights.size(); i++) {
                    View child = rights.get(i);
                    int childLayoutWidth = getLayoutWidth(child);
                    int childLayoutHeight = getLayoutHeight(child);
                    layoutLeft = layoutRight - childLayoutWidth;
                    LayoutUT.layout(child, layoutLeft, layoutTop);
                    layoutRight = getNextLayoutRight(layoutLeft);
                }
                break;
            case MID:
                for (int i = 0; i < rights.size(); i++) {
                    View child = rights.get(i);
                    int childLayoutWidth = getLayoutWidth(child);
                    int childLayoutHeight = getLayoutHeight(child);
                    layoutLeft = layoutRight - childLayoutWidth;
                    layoutTop = (h - childLayoutHeight) / 2;
                    LayoutUT.layout(child, layoutLeft, layoutTop);
                    layoutRight = getNextLayoutRight(layoutLeft);
                }
                break;
            case BOTTOM:
                int layoutBottom = h - getPaddingBottom();
                for (int i = 0; i < rights.size(); i++) {
                    View child = rights.get(i);
                    int childLayoutWidth = getLayoutWidth(child);
                    int childLayoutHeight = getLayoutHeight(child);
                    layoutLeft = layoutRight - childLayoutWidth;
                    layoutTop = layoutBottom - childLayoutHeight;

                    LayoutUT.layout(child, layoutLeft, layoutTop);
                    layoutRight = getNextLayoutRight(layoutLeft);
                }
                break;
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * 布局中间的控件。
     */
    private void layoutMidMenu(Vertical verti, int w, int h) {
        if (mid != null) {
            int layoutWidth = getLayoutWidth(mid);
            int layoutLeft = (w - layoutWidth) / 2;
            int layoutTop;
            switch (verti) {
                case TOP:
                    layoutTop = getPaddingTop();
                    LayoutUT.layout(mid, layoutLeft, layoutTop);
                    break;
                case MID:
                    int childLayoutHeight = getLayoutHeight(mid);
                    layoutTop = (h - childLayoutHeight) / 2;
                    LayoutUT.layout(mid, layoutLeft, layoutTop);
                    break;
                case BOTTOM:
                    int childLayoutHeight2 = getLayoutHeight(mid);
                    int layoutBottom = h - getPaddingBottom();
                    layoutTop = layoutBottom - childLayoutHeight2;
                    LayoutUT.layout(mid, layoutLeft, layoutTop);
                    break;
            }
        }
    }

    //////////////////////////////////////////////////

    /**
     * 获取左侧items，开始布局时的layoutLeft。
     */
    private int getStartLayoutLeft() {
        boolean has = adapter.isLeftAndRightHasGap();
        int gap = 0;
        if (has) {
            gap = adapter.getItemGap();
        }
        return getPaddingLeft() + gap;
    }

    /**
     * 获取右侧items，开始布局时的layoutRight。
     */
    private int getStartLayoutRight(int w) {
        boolean has = adapter.isLeftAndRightHasGap();
        int gap = 0;
        if (has) {
            gap = adapter.getItemGap();
        }
        return w - getPaddingRight() - gap;
    }

    /**
     * 获取下一个item的layoutLeft。
     *
     * @param layoutLeft       当前item的layoutLeft。
     * @param childLayoutWidth item的layoutWidth。
     * @return 获取下一个item的layoutLeft。
     */
    private int getNextLayoutLeft(int layoutLeft, int childLayoutWidth) {
        return layoutLeft + childLayoutWidth + adapter.getItemGap();
    }

    /**
     * 获取下一个item的layoutRight。
     *
     * @param layoutLeft 当前item的layoutLeft。
     * @return 获取下一个item的layoutLeft。
     */
    private int getNextLayoutRight(int layoutLeft) {
        return layoutLeft - adapter.getItemGap();
    }
    //////////////////////////////////////////////////

    /**
     *
     */
    public static abstract class Adapter {
        /**
         * 在该方法中创建所有view。
         * {@link Adapter#onGetMidMenu(Context)}
         * {@link Adapter#onGetLeftMenu(Context, int)}
         * {@link Adapter#onGetRightMenu(Context, int)}
         * 在以上方法中返回他们。
         *
         * @param context 上下文。
         */
        public abstract void onCreateViews(Context context);

        /**
         * 获取中间的控件。
         * <p>
         * 友情提示：可以返回null。
         *
         * @param context 上下文。
         * @return 中间的控件。
         */
        public abstract View onGetMidMenu(Context context);

        /**
         * 获取左边的控件。
         * <p>
         * 友情提示：可以返回null。
         *
         * @param context  上下文。
         * @param position 从左到右排序的下标。
         * @return 左边的控件。
         */
        public abstract View onGetLeftMenu(Context context, int position);

        /**
         * 获取右边的控件。
         * <p>
         * 友情提示：可以返回null。
         *
         * @param context  上下文。
         * @param position 从右到左排序的下标。
         * @return 右边的控件。
         */
        public abstract View onGetRightMenu(Context context, int position);

        //////////////////////////////////////////////////

        /**
         * titleLayout每次大小改变时，会调用该方法。
         * <p>
         * 友情提示：
         * 1、可以在该方法中调整子view的大小。
         * 2、如果{@link #leftsRightsSameSize()}返回true，
         * 那么左右两侧的item大小将采用{@link #getSameSize()}的大小。
         * 也就是说，在该方法调整左右两侧的view的大小是无效的。
         *
         * @param titleLayout titleLayout。
         * @param w           他的宽。
         * @param h           他的高。
         */
        public abstract void onSizeChangeSafely(TitleLayout titleLayout,
                                                int w, int h);

        /**
         * 获取左边控件的数量。
         */
        public abstract int getLeftMenuCount();

        /**
         * 获取右边控件的数量。
         */
        public abstract int getRightMenuCount();

        /**
         * 获取垂直方向的布局锚点。
         */
        public Vertical getVerticalType() {
            return Vertical.MID;
        }

        //////////////////////////////////////////////////

        /**
         * 获取位于左右两边的item，他们之间的间隙。
         */
        public int getItemGap() {
            return 0;
        }

        /**
         * 最左和最右两端是否有间隙。
         *
         * @return true：最左和最右也有间隙。
         */
        public boolean isLeftAndRightHasGap() {
            return true;
        }

        /**
         * 位于左右两边的item是否一样大小。
         * <p>
         * 警告：如果返回true，请重写{@link Adapter#getSameSize()}方法。
         *
         * @return true：他们一样大小。
         */
        public boolean leftsRightsSameSize() {
            return false;
        }

        /**
         * 获取位于左右两边的item的宽高。
         */
        public WH getSameSize() {
            throw new IllegalStateException("如果您重写了leftsRightsSameSize()方法，" +
                    "请重写getSameSize()方法。");
        }
        //////////////////////////////////////////////////

    }
}
