package com.smushytaco.human_reborn
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.entity.*
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.Heightmap
object HumanReborn: ModInitializer {
    private const val MOD_ID = "human_reborn"
    private val HUMAN_IDENTIFIER: Identifier = Identifier.of(MOD_ID, "human")
    private val SPAWN_EGG_IDENTIFIER: Identifier = Identifier.of(MOD_ID, "human_spawn_egg")
    val HUMAN_ENTITY_TYPE: EntityType<HumanEntity> = Registry.register(Registries.ENTITY_TYPE, HUMAN_IDENTIFIER, EntityType.Builder.create({ _, world -> HumanEntity(world) }, SpawnGroup.MONSTER).dimensions(0.6F, 1.8F).eyeHeight(1.62F).vehicleAttachment(PlayerEntity.VEHICLE_ATTACHMENT_POS).maxTrackingRange(8).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, HUMAN_IDENTIFIER)))
    private val HUMAN_SPAWN_EGG = SpawnEggItem(HUMAN_ENTITY_TYPE, Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, SPAWN_EGG_IDENTIFIER)))
    override fun onInitialize() {
        FabricDefaultAttributeRegistry.register(HUMAN_ENTITY_TYPE, HumanEntity.createMobAttributes())
        SpawnRestriction.register(HUMAN_ENTITY_TYPE, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark)
        BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.ZOMBIE), SpawnGroup.MONSTER, HUMAN_ENTITY_TYPE, 10, 1, 3)
        Registry.register(Registries.ITEM, SPAWN_EGG_IDENTIFIER, HUMAN_SPAWN_EGG)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ItemGroupEvents.ModifyEntries { it.add(HUMAN_SPAWN_EGG) })
    }
}