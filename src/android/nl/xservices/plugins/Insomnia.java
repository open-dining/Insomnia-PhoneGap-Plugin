package nl.xservices.plugins;

import android.view.WindowManager;
import android.content.pm.PackageManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.KeyguardManager;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.icu.util.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Insomnia extends CordovaPlugin {

  private static final String ACTION_KEEP_AWAKE = "keepAwake";
  private static final String ACTION_ALLOW_SLEEP_AGAIN = "allowSleepAgain";

  private static final String ACTION_BRING_TO_FRONT = "bringToFront";
  private static final String ACTION_GET_WINDOW_FLAGS = "getWindowFlags";

  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    try {
      if (ACTION_KEEP_AWAKE.equals(action)) {
        cordova.getActivity().runOnUiThread(
            new Runnable() {
              public void run() {
                cordova.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
              }
            });
        return true;

      } else if (ACTION_ALLOW_SLEEP_AGAIN.equals(action)) {
        cordova.getActivity().runOnUiThread(
            new Runnable() {
              public void run() {
                cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
              }
            });
        return true;

		} else if (ACTION_BRING_TO_FRONT.equals(action)) {
			cordova.getActivity().runOnUiThread(
				new Runnable() {
				  public void run() {
					cordova.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
						WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
						WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
						WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

					callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
				  }
				});
			return true;

		} else if (action.equals("test1")) {
			// This didn't work at all
			Activity activity = cordova.getActivity();
			Context context = activity.getApplicationContext();

			KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

			activity.setShowWhenLocked(true);
			activity.setTurnScreenOn(true);

			keyguardManager.requestDismissKeyguard(activity, null);

			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
			return true;

		} else if (action.equals("test2")) {
			// This awakened the device and turned the screen back on!
			// But it only showed a simple notification, not a fullscreen or anything
			cordova.getActivity().runOnUiThread(
				new Runnable() {
				  public void run() {
					Activity activity = cordova.getActivity();
					Context context = activity.getApplicationContext();

					PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
					WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "ODN");
					wakeLock.acquire(2000);

					activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
							WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
							WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
							WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON |
							WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

					KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

					activity.setShowWhenLocked(true);
					activity.setTurnScreenOn(true);

					keyguardManager.requestDismissKeyguard(activity, null);

					callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
				  }
				});
			return true;
		} else if (action.equals("test3")) {
			// This awakened the device and turned the screen back on!
			// But it only showed a simple notification, not a fullscreen or anything
			cordova.getActivity().runOnUiThread(
				new Runnable() {
				  public void run() {
					Activity activity = cordova.getActivity();
					Context context = activity.getApplicationContext();

					PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
					WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "ODN");
					wakeLock.acquire(2000);

					activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
							WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
							WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
							WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON |
							WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

					KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

					activity.setShowWhenLocked(true);
					activity.setTurnScreenOn(true);

					keyguardManager.requestDismissKeyguard(activity, null);

					// Same as test2, but try bringing the activity back
					PackageManager packageManager = context.getPackageManager();
					String packageName = activity.getPackageName();
					Intent intent = packageManager.getLaunchIntentForPackage(packageName);

					intent.addFlags(
						Intent.FLAG_ACTIVITY_NEW_TASK |
						Intent.FLAG_ACTIVITY_SINGLE_TOP |
						Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
					);

					// Start activity
					context.startActivity(intent);

					callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
				  }
				});
			return true;
		} else if (action.equals("test4")) {
				// This awakened the device and turned the screen back on!
				// But it only showed a simple notification, not a fullscreen or anything
				cordova.getActivity().runOnUiThread(
					new Runnable() {
					  public void run() {
						Activity activity = cordova.getActivity();
						Context context = activity.getApplicationContext();

						PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
						WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "ODN");
						wakeLock.acquire(2000);

						activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
								WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
								WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
								WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON |
								WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

						KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

						activity.setShowWhenLocked(true);
						activity.setTurnScreenOn(true);

						keyguardManager.requestDismissKeyguard(activity, null);

						// Same as test2, but try bringing the activity back
						PackageManager packageManager = context.getPackageManager();
						String packageName = activity.getPackageName();
						Intent intent = packageManager.getLaunchIntentForPackage(packageName);

						intent.addFlags(
							Intent.FLAG_ACTIVITY_NEW_TASK |
							Intent.FLAG_ACTIVITY_SINGLE_TOP |
							Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
						);

						// Start activity
						activity.startActivity(intent);

						callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
					  }
					});
				return true;
		} else if (ACTION_GET_WINDOW_FLAGS.equals(action)) {
			cordova.getActivity().runOnUiThread(
				new Runnable() {
				  public void run() {
					int flags = cordova.getActivity().getWindow().getAttributes().flags;
					callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, flags));
				  }
				});
			return true;

      } else {
        callbackContext.error("insomnia." + action + " is not a supported function. Did you mean '" + ACTION_KEEP_AWAKE + "'?");
        return false;
      }
    } catch (Exception e) {
      callbackContext.error(e.getMessage());
      return false;
    }
  }
}