package neww;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import wclass.android.ui.view.base_view.UsefulViewGroup;

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

    public TitleLayout(Context context) {
        super(context);
    }

    List<View> lefts = new ArrayList<>();
    List<View> rights = new ArrayList<>();
    View mid;
    Adapter adapter;

    @Override
    protected void onSizeChangedSafely(int w, int h) {
        super.onSizeChangedSafely(w, h);

        measureChildrenWithMarginsSelfish();
        Vertical verti = adapter.getVerticalType();
        layoutLeftssss(verti, w, h);
        layoutRightssss(verti, w, h);

        if (m id != null) {
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

    }

    /**
     *
     */
    public static abstract class Adapter {
        protected abstract int getLeftMenuCount();

        protected abstract int getRightMenuCount();


        public Vertical getVerticalType() {
            return Vertical.MID;
        }
    }
}
