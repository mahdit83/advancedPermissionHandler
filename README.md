# Advanced Permission Handler
This library have an abstact Activty, **advancedPermissionHandlerActivity** is that handle all permission stuff, pice of cake. 

Just inherit your desired Activity from **advancedPermissionHandlerActivity** and simply call one of ````askForPermission()```` methods which one with auto-generate message ability for your given permissions (works for Farsi right now) and one with your custom message.

```java

String[] permissions = new String[] {Manifest.permission.SEND_SMS, 
Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE , ...}

boolean stickyMode = true;

askForPermission(permissions , stickyMode, new PermissionCallBack() {
                    
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
**These are steps:**

1. First ask for all permissions.
2. Then try to aks for ungranted-permissions with custom message again from user.
3. Then try to open settings for permissions if user set 'Don't ask again'
4. Finaly if user deny, onPermissionsDenied callback will triggers.

Sticky mode specifies that, if all these four steps happen in one session or not.


For using **advancedPermissionHandler** add this line to gradle:

```groovy
compile 'ir.mtajik.android:advancedPermissionsHandler:1.0.0               
```

![Mahdi Tajik](http://www.mahditajik.ir/wp-content/uploads/2015/03/sample-logo-MT22.png)

my weblog: http://www.mahditajik.ir


