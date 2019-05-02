package wclass.android.util.debug;

import android.util.SparseArray;

/**
 * @作者 做就行了！
 * @时间 2019/5/2 0002
 * @使用说明：
 */
public class ToStrUT {
    public static String sparseKeyToStr(SparseArray sa) {
        StringBuilder sum = new StringBuilder("[数量为：" +sa.size()+" 。"+
                "键值为：");
        int size = sa.size();
        for (int i = 0; i < size; i++) {
            sum.append(sa.keyAt(i));
            if (i != size - 1) {
                sum.append(", ");
            }
        }
        sum.append(" ]");
        return sum.toString();
    }
}
