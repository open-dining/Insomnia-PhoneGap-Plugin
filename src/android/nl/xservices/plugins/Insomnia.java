package nl.xservices.plugins;

import android.view.WindowManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class Insomnia extends CordovaPlugin {

  private static final String ACTION_KEEP_AWAKE = "keepAwake";
  private static final String ACTION_ALLOW_SLEEP_AGAIN = "allowSleepAgain";

  private static final String ACTION_BRING_TO_FRONT = "bringToFront";
  private static final String ACTION_CLEAR_BRING_TO_FRONT = "clearBringToFront";

  private static final String ACTION_ADD_WINDOW_FLAGS = "addWindowFlags";
  private static final String ACTION_CLEAR_WINDOW_FLAGS = "clearWindowFlags";
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

		} else if (ACTION_CLEAR_BRING_TO_FRONT.equals(action)) {
			cordova.getActivity().runOnUiThread(
				new Runnable() {
				  public void run() {
					cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
												WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
												WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
												WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
					callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
				  }
				});
			return true;

		} else if (ACTION_ADD_WINDOW_FLAGS.equals(action)) {
			cordova.getActivity().runOnUiThread(
				new Runnable() {
				  public void run() {
					cordova.getActivity().getWindow().addFlags(args.getInt(0));
					callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
				  }
				});
			return true;

		} else if (ACTION_CLEAR_WINDOW_FLAGS.equals(action)) {
			cordova.getActivity().runOnUiThread(
				new Runnable() {
				  public void run() {
					cordova.getActivity().getWindow().clearFlags(args.getInt(0));
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