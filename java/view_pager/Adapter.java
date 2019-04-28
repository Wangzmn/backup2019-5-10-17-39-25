package view_pager;

import wclass.enums.Orien2;

/**
 * @作者 做就行了！
 * @时间 2019-04-28下午 2:24
 * @该类描述： -
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public abstract class Adapter {

    /**
     * step 来个异步加载怎么样？
     * 1、异步 new。
     * 2、同步setContent。
     * step “页”
     * 1、每页在哪个固定的scroll值？
     */
    /**
     *
     * 加载指定下标的view。
     *
     * @param position 加载该下标的view。
     */
    public abstract void load(int position);

    /**
     * 是否回收指定下标的view。
     *
     * @param position 回收该下标的view。
     * @return true：回收该view。false：不回收该view。
     */
    public abstract boolean recycle(int position);

    public int appearAtScroll(int position) {

    }

    public void isStableXY(int position){

    }

    public Orien2 getOrien() {
        return Orien2.HORIZONTAL;
    }
}
