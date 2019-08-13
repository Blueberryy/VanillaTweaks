package io.github.strikerrocker.vt.enchantments;

import com.google.common.collect.Maps;
import io.github.strikerrocker.vt.base.Feature;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Tuple;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EnchantmentFeature extends Feature {
    public static Object2BooleanOpenHashMap<Enchantment> enchantmentsToEnable = new Object2BooleanOpenHashMap<>();
    private static Map<Enchantment, Tuple<String, String>> enchantments = Maps.newHashMap();

    static {
        enchantments.put(new BlazingEnchantment("blazing"), new Tuple<>("blazing", "Want to smelt things when you mine them?"));
        enchantments.put(new HopsEnchantment("hops"), new Tuple<>("hops", "Want to jump more than a block high with an enchantment?"));
        enchantments.put(new NimbleEnchantment("nimble"), new Tuple<>("nimble", "Want more speed with an enchantment?"));
        enchantments.put(new SiphonEnchantment("siphon"), new Tuple<>("siphon", "Don't want the zombies stealing your items when you are mining?"));
        enchantments.put(new VeteranEnchantment("veteran"), new Tuple<>("veteran", "Want all the experience in the nearby area?"));
        enchantments.put(new VigorEnchantment("vigor"), new Tuple<>("vigor", "Want more health with an enchant?"));
        enchantments.put(new HomingEnchantment("homing"), new Tuple<>("homing", "Don't want to aim but love shooting arrows?"));
        enchantments.forEach((enchantment, idDesc) -> MinecraftForge.EVENT_BUS.register(enchantment));
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public void registerEnchantments(RegistryEvent.Register<Enchantment> registryEvent) {
            enchantmentsToEnable.forEach((enchantment, toEnable) -> {
                if (toEnable) registryEvent.getRegistry().register(enchantment);
            });
        }
    }
}