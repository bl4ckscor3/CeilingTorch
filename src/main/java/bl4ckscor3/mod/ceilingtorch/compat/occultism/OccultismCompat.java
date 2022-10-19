package bl4ckscor3.mod.ceilingtorch.compat.occultism;

import java.util.Map;
import java.util.Random;

import com.github.klikli_dev.occultism.registry.OccultismBlocks;
import com.github.klikli_dev.occultism.registry.OccultismParticles;
import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent.Register;

public class OccultismCompat implements ICeilingTorchCompat {
	private static Block ceilingSpiritTorch;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(ceilingSpiritTorch = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.noCollission()
				.instabreak()
				.lightLevel(state -> 10)
				.sound(SoundType.WOOD),
				null, OccultismBlocks.SPIRIT_TORCH) {
			@Override
			public void animateTick(BlockState state, Level level, BlockPos pos, Random rand)
			{
				double x = pos.getX() + 0.5D;
				double y = pos.getY() + 0.45D;
				double z = pos.getZ() + 0.5D;

				level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
				level.addParticle(OccultismParticles.SPIRIT_FIRE_FLAME.get(), x, y, z, 0.0D, 0.0D, 0.0D);
			}
		}.setRegistryName("occultism_spirit_torch"));
		//@formatter:on
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(OccultismBlocks.SPIRIT_TORCH.get().getRegistryName(), ceilingSpiritTorch);

		return placeEntries;
	}
}
