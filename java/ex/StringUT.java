package ex;

import android.util.Log;
import android.view.View;

/**
 * @作者 做就行了！
 * @时间 2019-05-07上午 12:27
 * @该类描述： -
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public class StringUT {

    public static String from_xywh(View view){
        String s = "[" +
                " x = " + view.getX() +
                ", y = " + view.getY() +
                ", width = " + view.getWidth() +
                ", height = " + view.getHeight() +
                " ]";
        return s;
    }
}
