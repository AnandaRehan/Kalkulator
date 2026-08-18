package com.ehan.kalkulator.data.local

import androidx.room.TypeConverter
import com.ehan.kalkulator.data.local.entity.ItemCategory
import com.ehan.kalkulator.data.local.entity.ItemPriority

class Converters {
    @TypeConverter
    fun fromCategory(value: ItemCategory): String = value.name

    @TypeConverter
    fun toCategory(value: String): ItemCategory = runCatching { ItemCategory.valueOf(value) }.getOrDefault(ItemCategory.GENERAL)

    @TypeConverter
    fun fromPriority(value: ItemPriority): String = value.name

    @TypeConverter
    fun toPriority(value: String): ItemPriority = runCatching { ItemPriority.valueOf(value) }.getOrDefault(ItemPriority.MEDIUM)
}
