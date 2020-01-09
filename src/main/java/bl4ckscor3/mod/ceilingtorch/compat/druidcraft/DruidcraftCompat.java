package bl4ckscor3.mod.ceilingtorch.compat.druidcraft;

import com.vulp.druidcraft.registry.ItemRegistry;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class DruidcraftCompat implements ICeilingTorchCompat
{
	public static Block fieryCeilingTorch;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(fieryCeilingTorch = new BlockFieryCeilingTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(15).sound(SoundType.BAMBOO)).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "druidcraft_fiery_torch")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(ItemRegistry.fiery_torch.getRegistryName(), fieryCeilingTorch);
	}
}
