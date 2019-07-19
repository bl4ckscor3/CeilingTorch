package bl4ckscor3.mod.ceilingtorch.compat.bonetorch;

import java.util.Random;

import com.builtbroken.bonetorch.BoneTorchMod;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.BlockCeilingTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockCeilingBoneTorch extends BlockCeilingTorch
{
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(BoneTorchMod.blockTorch);
	}
}
