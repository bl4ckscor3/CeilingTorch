package bl4ckscor3.mod.ceilingtorch.compat.gaiadimension;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.registration.ModParticles;
import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class GaiaDimensionCompat implements ICeilingTorchCompat {
	public static final RegistryObject<Block> PYRITE_CEILING_TORCH = CeilingTorch.BLOCKS.register("gaiadimension_pyrite_torch", () -> new CeilingTorchBlock(Block.Properties.of(Material.DECORATION).strength(0.0F).lightLevel(state -> 14).noCollission(), null, ModBlocks.pyrite_torch) {
		@Override
		public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
			double x = pos.getX() + rand.nextDouble() * 0.7D;
			double y = pos.getY() + rand.nextDouble() * 0.7D;
			double z = pos.getZ() + rand.nextDouble() * 0.7D;

			level.addParticle(ModParticles.PYRITE.get(), x, y, z, 0.0D, 0.0D, 0.0D);
		}
	});
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(ModItems.PYRITE_TORCH.getId(), PYRITE_CEILING_TORCH.get());

		return placeEntries;
	}
}
