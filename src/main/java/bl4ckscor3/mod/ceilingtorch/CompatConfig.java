package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;
import java.util.function.Supplier;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import bl4ckscor3.mod.ceilingtorch.compat.additionallights.AdditionalLightsCompat;
import bl4ckscor3.mod.ceilingtorch.compat.adorn.AdornCompat;
import bl4ckscor3.mod.ceilingtorch.compat.bambooeverything.BambooEverythingCompat;
import bl4ckscor3.mod.ceilingtorch.compat.bonetorch.BoneTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.chipped.ChippedCompat;
import bl4ckscor3.mod.ceilingtorch.compat.gaiadimension.GaiaDimensionCompat;
import bl4ckscor3.mod.ceilingtorch.compat.integrateddynamics.IntegratedDynamicsCompat;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

public class CompatConfig {
	public static final String FILE_NAME = "ceiling-torch-integrations.toml";
	private static ForgeConfigSpec configSpec;
	private static CompatConfig config;
	private Map<BooleanValue, CompatInfo> builtInCompatList;

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
		builtInCompatList = Map.ofEntries(
				makeEntry(builder, "additional_lights", () -> AdditionalLightsCompat::new),
				makeEntry(builder, "adorn", () -> AdornCompat::new),
				makeEntry(builder, "bambooeverything", () -> BambooEverythingCompat::new),
				makeEntry(builder, "chipped", () -> ChippedCompat::new),
				makeEntry(builder, "bonetorch", () -> BoneTorchCompat::new),
				makeEntry(builder, "gaiadimension", () -> GaiaDimensionCompat::new),
				makeEntry(builder, "integrateddynamics", () -> IntegratedDynamicsCompat::new));
		//@formatter:on
	}

	private Map.Entry<BooleanValue, CompatInfo> makeEntry(ForgeConfigSpec.Builder builder, String modid, Supplier<Supplier<ICeilingTorchCompat>> ceilingTorchCompat) {
		return Map.entry(builder.define(modid, true), new CompatInfo(modid, ceilingTorchCompat));
	}

	private Map.Entry<BooleanValue, CompatInfo> makeEntry(ForgeConfigSpec.Builder builder, String modid, String comment, Supplier<Supplier<ICeilingTorchCompat>> ceilingTorchCompat) {
		return Map.entry(builder.comment(comment).define(modid, true), new CompatInfo(modid, ceilingTorchCompat));
	}

	public Map<BooleanValue, CompatInfo> getBuiltInCompatList() {
		return builtInCompatList;
	}

	public static CompatConfig getConfig() {
		return config;
	}

	public record CompatInfo(String modid, Supplier<Supplier<ICeilingTorchCompat>> ceilingTorchCompat) {}
}
