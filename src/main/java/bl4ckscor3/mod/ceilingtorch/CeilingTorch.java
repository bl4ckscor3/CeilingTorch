package bl4ckscor3.mod.ceilingtorch;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod(CeilingTorch.MODID)
@EventBusSubscriber(bus=Bus.MOD)
public class CeilingTorch
{
	public static final String MODID = "ceilingtorch";
	public static final String NAME = "Ceiling Torch";
	@ObjectHolder(MODID + ":torch")
	public static final Block TORCH = null;
	@ObjectHolder(MODID + ":redstone_torch")
	public static final Block REDSTONE_TORCH = null;

	@SubscribeEvent
	public static void registerBlock(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new BlockCeilingTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.WOOD).lootFrom(Blocks.TORCH)).setRegistryName(new ResourceLocation(MODID, "torch")));
		event.getRegistry().register(new BlockRedstoneCeilingTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(7).sound(SoundType.WOOD).lootFrom(Blocks.REDSTONE_TORCH)).setRegistryName(new ResourceLocation(MODID, "redstone_torch")));
	}
}
