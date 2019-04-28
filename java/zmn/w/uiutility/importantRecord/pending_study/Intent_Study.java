package zmn.w.uiutility.importantRecord.pending_study;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.util.Log;

import zmn.w.uiutility.R;
import zmn.w.uiutility.activity.MainActivity;


/**
 * @作者 做就行了！
 * @时间 2019/1/30 0030
 * @使用说明：
 */
public class Intent_Study {

    public static void installShortCut(Activity activity ) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        Intent sendIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        sendIntent.putExtra("duplicate", false); // 不允许重复创建
        // 快捷方式的图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.
                fromContext(activity, R.drawable.ic_launcher_foreground);
        // 快捷方式的名称
        sendIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "我的快捷方式");
        sendIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        sendIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);

        activity.sendBroadcast(sendIntent);
    }
    public static void intent(Activity activity) {
//        installShortCut(context);


        Intent shortcutIntent = new Intent();
        shortcutIntent.setAction("android.intent.action.MAIN");
        shortcutIntent.addCategory("android.intent.category.LAUNCHER");
//        shortcutIntent.setClassName(activity, MainActivity.class);
        Log.e("TAG"," MainActivity.class.getName() "+"  "+"  "+MainActivity.class.getName());
//        shortcutIntent.setComponent(activity.getComponentName());
        Intent sendIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        sendIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "我的快捷方式");
        // 快捷方式的图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.
                fromContext(activity, R.drawable.ic_launcher_foreground);
        sendIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        sendIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
//        sendIntent.putExtra("duplicate", false); // 不允许重复创建
        activity.sendBroadcast(sendIntent);
        Log.e("TAG","  "+"  "+"  "+" 发送intent了，不知道创建了没有。 ");
//        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON,
//                BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_dialog_email));
    }

    /**
     * 添加当前应用的桌面快捷方式
     *
     * @param context
     */
    public static void addShortcut(Context context, int appIcon) {
        Intent shortcut = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");

        Intent shortcutIntent = context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // 获取当前应用名称
        String title = "这是快捷方式";
        // 快捷方式名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        // 不允许重复创建（不一定有效）
        shortcut.putExtra("duplicate", false);
        // 快捷方式的图标
        Parcelable iconResource = Intent.ShortcutIconResource.fromContext(context,
                appIcon);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

        context.sendBroadcast(shortcut);
    }

}
