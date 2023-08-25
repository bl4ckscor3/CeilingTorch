package bl4ckscor3.mod.ceilingtorch.compat.occultism;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.klikli_dev.occultism.registry.OccultismBlocks;
import com.klikli_dev.occultism.registry.OccultismParticles;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public class OccultismCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final RegistryObject<CeilingTorchBlock> CEILING_SPIRIT_TORCH = CeilingTorch.BLOCKS.register("occultism_spirit_torch", () -> new CeilingTorchBlock(Block.Properties.of()
			.noCollission()
			.instabreak()
			.lightLevel(state -> 10)
			.sound(SoundType.WOOD),
			null, OccultismBlocks.SPIRIT_TORCH) {
	//@formatter:on
		@Override
		public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.45D;
			double z = pos.getZ() + 0.5D;

			level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			level.addParticle(OccultismParticles.SPIRIT_FIRE_FLAME.get(), x, y, z, 0.0D, 0.0D, 0.0D);
		}
	});
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(OccultismBlocks.SPIRIT_TORCH.get()), CEILING_SPIRIT_TORCH.get());

		return placeEntries;
	}
}
