package bl4ckscor3.mod.ceilingtorch.compat.tofucraft;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuMaterial;
import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.RegistryEvent.Register;

public class TofuCraftCompat implements ICeilingTorchCompat
{
	public static Block tofuCeilingTorchMomen;
	public static Block tofuCeilingTorchIshi;
	public static Block tofuCeilingTorchMetal;
	public static Block tofuCeilingTorchKinu;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		//momen
		event.getRegistry().register(tofuCeilingTorchMomen = new CeilingTorchBlock(Block.Properties.create(TofuMaterial.TOFU).hardnessAndResistance(0.0F, 0.5F).setLightLevel(state -> 14).doesNotBlockMovement().notSolid().sound(SoundType.SNOW), ParticleTypes.FLAME) {
			@Override
			public ResourceLocation getLootTable()
			{
				return TofuBlocks.TOFUTORCH_MOMEN.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(TofuBlocks.TOFUTORCH_MOMEN);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_momen")));
		//ishi
		event.getRegistry().register(tofuCeilingTorchIshi = new CeilingTorchBlock(Block.Properties.create(TofuMaterial.TOFU).hardnessAndResistance(0.0F, 6.0F).setLightLevel(state -> 14).doesNotBlockMovement().notSolid().sound(SoundType.STONE), ParticleTypes.FLAME) {
			@Override
			public ResourceLocation getLootTable()
			{
				return TofuBlocks.TOFUTORCH_ISHI.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(TofuBlocks.TOFUTORCH_ISHI);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_ishi")));
		//metal
		event.getRegistry().register(tofuCeilingTorchMetal = new CeilingTorchBlock(Block.Properties.create(TofuMaterial.TOFU).hardnessAndResistance(0.0F, 7.5F).setLightLevel(state -> 14).doesNotBlockMovement().notSolid().sound(SoundType.METAL), ParticleTypes.FLAME) {
			@Override
			public ResourceLocation getLootTable()
			{
				return TofuBlocks.TOFUTORCH_METAL.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(TofuBlocks.TOFUTORCH_METAL);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_metal")));
		//kinu
		event.getRegistry().register(tofuCeilingTorchKinu = new CeilingTorchBlock(Block.Properties.create(TofuMaterial.TOFU).hardnessAndResistance(0.0F, 0.5F).setLightLevel(state -> 14).doesNotBlockMovement().notSolid().sound(SoundType.SNOW), ParticleTypes.FLAME) {
			@Override
			public ResourceLocation getLootTable()
			{
				return TofuBlocks.TOFUTORCH_KINU.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(TofuBlocks.TOFUTORCH_KINU);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_kinu")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(TofuBlocks.TOFUTORCH_MOMEN.getRegistryName(), tofuCeilingTorchMomen,
					TofuBlocks.TOFUTORCH_ISHI.getRegistryName(), tofuCeilingTorchIshi,
					TofuBlocks.TOFUTORCH_METAL.getRegistryName(), tofuCeilingTorchMetal,
					TofuBlocks.TOFUTORCH_KINU.getRegistryName(), tofuCeilingTorchKinu);
		}

		return placeEntries;
	}
}
