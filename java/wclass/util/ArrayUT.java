package wclass.util;

import java.util.Iterator;
import java.util.Set;

/**
 * @作者 做就行了！
 * @时间 2018-11-24下午 5:18
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 */
public class ArrayUT {
    /**
     * 将int类型set集合转为int类型array数组。
     * @param intSet int类型set集合。
     * @return 将int类型set集合转为int类型array数组。
     */
    @SuppressWarnings({"WhileLoopReplaceableByForEach", "WeakerAccess"})
    public static int[] toArray(Set<Integer> intSet) {
        int[] orderArray = new int[intSet.size()];
        int arrayDex = 0;
        Iterator<Integer> iterator = intSet.iterator();
        while (iterator.hasNext()) {
            orderArray[arrayDex++] = iterator.next();
        }
        return orderArray;
    }
}
