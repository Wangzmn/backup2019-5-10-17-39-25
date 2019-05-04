package neww;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.View;


/**
 * @作者 做就行了！
 * @时间 2019-05-05上午 12:35
 * @该类描述： -
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public abstract class FragmentAdapter extends ViewPager.Adapter<View> {
    private static final boolean DEBUG = true;
    private final FragmentManager mManager;

    public FragmentAdapter(FragmentManager fragmentManager) {
        mManager = fragmentManager;
    }

    @Override
    public View getView(Context context, int position, int pageW, int pageH) {
        PagerFragment frag = getPagerFragment(position);
        View view;
        if (DEBUG) {
            boolean added = frag.isAdded();
            boolean detached = frag.isDetached();
            boolean hidden = frag.isHidden();
            boolean inLayout = frag.isInLayout();
            boolean visible = frag.isVisible();
            //认真一点。干！！！。操！！！
            Log.e("TAG", getClass() + "#getView:" +
                            "fragment" + position+"\n"
                    + "added："+ added + " 。"+"\n"
                    + "detached："+ detached + " 。"+"\n"
                    + "hidden："+ hidden + " 。"+"\n"
                    + "inLayout："+ inLayout + " 。"+"\n"
                    + "visible："+ visible + " 。"

            );
        }
        if (!frag.isAdded()) {
            //step fragmentTransactiom必须为局部变量！！！
            FragmentTransaction trans = mManager.beginTransaction();
            trans.add(frag, generateTag(frag, position));
            trans.commit();
            view = frag.generateView(context, getViewPager());
        } else {
            view = frag.getView();
        }
        return view;
    }

    protected abstract PagerFragment getPagerFragment(int position);


    protected String generateTag(Fragment frag, int position) {
        return "fragment:" + position + " 。";
    }

    @Override
    public void onDetachView(View view, int position) {
        getPagerFragment(position).setVisibility(false);
    }

}
