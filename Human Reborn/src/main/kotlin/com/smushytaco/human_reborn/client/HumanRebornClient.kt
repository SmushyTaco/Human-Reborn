package com.smushytaco.human_reborn.client
import com.smushytaco.human_reborn.HumanReborn
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
@Suppress("UNUSED")
object HumanRebornClient: ClientModInitializer { override fun onInitializeClient() { EntityRendererRegistry.register(HumanReborn.HUMAN_ENTITY_TYPE) { HumanEntityRenderer(it) } } }