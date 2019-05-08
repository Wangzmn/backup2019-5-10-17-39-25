package ex;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import wclass.android.ui.view.base_view.UsefulFrameLayout;
import wclass.android.util.ViewUT;

/**
 * @作者 做就行了！
 * @时间 2019-05-08下午 11:03
 * @该类描述： -
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public class UsefulFrame extends UsefulFrameLayout {
    LinearLayout subRoot;
    private Context context;
    FrameLayout TOP;
    FrameLayout MID;
    FrameLayout BOTTOM;
    Adapter adapter;
    private boolean init;

    /**
     * todo
     * 1、TOP、BOTTOM大小固定。
     * 2、MID我想用weight为1撑起来。
     */
    public UsefulFrame(Context context) {
        super(context);
        this.context = context;
    }


    boolean requestLayout;
    @Override
    protected void onSizeChangedSafely(int w, int h) {
        super.onSizeChangedSafely(w, h);
        requestLayout = false;
        adapter.onSizeChangedSafely(this,w,h);
        if (!init) {
            init = true;
            subRoot = new LinearLayout(context);
            subRoot.setOrientation(LinearLayout.VERTICAL);
            ViewUT.toMatchParent(subRoot);
            TOP = new FrameLayout(context);
            MID = new FrameLayout(context);
            BOTTOM = new FrameLayout(context);
            adapter.onInitView();
            adapter.onInitTOP(TOP);
            adapter.onInitMID(MID);
            adapter.onInitBOTTOM(BOTTOM);
        }
        int midHeight = h - (adapter.getTOPHeight() + adapter.getBOTTOMHeight());
        adapter.onAdjustRoot(subRoot,w,h);
        adapter.onAdjustTOP(TOP, w, adapter.getTOPHeight());
        adapter.onAdjustMID(MID, w, midHeight);
        adapter.onAdjustBOTTOM(BOTTOM, w, adapter.getBOTTOMHeight());
        //--------------------------------------------------
    }

    @SuppressWarnings("WeakerAccess")
    public static abstract class Adapter {

        public abstract void onSizeChangedSafely(UsefulFrame usefulFrame, int w, int h);

        public abstract void onInitView();

        public abstract void onInitTOP(FrameLayout top);

        public abstract void onInitMID(FrameLayout mid);

        public abstract void onInitBOTTOM(FrameLayout bottom);

        public abstract void onAdjustRoot(LinearLayout subRoot, int w, int h);

        public abstract void onAdjustTOP(FrameLayout top, int w, int topHeight);

        public abstract void onAdjustMID(FrameLayout mid, int w, int midHeight);

        public abstract void onAdjustBOTTOM(FrameLayout bottom, int w, int bottomHeight);

        public abstract int getTOPHeight();

        public abstract int getBOTTOMHeight();

    }
}
