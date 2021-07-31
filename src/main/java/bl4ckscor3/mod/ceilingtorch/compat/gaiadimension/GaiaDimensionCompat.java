package bl4ckscor3.mod.ceilingtorch.compat.gaiadimension;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import androsa.gaiadimension.registry.ModItems;
import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class GaiaDimensionCompat implements ICeilingTorchCompat
{
	public static Block pyriteCeilingTorch = null;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(pyriteCeilingTorch = new PyriteCeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.strength(0.0F)
				.lightLevel(state -> 14))
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "gaiadimension_pyrite_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(ModItems.PYRITE_TORCH.get().getRegistryName(), pyriteCeilingTorch);

		return placeEntries;
	}
}
