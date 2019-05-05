package neww;

import android.view.View;
import android.view.ViewGroup;

/**
 * @作者 做就行了！
 * @时间 2019/5/5 0005
 * @使用说明：
 */
public class LayoutUT {

    public static void layout(View view, int leftTopX, int leftTopY) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        int offsetX = 0;
        int offsetY = 0;
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams lpp = (ViewGroup.MarginLayoutParams) lp;
            offsetX = lpp.leftMargin;
            offsetY = lpp.topMargin;
        }
        leftTopX += offsetX;
        leftTopY += offsetY;
        view.layout(leftTopX, leftTopY,
                leftTopX + view.getMeasuredWidth(),
                leftTopY + view.getMeasuredHeight());
    }
}
