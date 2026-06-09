package dev.matthiesen.pc_on_a_stick.registry;

import dev.matthiesen.common.matthiesen_lib.registry.AbstractCreativeModeTabRegistry;
import dev.matthiesen.pc_on_a_stick.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class CreativeTabRegistry extends AbstractCreativeModeTabRegistry {
    private static final CreativeTabRegistry INSTANCE = new CreativeTabRegistry();

    private CreativeTabRegistry() {
        super(Constants.MOD_ID);
    }

    public static final Supplier<CreativeModeTab> PC_ON_A_STICK_TAB;

    static {
        PC_ON_A_STICK_TAB = INSTANCE.register("pc_on_a_stick_items_tab", () -> INSTANCE.getRegistryBuilder()
                .newCreativeTabBuilder()
                .title(Component.translatable("creativetab.pc_on_a_stick.items"))
                .icon(() -> new ItemStack(ItemRegistry.PC_ON_A_STICK.get()))
                .displayItems((itemDisplayParameters, output) -> output.accept(ItemRegistry.PC_ON_A_STICK.get()))
                .build());
    }

    public static void init() {
        Constants.createInfoLog("Registering creative tabs for PC on a Stick...");
    }
}
