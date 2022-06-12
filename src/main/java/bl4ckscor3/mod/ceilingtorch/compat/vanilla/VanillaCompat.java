package bl4ckscor3.mod.ceilingtorch.compat.vanilla;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class VanillaCompat implements ICeilingTorchCompat
{
	public static final RegistryObject<Block> CEILING_TORCH = CeilingTorch.BLOCKS.register("torch", () -> new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
			.noCollission()
			.strength(0.0F)
			.lightLevel(state -> 14)
			.sound(SoundType.WOOD),
			ParticleTypes.FLAME, () -> Blocks.TORCH));
	public static final RegistryObject<Block> CEILING_REDSTONE_TORCH = CeilingTorch.BLOCKS.register("redstone_torch", () -> new RedstoneCeilingTorchBlock(Block.Properties.of(Material.DECORATION)
			.noCollission()
			.strength(0.0F)
			.lightLevel(state -> state.getValue(RedstoneTorchBlock.LIT) ? 7 : 0)
			.sound(SoundType.WOOD), () -> Blocks.REDSTONE_TORCH));
	public static final RegistryObject<Block> CEILING_SOUL_TORCH = CeilingTorch.BLOCKS.register("soul_torch", () -> new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
			.noCollission()
			.strength(0.0F)
			.lightLevel(state -> 10)
			.sound(SoundType.WOOD),
			ParticleTypes.SOUL_FIRE_FLAME, () -> Blocks.SOUL_TORCH));
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(getRegistryName(Items.TORCH), CEILING_TORCH.get(),
					getRegistryName(Items.REDSTONE_TORCH), CEILING_REDSTONE_TORCH.get(),
					getRegistryName(Items.SOUL_TORCH), CEILING_SOUL_TORCH.get());
		}

		return placeEntries;
	}
}
