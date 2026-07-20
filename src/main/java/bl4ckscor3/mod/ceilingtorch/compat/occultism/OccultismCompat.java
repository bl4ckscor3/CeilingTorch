package bl4ckscor3.mod.ceilingtorch.compat.occultism;

import java.util.Map;

import com.klikli_dev.occultism.registry.OccultismBlocks;
import com.klikli_dev.occultism.registry.OccultismParticles;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OccultismCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<CeilingTorchBlock> CEILING_SPIRIT_TORCH = CeilingTorch.BLOCKS.registerBlock("occultism_spirit_torch", p -> new CeilingTorchBlock(p, null, OccultismBlocks.SPIRIT_TORCH) {
		@Override
		public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.45D;
			double z = pos.getZ() + 0.5D;

			level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			level.addParticle(OccultismParticles.SPIRIT_FIRE_FLAME.get(), x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}, () -> BlockBehaviour.Properties.of()
		.noCollision()
		.instabreak()
		.lightLevel(_ -> 10)
		.sound(SoundType.WOOD));
	private Map<Identifier, Block> placeEntries;

	@Override
	public Map<Identifier, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = Map.of(OccultismBlocks.SPIRIT_TORCH.getId(), CEILING_SPIRIT_TORCH.get());

		return placeEntries;
	}
}
