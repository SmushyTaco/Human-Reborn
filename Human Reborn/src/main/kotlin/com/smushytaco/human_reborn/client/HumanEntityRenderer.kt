package com.smushytaco.human_reborn.client
import com.smushytaco.human_reborn.HumanEntity
import com.smushytaco.human_reborn.HumanReborn
import net.minecraft.client.model.PlayerModel
import net.minecraft.client.model.geom.ModelLayers
import net.minecraft.client.renderer.entity.ArmorModelSet
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.client.renderer.entity.layers.*
import net.minecraft.client.renderer.entity.state.AvatarRenderState
import net.minecraft.resources.ResourceLocation
class HumanEntityRenderer(ctx: EntityRendererProvider.Context): LivingEntityRenderer<HumanEntity, AvatarRenderState, PlayerModel>(ctx, PlayerModel(ctx.bakeLayer(
    ModelLayers.PLAYER), false), 0.5F) {
    init {
        addLayer(HumanoidArmorLayer(this, ArmorModelSet.bake(ModelLayers.PLAYER_ARMOR, ctx.modelSet) { PlayerModel(it, false) }, ctx.equipmentRenderer))
        addLayer(PlayerItemInHandLayer(this))
        addLayer(ArrowLayer(this, ctx))
        addLayer(CustomHeadLayer(this, ctx.modelSet, ctx.playerSkinRenderCache))
        addLayer(WingsLayer(this, ctx.modelSet, ctx.equipmentRenderer))
        addLayer(ParrotOnShoulderLayer(this, ctx.modelSet))
        addLayer(SpinAttackEffectLayer(this, ctx.modelSet))
        addLayer(BeeStingerLayer(this, ctx))
    }
    override fun getTextureLocation(state: AvatarRenderState): ResourceLocation = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/player/wide/steve.png")
    override fun createRenderState() = AvatarRenderState()
    override fun shouldShowName(livingEntity: HumanEntity, d: Double) = super.shouldShowName(livingEntity, d) && livingEntity.name != HumanReborn.HUMAN_ENTITY_TYPE.description
}