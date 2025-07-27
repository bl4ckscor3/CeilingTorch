package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.sammy.malum.registry.common.block.MalumBlockProperties;
import com.sammy.malum.registry.common.block.MalumBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MalumCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<CeilingEtherTorchBlock> ETHER_CEILING_TORCH = CeilingTorch.BLOCKS.register("malum_ether_torch", () -> new CeilingEtherTorchBlock(getProperties(), MalumBlocks.ETHER_TORCH));
	public static final DeferredBlock<CeilingEtherTorchBlock> IRIDESCENT_ETHER_CEILING_TORCH = CeilingTorch.BLOCKS.register("malum_iridescent_ether_torch", () -> new CeilingEtherTorchBlock(getProperties(), MalumBlocks.IRIDESCENT_ETHER_TORCH));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CeilingEtherTorchBlockEntity>> ETHER_CEILING_TORCH_BLOCK_ENTITY = CeilingTorch.BLOCK_ENTITIES.register("malum_ether_torch", () -> BlockEntityType.Builder.of(CeilingEtherTorchBlockEntity::new, ETHER_CEILING_TORCH.get(), IRIDESCENT_ETHER_CEILING_TORCH.get()).build(null));
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(getRegistryName(MalumBlocks.ETHER_TORCH.get()), ETHER_CEILING_TORCH.get(),
					getRegistryName(MalumBlocks.IRIDESCENT_ETHER_TORCH.get()), IRIDESCENT_ETHER_CEILING_TORCH.get());
			//@formatter:on
		}

		return placeEntries;
	}

	@Override
	public BlockState getStateToPlace(RightClickBlock event, Level level, BlockPos pos, BlockState state, ItemStack stack) {
		return state.setValue(BlockStateProperties.WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
	}

	private static BlockBehaviour.Properties getProperties() {
		return MalumBlockProperties.RUNEWOOD().noCollission().instabreak().lightLevel(state -> 14);
	}
}
