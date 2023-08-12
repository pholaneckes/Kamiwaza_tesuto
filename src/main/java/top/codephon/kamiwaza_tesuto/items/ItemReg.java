package top.codephon.kamiwaza_tesuto.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.codephon.kamiwaza_tesuto.KamiwazaTesuto;

public class ItemReg {
    //与1.16.5没有区别 用于注册物品 最后的MOD_ID改成自己的
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KamiwazaTesuto.MOD_ID);

    //可选
    //自定义装备属性
    //尽量属性不要过于不平衡，可以参考原版的  *方法名仅作参考
    static ArmorMaterial createKWPTesutoArmor(int durabi, int def){
        //实现这个 new
        return new ArmorMaterial() {
            //定义装备的耐久
            @Override
            public int getDurabilityForType(ArmorItem.Type type) {
                return durabi;
            }

            //定义装备的护甲值 （生存/冒险模式显示的 护甲值图标数量）
            @Override
            public int getDefenseForType(ArmorItem.Type type) {
                return def;
            }

            //附魔亲和度
            //数值越高越容易附魔到更好的附魔项 附魔项条数也会更容易更多
            @Override
            public int getEnchantmentValue() {
                return 10;
            }

            //装备装备时所播放的声音（音效）
            @Override
            public SoundEvent getEquipSound() {
                //return SoundEvents.EMPTY; 是不播放声音
                return SoundEvents.ARMOR_EQUIP_LEATHER;
            }

            //定义可以用什么材料来修复这个装备
            //return返回 Ingredient.of(物品（模组的加.get）);
            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(Items.ANVIL);
            }

            //这个是和之前唯一不同的地方
            //原版样式的装备这里表示 modid:贴图名称
            //贴图一定要放在 modid/textures/models/armor/贴图名称_layer_1.png 装备上部
            //以及 modid/textures/models/armor/贴图名称_layer_2.png 装备下部
            //这个也可以用BlockBench来画
            @Override
            public String getName() {
                return "kamiwaza_tesuto:kwp_tesuto";
            }

            //盔甲韧性
            //参考 钻石甲0.0f 下届合金甲3.0f
            @Override
            public float getToughness() {
                return 2.0F;
            }

            //盔甲抗击退度 实际显示的是这里的数值×10
            //参考 钻石甲0.0f 下届合金甲0.1f
            @Override
            public float getKnockbackResistance() {
                return 0.05F;
            }
        };
    }

    //注册装备，分为 头盔HELMET 胸甲CHESTPLATE 护腿LEGGINGS 和 鞋子BOOTS 四个部位
    //格式：
    //public static final RegistryObject<ArmorItem> 装备字段 = ITEMS.register("注册名",
    //()->new ArmorItem(上面的createXXXArmor(耐久，护甲值),ArmorItem.Type.部位,new Item.Properties()));
    public static final RegistryObject<ArmorItem> kwpTestArmorHelmet = ITEMS.register("kwp_test_armor_helmet",
            () -> new ArmorItem(createKWPTesutoArmor(570,4), ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<ArmorItem> kwpTestArmorChestplate = ITEMS.register("kwp_test_armor_chestplate",
            () -> new ArmorItem(createKWPTesutoArmor(900, 7), ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<ArmorItem> kwpTestArmorLeggings = ITEMS.register("kwp_test_armor_leggings",
            () -> new ArmorItem(createKWPTesutoArmor(750,5), ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> kwpTestArmorBoots = ITEMS.register("kwp_test_armor_boots",
            () -> new ArmorItem(createKWPTesutoArmor(540,4), ArmorItem.Type.BOOTS, new Item.Properties()));
}
