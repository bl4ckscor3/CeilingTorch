package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.sammy.malum.common.item.ether.AbstractEtherItem;
import com.sammy.malum.registry.common.block.BlockRegistry;
import com.sammy.malum.registry.common.block.MalumBlockProperties;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.RegistryObject;

public class MalumCompat implements ICeilingTorchCompat {
	public static final RegistryObject<CeilingEtherTorchBlock> ETHER_CEILING_TORCH = CeilingTorch.BLOCKS.register("malum_ether_torch", () -> new CeilingEtherTorchBlock(getProperties(), BlockRegistry.ETHER_TORCH));
	public static final RegistryObject<CeilingEtherTorchBlock> IRIDESCENT_ETHER_CEILING_TORCH = CeilingTorch.BLOCKS.register("malum_iridescent_ether_torch", () -> new CeilingEtherTorchBlock(getProperties(), BlockRegistry.IRIDESCENT_ETHER_TORCH));
	public static final RegistryObject<BlockEntityType<?>> ETHER_CEILING_TORCH_BLOCK_ENTITY = CeilingTorch.BLOCK_ENTITIES.register("malum_ether_torch", () -> BlockEntityType.Builder.of(CeilingEtherTorchBlockEntity::new, ETHER_CEILING_TORCH.get(), IRIDESCENT_ETHER_CEILING_TORCH.get()).build(null));
	private Map<ResourceLocation, Block> placeEntries;

	public MalumCompat() {
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> MalumCompatClient::addListeners);
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(getRegistryName(BlockRegistry.ETHER_TORCH.get()), ETHER_CEILING_TORCH.get(),
					getRegistryName(BlockRegistry.IRIDESCENT_ETHER_TORCH.get()), IRIDESCENT_ETHER_CEILING_TORCH.get());
			//@formatter:on
		}

		return placeEntries;
	}

	@Override
	public BlockState getStateToPlace(RightClickBlock event, Level level, BlockPos pos, BlockState state, ItemStack stack) {
		return state.setValue(BlockStateProperties.WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
	}

	@Override
	public void onPlace(RightClickBlock event, BlockPos placeAt, BlockState state) {
		if (event.getLevel().getBlockEntity(placeAt) instanceof CeilingEtherTorchBlockEntity be) {
			ItemStack held = event.getItemStack();
			AbstractEtherItem item = (AbstractEtherItem) held.getItem();

			be.setFirstColor(item.getFirstColor(held));
			be.setSecondColor(item.getSecondColor(held));
		}
	}

	private static Block.Properties getProperties() {
		return MalumBlockProperties.RUNEWOOD().noCollission().instabreak().lightLevel(state -> 14);
	}
}
