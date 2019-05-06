package neww;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import wclass.android.ui.view.base_view.UsefulViewGroup;
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
        int leftMenuCount = adapter.getLeftMenuCount();
        for (int i = 0; i < leftMenuCount; i++) {
            View leftMenu = adapter.onCreateLeftMenu(context, i, w, h);
            lefts.add(leftMenu);
            addView(leftMenu);
        }
        int rightMenuCount = adapter.getRightMenuCount();
        for (int i = 0; i < rightMenuCount; i++) {
            View rightMenu = adapter.onCreateRightMenu(context, i, w, h);
            rights.add(rightMenu);
            addView(rightMenu);
        }
        View midMenu = adapter.onCreateMidMenu(context, w, h);
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
        int layoutLeft = getPaddingLeft();
        int layoutTop;
        switch (verti) {
            case TOP:
                layoutTop = getPaddingTop();
                for (int i = 0; i < lefts.size(); i++) {
                    View child = lefts.get(i);
                    int childLayoutWidth = getLayoutWidth(child);
                    LayoutUT.layout(child, layoutLeft, layoutTop);
                    layoutLeft += childLayoutWidth;
                }
                break;
            case MID:
                for (int i = 0; i < lefts.size(); i++) {
                    View child = lefts.get(i);
                    int childLayoutWidth = getLayoutWidth(child);
                    int childLayoutHeight = getLayoutHeight(child);
                    layoutTop = (h - childLayoutHeight) / 2;
                    LayoutUT.layout(child, layoutLeft, layoutTop);
                    layoutLeft += childLayoutWidth;
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
                    layoutLeft += childLayoutWidth;
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
        int layoutRight = w - getPaddingRight();
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
                    layoutRight = layoutLeft;
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
                    layoutRight = layoutLeft;
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
                    layoutRight = layoutLeft;
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


    /**
     *
     */
    public static abstract class Adapter {

        /**
         * titleLayout每次大小改变时，会调用该方法。
         *
         * @param titleLayout titleLayout。
         * @param w           他的宽。
         * @param h           他的高。
         */
        public abstract void onSizeChangeSafely(TitleLayout titleLayout,
                                                int w, int h);

        /**
         * 获取中间的控件。
         * <p>
         * 友情提示：可以返回null。
         *
         * @param context 上下文。
         * @param w       titleLayout的宽。
         * @param h       titleLayout的高。
         * @return 中间的控件。
         */
        public abstract View onCreateMidMenu(Context context, int w, int h);

        /**
         * 获取左边的控件。
         * <p>
         * 友情提示：可以返回null。
         *
         * @param context  上下文。
         * @param position 从左到右排序的下标。
         * @param w        titleLayout的宽。
         * @param h        titleLayout的高。
         * @return 左边的控件。
         */
        public abstract View onCreateLeftMenu(Context context, int position, int w, int h);
        /**
         * 获取右边的控件。
         * <p>
         * 友情提示：可以返回null。
         *
         * @param context  上下文。
         * @param position 从右到左排序的下标。
         * @param w        titleLayout的宽。
         * @param h        titleLayout的高。
         * @return 右边的控件。
         */
        public abstract View onCreateRightMenu(Context context, int position, int w, int h);

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
         * 位于左右两边的item是否一样大小。
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
