package com.sandy.bluetooth.utils

class BluetoothDisabledException(message: String = "Disabled") : Exception(message)
class BluetoothPermissionDeniedException(message: String = "PermissionDenied") : Exception(message)
class WearableFailException(message: String = "WearableFail") : Exception(message)
