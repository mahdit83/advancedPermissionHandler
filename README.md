# Advanced Permission Handler Activity
advancedPermissionHandlerActivity is an abstact Activty for handle all permission stuff, pice of cake. 

Just inherit your desired Activity from advancedPermissionHandlerActivity and simply call one of ''''askForPermission'''' methods witch one with autoGenerate message ability for your given permissions (works for Farsi right now) and one with your custom message.

```java

String[] permissions = new String[]{Manifest.permission.SEND_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE , ...}

askForPermission(permissions , new PermissionCallBack() {
                    
                    @Override
                    public void onPermissionsGranted() {  
                        Log.i("mahdi", "onPermissionsGranted: ");
                    }

                    @Override
                    public void onPermissionsDenied(String[] permissions) {
                        Log.i("mahdi", "onPermissionsDenied: ");
                    }
                });
```
This small and wise lib will handle if permissions. show to user before and witch ones are permitted and witch ones are denied. 
1. First ask for all permissions.
2. Then try to aks again for ungranted-permissions with custom message and then ask for permission again.
3. Then try to open settings for permissions if user set 'Do not ask me again'
4. Finaly if user deny, onPermissionsDenied callback will calls.

Sticky mode specifies that, if all these four steps happen in one session or not.


For using **advancedPermissionHandler** add this line to gradle:

```groovy
compile 'ir.mtajik.android:advancedPermissionsHandler:1.0.0               
```


![Mahdi Tajik](http://www.mahditajik.ir/wp-content/uploads/2015/03/sample-logo-MT22.png)

This is my weblog: http://www.mahditajik.ir


