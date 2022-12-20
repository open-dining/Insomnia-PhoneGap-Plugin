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

Insomnia.prototype.test1 = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Insomnia", "test1", []);
};

Insomnia.prototype.test2 = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "Insomnia", "test2", []);
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