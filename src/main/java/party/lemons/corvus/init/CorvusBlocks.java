package party.lemons.corvus.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import party.lemons.corvus.Corvus;
import party.lemons.corvus.block.*;
import party.lemons.corvus.block.effectcandle.BlockEffectCandle;
import party.lemons.corvus.block.effectcandle.CandleEffect;
import party.lemons.corvus.block.effectcandle.TileEntityEffectCandle;
import party.lemons.corvus.block.tilentity.TileEntityBreathingTulip;
import party.lemons.corvus.crafting.CorvusTab;
import party.lemons.corvus.gen.FeatureFrankincenseTree;
import party.lemons.lemonlib.block.BlockRegistry;

@Mod.EventBusSubscriber(modid = Corvus.MODID)
@GameRegistry.ObjectHolder(Corvus.MODID)
public class CorvusBlocks
{
	public static final Block CRYSTAL_QUARTZ_ORE = Blocks.AIR;
	public static final Block WISER_GEM_ORE = Blocks.AIR;
	public static final Block WISER_GEM_BLOCK = Blocks.AIR;

	public static final Block CANDLE = Blocks.AIR;
	public static final Block CANDLE_GROWTH = Blocks.AIR;
	public static final Block CANDLE_RAGE = Blocks.AIR;
	public static final Block CANDLE_WIND = Blocks.AIR;

	public static final Block FRANKINSENCE_LOG = Blocks.AIR;
	public static final Block FRANKINSENCE_LEAVES = Blocks.AIR;
	public static final Block FRANKINSENCE_SAPLING = Blocks.AIR;

	public static final Block WORMWOOD = Blocks.AIR;
	public static final Block LAVENDER = Blocks.AIR;

	public static final Block BLAZING_TULIP = Blocks.AIR;
	public static final Block LILY_TOXIC = Blocks.AIR;
	public static final Block BREATHING_TULIP = Blocks.AIR;
	public static final Block BLOOD_ROSE = Blocks.AIR;
	public static final Block STUNNING_DAHLIA = Blocks.AIR;
	public static final Block BLOOM_OF_DEATH = Blocks.AIR;

	public static final Block MUD = Blocks.AIR;
	public static final Block TWILIGHT_GARGET = Blocks.AIR;


	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		BlockRegistry.setup(Corvus.MODID, event.getRegistry(), CorvusTab.INSTANCE);

		BlockRegistry.registerBlock(BlockRegistry.setProperties(new BlockCandle(), 0.3F, 0.25F, 0F), "candle");
		BlockRegistry.registerBlock(BlockRegistry.setProperties(new BlockEffectCandle(new CandleEffect.EffectFire()), 0.3F, 0.25F, 0F), "candle_rage");
		BlockRegistry.registerBlock(BlockRegistry.setProperties(new BlockEffectCandle(new CandleEffect.EffectGrowth()), 0.3F, 0.25F, 0F), "candle_growth");
		BlockRegistry.registerBlock(BlockRegistry.setProperties(new BlockEffectCandle(new CandleEffect.EffectWind()), 0.3F, 0.25F, 0F), "candle_wind");

		BlockRegistry.registerBlock(BlockRegistry.setProperties(new BlockCorvusOre(1, 2, ()->CorvusItems.CRYSTAL_QUARTZ), 3F, 5F, 0F), "crystal_quartz_ore", "oreCrystalQuartz");
		BlockRegistry.registerBlock(BlockRegistry.setProperties(new BlockCorvusOre(2, 2, ()->CorvusItems.WISER_GEM), 3F, 5F, 0F), "wiser_gem_ore", "oreWiserGem");
		BlockRegistry.registerBlock(new BlockCorvusLog(), "frankinsence_log", "logWood");
		BlockRegistry.registerBlock(new BlockCorvusLeaves(()->Item.getItemFromBlock(FRANKINSENCE_SAPLING), ()->CorvusItems.FRANKINCENSE_TEARS), "frankinsence_leaves", "treeLeaves");
		BlockRegistry.registerBlock(new BlockCorvusSapling(new FeatureFrankincenseTree(true)), "frankinsence_sapling", "treeSapling");
		BlockRegistry.registerBlock((BlockRegistry.setProperties(new BlockModel(Material.WOOD), 2F, 5F, 0F)), "frankinsence_planks", "plankWood");

		BlockRegistry.registerBlock(new BlockCorvusCrop(()->CorvusItems.LAVENDER, ()->CorvusItems.LAVENDER_SEEDS), "lavender");
		BlockRegistry.registerBlock(new BlockCorvusCrop(()->CorvusItems.WORMWOOD, ()->CorvusItems.WORMWOOD_SEEDS), "wormwood");

		BlockRegistry.registerBlock((BlockRegistry.setProperties(new BlockModel(Material.IRON), 3F, 5F, 0F)), "crystal_quartz_block", "blockCrystalQuartz");
		BlockRegistry.registerBlock((BlockRegistry.setProperties(new BlockModel(Material.IRON), 3F, 5F, 0F)), "wiser_gem_block", "blockWiserGem");

		BlockRegistry.registerBlock(new BlockLilyToxic(), "lily_toxic", "flower", "lily");
		BlockRegistry.registerBlock(new BlockBlazingTulip(), "blazing_tulip", "flower", "tulip");
		BlockRegistry.registerBlock(new BlockBreathingTulip(), "breathing_tulip", "flower", "tulip");
		BlockRegistry.registerBlock(new BlockBloodRose(), "blood_rose", "flower");
		BlockRegistry.registerBlock(new BlockStunningDahlia(), "stunning_dahlia", "flower");
		BlockRegistry.registerBlock(new BlockBloomOfDeath(), "bloom_of_death", "flower");

		BlockRegistry.registerBlock(new BlockMud(), "mud", "mud", "dirt");
		BlockRegistry.registerBlock(new BlockCorvusFlower(), "twilight_garget", "flower", "dyePurple");

		GameRegistry.registerTileEntity(TileEntityEffectCandle.class, new ResourceLocation(Corvus.MODID, "candle"));
		GameRegistry.registerTileEntity(TileEntityBreathingTulip.class, new ResourceLocation(Corvus.MODID, "breathing_tulip"));
	}
}
