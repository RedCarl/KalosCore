package kim.pokemon.manager.questmanager.quest;

public class TaskType {
    public static final int CATCH_MESSAGE = 1;//捕捉 参数：捕捉到的宝可梦   Type:Pokemon
    public static final int WIN_MESSAGE = 2;//击败    参数：打败的宝可梦列表 Type:List<Pokemon>
    public static final int HATCH_MESSAGE = 3;//孵化  参数：孵化出的宝可梦   Type:Pokemon
    public static final int TIME_MESSAGE = 4;//时间   参数：timestamp     Type:long
    public static final int CRAFT_MASSAGE = 5;//制造  参数：合成出的物品    Type:ItemStack
}
