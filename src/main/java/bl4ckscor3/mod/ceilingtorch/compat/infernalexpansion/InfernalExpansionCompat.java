package bl4ckscor3.mod.ceilingtorch.compat.infernalexpansion;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.init.IEItems;
import com.nekomaster1000.infernalexp.init.IEParticleTypes;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;

public class InfernalExpansionCompat implements ICeilingTorchCompat
{
	public static Block glowlightCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(glowlightCeilingTorch = new CeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), null, IEBlocks.GLOW_TORCH) {
			@Override
			public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
			{
				double x = pos.getX() + 0.5D;
				double y = pos.getY() + 0.45D;
				double z = pos.getZ() + 0.5D;

				world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);

				if(rand.nextInt(2) == 1)
					world.addParticle(IEParticleTypes.GLOWSTONE_SPARKLE.get(), x, y, z, 0.0D, -1.0D, 0.0D);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "infernalexpansion_glowlight_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(IEItems.GLOW_TORCH.get().getRegistryName(), glowlightCeilingTorch);

		return placeEntries;
	}
}
