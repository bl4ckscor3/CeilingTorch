package bl4ckscor3.mod.ceilingtorch.compat.dwarfcoal;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sora.dwarfcoal.init.ModBlocks;

public class DwarfCeilingTorchBlock extends CeilingTorchBlock
{
	public static final VoxelShape SHAPE = Block.makeCuboidShape(6.0D, 11.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private final boolean isStone;

	public DwarfCeilingTorchBlock(Properties properties, boolean isStone)
	{
		super(properties, ParticleTypes.FLAME);

		this.isStone = isStone;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.75D;
		double z = pos.getZ() + 0.5D;

		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public ResourceLocation getLootTable()
	{
		if(isStone)
			return ModBlocks.STONE_DWARF_TORCH.get().getLootTable();
		else return ModBlocks.DWARF_TORCH.get().getLootTable();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		if(isStone)
			return new ItemStack(ModBlocks.STONE_DWARF_TORCH.get());
		else return new ItemStack(ModBlocks.DWARF_TORCH.get());
	}
}
