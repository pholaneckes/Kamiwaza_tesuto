package top.codephon.kamiwaza_tesuto.screen.hud;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.codephon.kamiwaza_tesuto.entities.mins.Turbomin;
import top.codephon.kamiwaza_tesuto.items.ItemReg;

import java.util.Set;

import static top.codephon.kamiwaza_tesuto.commands.KWPCommand.getTesutoNum;
import static top.codephon.kamiwaza_tesuto.commands.KWPCommand.tesutoNum;

//渲染HUD事件
@Mod.EventBusSubscriber
public class RenderHUDEvent {
    @SubscribeEvent
    //仅客户端
    @OnlyIn(Dist.CLIENT)
    public static void onRenderGameOverlayPost(RenderGuiOverlayEvent event) {
        //获取一些必要参数（如宽 高）
        int width = event.getWindow().getGuiScaledWidth();
        int height = event.getWindow().getGuiScaledHeight();
        int halfWidth = width /2 ;
        int halfHeight = height /2;
        Minecraft minecraft = Minecraft.getInstance();
        GuiGraphics guiGraphics = event.getGuiGraphics();
        //上面和之前HUD没有区别
        //渲染的条件
        if (minecraft.player != null) {
            //获取玩家
            Player player = minecraft.player;
            //装备集合
            Set<Item> tesutoKWPArmor = new ObjectOpenHashSet<>();
//注释掉的代码不重要
//            if(tesutoNum == 0 && player.getCommandSenderWorld().getScoreboard().getObjective("tst_kwp") != null){
//                tesutoNum = getTesutoNum(player);}
            for (ItemStack stack : player.getArmorSlots()) {
                //当某个装备槽未装备装备时 不渲染HUD
                if(stack.isEmpty()){
                    return;
                }
                //添加装备的装备至「所装备的装备集合」里
                tesutoKWPArmor.add(stack.getItem());
            }
            //装备了正确的套装后并且骑在特定实体上时才会渲染HUD
            //player.getVehicle()是获取玩家骑的东西
            if(tesutoKWPArmor.containsAll(ObjectArrayList.of(
                    ItemReg.kwpTestArmorHelmet.get(),
                    ItemReg.kwpTestArmorLeggings.get(),
                    ItemReg.kwpTestArmorChestplate.get(),
                    ItemReg.kwpTestArmorBoots.get())) && player.getVehicle() instanceof Turbomin){
                ShakerShowOutHUD hud = new ShakerShowOutHUD();
                hud.render(guiGraphics,halfHeight,halfWidth,0);
            }
        }
    }
}
