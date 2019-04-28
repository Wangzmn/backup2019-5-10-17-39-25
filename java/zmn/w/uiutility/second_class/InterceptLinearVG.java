package zmn.w.uiutility.second_class;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class InterceptLinearVG extends LinearLayout {

    public InterceptLinearVG(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return cb != null && cb.onInterceptTouchEvent(ev) ||
                super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        if (cb != null) {
            b |= cb.onTouchEvent(event);
        }
        return b;
    }

    OnTouchEventListener cb;
    public void setOnInterceptTouchEventListener(OnTouchEventListener cb) {
        this.cb = cb;
    }
}