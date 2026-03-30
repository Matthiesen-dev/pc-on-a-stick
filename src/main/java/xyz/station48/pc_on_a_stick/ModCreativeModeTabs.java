package xyz.station48.pc_on_a_stick;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PcOnAStick.MOD_ID);

    public static final Supplier<CreativeModeTab> PC_ON_A_STICK_TAB = CREATIVE_MODE_TAB.register("pc_on_a_stick_items_tab",
            () -> CreativeModeTab
                    .builder()
                    .icon(() -> new ItemStack(ModItems.PC_ON_A_STICK.get()))
                    .title(Component.translatable("creativetab.pc_on_a_stick.items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PC_ON_A_STICK);
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
