function Insomnia() {
}

Insomnia.prototype.keepAwake = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Insomnia", "keepAwake", []);
};

Insomnia.prototype.allowSleepAgain = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Insomnia", "allowSleepAgain", []);
};

Insomnia.prototype.bringToFront = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Insomnia", "bringToFront", []);
};

Insomnia.prototype.clearBringToFront = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Insomnia", "clearBringToFront", []);
};

Insomnia.prototype.addWindowFlags = function (flags, successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Insomnia", "addWindowFlags", [flags]);
};

Insomnia.prototype.clearWindowFlags = function (flags, successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Insomnia", "clearWindowFlags", [flags]);
};

Insomnia.prototype.getWindowFlags = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Insomnia", "getWindowFlags", []);
};

Insomnia.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.insomnia = new Insomnia();
  return window.plugins.insomnia;
};

cordova.addConstructor(Insomnia.install);