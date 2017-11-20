package ir.mtajik.android.advancedPermissionsHandler;

import android.Manifest;
import android.content.Context;

class MessageGenerator {



    public static String generateMessageForThesePermissions(String[] permissions ,Context context) {

        String result = "";
        for (String permission :
                permissions) {
            result = result + " ،" + generateMessageForThisPermission(permission,context);
        }

        return result;
    }

    public static String generateMessageForThisPermission(String permission , Context context) {

        String result = "";
        if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            result = context.getString(R.string.READ_EXTERNAL_STORAGE);
        } else if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            result = context.getString(R.string.WRITE_EXTERNAL_STORAGE);

        } else if (permission.equals(Manifest.permission.SEND_SMS)) {
            result = context.getString(R.string.SEND_SMS);

        } else if (permission.equals(Manifest.permission.RECEIVE_SMS)) {
            result = context.getString(R.string.RECEIVE_SMS);

        }

        return result;
    }

    static String makeAlertDialogMessage(String[] permissions, Context context) {

        String alertMsgInDialog_pre = context.getResources().getString(R.string.alert_pre);
         String alertMsgInDialog_post = context.getResources().getString(R.string.alert_post);

        return alertMsgInDialog_pre + generateMessageForThesePermissions(permissions ,context) +
                alertMsgInDialog_post;
    }

    static String makeToastDialogMessage(String[] permissions, Context context) {

        String toastMsgInSettings_pre = context.getResources().getString(R.string.toast_pre) ;
        String toastMsgInSettings_post = context.getResources().getString(R.string.toast_post);

        return toastMsgInSettings_pre + generateMessageForThesePermissions(permissions ,context) +
                toastMsgInSettings_post;
    }
}
