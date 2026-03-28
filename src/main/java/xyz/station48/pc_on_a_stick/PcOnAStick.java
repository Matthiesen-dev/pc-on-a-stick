package xyz.station48.pc_on_a_stick;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import xyz.station48.pc_on_a_stick.items.ModCreativeModeTabs;
import xyz.station48.pc_on_a_stick.items.ModItems;

@Mod(PcOnAStick.MOD_ID)
public final class PcOnAStick {
    public static final String MOD_ID = "pc_on_a_stick";

    public PcOnAStick(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::addCreative);
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.PC_ON_A_STICK);
        }
    }
}
