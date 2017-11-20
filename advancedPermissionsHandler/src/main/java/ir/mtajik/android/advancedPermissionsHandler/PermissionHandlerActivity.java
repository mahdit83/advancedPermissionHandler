package ir.mtajik.android.advancedPermissionsHandler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is abstract so can not have instance
 */
public abstract class PermissionHandlerActivity extends AppCompatActivity implements ActivityCompat
        .OnRequestPermissionsResultCallback {
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;

    SharedPreferences permissionStatus;
    private PermissionCallBack permissionCallBack;
    private String[] permissionsArray;
    private List<String> remainedPermissionsList = new ArrayList<>();
    private Context context;
    private AppCompatActivity mActivity = new AppCompatActivity();
    private boolean mSticky = false;
    private String customMessage;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SETTING) {

            if (permissionCallBack != null) {
                if (!checkIfNotGrantedOneOfPermissions()) {
                    //Got Permission
                    permissionCallBack.onPermissionsGranted();
                } else {
                    permissionCallBack.onPermissionsDenied(convertListToArray
                            (remainedPermissionsList));
                }
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CALLBACK_CONSTANT) {

            updatePermissionRequestStatus(permissions, grantResults);

            if (permissionCallBack != null) {

                if (remainedPermissionsList.size() == 0) {
                    permissionCallBack.onPermissionsGranted();
                } else {

                    if (this.mSticky) {
                        checkPermissionStuff();
                    } else {
                        permissionCallBack.onPermissionsDenied(convertListToArray
                                (remainedPermissionsList));
                    }
                }
            }
        }
    }

    /**
     * use this method if you want to pass
     * your own message.
     *
     * @param permissions
     * @param message
     * @param permissionGranted
     */
    public void askForPermission(String[] permissions, String message, boolean sticky,
                                 PermissionCallBack
            permissionGranted) {

        initialize(permissions, message, sticky, permissionGranted);
        checkPermissionStuff();
    }

    /**
     * Use this method if you want to auto generate
     * message for permissions.
     * This works just for Farsi at right now.
     *
     * @param permissions
     * @param permissionGranted
     */
    public void askForPermission(String[] permissions, boolean sticky, PermissionCallBack
            permissionGranted) {


        initialize(permissions, null, sticky,
                permissionGranted);
        checkPermissionStuff();


    }

    public void openSettingsForPermission() {

        Toast.makeText(this, MessageGenerator.makeToastDialogMessage(convertListToArray
                (remainedPermissionsList), this), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        this.startActivityForResult(intent, REQUEST_PERMISSION_SETTING);

    }

    public String[] convertListToArray(List<String> list) {
        String[] converted = new String[remainedPermissionsList.size()];
        return list.toArray(converted);

    }

    private void updatePermissionRequestStatus(String[] permissions, int[] grantResults) {

        remainedPermissionsList.clear();

        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                remainedPermissionsList.add(permissions[i]);
            } else {
                //remove permission from blacklist
                permissionStatus.edit().putBoolean(permissions[i], false).apply();
            }
        }
    }

    private void checkPermissionStuff() {


        if (Build.VERSION.SDK_INT < 23 || checkIfNotGrantedOneOfPermissions()) {

            if (shouldShowRequestPermissionRationaleForAll()) {
                //true means user not allowed the permission but may we can convince him/her
                //false have two meaning: 1-user not asked for permission 2-user denied and check
                // 'Don't Ask Again'
                //so we had to Show Information about why you need the permission
                showPermissionDialog(convertListToArray(remainedPermissionsList));

            } else if (checkIfUserDeniedOneOfOurPermissionsBefore()) {
                //Previously One Permission Request was cancelled with 'Don't Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                openSettingsForPermission();
            } else {
                //just request the permission
                Toast.makeText(this, MessageGenerator.makeToastDialogMessage(permissionsArray,
                        this),
                        Toast.LENGTH_SHORT).show();
                askForPermission(permissionsArray);
            }

            //update this permission so we asked once and if another time user asked Rationale if
            // it will be false it means that user Denied for ever
            updateAllPermissionToAskedOnce();

        } else {
            if (permissionCallBack != null) permissionCallBack.onPermissionsGranted();
        }
    }

    private void updateAllPermissionToAskedOnce() {

        for (String permission :
                permissionsArray) {
            permissionStatus.edit().putBoolean(permission, true).apply();
        }

    }

    private boolean checkIfUserDeniedOneOfOurPermissionsBefore() {

        boolean ifJustOneIsDeniedForEver = false;
        remainedPermissionsList.clear();

        for (String permission :
                permissionsArray) {
            if (permissionStatus.getBoolean(permission, false)) {
                ifJustOneIsDeniedForEver = true;
                remainedPermissionsList.add(permission);
            }
        }

        return ifJustOneIsDeniedForEver;
    }

    private boolean shouldShowRequestPermissionRationaleForAll() {

        boolean oneNotGranted = false;
        remainedPermissionsList.clear();

        for (String permission :
                permissionsArray) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
                oneNotGranted = true;
                remainedPermissionsList.add(permission);
            }
        }

        return oneNotGranted;
    }

    private boolean checkIfNotGrantedOneOfPermissions() {

        remainedPermissionsList.clear();
        boolean oneNotGranted = false;
        for (String permission : permissionsArray) {
            if (ActivityCompat.checkSelfPermission(this, permission) ==
                    PackageManager.PERMISSION_GRANTED) {

            } else {
                oneNotGranted = true;
                remainedPermissionsList.add(permission);
            }
        }

        return oneNotGranted;
    }

    private void initialize(String[] permissions, @Nullable String message, boolean sticky,
                            PermissionCallBack
            mPermissionCallBack) {
        this.mActivity = this;
        this.permissionCallBack = mPermissionCallBack;
        this.context = this;
        this.permissionsArray = permissions;
        this.mSticky = sticky;
        this.customMessage = customMessage;

        permissionStatus = this.getSharedPreferences("permissionStatus",
                this.MODE_PRIVATE);
    }

    private void showPermissionDialog(final String[] permissions) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(MessageGenerator.makeAlertDialogMessage(permissions, this)
        ).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                askForPermission(permissions);
            }


        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mSticky) {
                    openSettingsForPermission();
                } else {
                    if (permissionCallBack != null) {
                        permissionCallBack.onPermissionsDenied(permissions);
                    }
                }
            }
        }).setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void askForPermission(String[] permissions) {
        ActivityCompat.requestPermissions(mActivity, permissions, PERMISSION_CALLBACK_CONSTANT);
    }

    public interface PermissionCallBack {
        void onPermissionsGranted();

        void onPermissionsDenied(String[] deniedPermissions);
    }
}
