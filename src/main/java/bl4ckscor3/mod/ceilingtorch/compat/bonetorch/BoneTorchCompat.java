package bl4ckscor3.mod.ceilingtorch.compat.bonetorch;

import com.builtbroken.bonetorch.BoneTorchMod;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.BlockCeilingTorch;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CeilingTorch.MODID)
public class BoneTorchCompat implements ICeilingTorchCompat
{
	public static final Block BONETORCH_BONETORCH = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new BlockCeilingTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.WOOD)) {
			@Override
			public ResourceLocation getLootTable()
			{
				return BoneTorchMod.blockTorch.getLootTable();
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bonetorch_bonetorch")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(new ResourceLocation("bonetorch", "bonetorch"), BONETORCH_BONETORCH);
	}
}
