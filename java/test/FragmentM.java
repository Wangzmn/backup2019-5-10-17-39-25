package test;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @作者 做就行了！
 * @时间 2019/5/3 0003
 * @使用说明：
 */
public abstract class FragmentM extends Fragment {
    private static final boolean DEBUG = true;
    ViewPager viewPager;
    //////////////////////////////////////////////////
    View mView;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (DEBUG) {
            Log.e("TAG",getClass()+"#onCreateView:  ");
        }
        if(mView!=null){
            return mView;
        }else{
            return mView =onCreateViewOptimize(inflater,container,savedInstanceState);
        }
    }
    protected abstract View onCreateViewOptimize(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) ;

    @Override
    public void onResume() {
        if (DEBUG) {
            Log.e("TAG",getClass()+"#onResume:  ");
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if (DEBUG) {
            Log.e("TAG",getClass()+"#onPause:  ");
        }
        super.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (DEBUG) {
            Log.e("TAG",getClass()+"#onCreate:  ");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (DEBUG) {
            Log.e("TAG",getClass()+"#onViewCreated:  ");
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (DEBUG) {
            Log.e("TAG",getClass()+"#onDestroy:  ");
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (DEBUG) {
            Log.e("TAG",getClass()+"#onConfigurationChanged:  ");
        }
        super.onConfigurationChanged(newConfig);
    }
}
