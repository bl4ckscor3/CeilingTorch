package bl4ckscor3.mod.ceilingtorch.compat.inspirations;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import knightminer.inspirations.utility.InspirationsUtility;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.registries.RegistryObject;

public class InspirationsCompat implements ICeilingTorchCompat {
	public static final RegistryObject<Block> CEILING_TORCH_LEVER = CeilingTorch.BLOCKS.register("inspirations_torch_lever", () -> new CeilingTorchLeverBlock(BlockBehaviour.Properties.copy(Blocks.TORCH).lootFrom(() -> InspirationsUtility.torchLeverFloor), ParticleTypes.FLAME) {
		@Override
		public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
			return new ItemStack(InspirationsUtility.torchLeverItem);
		}
	});
	public static final RegistryObject<Block> CEILING_SOUL_TORCH_LEVER = CeilingTorch.BLOCKS.register("inspirations_soul_torch_lever", () -> new CeilingTorchLeverBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_TORCH).lootFrom(() -> InspirationsUtility.soulLeverFloor), ParticleTypes.SOUL_FIRE_FLAME) {
		@Override
		public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
			return new ItemStack(InspirationsUtility.soulLeverItem);
		}
	});
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			placeEntries = ImmutableMap.of(
				getRegistryName(InspirationsUtility.torchLeverFloor.asItem()), CEILING_TORCH_LEVER.get(),
				getRegistryName(InspirationsUtility.soulLeverFloor.asItem()), CEILING_SOUL_TORCH_LEVER.get()
			);
		}

		return placeEntries;
	}
}
