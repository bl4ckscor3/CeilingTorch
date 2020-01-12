package bl4ckscor3.mod.ceilingtorch.compat.vanilla;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CeilingTorch.MODID)
public class VanillaCompat implements ICeilingTorchCompat
{
	public static final Block TORCH = null;
	public static final Block REDSTONE_TORCH = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.WOOD).lootFrom(Blocks.TORCH)).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "torch")));
		event.getRegistry().register(new RedstoneCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(7).sound(SoundType.WOOD).lootFrom(Blocks.REDSTONE_TORCH)).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "redstone_torch")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(Items.TORCH.getRegistryName(), TORCH);
		PlaceHandler.registerPlaceEntry(Items.REDSTONE_TORCH.getRegistryName(), REDSTONE_TORCH);
	}
}
