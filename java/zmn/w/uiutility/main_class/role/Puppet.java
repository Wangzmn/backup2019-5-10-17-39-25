package zmn.w.uiutility.main_class.role;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import wclass.android.ui.FloatWindowUT;
import zmn.w.uiutility.main_class.window.Window;

/**
 * @作者 做就行了！
 * @时间 2019-02-21下午 2:52
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public class Puppet extends Window {
    /**
     * 构造方法。
     *
     * @param context
     */
    public Puppet(Context context,View view) {
        super(context);
        //这里直接添加view，不需要Plugin级别的。
        getRoot().addView(view);
        WindowManager.LayoutParams lp = FloatWindowUT.makeDefaultLeftTopParams_NoTouch();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        setLayoutParams(lp);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

}
