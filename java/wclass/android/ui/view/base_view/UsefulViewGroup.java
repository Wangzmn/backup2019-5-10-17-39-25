package wclass.android.ui.view.base_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import wclass.enums.Orien2;
import wclass.ui.event_parser.MultiSingleParser;

import static wclass.android.ui.view.base_view.UsefulViewGroup.Strategy.CANT_TOUCH_SCROLL;
import static wclass.android.ui.view.base_view.UsefulViewGroup.Strategy.CAN_TOUCH_SCROLL;
import static wclass.android.ui.view.base_view.UsefulViewGroup.Strategy.PENDING;

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
public abstract class UsefulViewGroup extends ViewGroup {
    //////////////////////////////////////////////////
    /*ViewGroup子类通用代码。
    滑动处理。2019年4月29日18:00:40*/
    VelocityTracker vt;
    MultiSingleParser parser;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int actionMasked = ev.getActionMasked();
        if (actionMasked == MotionEvent.ACTION_DOWN) {
            childRequestEvent = false;
        }
        if (!needScroll()) {
            return super.onInterceptTouchEvent(ev);
        }
        return handleEventForScroll(ev, actionMasked);
    }


    private boolean handleEventForScroll(MotionEvent ev, int actionMasked) {
        boolean b = super.onInterceptTouchEvent(ev);
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                needDownEvent = false;
                strategy = PENDING;
                vt.clear();
                break;
        }
        if (needDownEvent) {
            return b;
        }
        if (parser == null) {
            vt = onCreateVT();
            parser = onCreateParser();
        }
        switch (strategy) {
            case PENDING:
                //只要子view请求一次，就都走这里了。
                if (isChildRequestEvent()) {
                    strategy = CANT_TOUCH_SCROLL;
                    break;
                }
                parser.parse(ev);
                vt.addMovement(ev);
                //横向大幅移动，竖向小幅移动，此时触发滑动。
                if (verifyCanScroll(parser)) {
                    if (lastAskChildNeedEventBeforeScroll(ev)) {
                        strategy = CANT_TOUCH_SCROLL;
                    }
                    //item不需要事件，由自己处理滑动操作。
                    else {
                        strategy = CAN_TOUCH_SCROLL;
                        onScrollStart(parser,ev);
                        b = true;
                    }
                }
                break;

            case CAN_TOUCH_SCROLL:
                parser.parse(ev);
                vt.addMovement(ev);
                onRealTimeTouchScroll(parser);
                onScrolling(parser,ev);

                if(actionMasked == MotionEvent.ACTION_UP){
                    onScrollFinish(parser,ev);

//                 todo   触发fling
                    onScrollFinishFling(parser,vt,ev);
                }
                b = true;
                break;

            case CANT_TOUCH_SCROLL:
                break;

            default:
                throw new IllegalStateException();
        }
        return b;
    }

    protected void onScrollStart(MultiSingleParser parser, MotionEvent ev) {
    }

    protected void onScrollFinish(MultiSingleParser parser, MotionEvent ev) {
    }

    protected void onScrolling(MultiSingleParser parser, MotionEvent ev) {
    }

    protected void onScrollFinishFling(MultiSingleParser parser, VelocityTracker vt, MotionEvent ev) {
    }

    /**
     * 实时滑动。
     */
    protected void onRealTimeTouchScroll(MultiSingleParser parser) {
        switch (getScrollOrien()) {
            case HORIZONTAL:
                //横向滑动时，设置X值。
                scrollBy((int) (parser.getScrollDeltaX_cutMove() + 0.5f),
                        getScrollY());
                break;
            case VERTICAL:
                //纵向滑动时，设置Y值
                scrollBy(getScrollX(),
                        (int) (parser.getScrollDeltaY_cutMove() + 0.5f));
                break;
            default:
                throw new IllegalStateException();
        }
    }

    protected VelocityTracker onCreateVT() {
        return VelocityTracker.obtain();
    }

    /**
     * 验证是否可以滑动。
     * @param parser 触摸事件记录点信息的类。
     * @return true：可以进入滑动。
     */
    protected boolean verifyCanScroll(MultiSingleParser parser) {
        switch (getScrollOrien()) {
            case HORIZONTAL:
                //横向大幅移动，纵向小幅移动。
                return !parser.isWormX() && parser.isWormY();
            case VERTICAL:
                //纵向大幅移动，横向小幅移动。
                return !parser.isWormY() && parser.isWormX();
            default:
                throw new IllegalStateException();
        }
    }

    protected MultiSingleParser onCreateParser() {
        switch (getScrollOrien()) {
            case HORIZONTAL:
                //横向滑动时，纵向条件放宽一点。
                return new MultiSingleParser(
                        ViewConfiguration.get(getContext()).getScaledTouchSlop()
                        , 1, 3);

            case VERTICAL:
                //纵向滑动时，横向条件放宽一点。
                return new MultiSingleParser(
                        ViewConfiguration.get(getContext()).getScaledTouchSlop()
                        , 3, 1);
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * 触发滑动之前，最后一次询问子view，是否需要自己处理事件。
     * 如果返回false，此次触摸事件子view将不再收到触摸事件。
     *
     * @return true：子view需要自己处理事件。
     */
    protected boolean lastAskChildNeedEventBeforeScroll(MotionEvent ev) {
        return false;
    }

    /**
     * 询问子类是否需要滑动。
     *
     * @return true：子类需要滑动。
     */
    protected boolean needScroll() {
        return false;
    }

    /**
     * 获取滑动方向。
     */
    protected Orien2 getScrollOrien() {
        return Orien2.HORIZONTAL;
    }

    /**
     * 触摸事件的策略标记。
     */
    Strategy strategy = PENDING;

    enum Strategy {
        PENDING,//待验证。
        CAN_TOUCH_SCROLL,//可以滑动。
        CANT_TOUCH_SCROLL;//不可以滑动。
    }
    //////////////////////////////////////////////////
    /*ViewGroup子类通用代码。
    子view请求事件。2019年4月29日18:00:31*/
    /**
     * 标记：子view是否请求触摸事件。
     */
    private boolean childRequestEvent;
    /**
     * 该变量作用：防止熄屏时手指按下期间开屏滑动。
     */
    boolean needDownEvent;

    public boolean isChildRequestEvent() {
        return childRequestEvent;
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        childRequestEvent |= disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            needDownEvent = true;
        }
    }
    //////////////////////////////////////////////////

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w == 0 || h == 0) {
            return;
        }
        onSizeChangedSafely(w, h);
    }

    protected void onSizeChangedSafely(int w, int h) {

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
    //--------------------------------------------------
    /**{@link #onMeasure}中，可使用方法。*/
    /**
     * 测量指定的孩子。
     */
    protected void measureChild(View child) {
        measureChild(child, getMeasuredWidthAndState(), getMeasuredHeightAndState());
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
    public UsefulViewGroup(Context context) {
        super(context);
    }

    public UsefulViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UsefulViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
