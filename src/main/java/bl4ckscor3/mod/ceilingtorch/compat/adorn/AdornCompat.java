package bl4ckscor3.mod.ceilingtorch.compat.adorn;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import juuxel.adorn.block.AdornBlocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.event.RegistryEvent.Register;

public class AdornCompat implements ICeilingTorchCompat {
	public static Block stoneCeilingTorch;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(stoneCeilingTorch = new CeilingTorchBlock(Block.Properties.copy(Blocks.TORCH)
				.sound(SoundType.STONE)
				.lightLevel(state -> 15), ParticleTypes.FLAME, () -> AdornBlocks.INSTANCE.getSTONE_TORCH_GROUND())
				.setRegistryName("adorn_stone_torch"));
		//@formatter:on
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(AdornBlocks.INSTANCE.getSTONE_TORCH_GROUND().getRegistryName(), stoneCeilingTorch);

		return placeEntries;
	}
}
