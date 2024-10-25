package com.smushytaco.human_reborn.client
import com.smushytaco.human_reborn.HumanEntity
import com.smushytaco.human_reborn.HumanReborn
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.*
import net.minecraft.client.render.entity.model.ArmorEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.client.render.entity.state.PlayerEntityRenderState
import net.minecraft.util.Identifier
@Environment(EnvType.CLIENT)
class HumanEntityRenderer(ctx: EntityRendererFactory.Context): LivingEntityRenderer<HumanEntity, PlayerEntityRenderState, PlayerEntityModel>(ctx, PlayerEntityModel(ctx.getPart(EntityModelLayers.PLAYER), false), 0.5F) {
    init {
        addFeature(ArmorFeatureRenderer(this, ArmorEntityModel(ctx.getPart(EntityModelLayers.PLAYER_INNER_ARMOR)), ArmorEntityModel(ctx.getPart(EntityModelLayers.PLAYER_OUTER_ARMOR)), ctx.equipmentRenderer))
        addFeature(PlayerHeldItemFeatureRenderer(this, ctx.itemRenderer))
        addFeature(StuckArrowsFeatureRenderer(this, ctx))
        addFeature(HeadFeatureRenderer(this, ctx.modelLoader, ctx.itemRenderer))
        addFeature(ElytraFeatureRenderer(this, ctx.modelLoader, ctx.equipmentRenderer))
        addFeature(ShoulderParrotFeatureRenderer(this, ctx.modelLoader))
        addFeature(TridentRiptideFeatureRenderer(this, ctx.modelLoader))
        addFeature(StuckStingersFeatureRenderer(this, ctx))
    }
    override fun getTexture(state: PlayerEntityRenderState): Identifier = Identifier.of("minecraft", "textures/entity/player/wide/steve.png")
    override fun createRenderState() = PlayerEntityRenderState()
    override fun hasLabel(livingEntity: HumanEntity, d: Double) = super.hasLabel(livingEntity, d) && livingEntity.name != HumanReborn.HUMAN_ENTITY_TYPE.name
}