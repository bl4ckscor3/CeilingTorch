package bl4ckscor3.mod.ceilingtorch.compat.dwarfcoal;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.RegistryEvent;
import sora.dwarfcoal.init.ModBlocks;

public class DwarfCoalCompat implements ICeilingTorchCompat
{
	public static Block dwarfCeilingTorch;
	public static Block stoneDwarfCeilingTorch;
	public static Block stoneCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(dwarfCeilingTorch = new DwarfCeilingTorchBlock(Block.Properties.from(Blocks.TORCH).setLightLevel(state -> 10), false).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "dwarfcoal_dwarf_torch")));
		event.getRegistry().register(stoneDwarfCeilingTorch = new DwarfCeilingTorchBlock(Block.Properties.from(Blocks.TORCH).setLightLevel(state -> 10), true).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "dwarfcoal_stone_dwarf_torch")));
		event.getRegistry().register(stoneCeilingTorch = new CeilingTorchBlock(Block.Properties.from(Blocks.TORCH).setLightLevel(state -> 14), ParticleTypes.FLAME) {
			@Override
			public ResourceLocation getLootTable()
			{
				return ModBlocks.STONE_TORCH.get().getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(ModBlocks.STONE_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "dwarfcoal_stone_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(Item.getItemFromBlock(ModBlocks.DWARF_TORCH.get()).getRegistryName(), dwarfCeilingTorch,
					Item.getItemFromBlock(ModBlocks.STONE_DWARF_TORCH.get()).getRegistryName(), stoneDwarfCeilingTorch,
					Item.getItemFromBlock(ModBlocks.STONE_TORCH.get()).getRegistryName(), stoneCeilingTorch);
		}

		return placeEntries;
	}
}
