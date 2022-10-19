package bl4ckscor3.mod.ceilingtorch.compat.pokecubeaio;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

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
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import pokecube.legends.init.BlockInit;
import pokecube.legends.init.ParticleInit;

public class PokecubeAIOCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final RegistryObject<Block> INFECTED_CEILING_TORCH = CeilingTorch.BLOCKS.register("pokecube_legends_infected_torch", () -> new CeilingTorchBlock(
			Block.Properties.of(Material.DECORATION)
			.noCollission()
			.instabreak()
			.lightLevel(state -> 10)
			.sound(SoundType.WOOD),
			null, BlockInit.INFECTED_TORCH) {
	//@formatter:on
		@Override
		public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.3D;
			double z = pos.getZ() + 0.5D;

			level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleInit.INFECTED_FIRE_FLAME.get(), x, y, z, 0.0D, 0.0D, 0.0D);
		}
	});
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(BlockInit.INFECTED_TORCH.get()), INFECTED_CEILING_TORCH.get());

		return placeEntries;
	}
}
