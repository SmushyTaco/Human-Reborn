package com.smushytaco.human_reborn.client
import com.smushytaco.human_reborn.HumanEntity
import com.smushytaco.human_reborn.HumanReborn
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.*
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.EquipmentModelData
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.client.render.entity.state.PlayerEntityRenderState
import net.minecraft.util.Identifier
class HumanEntityRenderer(ctx: EntityRendererFactory.Context): LivingEntityRenderer<HumanEntity, PlayerEntityRenderState, PlayerEntityModel>(ctx, PlayerEntityModel(ctx.getPart(EntityModelLayers.PLAYER), false), 0.5F) {
    init {
        addFeature(ArmorFeatureRenderer(this, EquipmentModelData.mapToEntityModel(EntityModelLayers.PLAYER_EQUIPMENT, ctx.entityModels) { PlayerEntityModel(it, false) }, ctx.equipmentRenderer))
        addFeature(PlayerHeldItemFeatureRenderer(this))
        addFeature(StuckArrowsFeatureRenderer(this, ctx))
        addFeature(HeadFeatureRenderer(this, ctx.entityModels, ctx.playerSkinCache))
        addFeature(ElytraFeatureRenderer(this, ctx.entityModels, ctx.equipmentRenderer))
        addFeature(ShoulderParrotFeatureRenderer(this, ctx.entityModels))
        addFeature(TridentRiptideFeatureRenderer(this, ctx.entityModels))
        addFeature(StuckStingersFeatureRenderer(this, ctx))
    }
    override fun getTexture(state: PlayerEntityRenderState): Identifier = Identifier.of("minecraft", "textures/entity/player/wide/steve.png")
    override fun createRenderState() = PlayerEntityRenderState()
    override fun hasLabel(livingEntity: HumanEntity, d: Double) = super.hasLabel(livingEntity, d) && livingEntity.name != HumanReborn.HUMAN_ENTITY_TYPE.name
}