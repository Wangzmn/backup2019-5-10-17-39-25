package wclass.android.z_debug;

import android.util.Log;
import android.view.View;

import ex.StringUT;

/**
 * @作者 做就行了！
 * @时间 2019-04-19下午 11:49
 * @该类描述： -
 * 1、打印日志的简单封装。
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public class LogUT {
    public static void log_xywh(View view, String prefix) {
        Log.e("TAG", prefix + StringUT.from_xywh(view));
    }
}
