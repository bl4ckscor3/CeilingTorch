package bl4ckscor3.mod.ceilingtorch.compat.silentgear;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.silentchaos512.gear.init.ModBlocks;

public class SilentGearCompat implements ICeilingTorchCompat
{
	public static Block stoneCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(stoneCeilingTorch = new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.setLightLevel(state -> 14)
				.sound(SoundType.STONE),
				ParticleTypes.FLAME, () -> ModBlocks.STONE_TORCH.asBlock()).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "silentgear_stone_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(ModBlocks.STONE_TORCH.asItem().getRegistryName(), stoneCeilingTorch);

		return placeEntries;
	}
}
