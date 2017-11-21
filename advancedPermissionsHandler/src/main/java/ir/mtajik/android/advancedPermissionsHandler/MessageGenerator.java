package ir.mtajik.android.advancedPermissionsHandler;

import android.Manifest;
import android.content.Context;

class MessageGenerator {


    public static String generateMessageForThesePermissions(String[] permissions, Context context) {

        String result = "";
        for (String permission :
                permissions) {
            result = result + " ،" + generateMessageForThisPermission(permission, context);
        }

        return result;
    }

    public static String generateMessageForThisPermission(String permission, Context context) {

        String result = "";

        //android dangerous permissions

        /*
        ACCESS_LOCATION_EXTRA_COMMANDS
        ACCESS_NETWORK_STATE
        ACCESS_NOTIFICATION_POLICY
        ACCESS_WIFI_STATE
        BLUETOOTH
        BLUETOOTH_ADMIN
        BROADCAST_STICKY
        CHANGE_NETWORK_STATE
        CHANGE_WIFI_MULTICAST_STATE
        CHANGE_WIFI_STATE
        DISABLE_KEYGUARD
        EXPAND_STATUS_BAR
        GET_PACKAGE_SIZE
        INSTALL_SHORTCUT
        INTERNET
        KILL_BACKGROUND_PROCESSES
        MODIFY_AUDIO_SETTINGS
        NFC
        READ_SYNC_SETTINGS
        READ_SYNC_STATS
        RECEIVE_BOOT_COMPLETED
        REORDER_TASKS
        REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
        REQUEST_INSTALL_PACKAGES
        SET_ALARM
        SET_TIME_ZONE
        SET_WALLPAPER
        SET_WALLPAPER_HINTS
        TRANSMIT_IR
        UNINSTALL_SHORTCUT
        USE_FINGERPRINT
        VIBRATE
        WAKE_LOCK
        WRITE_SYNC_SETTINGS
         */

        if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            result = context.getString(R.string.READ_EXTERNAL_STORAGE);

        } else if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            result = context.getString(R.string.WRITE_EXTERNAL_STORAGE);

        } else if (permission.equals(Manifest.permission.SEND_SMS)) {
            result = context.getString(R.string.SEND_SMS);

        } else if (permission.equals(Manifest.permission.RECEIVE_SMS)) {
            result = context.getString(R.string.RECEIVE_SMS);

        } else if (permission.equals(Manifest.permission.READ_SMS)) {
            result = context.getString(R.string.READ_SMS);

        } else if (permission.equals(Manifest.permission.CAMERA)) {
            result = context.getString(R.string.CAMERA);

        } else if (permission.equals(Manifest.permission.READ_CONTACTS)) {
            result = context.getString(R.string.READ_CONTACTS);

        } else if (permission.equals(Manifest.permission.READ_CALENDAR)) {
            result = context.getString(R.string.READ_CALENDAR);

        } else if (permission.equals(Manifest.permission.WRITE_CALENDAR)) {
            result = context.getString(R.string.WRITE_CALENDAR);

        } else if (permission.equals(Manifest.permission.WRITE_CONTACTS)) {
            result = context.getString(R.string.WRITE_CONTACTS);

        } else if (permission.equals(Manifest.permission.GET_ACCOUNTS)) {
            result = context.getString(R.string.GET_ACCOUNTS);

            // get  NETWORK_PROVIDER and GPS_PROVIDER
        } else if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
            result = context.getString(R.string.ACCESS_FINE_LOCATION);

            // includes permission only for NETWORK_PROVIDER
        } else if (permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            result = context.getString(R.string.ACCESS_COARSE_LOCATION);

        } else if (permission.equals(Manifest.permission.RECORD_AUDIO)) {
            result = context.getString(R.string.RECORD_AUDIO);

        } else if (permission.equals(Manifest.permission.READ_PHONE_STATE)) {
            result = context.getString(R.string.READ_PHONE_STATE);

        } else if (permission.equals(Manifest.permission.CALL_PHONE)) {
            result = context.getString(R.string.CALL_PHONE);

        } else if (permission.equals(Manifest.permission.READ_CALL_LOG)) {
            result = context.getString(R.string.READ_CALL_LOG);

        } else if (permission.equals(Manifest.permission.WRITE_CALL_LOG)) {
            result = context.getString(R.string.WRITE_CALL_LOG);

        } else if (permission.equals(Manifest.permission.ADD_VOICEMAIL)) {
            result = context.getString(R.string.ADD_VOICEMAIL);

        } else if (permission.equals(Manifest.permission.USE_SIP)) {
            result = context.getString(R.string.USE_SIP);

        } else if (permission.equals(Manifest.permission.PROCESS_OUTGOING_CALLS)) {
            result = context.getString(R.string.PROCESS_OUTGOING_CALLS);

        } else if (permission.equals(Manifest.permission.BODY_SENSORS)) {
            result = context.getString(R.string.BODY_SENSORS);

        } else if (permission.equals(Manifest.permission.RECEIVE_WAP_PUSH)) {
            result = context.getString(R.string.RECEIVE_WAP_PUSH);

        } else if (permission.equals(Manifest.permission.RECEIVE_MMS)) {
            result = context.getString(R.string.RECEIVE_MMS);

        }

        return result;
    }

    static String makeAlertDialogMessage(String[] permissions, Context context) {

        String alertMsgInDialog_pre = context.getResources().getString(R.string.alert_pre);
        String alertMsgInDialog_post = context.getResources().getString(R.string.alert_post);

        return alertMsgInDialog_pre + " " + generateMessageForThesePermissions(permissions, context) + " " +
                alertMsgInDialog_post;
    }

    static String makeToastDialogMessage(String[] permissions, Context context) {

        String toastMsgInSettings_pre = context.getResources().getString(R.string.toast_pre);
        String toastMsgInSettings_post = context.getResources().getString(R.string.toast_post);

        return toastMsgInSettings_pre + " " + generateMessageForThesePermissions(permissions,
                context) + " " +
                toastMsgInSettings_post;
    }
}
