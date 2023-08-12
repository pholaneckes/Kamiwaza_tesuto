package top.codephon.kamiwaza_tesuto.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import top.codephon.kamiwaza_tesuto.KamiwazaTesuto;

public class NetworkRegHandler {
    //包SimpleChannel字段
    public static SimpleChannel CHANNEL;
    //包的版本，越个性越好
    private static final String VERSION = "0_KWP";
    //包号
    private static int ID = 0;
    //每发个包 包号+1， 防止包冲突
    public static int nextID(){
        return ID++;
    }
    //包 注册
    public static void registerMessage(){
        //除了 MODID 和 后面的"包注册名"之外 照抄
        CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(KamiwazaTesuto.MOD_ID,"kamiwazing"),
                ()->VERSION,
                (version)-> version.equals(VERSION),
                (version)-> version.equals(VERSION));
        CHANNEL.messageBuilder(SendPack.class,nextID())
                .encoder(SendPack::toBytes)
                .decoder(SendPack::new)
                .consumerNetworkThread(SendPack::handler)
                .add();
    }
}
