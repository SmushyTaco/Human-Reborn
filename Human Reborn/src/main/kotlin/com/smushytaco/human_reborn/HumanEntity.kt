package com.smushytaco.human_reborn
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.monster.ZombifiedPiglin
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
class HumanEntity(world: Level): Monster(HumanReborn.HUMAN_ENTITY_TYPE, world) {
    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(8, LookAtPlayerGoal(this, Player::class.java, 8.0F))
        goalSelector.addGoal(8, RandomLookAroundGoal(this))
        goalSelector.addGoal(2, MeleeAttackGoal(this, 1.0, false))
        goalSelector.addGoal(7, WaterAvoidingRandomStrollGoal(this, 1.0))
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, Player::class.java, true))
        targetSelector.addGoal(1, HurtByTargetGoal(this).setAlertOthers(ZombifiedPiglin::class.java))
    }
    override fun getSwimSound(): SoundEvent = SoundEvents.PLAYER_SWIM
    override fun getSwimSplashSound(): SoundEvent = SoundEvents.PLAYER_SPLASH
    override fun getHurtSound(source: DamageSource): SoundEvent = SoundEvents.PLAYER_HURT
    override fun getDeathSound(): SoundEvent = SoundEvents.PLAYER_DEATH
    override fun getFallSounds(): Fallsounds = Fallsounds(SoundEvents.PLAYER_SMALL_FALL, SoundEvents.PLAYER_BIG_FALL)
    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder = createMonsterAttributes()
            .add(Attributes.FOLLOW_RANGE, 35.0)
            .add(Attributes.MOVEMENT_SPEED, 0.35)
            .add(Attributes.ATTACK_DAMAGE, 4.0)
            .add(Attributes.ARMOR, 2.0)
    }
}