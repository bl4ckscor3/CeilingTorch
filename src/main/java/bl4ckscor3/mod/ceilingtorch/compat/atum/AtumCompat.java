package bl4ckscor3.mod.ceilingtorch.compat.atum;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.teammetallurgy.atum.init.AtumBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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
		event.getRegistry().register(palmCeilingTorch = new CeilingTorchBlock(getProperties(),
				() -> AtumBlocks.PALM_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_palm_torch")));
		event.getRegistry().register(deadwoodCeilingTorch = new CeilingTorchBlock(getProperties(),
				() -> AtumBlocks.DEADWOOD_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_deadwood_torch")));
		event.getRegistry().register(limestoneCeilingTorch = new CeilingTorchBlock(getProperties(),
				() -> AtumBlocks.LIMESTONE_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_limestone_torch")));
		event.getRegistry().register(boneCeilingTorch = new CeilingTorchBlock(getProperties(),
				() -> AtumBlocks.BONE_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_bone_torch")));
		event.getRegistry().register(pharaohCeilingTorch = new CeilingTorchBlock(getProperties(),
				() -> AtumBlocks.PHARAOH_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_pharaoh_torch")));
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

	private Block.Properties getProperties()
	{
		return Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.WOOD);
	}
}
