package com.core.data.mapper

import com.core.model.Device
import com.google.android.gms.wearable.Node
import com.sandy.datastore.model.DeviceDTO

internal fun DeviceDTO.toDevice(): Device {
    return Device(
        name = name,
        alias = alias.ifBlank { name },
        id = id,
    )
}

internal fun Node.toDevice(alias: String? = null): Device {
    return Device(
        name = displayName,
        alias = alias ?: displayName,
        id = id,
        isNearby = isNearby,
    )
}
