package bl4ckscor3.mod.ceilingtorch.compat.endergetic;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.teamabnormals.endergetic.core.registry.EEBlocks;
import com.teamabnormals.endergetic.core.registry.EEParticleTypes;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public class EndergeticCompat implements ICeilingTorchCompat {
	public static final RegistryObject<Block> ENDER_CEILING_TORCH = CeilingTorch.BLOCKS.register("endergetic_ender_torch", () -> new CeilingTorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH), ParticleTypes.FLAME, EEBlocks.ENDER_TORCH) {
		@Override
		public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.45D;
			double z = pos.getZ() + 0.5D;

			level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			level.addParticle(EEParticleTypes.ENDER_FIRE_FLAME.get(), x, y, z, 0.0D, 0.0D, 0.0D);
		}
	});
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(EEBlocks.ENDER_TORCH.get()), ENDER_CEILING_TORCH.get());

		return placeEntries;
	}
}
