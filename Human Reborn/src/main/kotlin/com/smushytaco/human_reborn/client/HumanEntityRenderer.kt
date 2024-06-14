package com.smushytaco.human_reborn.client
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.entity.BipedEntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.entity.mob.MobEntity
import net.minecraft.util.Identifier
@Environment(EnvType.CLIENT)
class HumanEntityRenderer(ctx: EntityRendererFactory.Context): BipedEntityRenderer<MobEntity, PlayerEntityModel<MobEntity>>(ctx, PlayerEntityModel(ctx.getPart(EntityModelLayers.PLAYER), false), 0.5F) {
    init {
        addFeature(ArmorFeatureRenderer(this, BipedEntityModel(ctx.getPart(EntityModelLayers.PLAYER_INNER_ARMOR)), BipedEntityModel(ctx.getPart(EntityModelLayers.PLAYER_OUTER_ARMOR)), ctx.modelManager))
    }
    override fun getTexture(entity: MobEntity): Identifier = Identifier.of("minecraft", "textures/entity/player/wide/steve.png")
}