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
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object StringTimeSerializer : KSerializer<String> {

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("String", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): String {
        val dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(decoder.decodeLong()),
            ZoneId.systemDefault()
        )

        val formatter = DateTimeFormatter.ofPattern("HH:mm")

        return dateTime.format(formatter)
    }

    override fun serialize(encoder: Encoder, value: String) =
        encoder.encodeLong(value.toLong())
}