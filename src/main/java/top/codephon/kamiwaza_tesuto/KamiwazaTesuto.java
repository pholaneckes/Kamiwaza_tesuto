package top.codephon.kamiwaza_tesuto;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.codephon.kamiwaza_tesuto.entities.EntityReg;
import top.codephon.kamiwaza_tesuto.items.CreativeTabReg;
import top.codephon.kamiwaza_tesuto.items.ItemReg;
import top.codephon.kamiwaza_tesuto.network.NetworkRegHandler;


//和1.16.5大同小异
//这个是主类 下面的 modid 换成自己的（与mods.toml里的保持一致）
@Mod("kamiwaza_tesuto")
@Mod.EventBusSubscriber(modid = "kamiwaza_tesuto")
public class KamiwazaTesuto {
    public static final String MOD_ID = "kamiwaza_tesuto";
    //模组输出
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public KamiwazaTesuto(){
        //Forge注册的总线
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //注册物品
        ItemReg.ITEMS.register(modEventBus);
        //注册创造模式物品栏
        CreativeTabReg.CREATIVE_MODE_TABS.register(modEventBus);
        //注册实体
        EntityReg.ENTITIES.register(modEventBus);
        //注册Common(服务器 客户端)的
        modEventBus.addListener(this::onCommomSetup);

        //注册事件
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onCommomSetup(FMLCommonSetupEvent event){
        //注册发包
        NetworkRegHandler.registerMessage();
    }
}
