package neww;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @作者 做就行了！
 * @时间 2019-05-04下午 4:18
 * @该类描述： -
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public abstract class PagerFragment<T extends View> extends Fragment {
    private static final boolean DEBUG = true;
//    ViewPager viewPager;
    //////////////////////////////////////////////////
    T mView;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (DEBUG) {
            Log.e("TAG", getClass() + "#onCreateView:  ");
        }
        if (mView != null) {
            onAdjustViewState(container, mView, savedInstanceState);
            return mView;
        } else {
            return mView = onCreateViewOptimize(inflater.getContext(), container, savedInstanceState);
        }
    }

    /**
     * 该方法由外界创建后调用一次！！！
     *
     * @param context 上下文。
     * @param parent  fragment持有的view，的容器。
     * @return
     */
    public T preGenerate(Context context, ViewGroup parent) {
        return mView = onCreateViewOptimize(context, parent, null);
    }

    protected abstract T onCreateViewOptimize(Context context, ViewGroup container, Bundle savedInstanceState);

    protected abstract void onAdjustViewState(ViewGroup container, T view, Bundle savedInstanceState);

    /**
     * 将
     * @param visible
     */
    public void setVisibility(boolean visible){
        setMenuVisibility(visible);
        setUserVisibleHint(visible);
    }
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
            if (menuVisible) {
                try {
                    if (mView.getVisibility() != View.VISIBLE) {
                        mView.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IllegalStateException("请在与用户交互以后，再调用该方法。");
                }
                onViewVisible();
            } else {
                try {
                    if (mView.getVisibility() != View.INVISIBLE) {
                        mView.setVisibility(View.INVISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IllegalStateException("请在与用户交互以后，再调用该方法。");
                }
                onViewInvisible();
            }
    }

    @Nullable
    @Override
    public View getView() {
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            mView.setEnabled(isVisibleToUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("请在与用户交互以后，再调用该方法。");
        }
        if (isVisibleToUser) {
            onUserVisible();
        } else {
            onUserInvisible();
        }
    }

    protected void onViewVisible() {
    }

    protected void onViewInvisible() {
    }

    protected void onUserVisible() {
    }

    protected void onUserInvisible() {
    }

    @Override
    public void onResume() {
        if (DEBUG) {
            Log.e("TAG", getClass() + "#onResume:  ");
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if (DEBUG) {
            Log.e("TAG", getClass() + "#onPause:  ");
        }
        super.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (DEBUG) {
            Log.e("TAG", getClass() + "#onCreate:  ");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (DEBUG) {
            Log.e("TAG", getClass() + "#onViewCreated:  ");
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        mView = null;
        if (DEBUG) {
            Log.e("TAG", getClass() + "#onDestroy:  ");
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (DEBUG) {
            Log.e("TAG", getClass() + "#onConfigurationChanged:  ");
        }
        super.onConfigurationChanged(newConfig);
    }
}
