package neww;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import wclass.android.util.DebugUT;
import wclass.android.util.ViewUT;

/**
 * @作者 做就行了！
 * @时间 2019/5/6 0006
 * @使用说明：
 */
public class AdapterTestEach extends TitleLayout.Adapter {
    @Override
    public void onSizeChangeSafely(TitleLayout titleLayout, int w, int h) {
        titleLayout.setPadding(5,5,5,5);
    }

    TextView l0;
    TextView l1;
    TextView l2;

    TextView r0;
    TextView r1;

    @Override
    public View onCreateMidMenu(Context context, int w, int h) {
        int width = 30;
        TextView tv = new TextView(context);
        tv.setTextSize(width / 9);
        tv.setIncludeFontPadding(false);
        tv.setText("我是mid" );
        ViewUT.adjustSize(tv, width,h/2);
        DebugUT.randomBG(tv);
        return tv;
    }
    @Override
    public View onCreateLeftMenu(Context context, int position, int w, int h) {
        TextView tv = new TextView(context);
        tv.setIncludeFontPadding(false);
        tv.setText("我是left" + position);
        DebugUT.randomBG(tv);
        switch (position) {
            case 0:
                int width = 60;
                l0 = tv;
                ViewUT.adjustSize(l0,width,h/2,
                        10,10,10,10);
//                tv.setTextSize(width / 9);
                break;
            case 1:
                int width1 = 30;
                l1 = tv;
                ViewUT.adjustSize(l1,width1,h/2,
                        10,10,10,10);
//                tv.setTextSize(width1 / 9);
                break;
            case 2:
                int width2 = 30;
                l2 = tv;
                ViewUT.adjustSize(l2,width2,h/2,
                        10,10,10,10);
//                tv.setTextSize(width2 / 9);
                break;
        }
        return tv;
    }

    @Override
    public View onCreateRightMenu(Context context, int position, int w, int h) {
        TextView tv = new TextView(context);
        tv.setIncludeFontPadding(false);
        tv.setText("我是right" + position);
        DebugUT.randomBG(tv);
        switch (position) {
            case 0:
                int width = 30;
                r0 = tv;
                ViewUT.adjustSize(r0,width,h/2,
                        10,10,10,10);
//                tv.setTextSize(width / 9);
                break;
            case 1:
                int width1 = 45;
                r1 = tv;
                ViewUT.adjustSize(r1,width1,h/2,
                        10,10,10,10);
//                tv.setTextSize(width1 / 9);
                break;
        }
        return tv;
    }

    @Override
    public int getLeftMenuCount() {
        return 3;
    }

    @Override
    public int getRightMenuCount() {
        return 2;
    }

    @Override
    public Vertical getVerticalType() {
//        return Vertical.TOP;
        return Vertical.BOTTOM;
    }
}
