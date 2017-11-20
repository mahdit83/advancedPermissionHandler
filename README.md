# Advanced Permission Handler Activity
advancedPermissionHandlerActivity is an abstact Activty for handle all permission stuff. for using it add this line to gradle:
```groovy
compile 'ir.mtajik.android:advancedPermissionsHandler:1.0.0               
```
And inherit your desired Activity from advancedPermissionHandlerActivity and 
After that simply call sendSms that have a Interface for all callbacks. smsId is a random unique auto generated Id that generated for every single sms that created by your app.
In version 1.0.5 , i implement Builder design pattern. All the ```with``` parameters are optional. 
```java

String[] permissions = new String[]{new String[]{Manifest.permission.SEND_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE}
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


This library created with MVP architecture and Uses Dagger2 as DI container with these dependencies:

```groovy
compile 'com.google.dagger:dagger:2.7'
annotationProcessor 'com.google.dagger:dagger-compiler:2.7'
```           
           
So if you use dagger2 make sure that use compatible dependencies. I hope this library would be useful and wait for your comments.


![Mahdi Tajik](http://www.mahditajik.ir/wp-content/uploads/2015/03/sample-logo-MT22.png)

This is my weblog: http://www.mahditajik.ir


