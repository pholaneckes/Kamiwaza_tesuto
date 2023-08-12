package top.codephon.kamiwaza_tesuto.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.codephon.kamiwaza_tesuto.entities.EntityReg;
import top.codephon.kamiwaza_tesuto.entities.mins.Turbomin;

//注册创建实体属性
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvents {
    @SubscribeEvent
    public static void setupMCAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityReg.turboMin.get(), Turbomin.createAttributes().build());
    }
}
