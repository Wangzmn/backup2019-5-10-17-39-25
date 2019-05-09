package simple_view;

import android.content.Context;
import android.widget.TextView;

import wclass.android.util.ViewUT;

/**
 * @作者 做就行了！
 * @时间 2019-05-09下午 10:59
 * @该类描述： -
 * @名词解释： -
 * @该类用途： -
 * @注意事项： -
 * @使用说明： -
 * @思维逻辑： -
 * @优化记录： -
 * @待解决： -
 */
public class TextViewSK extends TextView {
    public TextViewSK(Context context, String s, int pixFontSize, int fontColor) {
        super(context);
        setIncludeFontPadding(false);
        setText(s);
        ViewUT.adjustFontSize(this,pixFontSize);
        setTextColor(fontColor);
    }
    public TextViewSK(Context context, String s) {
        super(context);
        setIncludeFontPadding(false);
        setText(s);
    }
}
