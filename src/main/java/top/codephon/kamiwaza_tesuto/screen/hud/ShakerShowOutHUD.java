package top.codephon.kamiwaza_tesuto.screen.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.codephon.kamiwaza_tesuto.KamiwazaTesuto;

import static top.codephon.kamiwaza_tesuto.commands.KWPCommand.tesutoNum;

//仅客户端
@OnlyIn(Dist.CLIENT)
//实现 Renderable
public class ShakerShowOutHUD implements Renderable {
    //贴图
    private static final ResourceLocation TEX =
            new ResourceLocation(KamiwazaTesuto.MOD_ID,"textures/gui/tesuto_hud.png");
    //字体 modid, font下的字体文件名  这里借用数码宝贝DIGICODE的字体
    public static final ResourceLocation FONT_KONT = new ResourceLocation("kamiwaza_tesuto", "digimobs");

    private final Minecraft minecraft;
    public ShakerShowOutHUD(){
        this.minecraft = Minecraft.getInstance();
    }

    //用render方法渲染
    @Override
    public void render(GuiGraphics guiGraphics, int height, int width, float par) {
        //初始化
        guiGraphics.setColor(1,1,1,1);
        //添加贴图
        guiGraphics.blit(TEX,width-70,height-110,0,0,96,64,96,64);

        //添加渲染文字
        //以某个字体显示文本
        //的格式：Component.nullToEmpty("文本").copy().setStyle(Component.empty().getStyle().withFont(上述的字体))
        guiGraphics.drawString(this.minecraft.font, Component.nullToEmpty("" + tesutoNum).copy()
                        .setStyle(Component.empty().getStyle().withFont(FONT_KONT)),
                width-42,height-75,0Xff8c29);
    }
}
