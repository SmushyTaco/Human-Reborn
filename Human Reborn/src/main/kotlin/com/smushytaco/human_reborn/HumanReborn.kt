package com.smushytaco.human_reborn
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.*
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.SpawnEggItem
import net.minecraft.world.level.levelgen.Heightmap
object HumanReborn: ModInitializer {
    private const val MOD_ID = "human_reborn"
    private val HUMAN_IDENTIFIER: ResourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, "human")
    private val SPAWN_EGG_IDENTIFIER: ResourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, "human_spawn_egg")
    val HUMAN_ENTITY_TYPE: EntityType<HumanEntity> = Registry.register(
        BuiltInRegistries.ENTITY_TYPE, HUMAN_IDENTIFIER, EntityType.Builder.of({ _, world -> HumanEntity(world) }, MobCategory.MONSTER).sized(0.6F, 1.8F).eyeHeight(1.62F).vehicleAttachment(
            Avatar.DEFAULT_VEHICLE_ATTACHMENT
        ).clientTrackingRange(8).build(ResourceKey.create(Registries.ENTITY_TYPE, HUMAN_IDENTIFIER)))
    private val HUMAN_SPAWN_EGG = SpawnEggItem(
        Item.Properties().spawnEgg(HUMAN_ENTITY_TYPE).setId(
            ResourceKey.create(
                Registries.ITEM, SPAWN_EGG_IDENTIFIER)))
    override fun onInitialize() {
        FabricDefaultAttributeRegistry.register(HUMAN_ENTITY_TYPE, HumanEntity.createMobAttributes())
        SpawnPlacements.register(HUMAN_ENTITY_TYPE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules)
        BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.ZOMBIE), MobCategory.MONSTER, HUMAN_ENTITY_TYPE, 10, 1, 3)
        Registry.register(BuiltInRegistries.ITEM, SPAWN_EGG_IDENTIFIER, HUMAN_SPAWN_EGG)
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(ItemGroupEvents.ModifyEntries { it.accept(HUMAN_SPAWN_EGG) })
    }
}