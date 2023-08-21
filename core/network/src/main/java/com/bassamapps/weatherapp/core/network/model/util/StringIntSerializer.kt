/*
 * *
 *  * Created by Bassam Abdulrazzaq on 8/20/23, 2:14 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 8/17/23, 9:39 PM
 *
 */

package com.bassamapps.weatherapp.core.network.model.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object StringIntSerializer : KSerializer<String> {

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("String", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): String =
        decoder.decodeInt().toString()

    override fun serialize(encoder: Encoder, value: String) =
        encoder.encodeInt(value.toInt())
}