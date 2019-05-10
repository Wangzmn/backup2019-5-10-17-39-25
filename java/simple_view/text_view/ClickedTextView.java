package simple_view.text_view;

import android.content.Context;
import android.widget.TextView;

import ex.StateUT;
import wclass.android.util.ViewUT;

/**
 * @作者 做就行了！
 * @时间 2019-05-10下午 3:40
 * @该类描述： -
 * 1、可点击的textView，他有常态时的字体颜色、按下时的字体颜色。
 * 2、他的大小为：包裹内容。
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public class ClickedTextView extends TextView{
    public ClickedTextView(Context context, String content, int pixFontSize, int pressColor, int normColor) {
        super(context);
        setIncludeFontPadding(false);
        setClickable(true);
        setTextColor(StateUT.make_Press_Norm(pressColor,normColor));
        setText(content);
        ViewUT.toWrapContent(this,pixFontSize);
    }
}
