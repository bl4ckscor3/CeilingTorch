package bl4ckscor3.mod.ceilingtorch.compat.extlights;

import java.util.function.Supplier;

import com.polyvalord.extlights.blocks.CeilingBlock;

import bl4ckscor3.mod.ceilingtorch.UpsideDownShape;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class UpsideDownGroundBlock extends CeilingBlock {
	private final Supplier<Block> originalBlock;
	private final VoxelShape shape;

	public UpsideDownGroundBlock(Properties properties, Supplier<Block> originalBlock, Supplier<VoxelShape> shapeSupplier) {
		super(properties.lootFrom(originalBlock));
		this.originalBlock = originalBlock;
		shape = UpsideDownShape.create(shapeSupplier.get());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
		return shape;
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		return new ItemStack(getOriginalBlock());
	}

	public Block getOriginalBlock() {
		return originalBlock.get();
	}
}
