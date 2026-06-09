package dev.matthiesen.pc_on_a_stick;

import dev.matthiesen.pc_on_a_stick.registry.CreativeTabRegistry;
import dev.matthiesen.pc_on_a_stick.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(Constants.MOD_ID)
public final class PcOnAStick {
    public PcOnAStick(IEventBus modBus) {
        ItemRegistry.init();
        CreativeTabRegistry.init();
        modBus.addListener(this::addCreative);
        Constants.createInfoLog("PC on a Stick has been initialized!");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ItemRegistry.PC_ON_A_STICK.get());
        }
    }
}
