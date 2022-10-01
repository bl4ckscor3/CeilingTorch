package bl4ckscor3.mod.ceilingtorch.compat.silentgear;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import net.silentchaos512.gear.init.ModBlocks;

public class SilentGearCompat implements ICeilingTorchCompat
{
	public static final RegistryObject<Block> STONE_CEILING_TORCH = CeilingTorch.BLOCKS.register("silentgear_stone_torch", () -> new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
			.noCollission()
			.strength(0.0F)
			.lightLevel(state -> 14)
			.sound(SoundType.STONE),
			ParticleTypes.FLAME, () -> ModBlocks.STONE_TORCH.asBlock()));
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(ModBlocks.STONE_TORCH.asItem()), STONE_CEILING_TORCH.get());

		return placeEntries;
	}
}
