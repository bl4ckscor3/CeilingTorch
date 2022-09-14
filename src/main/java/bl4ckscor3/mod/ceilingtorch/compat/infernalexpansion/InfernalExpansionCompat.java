package bl4ckscor3.mod.ceilingtorch.compat.infernalexpansion;

import java.util.Map;

import org.infernalstudios.infernalexp.init.IEBlocks;
import org.infernalstudios.infernalexp.init.IEItems;
import org.infernalstudios.infernalexp.init.IEParticleTypes;

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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public class InfernalExpansionCompat implements ICeilingTorchCompat
{
	public static final RegistryObject<Block> GLOWLIGHT_CEILING_TORCH = CeilingTorch.BLOCKS.register("infernalexpansion_glowlight_torch", () -> new CeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), null, IEBlocks.GLOW_TORCH) {
		@Override
		public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand)
		{
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.45D;
			double z = pos.getZ() + 0.5D;

			world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);

			if(rand.nextInt(2) == 1)
				world.addParticle(IEParticleTypes.GLOWSTONE_SPARKLE.get(), x, y, z, 0.0D, -1.0D, 0.0D);
		}
	});
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(IEItems.GLOW_TORCH.get()), GLOWLIGHT_CEILING_TORCH.get());

		return placeEntries;
	}
}
