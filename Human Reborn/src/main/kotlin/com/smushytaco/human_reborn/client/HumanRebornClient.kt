package com.smushytaco.human_reborn.client
import com.smushytaco.human_reborn.HumanReborn
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
@Suppress("UNUSED")
@Environment(EnvType.CLIENT)
object HumanRebornClient: ClientModInitializer {
    override fun onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(HumanReborn.HUMAN_ENTITY_TYPE) {
            HumanEntityRenderer(it)
        }
    }
}