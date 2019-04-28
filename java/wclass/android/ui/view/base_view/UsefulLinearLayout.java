package wclass.android.ui.view.base_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @作者 做就行了！
 * @时间 2019-04-09下午 9:18
 * @该类描述： -
 * 1、该类提供一些方法，辅助测量子view、获取子view参数。
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
@SuppressWarnings("DanglingJavadoc")
public abstract class UsefulLinearLayout extends LinearLayout {

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w==0||h==0){
            return;
        }
        onSizeChangedSafely(w,h);
    }

    protected void onSizeChangedSafely(int w, int h){

    }

    //////////////////////////////////////////////////
    /** {@link #onLayout}中，可使用方法。*/

    /**
     * 获取布局时可用的宽。
     */
    protected int getUsableWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 获取布局时可用的高。
     */
    protected int getUsableHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    //////////////////////////////////////////////////
    /**{@link #onMeasure}中，可使用方法。*/
    /**
     * 测量指定的孩子。
     */
    protected void measureChild(View child){
        measureChild(child,getMeasuredWidthAndState(),getMeasuredHeightAndState());
    }
    /**
     * 包括margin在内测量孩子的宽高，每个孩子独立测量，互不影响。
     */
    protected void measureChildrenWithMarginsSelfish() {
        int widthMeasureSpec = getMeasuredWidthAndState();
        int heightMeasureSpec = getMeasuredHeightAndState();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getLayoutParams() instanceof MarginLayoutParams) {
                measureChildWithMargins(child, widthMeasureSpec, 0,
                        heightMeasureSpec, 0);
            } else {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
            }
        }
    }
    //////////////////////////////////////////////////

    /**
     * 获取子view布局时的宽。
     *
     * @param child       子view。
     * @param childParams 子view的params。
     * @return 获取子view布局时的宽。
     */
    protected int getLayoutWidth(View child, LayoutParams childParams) {
        if (childParams instanceof MarginLayoutParams) {
            MarginLayoutParams childParams1 = (MarginLayoutParams) childParams;
            return child.getMeasuredWidth() + childParams1.leftMargin + childParams1.rightMargin;
        } else {
            return child.getMeasuredWidth();
        }
    }

    /**
     * 获取子view布局时的高。
     *
     * @param child       子view。
     * @param childParams 子view的params。
     * @return 获取子view布局时的宽。
     */
    protected int getLayoutHeight(View child, LayoutParams childParams) {
        if (childParams instanceof MarginLayoutParams) {
            MarginLayoutParams childParams1 = (MarginLayoutParams) childParams;
            return child.getMeasuredHeight() + childParams1.topMargin + childParams1.bottomMargin;
        } else {
            return child.getMeasuredHeight();
        }
    }

    //////////////////////////////////////////////////
    public UsefulLinearLayout(Context context) {
        super(context);
    }

    public UsefulLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UsefulLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
