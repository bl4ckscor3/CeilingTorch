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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.RegistryEvent;

public class InspirationsCompat implements ICeilingTorchCompat {
	public static Block ceilingTorchLever;
	public static Block ceilingSoulTorchLever;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(ceilingTorchLever = new CeilingTorchLeverBlock(Block.Properties.copy(Blocks.TORCH).lootFrom(() -> InspirationsUtility.torchLeverFloor), ParticleTypes.FLAME) {
			@Override
			public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
				return new ItemStack(InspirationsUtility.torchLeverItem);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "inspirations_torch_lever")));
		event.getRegistry().register(ceilingSoulTorchLever = new CeilingTorchLeverBlock(Block.Properties.copy(Blocks.SOUL_TORCH).lootFrom(() -> InspirationsUtility.soulLeverFloor), ParticleTypes.SOUL_FIRE_FLAME) {
			@Override
			public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
				return new ItemStack(InspirationsUtility.soulLeverItem);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "inspirations_soul_torch_lever")));
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(InspirationsUtility.torchLeverItem.getRegistryName(), ceilingTorchLever, InspirationsUtility.soulLeverItem.getRegistryName(), ceilingSoulTorchLever);

		return placeEntries;
	}
}
