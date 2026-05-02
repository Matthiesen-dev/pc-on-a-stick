package dev.matthiesen.pc_on_a_stick.items;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.storage.pc.PCStore;
import com.cobblemon.mod.common.api.storage.pc.link.PCLink;
import com.cobblemon.mod.common.api.storage.pc.link.PCLinkManager;
import com.cobblemon.mod.common.net.messages.client.storage.pc.OpenPCPacket;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import com.ofekn.crafting_on_a_stick.api.IWheelItem;
import com.ofekn.crafting_on_a_stick.api.Ref;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class PcOnAStickItem extends Item implements IWheelItem {
    public PcOnAStickItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        openPCStorage(player);
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide);
    }

    public void onWheelAction(Player player, Ref<ItemStack> ref) {
        openPCStorage(player);
    }

    public static void openPCStorage(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            // Check if player is in Battle
            if(PlayerExtensionsKt.isInBattle(serverPlayer)) {
                player.sendSystemMessage(Component.translatable("cobblemon.pc.inbattle").withStyle(ChatFormatting.RED));
                return;
            }
            // Not in a Battle? Open PC
            PCStore pc = Cobblemon.INSTANCE.getStorage().getPC(serverPlayer);
            PCLinkManager.INSTANCE.addLink(new PCLink(pc, player.getUUID()));
            player.level().playSound(null, player.blockPosition(), CobblemonSounds.PC_ON, SoundSource.NEUTRAL, 0.5f, 1.0f);
            new OpenPCPacket(pc).sendToPlayer(serverPlayer);
        }
    }
}
