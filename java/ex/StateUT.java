package ex;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

/**
 * @作者 做就行了！
 * @时间 2019-05-10下午 4:19
 * @该类描述： -
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
@SuppressWarnings("WeakerAccess")
public class StateUT {

    /**
     * 生成按下时和常态时对应的颜色。
     *
     * @param pressColor 按下时的颜色。
     * @param normColor  常态时的颜色。
     * @return 按下时和常态时对应的颜色。
     */
    public static ColorStateList make_Press_Norm(int pressColor, int normColor) {
        int[] color2 = new int[]{pressColor, normColor};
        if (color2.length != 2) throw new IllegalArgumentException("请认真一点，" +
                "只有2种颜色，为什么数组长度不为2。");
        int[][] state = new int[2][];
        state[0] = getPressedArray();
        state[1] = getEmptyArray();
        return new ColorStateList(state, color2);
    }

    /**
     * 生成按下时和选中时和常态时对应的颜色。
     *
     * @param pressColor    按下时的颜色。
     * @param selectedColor 选中时的颜色。
     * @param normColor     常态时的颜色。
     * @return 按下时和常态时对应的颜色。
     */
    public static ColorStateList make_Press_Selected_Norm(
            int pressColor, int selectedColor, int normColor) {
        int[] color3 = new int[]{pressColor, selectedColor, normColor};
        if (color3.length != 3) throw new IllegalArgumentException("请认真一点，" +
                "只有3种颜色，为什么数组长度不为3。");
        int[][] state = new int[3][];
        state[0] = getPressedArray();
        state[1] = getSelectedArray();
        state[2] = getEmptyArray();
        return new ColorStateList(state, color3);
    }

    /**
     * 生成按下时和常态时对应的图片。
     *
     * @param press 按下时的图片。
     * @param norm  常态时的图片。
     * @return 按下时和常态时对应的图片。
     */
    public static StateListDrawable make_Press_Norm(Drawable press, Drawable norm) {
        StateListDrawable sd = new StateListDrawable();
        sd.addState(getPressedArray(), press);
        sd.addState(getEmptyArray(), norm);
        return sd;
    }

    /**
     * 生成按下时和选中时和常态时对应的图片。
     *
     * @param press    按下时的图片。
     * @param selected 选中时的图片。
     * @param norm     常态时的图片。
     * @return 按下时和常态时对应的图片。
     */
    public static StateListDrawable make_Press_Selected_Norm(
            Drawable selected, Drawable press, Drawable norm) {
        StateListDrawable sd = new StateListDrawable();
        sd.addState(getSelectedArray(), selected);
        sd.addState(getPressedArray(), press);
        sd.addState(getEmptyArray(), norm);
        return sd;
    }
    //////////////////////////////////////////////////


    /**
     * 获取选中状态的数组。
     */
    public static int[] getSelectedArray() {
        return new int[]{SELECTED};
    }

    /**
     * 获取按下状态的数组。
     */
    public static int[] getPressedArray() {
        return new int[]{PRESSED};
    }

    /**
     * 获取空状态的数组，该状态通常用于normal状态。
     */
    public static int[] getEmptyArray() {
        return new int[]{};
    }

    /**
     * 获取空状态的数组集，用于存放各种状态。
     */
    public static int[][] getEmptyStates(int size) {
        return new int[size][];
    }

    //////////////////////////////////////////////////
    public static final int PRESSED = android.R.attr.state_selected;
    public static final int SELECTED = android.R.attr.state_pressed;
    public static final int ENABLED = android.R.attr.state_enabled;
    public static final int FOCUSED = android.R.attr.state_focused;
//    public static final int TEST = TTT.SELECTED;
}
