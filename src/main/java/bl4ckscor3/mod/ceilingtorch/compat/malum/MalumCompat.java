package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.sammy.malum.MalumColors;
import com.sammy.malum.core.init.blocks.MalumBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MalumCompat implements ICeilingTorchCompat
{
	public static Block orangeEtherCeilingTorch;
	public static Block magentaEtherCeilingTorch;
	public static Block lightBlueEtherCeilingTorch;
	public static Block yellowEtherCeilingTorch;
	public static Block limeEtherCeilingTorch;
	public static Block pinkEtherCeilingTorch;
	public static Block cyanEtherCeilingTorch;
	public static Block purpleEtherCeilingTorch;
	public static Block blueEtherCeilingTorch;
	public static Block brownEtherCeilingTorch;
	public static Block greenEtherCeilingTorch;
	public static Block redEtherCeilingTorch;
	private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, CeilingTorch.MODID);
	public static final RegistryObject<TileEntityType<?>> ETHER_CEILING_TORCH = TILE_ENTITIES.register("ether_ceiling_torch", () -> TileEntityType.Builder.create(CeilingLightingTileEntity::new,
			orangeEtherCeilingTorch,
			magentaEtherCeilingTorch,
			lightBlueEtherCeilingTorch,
			yellowEtherCeilingTorch,
			limeEtherCeilingTorch,
			pinkEtherCeilingTorch,
			cyanEtherCeilingTorch,
			purpleEtherCeilingTorch,
			blueEtherCeilingTorch,
			brownEtherCeilingTorch,
			greenEtherCeilingTorch,
			redEtherCeilingTorch).build(null));
	private Map<ResourceLocation,Block> placeEntries;

	public MalumCompat()
	{
		TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(orangeEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.ORANGE_ETHER_TORCH), MalumColors.ORANGE) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.ORANGE_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_orange_ether_torch")));
		event.getRegistry().register(magentaEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.MAGENTA_ETHER_TORCH), MalumColors.MAGENTA) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.MAGENTA_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_magenta_ether_torch")));
		event.getRegistry().register(lightBlueEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.LIGHT_BLUE_ETHER_TORCH), MalumColors.LIGHT_BLUE) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.LIGHT_BLUE_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_light_blue_ether_torch")));
		event.getRegistry().register(yellowEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.YELLOW_ETHER_TORCH), MalumColors.YELLOW) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.YELLOW_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_yellow_ether_torch")));
		event.getRegistry().register(limeEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.LIME_ETHER_TORCH), MalumColors.LIME) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.LIME_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_lime_ether_torch")));
		event.getRegistry().register(pinkEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.PINK_ETHER_TORCH), MalumColors.PINK) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.PINK_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_pink_ether_torch")));
		event.getRegistry().register(cyanEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.CYAN_ETHER_TORCH), MalumColors.CYAN) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.CYAN_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_cyan_ether_torch")));
		event.getRegistry().register(purpleEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.PURPLE_ETHER_TORCH), MalumColors.PURPLE) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.PURPLE_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_purple_ether_torch")));
		event.getRegistry().register(blueEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.BLUE_ETHER_TORCH), MalumColors.BLUE) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.BLUE_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_blue_ether_torch")));
		event.getRegistry().register(brownEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.BROWN_ETHER_TORCH), MalumColors.BROWN) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.BROWN_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_brown_ether_torch")));
		event.getRegistry().register(greenEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.GREEN_ETHER_TORCH), MalumColors.GREEN) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.GREEN_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_green_ether_torch")));
		event.getRegistry().register(redEtherCeilingTorch = new CeilingEtherTorchBlock(MalumBlocks.RUNEWOOD_PROPERTIES().doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14)
				.lootFrom(MalumBlocks.RED_ETHER_TORCH), MalumColors.RED) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(MalumBlocks.RED_ETHER_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_red_ether_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(MalumBlocks.ORANGE_ETHER_TORCH.get().getRegistryName(), orangeEtherCeilingTorch)
					.put(MalumBlocks.MAGENTA_ETHER_TORCH.get().getRegistryName(), magentaEtherCeilingTorch)
					.put(MalumBlocks.LIGHT_BLUE_ETHER_TORCH.get().getRegistryName(), lightBlueEtherCeilingTorch)
					.put(MalumBlocks.YELLOW_ETHER_TORCH.get().getRegistryName(), yellowEtherCeilingTorch)
					.put(MalumBlocks.LIME_ETHER_TORCH.get().getRegistryName(), limeEtherCeilingTorch)
					.put(MalumBlocks.PINK_ETHER_TORCH.get().getRegistryName(), pinkEtherCeilingTorch)
					.put(MalumBlocks.CYAN_ETHER_TORCH.get().getRegistryName(), cyanEtherCeilingTorch)
					.put(MalumBlocks.PURPLE_ETHER_TORCH.get().getRegistryName(), purpleEtherCeilingTorch)
					.put(MalumBlocks.BLUE_ETHER_TORCH.get().getRegistryName(), blueEtherCeilingTorch)
					.put(MalumBlocks.BROWN_ETHER_TORCH.get().getRegistryName(), brownEtherCeilingTorch)
					.put(MalumBlocks.GREEN_ETHER_TORCH.get().getRegistryName(), greenEtherCeilingTorch)
					.put(MalumBlocks.RED_ETHER_TORCH.get().getRegistryName(), redEtherCeilingTorch).build();
		}

		return placeEntries;
	}
}
