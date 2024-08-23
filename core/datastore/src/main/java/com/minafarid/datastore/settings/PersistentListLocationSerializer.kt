package com.minafarid.datastore.settings

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = PersistentList::class)
object PersistentListLocationSerializer : KSerializer<PersistentList<Location>> {

  private val delegateSerializer = ListSerializer(Location.serializer())

  @OptIn(InternalSerializationApi::class)
  override val descriptor: SerialDescriptor = buildSerialDescriptor(
    "PersistentList",
    SerialKind.CONTEXTUAL, // Indicates that this is a contextual (custom) serializer
    listSerialDescriptor(Location.serializer().descriptor), // Descriptor for the elements
  )

  override fun serialize(encoder: Encoder, value: PersistentList<Location>) {
    delegateSerializer.serialize(encoder, value.toList())
  }

  override fun deserialize(decoder: Decoder): PersistentList<Location> {
    return delegateSerializer.deserialize(decoder).toPersistentList()
  }
}
