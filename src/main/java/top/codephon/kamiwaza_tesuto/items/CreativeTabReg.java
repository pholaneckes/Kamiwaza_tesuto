package top.codephon.kamiwaza_tesuto.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import top.codephon.kamiwaza_tesuto.KamiwazaTesuto;

import static top.codephon.kamiwaza_tesuto.items.ItemReg.*;

//这个与1.16.5注册的方式不同 创造模式物品栏成为了一个新的注册项
public class CreativeTabReg {
    //与物品注册大同小异 其它注册也是类似 如流体、方块、实体、容器等 后面MODID还是要换成自己的
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KamiwazaTesuto.MOD_ID);

    //添加创造模式物品栏 name:填自己（给这一分类）取的名称
    public static final RegistryObject<CreativeModeTab> KWP_GROUP_TAB = CREATIVE_MODE_TABS.register("kwp_tesuto_group", () -> CreativeModeTab.builder()
            //创建图标 模组物品为：物品字段名+.get() 原版的不用加.get()
            .icon(() -> kwpTestArmorHelmet.get().getDefaultInstance())
            //调整创造模式物品栏名称 否则为空白 在 语言.json文件里 可修改
            .title(Component.nullToEmpty("Tesuto"))
            //添加物品进该创造模式物品栏
            //格式：output.accept(物品字段名+.get());
            .displayItems((parameters, output) -> {
                output.accept(kwpTestArmorHelmet.get());
                output.accept(kwpTestArmorChestplate.get());
                output.accept(kwpTestArmorLeggings.get());
                output.accept(kwpTestArmorBoots.get());
            //构建
            }).build());
}
