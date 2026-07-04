package bl4ckscor3.mod.ceilingtorch.compat.invisibletorches;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.RegistryObject;
import wallywhip.InvisibleTorches.init.initBlocks;
import wallywhip.InvisibleTorches.init.initItems;

public class InvisibleTorchesCompat implements ICeilingTorchCompat {
	public static final RegistryObject<Block> CEILING_INVISIBLE_TORCH = CeilingTorch.BLOCKS.register("invisibletorches_torch", () -> new InvisibleCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), ParticleTypes.FLAME, initBlocks.TORCH) {
		@Override
		public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
			return new InvisibleCeilingTorchBlockEntity(pos, state);
		}
	});
	public static final RegistryObject<Block> CEILING_INVISIBLE_SOUL_TORCH = CeilingTorch.BLOCKS.register("invisibletorches_soul_torch", () -> new InvisibleCeilingTorchBlock(Block.Properties.copy(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME, initBlocks.SOUL_TORCH) {
		@Override
		public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
			return new InvisibleCeilingSoulTorchBlockEntity(pos, state);
		}
	});
	public static final RegistryObject<Block> CEILING_INVISIBLE_TORCH_ULTIMATE = CeilingTorch.BLOCKS.register("invisibletorches_torch_ultimate", () -> new UltimateInvisibleCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), ParticleTypes.FLAME, initBlocks.TORCH_ULTIMATE));
	public static final RegistryObject<Block> CEILING_INVISIBLE_SOUL_TORCH_ULTIMATE = CeilingTorch.BLOCKS.register("invisibletorches_soul_torch_ultimate", () -> new UltimateInvisibleCeilingTorchBlock(Block.Properties.copy(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME, initBlocks.SOUL_TORCH_ULTIMATE));
	public static final RegistryObject<BlockEntityType<InvisibleCeilingTorchBlockEntity>> CEILING_INVISIBLE_TORCH_BLOCK_ENTITY = CeilingTorch.BLOCK_ENTITIES.register("invisibletorches_ceiling_torch", () -> BlockEntityType.Builder.of(InvisibleCeilingTorchBlockEntity::new, CEILING_INVISIBLE_TORCH.get()).build(null));
	public static final RegistryObject<BlockEntityType<InvisibleCeilingSoulTorchBlockEntity>> CEILING_INVISIBLE_SOUL_TORCH_BLOCK_ENTITY = CeilingTorch.BLOCK_ENTITIES.register("invisibletorches_ceiling_soul_torch", () -> BlockEntityType.Builder.of(InvisibleCeilingSoulTorchBlockEntity::new, CEILING_INVISIBLE_SOUL_TORCH.get()).build(null));
	private Map<ResourceLocation, Block> placeEntries;

	public InvisibleTorchesCompat() {
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InvisibleTorchesCompatClient::addListeners);
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			placeEntries = ImmutableMap.of(
				getRegistryName(initItems.TORCH.get()), CEILING_INVISIBLE_TORCH.get(),
				getRegistryName(initItems.SOUL_TORCH.get()), CEILING_INVISIBLE_SOUL_TORCH.get(),
				getRegistryName(initItems.TORCH_ULTIMATE.get()), CEILING_INVISIBLE_TORCH_ULTIMATE.get(),
				getRegistryName(initItems.SOUL_TORCH_ULTIMATE.get()), CEILING_INVISIBLE_SOUL_TORCH_ULTIMATE.get()
			);
		}

		return placeEntries;
	}
}
