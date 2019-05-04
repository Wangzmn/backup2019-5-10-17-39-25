package neww;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;


/**
 * @作者 做就行了！
 * @时间 2019/5/3 0003
 * @使用说明：
 */
public class FragmentAdapter extends ViewPager.Adapter<View> {
    Fragment fragment;
    View view;
    SparseArray<PagerFragment> frags = new SparseArray<>();
    private  FragmentTransaction trans;
    private final FragmentManager mManager;
    private int mCount;

    public FragmentAdapter(FragmentManager fragmentManager) {
        mManager = fragmentManager;
    }

    @Override
    public View getView(Context context, int position, int pageW, int pageH) {
        PagerFragment frag = frags.get(position);
        View view;
        if(frag == null){
            frag = new PagerFragmentImpl();
            frags.put(position,frag);
             view = frag.preGenerate(context, getViewPager());

            preTransaction();
            trans.add(frag,generateTag(frag,position));
            trans.commit();
            //            frag
        }
        else{
            view = frag.getView();
        }
        return view;
    }

    private void preTransaction() {
        if(trans == null){
            trans = mManager.beginTransaction();
        }
    }

    protected String generateTag(Fragment frag,int position){
        return "fragment:"+position+" 。";
    }

    @Override
    public void onDetachView(View view, int position) {
        PagerFragment frag = frags.get(position);
        frag.setVisibility(false);
    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}
