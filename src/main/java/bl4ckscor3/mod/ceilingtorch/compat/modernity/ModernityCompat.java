package bl4ckscor3.mod.ceilingtorch.compat.modernity;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import modernity.common.block.MDBlockStateProperties;
import modernity.common.block.MDBuildingBlocks;
import modernity.common.block.fluid.WaterlogType;
import modernity.common.block.fluid.WaterloggedBlock;
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
	public static Block luminositeCeilingTorch;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(extinguishedAnthraciteCeilingTorch = new ModernityCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS, MaterialColor.SNOW).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.WOOD), false) {
			@Override
			public ResourceLocation getLootTable()
			{
				return MDBuildingBlocks.EXTINGUISHED_ANTHRACITE_TORCH.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MDBuildingBlocks.EXTINGUISHED_ANTHRACITE_TORCH);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "modernity_extinguished_anthracite_torch")));
		event.getRegistry().register(anthraciteCeilingTorch = new ModernityExtinguishableCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS, MaterialColor.SNOW).doesNotBlockMovement().hardnessAndResistance(0).lightValue(15).sound(SoundType.WOOD), true, extinguishedAnthraciteCeilingTorch) {
			@Override
			public ResourceLocation getLootTable()
			{
				return MDBuildingBlocks.ANTHRACITE_TORCH.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MDBuildingBlocks.ANTHRACITE_TORCH);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "modernity_anthracite_torch")));
		event.getRegistry().register(luminositeCeilingTorch = new ModernityCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS, MaterialColor.SNOW).doesNotBlockMovement().hardnessAndResistance(0).lightValue(15).sound(SoundType.WOOD), false) {
			@Override
			public ResourceLocation getLootTable()
			{
				return MDBuildingBlocks.LUMINOSITE_TORCH.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MDBuildingBlocks.LUMINOSITE_TORCH);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "modernity_luminosite_torch")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(MDBuildingBlocks.EXTINGUISHED_ANTHRACITE_TORCH.getRegistryName(), extinguishedAnthraciteCeilingTorch);
		PlaceHandler.registerPlaceEntry(MDBuildingBlocks.ANTHRACITE_TORCH.getRegistryName(), anthraciteCeilingTorch);
		PlaceHandler.registerPlaceEntry(MDBuildingBlocks.LUMINOSITE_TORCH.getRegistryName(), luminositeCeilingTorch);
	}

	public static void handlePlacement(RightClickBlock event, ItemStack held, Block block, World world, BlockPos pos, BlockPos placeAt, Direction face)
	{
		if(face == Direction.DOWN && Block.hasEnoughSolidSide(world, pos, Direction.DOWN))
		{
			IFluidState fluidState = world.getFluidState(placeAt);
			boolean air = world.isAirBlock(placeAt);
			WaterlogType waterlogType = WaterlogType.getType(fluidState);
			boolean water = waterlogType != WaterlogType.NONE;

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
