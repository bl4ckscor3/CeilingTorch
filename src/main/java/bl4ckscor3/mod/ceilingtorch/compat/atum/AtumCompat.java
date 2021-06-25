package bl4ckscor3.mod.ceilingtorch.compat.atum;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.teammetallurgy.atum.init.AtumBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class AtumCompat implements ICeilingTorchCompat
{
	public static Block palmCeilingTorch;
	public static Block deadwoodCeilingTorch;
	public static Block limestoneCeilingTorch;
	public static Block boneCeilingTorch;
	public static Block pharaohCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(palmCeilingTorch = new AtumCeilingTorchBlock(() -> AtumBlocks.PALM_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_palm_torch")));
		event.getRegistry().register(deadwoodCeilingTorch = new AtumCeilingTorchBlock(() -> AtumBlocks.DEADWOOD_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_deadwood_torch")));
		event.getRegistry().register(limestoneCeilingTorch = new AtumCeilingTorchBlock(() -> AtumBlocks.LIMESTONE_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_limestone_torch")));
		event.getRegistry().register(boneCeilingTorch = new AtumCeilingTorchBlock(() -> AtumBlocks.BONE_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_bone_torch")));
		event.getRegistry().register(pharaohCeilingTorch = new AtumCeilingTorchBlock(() -> AtumBlocks.PHARAOH_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_pharaoh_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(AtumBlocks.PALM_TORCH.getRegistryName(), palmCeilingTorch,
					AtumBlocks.DEADWOOD_TORCH.getRegistryName(), deadwoodCeilingTorch,
					AtumBlocks.LIMESTONE_TORCH.getRegistryName(), limestoneCeilingTorch,
					AtumBlocks.BONE_TORCH.getRegistryName(), boneCeilingTorch,
					AtumBlocks.PHARAOH_TORCH.getRegistryName(), pharaohCeilingTorch);
		}

		return placeEntries;
	}
}
