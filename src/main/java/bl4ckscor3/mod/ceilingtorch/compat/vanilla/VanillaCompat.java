package bl4ckscor3.mod.ceilingtorch.compat.vanilla;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CeilingTorch.MODID)
public class VanillaCompat implements ICeilingTorchCompat
{
	public static final Block TORCH = null;
	public static final Block REDSTONE_TORCH = null;
	public static final Block SOUL_TORCH = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.func_235838_a_(state -> 14)
				.sound(SoundType.WOOD)
				.lootFrom(Blocks.TORCH),
				ParticleTypes.FLAME).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "torch")));
		event.getRegistry().register(new RedstoneCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.func_235838_a_(state -> 7)
				.sound(SoundType.WOOD)
				.lootFrom(Blocks.REDSTONE_TORCH)).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "redstone_torch")));
		event.getRegistry().register(new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.func_235838_a_(state -> 10)
				.sound(SoundType.WOOD)
				.lootFrom(Blocks.field_235339_cQ_),
				ParticleTypes.field_239811_B_).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "soul_torch")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(Items.TORCH.getRegistryName(), TORCH);
		PlaceHandler.registerPlaceEntry(Items.REDSTONE_TORCH.getRegistryName(), REDSTONE_TORCH);
		PlaceHandler.registerPlaceEntry(Items.field_234737_dp_.getRegistryName(), SOUL_TORCH);
	}
}
