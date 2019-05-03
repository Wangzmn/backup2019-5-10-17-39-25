package view_pager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @作者 做就行了！
 * @时间 2019/5/3 0003
 * @使用说明：
 */
public class FragmentAdapter extends ViewPager.Adapter<FrameLayout> {
    Fragment fragment;
    View view;

    public FragmentAdapter() {
        view.setId(0);
        view.getId()
        FragmentManager m;
        FragmentTransaction trans = m.beginTransaction();
        trans.replace()

    }

    @Override
    public FrameLayout getView(Context context, int position, int pageW, int pageH) {
        return null;
    }

    @Override
    public void onDetachView(FrameLayout view, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
