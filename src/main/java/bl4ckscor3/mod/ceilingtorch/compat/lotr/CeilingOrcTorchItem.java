package bl4ckscor3.mod.ceilingtorch.compat.lotr;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;

public class CeilingOrcTorchItem extends BlockItem
{
	public CeilingOrcTorchItem(Item.Properties builder)
	{
		super(LotrCompat.ceilingOrcTorch, builder);
	}

	@Override
	protected boolean placeBlock(BlockItemUseContext ctx, BlockState state)
	{
		return super.placeBlock(ctx, state);
	}
}
