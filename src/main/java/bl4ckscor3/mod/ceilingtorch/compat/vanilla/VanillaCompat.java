package bl4ckscor3.mod.ceilingtorch.compat.vanilla;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CeilingTorch.MODID)
public class VanillaCompat implements ICeilingTorchCompat
{
	public static final Block TORCH = null;
	public static final Block REDSTONE_TORCH = null;
	public static final Block SOUL_TORCH = null;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.setLightLevel(state -> 14)
				.sound(SoundType.WOOD),
				ParticleTypes.FLAME, () -> Blocks.TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "torch")));
		event.getRegistry().register(new RedstoneCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.setLightLevel(state -> state.get(RedstoneTorchBlock.LIT) ? 7 : 0)
				.sound(SoundType.WOOD), () -> Blocks.REDSTONE_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "redstone_torch")));
		event.getRegistry().register(new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.setLightLevel(state -> 10)
				.sound(SoundType.WOOD),
				ParticleTypes.SOUL_FIRE_FLAME, () -> Blocks.SOUL_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "soul_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(Items.TORCH.getRegistryName(), TORCH,
					Items.REDSTONE_TORCH.getRegistryName(), REDSTONE_TORCH,
					Items.SOUL_TORCH.getRegistryName(), SOUL_TORCH);
		}

		return placeEntries;
	}
}
