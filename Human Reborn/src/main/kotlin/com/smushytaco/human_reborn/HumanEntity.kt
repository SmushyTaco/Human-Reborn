package com.smushytaco.human_reborn
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.CreeperEntity
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.mob.ZombifiedPiglinEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World
class HumanEntity(world: World): HostileEntity(HumanReborn.HUMAN_ENTITY_TYPE, world) {
    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0F))
        goalSelector.add(8, LookAroundGoal(this))
        goalSelector.add(2, MeleeAttackGoal(this, 1.0, false))
        goalSelector.add(7, WanderAroundFarGoal(this, 1.0))
        targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(1, RevengeGoal(this).setGroupRevenge(ZombifiedPiglinEntity::class.java))
    }
    override fun getSwimSound(): SoundEvent = SoundEvents.ENTITY_PLAYER_SWIM
    override fun getSplashSound(): SoundEvent = SoundEvents.ENTITY_PLAYER_SPLASH
    override fun getHurtSound(source: DamageSource): SoundEvent = SoundEvents.ENTITY_PLAYER_HURT
    override fun getDeathSound(): SoundEvent = SoundEvents.ENTITY_PLAYER_DEATH
    override fun getFallSounds(): FallSounds = FallSounds(SoundEvents.ENTITY_PLAYER_SMALL_FALL, SoundEvents.ENTITY_PLAYER_BIG_FALL)
    override fun dropEquipment(world: ServerWorld, source: DamageSource, causedByPlayer: Boolean) {
        super.dropEquipment(world, source, causedByPlayer)
        val entity = source.attacker ?: return
        if (entity !is CreeperEntity) return
        if (!entity.shouldDropHead()) return
        entity.onHeadDropped()
        dropStack(world, ItemStack(Items.PLAYER_HEAD))
    }
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.FOLLOW_RANGE, 35.0)
            .add(EntityAttributes.MOVEMENT_SPEED, 0.35)
            .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
            .add(EntityAttributes.ARMOR, 2.0)
    }
}