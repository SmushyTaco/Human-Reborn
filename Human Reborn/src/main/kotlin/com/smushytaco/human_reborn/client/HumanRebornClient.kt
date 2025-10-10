package com.smushytaco.human_reborn.client
import com.smushytaco.human_reborn.HumanReborn
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.render.entity.EntityRendererFactories
@Suppress("UNUSED")
object HumanRebornClient: ClientModInitializer { override fun onInitializeClient() { EntityRendererFactories.register(HumanReborn.HUMAN_ENTITY_TYPE) { HumanEntityRenderer(it) } } }