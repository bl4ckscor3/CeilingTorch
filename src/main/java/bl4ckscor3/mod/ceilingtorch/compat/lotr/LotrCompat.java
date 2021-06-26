package bl4ckscor3.mod.ceilingtorch.compat.lotr;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import lotr.common.init.LOTRBlocks;
import lotr.common.init.LOTRItems;
import lotr.common.init.LOTRParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

public class LotrCompat implements ICeilingTorchCompat
{
	public static Block ceilingBlubberTorch;
	public static Block ceilingBlueMallornTorch;
	public static Block ceilingDwarvenTorch;
	public static Block ceilingGoldMallornTorch;
	public static Block ceilingGreenMallornTorch;
	public static Block ceilingHighElvenTorch;
	public static Block ceilingOrcTorch;
	public static Block ceilingSilverMallonTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		//blubber torch
		event.getRegistry().register(ceilingBlubberTorch = new LotrCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.lightValue(12)
				.sound(SoundType.WOOD),
				LOTRBlocks.BLUBBER_TORCH, () -> ParticleTypes.SMOKE, () -> ParticleTypes.FLAME).setRegistryName("lotr_blubber_torch"));
		//blue mallorn torch
		event.getRegistry().register(ceilingBlueMallornTorch = new LotrCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.lightValue(14)
				.sound(SoundType.WOOD),
				LOTRBlocks.BLUE_MALLORN_TORCH, LOTRParticles.BLUE_ELVEN_GLOW).setRegistryName("lotr_blue_mallorn_torch"));
		//dwarven torch
		event.getRegistry().register(ceilingDwarvenTorch = new LotrCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.lightValue(14)
				.sound(SoundType.WOOD),
				LOTRBlocks.DWARVEN_TORCH, () -> ParticleTypes.SMOKE, () -> ParticleTypes.FLAME).setRegistryName("lotr_dwarven_torch"));
		//gold mallorn torch
		event.getRegistry().register(ceilingGoldMallornTorch = new LotrCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.lightValue(14)
				.sound(SoundType.WOOD),
				LOTRBlocks.GOLD_MALLORN_TORCH, LOTRParticles.GOLD_ELVEN_GLOW).setRegistryName("lotr_gold_mallorn_torch"));
		//green mallorn torch
		event.getRegistry().register(ceilingGreenMallornTorch = new LotrCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.lightValue(14)
				.sound(SoundType.WOOD),
				LOTRBlocks.GREEN_MALLORN_TORCH, LOTRParticles.GREEN_ELVEN_GLOW).setRegistryName("lotr_green_mallorn_torch"));
		//high elven torch
		event.getRegistry().register(ceilingHighElvenTorch = new LotrCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.lightValue(14)
				.sound(SoundType.WOOD),
				LOTRBlocks.HIGH_ELVEN_TORCH, LOTRParticles.BLUE_ELVEN_GLOW).setRegistryName("lotr_high_elven_torch"));
		//orc torch
		event.getRegistry().register(ceilingOrcTorch = new CeilingOrcTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.lightValue(14)
				.sound(SoundType.WOOD))
				.setRegistryName("lotr_orc_torch"));
		//silver mallorn torch
		event.getRegistry().register(ceilingSilverMallonTorch = new LotrCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.lightValue(14)
				.sound(SoundType.WOOD),
				LOTRBlocks.SILVER_MALLORN_TORCH, LOTRParticles.SILVER_ELVEN_GLOW).setRegistryName("lotr_silver_mallorn_torch"));
	}

	@Override
	public void onPlace(RightClickBlock event, BlockPos placeAt, BlockState state)
	{
		if(state.getBlock() == ceilingOrcTorch)
			event.getWorld().setBlockState(placeAt.down(), ceilingOrcTorch.getDefaultState().with(CeilingOrcTorchBlock.HALF, DoubleBlockHalf.LOWER), 27);
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(LOTRItems.BLUBBER_TORCH.get().getRegistryName(), ceilingBlubberTorch)
					.put(LOTRItems.BLUE_MALLORN_TORCH.get().getRegistryName(), ceilingBlueMallornTorch)
					.put(LOTRItems.DWARVEN_TORCH.get().getRegistryName(), ceilingDwarvenTorch)
					.put(LOTRItems.GOLD_MALLORN_TORCH.get().getRegistryName(), ceilingGoldMallornTorch)
					.put(LOTRItems.GREEN_MALLORN_TORCH.get().getRegistryName(), ceilingGreenMallornTorch)
					.put(LOTRItems.HIGH_ELVEN_TORCH.get().getRegistryName(), ceilingHighElvenTorch)
					.put(LOTRItems.ORC_TORCH.get().getRegistryName(), ceilingOrcTorch)
					.put(LOTRItems.SILVER_MALLORN_TORCH.get().getRegistryName(), ceilingSilverMallonTorch)
					.build();
		}

		return placeEntries;
	}
}
