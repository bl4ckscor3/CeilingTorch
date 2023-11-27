package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;
import java.util.function.Supplier;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import bl4ckscor3.mod.ceilingtorch.compat.additionallights.AdditionalLightsCompat;
import bl4ckscor3.mod.ceilingtorch.compat.adorn.AdornCompat;
import bl4ckscor3.mod.ceilingtorch.compat.aquatictorches.AquaticTorchesCompat;
import bl4ckscor3.mod.ceilingtorch.compat.bambooeverything.BambooEverythingCompat;
import bl4ckscor3.mod.ceilingtorch.compat.bonetorch.BoneTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.chipped.ChippedCompat;
import bl4ckscor3.mod.ceilingtorch.compat.gaiadimension.GaiaDimensionCompat;
import bl4ckscor3.mod.ceilingtorch.compat.hardcoretorches.HardcoreTorchesCompat;
import bl4ckscor3.mod.ceilingtorch.compat.ilikewood.ILikeWoodCompat;
import bl4ckscor3.mod.ceilingtorch.compat.integrateddynamics.IntegratedDynamicsCompat;
import bl4ckscor3.mod.ceilingtorch.compat.magicaltorches.MagicalTorchesCompat;
import bl4ckscor3.mod.ceilingtorch.compat.moshiz.MoShizCompat;
import bl4ckscor3.mod.ceilingtorch.compat.nethersdelight.NethersDelightCompat;
import bl4ckscor3.mod.ceilingtorch.compat.occultism.OccultismCompat;
import bl4ckscor3.mod.ceilingtorch.compat.pokecubeaio.PokecubeAIOCompat;
import bl4ckscor3.mod.ceilingtorch.compat.reliquary.ReliquaryCompat;
import bl4ckscor3.mod.ceilingtorch.compat.silentgear.SilentGearCompat;
import bl4ckscor3.mod.ceilingtorch.compat.tofucraft.TofuCraftCompat;
import bl4ckscor3.mod.ceilingtorch.compat.torchbandolier.TorchBandolierCompat;
import bl4ckscor3.mod.ceilingtorch.compat.torchmaster.TorchmasterCompat;
import bl4ckscor3.mod.ceilingtorch.compat.undergarden.UndergardenCompat;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

public class CompatConfig {
	public static final String FILE_NAME = "ceiling-torch-integrations.toml";
	private static ForgeConfigSpec configSpec;
	private static CompatConfig config;
	private Map<String, CompatInfo> builtInCompat;

	public static void init(ModLoadingContext ctx) {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		CommentedFileConfig fileConfig;

		config = new CompatConfig(builder);
		configSpec = builder.build();
		//@formatter:off
		fileConfig = CommentedFileConfig
				.builder(FMLPaths.CONFIGDIR.get().resolve(FILE_NAME))
				.preserveInsertionOrder()
				.writingMode(WritingMode.REPLACE)
				.build();
		//@formatter:on
		fileConfig.load();
		fileConfig.save();
		configSpec.setConfig(fileConfig);
		ctx.registerConfig(ModConfig.Type.COMMON, configSpec, FILE_NAME);
	}

	CompatConfig(ForgeConfigSpec.Builder builder) {
		//@formatter:off
		builder.comment(
				"This configuration is meant to allow turning off mod integration that has stopped working. All integrations are enabled by default. To disable a specific integration, set the relevant config value to \"false\".",
				"Should you encounter any crashes when using Ceiling Torch, please report them to https://github.com/bl4ckscor3/CeilingTorch/issues",
				"If you turn off a mod integration using this config, you can continue playing without needing an update of Ceiling Torch; However do note that the ceiling torches from that integration will disappear from your world. Note, that if you do not place a block in a space where a ceiling torch was, and then re-enable the respective integration, the torch will reappear.",
				"Turning off integration with a mod that you are not using will not have any effect, as Ceiling Torch automatically checks for the presence of mods it integrates with.");
		builtInCompat = Map.ofEntries(
				makeEntry(builder, "additional_lights", () -> AdditionalLightsCompat::new),
				makeEntry(builder, "adorn", () -> AdornCompat::new),
				makeEntry(builder, "aquatictorches", () -> AquaticTorchesCompat::new),
				makeEntry(builder, "bambooeverything", () -> BambooEverythingCompat::new),
				makeEntry(builder, "chipped", () -> ChippedCompat::new),
				makeEntry(builder, "bonetorch", () -> BoneTorchCompat::new),
				makeEntry(builder, "gaiadimension", () -> GaiaDimensionCompat::new),
				makeEntry(builder, "hardcore_torches", () -> HardcoreTorchesCompat::new),
				makeEntry(builder, "ilikewood", () -> ILikeWoodCompat::new),
				makeEntry(builder, "integrateddynamics", () -> IntegratedDynamicsCompat::new),
				makeEntry(builder, "magical_torches", () -> MagicalTorchesCompat::new),
				makeEntry(builder, "ms", "Mo' Shiz Mod", () -> MoShizCompat::new),
				makeEntry(builder, "nethersdelight", () -> NethersDelightCompat::new),
				makeEntry(builder, "pokecube_legends", () -> PokecubeAIOCompat::new),
				makeEntry(builder, "occultism", () -> OccultismCompat::new),
				makeEntry(builder, "reliquary", () -> ReliquaryCompat::new),
				makeEntry(builder, "silentgear", () -> SilentGearCompat::new),
				makeEntry(builder, "tofucraft", () -> TofuCraftCompat::new),
				makeEntry(builder, "torchbandolier", () -> TorchBandolierCompat::new),
				makeEntry(builder, "torchmaster", () -> TorchmasterCompat::new),
				makeEntry(builder, "undergarden", () -> UndergardenCompat::new));
		//@formatter:on
	}

	private Map.Entry<String, CompatInfo> makeEntry(ForgeConfigSpec.Builder builder, String modid, Supplier<Supplier<ICeilingTorchCompat>> ceilingTorchCompat) {
		return Map.entry(modid, new CompatInfo(builder.define(modid, true), ceilingTorchCompat));
	}

	private Map.Entry<String, CompatInfo> makeEntry(ForgeConfigSpec.Builder builder, String modid, String comment, Supplier<Supplier<ICeilingTorchCompat>> ceilingTorchCompat) {
		return Map.entry(modid, new CompatInfo(builder.comment(comment).define(modid, true), ceilingTorchCompat));
	}

	public Map<String, CompatInfo> getBuiltInCompat() {
		return builtInCompat;
	}

	public static CompatConfig getConfig() {
		return config;
	}

	public record CompatInfo(BooleanValue config, Supplier<Supplier<ICeilingTorchCompat>> ceilingTorchCompat) {}
}
