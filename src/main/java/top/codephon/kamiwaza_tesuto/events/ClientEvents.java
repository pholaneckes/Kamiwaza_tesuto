package top.codephon.kamiwaza_tesuto.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.codephon.kamiwaza_tesuto.entities.EntityReg;
import top.codephon.kamiwaza_tesuto.entities.render.MinionRender;

//实体渲染的注册
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityReg.turboMin.get(), MinionRender::new);
    }
}
