package wclass.android.ui.view.base_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @作者 做就行了！
 * @时间 2019-04-09下午 9:18
 * @该类描述： -
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
@Deprecated
@SuppressWarnings("DanglingJavadoc")
public abstract class BaseViewGroup extends ViewGroup {

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
    public BaseViewGroup(Context context) {
        super(context);
    }

    public BaseViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
