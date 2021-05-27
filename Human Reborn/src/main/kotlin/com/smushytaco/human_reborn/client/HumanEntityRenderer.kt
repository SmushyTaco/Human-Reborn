package com.smushytaco.human_reborn.client
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.entity.BipedEntityRenderer
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.entity.mob.MobEntity
@Environment(EnvType.CLIENT)
class HumanEntityRenderer(entityRenderDispatcher: EntityRenderDispatcher): BipedEntityRenderer<MobEntity, PlayerEntityModel<MobEntity>>(entityRenderDispatcher, PlayerEntityModel(0.0F, false), 0.5F) {
    init {
        addFeature(ArmorFeatureRenderer(this, BipedEntityModel(0.5F), BipedEntityModel(1.0F)))
    }
}