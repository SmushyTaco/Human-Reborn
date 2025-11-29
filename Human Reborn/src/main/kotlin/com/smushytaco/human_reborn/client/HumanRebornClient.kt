package com.smushytaco.human_reborn.client
import com.smushytaco.human_reborn.HumanReborn
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.renderer.entity.EntityRenderers
@Suppress("UNUSED")
object HumanRebornClient: ClientModInitializer { override fun onInitializeClient() { EntityRenderers.register(HumanReborn.HUMAN_ENTITY_TYPE) { HumanEntityRenderer(it) } } }