package top.codephon.kamiwaza_tesuto.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import top.codephon.kamiwaza_tesuto.network.NetworkRegHandler;
import top.codephon.kamiwaza_tesuto.network.SendPack;

@Mod.EventBusSubscriber
public class KWPCommand {
    public static int tesutoNum = 0;

    //计分板部分 get是获取 set是设置为..
    //自动创建 只需要更改 "tst_kwp" 为其它名称，其余可以照搬
    public static int getTesutoNum(Player player){
        //获取玩家计分板tst_kwp的值
        return player.getScoreboard().getOrCreatePlayerScore(player.getScoreboardName(),
                player.getCommandSenderWorld().getScoreboard().getObjective("tst_kwp")).getScore();
    }

    //参数：玩家，修改的值
    public static void setTesutoNum(Player player, int val) {
        //获取存档所有计分板
        Scoreboard sb = player.getCommandSenderWorld().getScoreboard();
        //获取名为tst_kwp的计分板项
        Objective sbObj = sb.getObjective("tst_kwp");
        //没有时自动创建一个
        if(sbObj == null){
            sbObj = sb.addObjective("tst_kwp",ObjectiveCriteria.DUMMY,Component.nullToEmpty("tst_kwp"),
                    ObjectiveCriteria.RenderType.INTEGER);
        }
        //设置计分板项值为..
        sb.getOrCreatePlayerScore(player.getScoreboardName(),sbObj).setScore(val);
    }

    @SubscribeEvent
    //注册命令的事件
    public static void registerCommands(RegisterCommandsEvent event) {
        //命令的名称
        event.getDispatcher().register(LiteralArgumentBuilder.<CommandSourceStack>literal("kwp")
                //2级管理员权限
                .requires((req)->req.hasPermission(2))
                .then(Commands.literal("tesuto_add")
                        //添加一个int参数
                        .then(Commands.argument("count",IntegerArgumentType.integer())
                        .executes(arg ->{
                            //获取该int类型的参数
                            int count = IntegerArgumentType.getInteger(arg.copyFor(arg.getSource()),"count");
                            //判断执行命令的是玩家而不是其它东西（如命令方块，服务器控制台，被execute run的实体）
                            if(arg.getSource().getEntity() instanceof Player player) {
                                //获取计分板值
                            if(tesutoNum == 0 && player.getCommandSenderWorld().getScoreboard()
                                                        .getObjective("tst_kwp") != null){
                                tesutoNum = getTesutoNum(player);
                            }
                            //使这个变量加参数的值
                            tesutoNum += count;
                            //向客户端发包，以同步这个值
                                //还是用之前那个发包类
                                NetworkRegHandler.CHANNEL.send(
                                        PacketDistributor.PLAYER.with(
                                                ()->(ServerPlayer) player
                                ),
                    //这个包也是自定义的  这里传的是 玩家唯一码，修改结果，包编号
                    //(包若以编号区分的话，建议用short，同一种类型最好不要用两次容易会窜;整数类型中short最没用，所以"浪费"一个short来表示包编号)
                                        new SendPack(arg.getSource().getEntity().getUUID(), tesutoNum, (short) 1000));
                                //向计分板传数值
                                setTesutoNum(player,count);
                            }
                            return 0;
                        })))
        );
    }
}
