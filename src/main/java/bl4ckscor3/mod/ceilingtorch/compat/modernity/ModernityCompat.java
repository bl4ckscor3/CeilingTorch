package bl4ckscor3.mod.ceilingtorch.compat.modernity;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import modernity.api.util.EWaterlogType;
import modernity.common.block.MDBlockStateProperties;
import modernity.common.block.MDBlocks;
import modernity.common.block.base.WaterloggedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

public class ModernityCompat implements ICeilingTorchCompat
{
	public static Block extinguishedAnthraciteCeilingTorch;
	public static Block anthraciteCeilingTorch;
	public static Block lightrockCeilingTorch;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(extinguishedAnthraciteCeilingTorch = new ModernityCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS, MaterialColor.SNOW).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.WOOD), false) {
			@Override
			public ResourceLocation getLootTable()
			{
				return MDBlocks.EXTINGUISHED_ANTHRACITE_TORCH.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MDBlocks.EXTINGUISHED_ANTHRACITE_TORCH);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "modernity_extinguished_anthracite_torch")));
		event.getRegistry().register(anthraciteCeilingTorch = new ModernityExtinguishableCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS, MaterialColor.SNOW).doesNotBlockMovement().hardnessAndResistance(0).lightValue(15).sound(SoundType.WOOD), true, extinguishedAnthraciteCeilingTorch) {
			@Override
			public ResourceLocation getLootTable()
			{
				return MDBlocks.ANTHRACITE_TORCH.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MDBlocks.ANTHRACITE_TORCH);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "modernity_anthracite_torch")));
		event.getRegistry().register(lightrockCeilingTorch = new ModernityCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS, MaterialColor.SNOW).doesNotBlockMovement().hardnessAndResistance(0).lightValue(15).sound(SoundType.WOOD), false) {
			@Override
			public ResourceLocation getLootTable()
			{
				return MDBlocks.LIGHTROCK_TORCH.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MDBlocks.LIGHTROCK_TORCH);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "modernity_lightrock_torch")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(MDBlocks.EXTINGUISHED_ANTHRACITE_TORCH.getRegistryName(), extinguishedAnthraciteCeilingTorch);
		PlaceHandler.registerPlaceEntry(MDBlocks.ANTHRACITE_TORCH.getRegistryName(), anthraciteCeilingTorch);
		PlaceHandler.registerPlaceEntry(MDBlocks.LIGHTROCK_TORCH.getRegistryName(), lightrockCeilingTorch);
	}

	public static void handlePlacement(RightClickBlock event, ItemStack held, Block block, World world, BlockPos pos, BlockPos placeAt, Direction face)
	{
		if(face == Direction.DOWN && Block.hasEnoughSolidSide(world, pos, Direction.DOWN))
		{
			IFluidState fluidState = world.getFluidState(placeAt);
			boolean air = world.isAirBlock(placeAt);
			EWaterlogType waterlogType = EWaterlogType.getType(fluidState);
			boolean water = waterlogType != EWaterlogType.NONE;

			if(!air && !water)
				return;

			BlockState state = block.getDefaultState();

			if(block instanceof WaterloggedBlock) //modernity waterlogged
				state = state.with(MDBlockStateProperties.WATERLOGGED, waterlogType);
			else if(block instanceof IWaterLoggable) //vanilla waterlogged
				state = state.with(BlockStateProperties.WATERLOGGED, water);

			PlaceHandler.placeTorch(event, held, block, pos, placeAt, world, state);
		}
	}
}
