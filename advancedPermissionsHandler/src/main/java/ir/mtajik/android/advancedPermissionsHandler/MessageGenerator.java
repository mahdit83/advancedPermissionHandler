package ir.mtajik.android.advancedPermissionsHandler;

import android.Manifest;

class MessageGenerator {

    private static String alertMsgInDialog_pre = "برای استفاده از این قابلیت، اجازه دسترسی به ";
    private static String alertMsgInDialog_post = " ضروری است";
    private static String toastMsgInSettings_pre = "برای استفاده از این قابلیت باید ابتدا اجازه " +
            "دسترسی به ";
    private static String toastMsgInSettings_post = " در تنظیمات داده شود";


    public static String generateMessageForThesePermissions(String[] permissions) {

        String result = "";
        for (String permission :
                permissions) {
            result = result + " ،" + generateMessageForThisPermission(permission);
        }

        return result;
    }

    public static String generateMessageForThisPermission(String permission) {

        String result = "";
        if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            result = "خواندن کارت حافظه";
        } else if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            result = "نوشتن کارت حافظه";

        } else if (permission.equals(Manifest.permission.SEND_SMS)) {
            result = "ارسال پیام کوتاه";

        } else if (permission.equals(Manifest.permission.RECEIVE_SMS)) {
            result = "ردیافت پیام کوتاه";

        }

        return result;
    }

    static String makeAlertDialogMessage(String[] permissions) {

        return alertMsgInDialog_pre + generateMessageForThesePermissions(permissions) +
                alertMsgInDialog_post;
    }

    static String makeToastDialogMessage(String[] permissions) {

        return toastMsgInSettings_pre + generateMessageForThesePermissions(permissions) +
                toastMsgInSettings_post;
    }
}
