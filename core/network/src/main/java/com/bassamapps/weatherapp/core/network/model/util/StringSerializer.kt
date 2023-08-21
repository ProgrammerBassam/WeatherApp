/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/21/23, 10:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/20/23, 2:14 AM
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


object StringSerializer: KSerializer<String> {

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("String", PrimitiveKind.DOUBLE)

    override fun deserialize(decoder: Decoder): String =
        decoder.decodeDouble().roundToInt().toString()

    override fun serialize(encoder: Encoder, value: String) =
        encoder.encodeDouble(value.toDouble())
}