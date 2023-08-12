package top.codephon.kamiwaza_tesuto.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

import static top.codephon.kamiwaza_tesuto.commands.KWPCommand.tesutoNum;

//类名称不建议更变
public class SendPack {
    //可能用到的发包数据类型 支持的类型不多 不建议重复的类型添加多个，容易窜
    private final UUID uuid;
    private final int integer;
    private final short type;
    private final boolean boo;
    private final String text;

    //每有一个变量，就要为该类型写一行 这个是读取 变量 = buf.read变量类型(); 其中String是readUtf()
    public SendPack(FriendlyByteBuf buf) {
        uuid = buf.readUUID();
        integer = buf.readInt();
        type = buf.readShort();
        boo = buf.readBoolean();
        text = buf.readUtf();
    }

    //各种接包的方法 每个参数（类型及数量）都不同 用不到的类型随便填点值，但不可以不填或者填null
    public SendPack(UUID uuid,int integer, short type) {
        this.uuid = uuid;
        this.integer = integer;
        this.type = type;
        this.boo = false;
        this.text = "none";
    }

    //每有一个变量，就要为该类型写一行 这个是写入 buf.write类型(this.其变量);
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(this.uuid);
        buf.writeInt(this.integer);
        buf.writeShort(this.type);
        buf.writeBoolean(this.boo);
        buf.writeUtf(this.text);
    }

    //接收包 以包编号判断应该执行哪个方法
    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if(type == 1000) {
                updateNumber(integer);
            }
        });
        ctx.get().setPacketHandled(true);
    }

    //最简单的发包同步（直接写上面也可以，但不建议）
    private static void updateNumber(int i){
        tesutoNum = i;
    }
}
