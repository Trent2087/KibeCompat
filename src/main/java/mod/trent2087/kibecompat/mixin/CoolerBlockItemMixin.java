package mod.trent2087.kibecompat.mixin;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import io.github.lucaargolo.kibe.items.cooler.CoolerBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static dev.sapphic.beacons.BeaconMobEffects.NUTRITION;

@Mixin(value = CoolerBlockItem.class)
public class CoolerBlockItemMixin {

    @Redirect(method = "inventoryTick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;eatFood(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
        private ItemStack FixCooler(PlayerEntity entity, World world, ItemStack foodStack) {
            if(!entity.hasStatusEffect(NUTRITION)) {
                foodStack.finishUsing(world, entity);
            }
        return foodStack;
    }
}