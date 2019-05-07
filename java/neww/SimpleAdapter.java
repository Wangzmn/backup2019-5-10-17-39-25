package neww;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import wclass.android.util.DebugUT;
import wclass.android.util.ViewUT;
import wclass.common.WH;

/**
 * @作者 做就行了！
 * @时间 2019-05-07下午 5:08
 * @该类描述： -
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public  class SimpleAdapter extends TitleLayout.Adapter {
    private static final boolean DEBUG = true;
    private final Context context;
    private final int leftCount;
    private final int rightCount;

    private FrameLayout mid;
    private List<ImageView> lefts = new ArrayList<>();
    private List<ImageView> rights = new ArrayList<>();

    /**
     * 存放左右方view的宽高。
     */
    private WH wh = new WH();

    public SimpleAdapter(Context context, int leftCount, int rightCount) {
        this.context = context;
        this.leftCount = leftCount;
        this.rightCount = rightCount;
    }

    /**
     * 获取指定的左边的view。
     * @param position 该view的下标。
     * @return 指定的左边的view。
     */
    public ImageView getLeftView(int position) {
        return lefts.get(position);
    }

    /**
     * 获取指定的右边的view。
     * @param position 该view的下标。
     * @return 指定的右边的view。
     */
    public ImageView getRightView(int position) {
        return rights.get(position);
    }

    /**
     * 获取左边view的数量。
     */
    public int getLeftCount() {
        return leftCount;
    }

    /**
     * 获取右边view的数量。
     */
    public int getRightCount() {
        return rightCount;
    }

    //////////////////////////////////////////////////

    /**
     * 子类可以在该方法中设置view的context。
     *
     * @param context 上下文。
     */
    @Override
    public void onCreateViews(Context context) {
        for (int i = 0; i < leftCount; i++) {
            ImageView iv = new ImageView(context);
            lefts.add(iv);
            if (DEBUG) {
                DebugUT.randomBG(iv);
            }
        }

        for (int i = 0; i < rightCount; i++) {
            ImageView iv = new ImageView(context);
            rights.add(iv);
            if (DEBUG) {
                DebugUT.randomBG(iv);
            }
        }

        mid = new FrameLayout(context);
        ViewUT.toWrapContent(mid);

        if (DEBUG) {
            DebugUT.randomBG(mid);
        }
    }

    @Override
    public void onSizeChangeSafely(TitleLayout titleLayout, int w, int h) {
        wh.set(getSize(titleLayout, w, h));
        if (DEBUG) {
            ViewUT.adjustSize(mid, wh.h*2, wh.h);
        }
    }

    /**
     * 子类可以重写左右方view的大小。
     *
     * @param titleLayout titleLayout。
     * @param w           他的宽。
     * @param h           他的高。
     * @return 左右方view的大小
     */
    protected WH getSize(TitleLayout titleLayout, int w, int h) {
        int pt = titleLayout.getPaddingTop();
        int pb = titleLayout.getPaddingBottom();
        int size = h - pt - pb;
        return new WH(size, size);
    }

    //////////////////////////////////////////////////
    @Override
    public View onGetMidMenu(Context context) {
        return mid;
    }

    @Override
    public View onGetLeftMenu(Context context, int position) {
        return lefts.get(position);
    }

    @Override
    public View onGetRightMenu(Context context, int position) {
        return rights.get(position);
    }

    @Override
    public boolean leftsRightsSameSize() {
        return true;
    }

    @Override
    public int getItemGap() {
        return wh.h / 8;
    }

    @Override
    public WH getSameSize() {
        return wh;
    }

    @Override
    public int getLeftMenuCount() {
        return lefts.size();
    }

    @Override
    public int getRightMenuCount() {
        return rights.size();
    }
}
