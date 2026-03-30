package xyz.station48.pc_on_a_stick;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(PcOnAStick.MOD_ID)
public final class PcOnAStick {
    public static final String MOD_ID = "pc_on_a_stick";

    public PcOnAStick(IEventBus eventBus) {
        eventBus.addListener(this::addCreative);
        ModCreativeModeTabs.register(eventBus);
        ModItems.register(eventBus);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.PC_ON_A_STICK);
        }
    }
}
