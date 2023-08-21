/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 8:56 PM
 *
 */

package com.bassamapps.weatherapp.core.network.model.util


import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.math.roundToInt


object BooleanSerializer: KSerializer<Boolean> {

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Boolean", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): Boolean =
        decoder.decodeInt() != 0

    override fun serialize(encoder: Encoder, value: Boolean) {
        if (value) {
            encoder.encodeInt(1)
        } else {
            encoder.encodeInt(0)
        }
    }


}