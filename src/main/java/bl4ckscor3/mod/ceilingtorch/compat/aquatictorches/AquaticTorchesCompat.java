package bl4ckscor3.mod.ceilingtorch.compat.aquatictorches;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import realmayus.aquatictorches.AquaticTorches;

public class AquaticTorchesCompat implements ICeilingTorchCompat {
	public static Block aquaticCeilingTorch;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(aquaticCeilingTorch = new AquaticCeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.noCollission()
				.instabreak()
				.lightLevel(state -> 15)
				.sound(SoundType.WOOD),
				ParticleTypes.GLOW_SQUID_INK, () -> AquaticTorches.AQUATIC_TORCH).setRegistryName("aquatictorches_aquatic_torch"));
		//@formatter:on
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(AquaticTorches.AQUATIC_TORCH.getRegistryName(), aquaticCeilingTorch);

		return placeEntries;
	}

	@Override
	public BlockState getStateToPlace(RightClickBlock event, Level level, BlockPos pos, BlockState state, ItemStack stack) {
		FluidState fluidState = level.getFluidState(pos);
		boolean isFlowing = fluidState.getType() == Fluids.FLOWING_WATER;
		boolean isWater = fluidState.getType() == Fluids.WATER || isFlowing;

		return state.setValue(AquaticCeilingTorchBlock.WATERLOGGED, isWater).setValue(AquaticCeilingTorchBlock.FLOWING_WATER, isFlowing ? fluidState.getAmount() : 8);
	}
}
