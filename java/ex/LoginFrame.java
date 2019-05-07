package ex;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import wclass.android.util.ViewUT;

/**
 * @作者 做就行了！
 * @时间 2019/5/8 0008
 * @使用说明：
 */
public class LoginFrame extends FrameLayout{
    LinearLayout asRoot;
    LinearLayout titleRoot;
    LinearLayout acountRoot;
    LinearLayout passwordRoot;

    /**
     * todo
     * 1、先layout asRoot。
     * 2、再添加asRoot的子view。
     *
     */
    public LoginFrame(Context context) {
        super(context);
        asRoot = new LinearLayout(context);
        ViewUT.toMatchParent(asRoot);
        //--------------------------------------------------

        //--------------------------------------------------
        //--------------------------------------------------
        //--------------------------------------------------



    }

    public static abstract class Adapter{
        /*分别在他们之前调用：添加标题、用户名、密码的root控件*/
        public abstract void preAddTitle(LinearLayout titleRoot,int w,int h);
        public abstract void preAddAccount(LinearLayout accountRoot,int w,int h);
        public abstract void preAddPassword(LinearLayout passwordRoot,int w,int h);
        //--------------------------------------------------
        /*正式添加标题、用户名、密码的root控件*/
        public abstract void onAddTitle(LinearLayout titleRoot,int w,int h);
        public abstract void onAddAccount(LinearLayout accountRoot,int w,int h);
        public abstract void onAddPassword(LinearLayout passwordRoot,int w,int h);
        //--------------------------------------------------
        public abstract int getTitleHeight(int w,int h);
        public abstract int getAccountHeight(int w,int h);
        public abstract int getPasswordHeight(int w,int h);

    }

}
