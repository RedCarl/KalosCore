package red.kalos.core.manager.citizens;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import red.kalos.core.entity.NPCs;
import red.kalos.core.util.ColorParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: carl0
 * @DATE: 2022/8/3 17:41
 */
public class CitizensManager implements Listener {
    List<NPC> npcList = new ArrayList<>();
    private static final CitizensManager instance = new CitizensManager();

    public static CitizensManager getInstance() {
        return instance;
    }


    public void init(World world) {
        NPC npc;

        npc = NPCs.createNPC(new Location(world, -176.5, 105, 66.5, 90, 0),
                Arrays.asList(ColorParser.parse("&7&o在这里 &a&o签到 &7&o并获得奖励。"), ColorParser.parse("&d艾莉")), 0.25, n -> {
            n.data().set("kalos_spawn", "签到");
            SkinTrait skinTrait = n.getOrAddTrait(SkinTrait.class);
            skinTrait.setSkinPersistent(
                    "e6d6df5262ae4f22aa03bc76e431e8f7",
                    "r3LRVuKmMaz2J+zNFyC+oE5QUXzg9H/ms2U58jbgNKLi1dxFK1YqD9phxXlOwXc6PpbH93oLuk+LPjAIuO7bSukypEWUrbqOBHfqMf668TxJb/baUUYSRiRtMrjWbH+4X7cOqXwoCLKNxjeqiJH0gJ4+pCrlZ9GvBp/xNDLJif4ttC2aOZZzPUGj6Jk6VevAOKy9f91+RYj3/z1FdbPZrHqK0kzhPSl4C/zIBBjnkV3T7usMl881a2Z5ZmUvtImt1JAMsmsc6jVp1MiTU7tGGZxAB+FzJkKKAds32IyEtdw+ThFRJbsorvrUG5FDm8t4jQ79bA7NdUEYv1iXEGbSyVFVOWcmC/1chyaY4yTx5uDdmPsozGXy4h0+TpsZw3nTRmVugOFrV+MMOrmpR+dNcwdU8wjSJxEyfkmgwl5UQ6WGX/OCLM2KVRjxtW42qSB9n5B7heAhJF2Tjsy7ZH3jqe99OW0QTySClf627z06YU/Q9DQY9bYEZYJ6f55dy/WmeXJWdfZh7oJ40PnWxaz3S268+sUJn5f9qFkgd3NXc8L7v/sa+vLmx0n5yKMzthe0C8FCnnBMZdzlrdOzcVj5yV81DbVKcRms9BH0/bx7+J26FXXNc4XUhyMZSqGUOd6VfLULpGyL0FHIyylnuNGexm64g8BzyCuKiGRqmgYt5wE=",
                    "ewogICJ0aW1lc3RhbXAiIDogMTY1NzY4MTM4OTk2MSwKICAicHJvZmlsZUlkIiA6ICJlNmQ2ZGY1MjYyYWU0ZjIyYWEwM2JjNzZlNDMxZThmNyIsCiAgInByb2ZpbGVOYW1lIiA6ICI1X1AiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGM3ZTljODIyOWZhZjU2ZGE2MDI3YjViODcwZTA3ZTM0MjQzZDM5NDQzMzgwYjZlMDBmNWE2Yzk5OWFiMWIxNyIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9LAogICAgIkNBUEUiIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIzNDBjMGUwM2RkMjRhMTFiMTVhOGIzM2MyYTdlOWUzMmFiYjIwNTFiMjQ4MWQwYmE3ZGVmZDYzNWNhN2E5MzMiCiAgICB9CiAgfQp9"
            );
        });
        npcList.add(npc);

        npc = NPCs.createNPC(new Location(world, -188.5, 100, 46.5, -90, 0),
                Arrays.asList(ColorParser.parse("&7&o在这里查看 &a&o礼物 &7&o并领取。"), ColorParser.parse("&6米拉")), 0.25, n -> {
            n.data().set("kalos_spawn", "礼物");
            SkinTrait skinTrait = n.getOrAddTrait(SkinTrait.class);
            skinTrait.setSkinPersistent(
                    "86f0b07cbff94c19942d17ed6b3c1ec4",
                    "ZL1ygicZ3Ga4ZuZrls7IFCnspXZ0r5nDymMiksgmeBbfKYI5bMawmjyDZkO2GD9LnAdjMXXZbdpK/W9xzVi+qEZaeGMt2w83ogWcgTF3xTYPlYqDe0KXQlI0l8LdXzHVojhmXt8wut6GXDLeFPLUq8J3AsAzAm4+03kpUfkrqAMUTXODntkHLJq9nKxPTqtGq3oJT0tDadyotdoZX6JS1+Z7KXoE63IyOfloN0P5ZgDMZKax7/wvlIS39Glm6PitIhnfYxLCfHjdtr+VBA7/NWmxAP1C3fiKcuhW+9jdWP3Z5nnuYg0YpxUBsTyk96J1DXOWL5Z4oyZXZJFMfeFs9InOK3bZV24TY7IsgIONm0SHqYUKlqSCSn20rkDkp9eve6dvQrvJBqJ/P52JRhZKUUyPqNauIq7Yg1ev5SB+zcV0AcQgPHGB1WBGVthlpgRoWDHphZHqd+t/lHndWD1WbNrt/vcfiOUZBc5HxxbL7RxZju0WaTf2fv7VPgOYXiYWBPdAXI5eo5pSr5OTrz10WW4v0fKRi9VhRQTzIbSeUdlqBJr2wmNa/TjFJhrsBksDhion0k9mP5FganG6QAXP8yQjJC0nhZnO2dIgEmCLom16+bQqMzsDvt46QFA+PDFe71T6waX6pqyZAN4veVhnb43mlCex7p2oVmLBn/vSmSk=",
                    "ewogICJ0aW1lc3RhbXAiIDogMTY1NzgxNjExNTg5MiwKICAicHJvZmlsZUlkIiA6ICI4NmYwYjA3Y2JmZjk0YzE5OTQyZDE3ZWQ2YjNjMWVjNCIsCiAgInByb2ZpbGVOYW1lIiA6ICIxMTBLSUxMIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2U0YjI5ZTcyM2Q0ZGMxM2Q4MDc4ZmRhNTQ3MjY5ZDVjMWExZjAzNWJmMTgzODAzNmNkZTMwY2M0MGQ3OTIzMzUiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="
            );
        });
        npcList.add(npc);
//
//        npc = NPCs.createNPC(new Location(world, 22.5, 59, 17, 90, 0),
//                Arrays.asList(ColorParser.parse("&7(右键点击)"), "factions.npc.kits"), 0.25, n -> {
//            n.data().set("factions_npc", "kits");
//            SkinTrait skinTrait = n.getOrAddTrait(SkinTrait.class);
//            skinTrait.setSkinPersistent(
//                    "cb6f3204b1204cc398ff444ff6a761bd",
//                    "wCeppxRGULUXL+Sw6QRlzv1RCpK2LawiaRqM3vhly+H2weaJFhpZi5iiOlBxJZNderTpdz0GxnYPW/P/COaYlE8zhY7RafKMQx2Ia+vFuL6i8zDnbG0oAku486Fhm0ZrkQQkmx9UstO6p29wrlMpwhZRYKXBv5z5feHAIkE4XIFKjVLlDzsmtGW8tMeRslyZAi77lDPO1oid9c1DcrJprv4nOeHwmeg9G5xtsh7U53vyEeUITogstZboSeCpVZvv5JVRBnSEmvijHsDCKzKAIz3KPLMuZXod1XHudI9QGAP+FiXZWl+GK4R1QqtOAFCLmMaj3h5R1U8QymTI2J06cGVecyqUKIbL5W3CLRddtCYk13pxxLxTlZiPmvAZz2D1FIFeQQCbi+EWSJF3P1jrh50WKNy7YVUlb2JkJcZMstoCNLT3UWEpZQIdd7qlxsy2rAL/23aencnI2QnfhUnUpMGHM+o8Y/Oz8V9wEe/4Ddr/300ok7TIcrKH8IZ9v7oHA9JotWMK+HtB0sGknXxzZFKa6GkBrtV48inZYSZKIlFzqdh1ueEVFXu0D98kCpqr8qnJDA7zft5245ttnxjW8mYiZSIIkE+EPoeJvxmHsj/ZF6ZpOXkcNpjHZLgjWOWVNIXOoLqoZLNDFGXU3xz73FMMcbrgRUNM0RXTIPb3bRo=",
//                    "ewogICJ0aW1lc3RhbXAiIDogMTY1NzgxNjUwNjEzOSwKICAicHJvZmlsZUlkIiA6ICJjYjZmMzIwNGIxMjA0Y2MzOThmZjQ0NGZmNmE3NjFiZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0cmFuZ2N1dGUxOXQ5Mms4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQ5YzMxYTBlMjFkZTUwNTM1MjhjNjhjOTQzMjk3YWRjNWM3MDE0ZjkzOTM0MDQzODFiMWM2ZGU4NzIzYjBkM2UiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfSwKICAgICJDQVBFIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzQwYzBlMDNkZDI0YTExYjE1YThiMzNjMmE3ZTllMzJhYmIyMDUxYjI0ODFkMGJhN2RlZmQ2MzVjYTdhOTMzIgogICAgfQogIH0KfQ=="
//            );
//        });
//        npcList.add(npc);
//
//        npc = NPCs.createNPC(new Location(world, 18, 59, 22.5, 180, 0),
//                Arrays.asList(ColorParser.parse("&7(右键点击)"), "factions.npc.tag"), 0.25, n -> {
//            n.data().set("factions_npc", "tag");
//            SkinTrait skinTrait = n.getOrAddTrait(SkinTrait.class);
//            skinTrait.setSkinPersistent(
//                    "e23d5d9a533f411f81ae1c341ebc8856",
//                    "i2+QjH48z/tTS9HPhRwJFWaSA6pCCIeDBzmz0IKXdSefijTA++G0bFki2oIS/XktW/GMm/9aye8Tt4PTei6Yqw4+ok4BMfLwJ/V+1d27aykD0xtvKS/MxCvr6vW7EyM4pfq9n4ahZzFqAaT8Vtw7q5joVMaaZRaTkVG2o35hBu7WJeLfVPRWozNriIiitpnvySVhUP9y2gXFwXrvgL3fr9K07+4M/PWBpliPDFbkF+ubmkMatOmyjqra+AIX794/eCeTRdTdIqfX5z/AYZf4ltWL8OMfuWnRpkZdjBmGPdLg53wKBR10PeRzUUHQB1cjJlNN13YN8yoJyPxkg9cdC3v6rUkf52Vi606kfUjLwURDx/w0vIDjxpE34GU6t7CY/nIOrhRzNANwaaJnv4WpxsjMggizBeJn5Uw4SfEi+ZY5OyO9uoGxgwvmP7VlhjxSNujrFQKuALUhoOpzEe1brwda/ADrAM3JZy2zBxYU1Svp3Fm1wIBfYVVWUElvXLeyFCJ+BMivu8Q4GS4c4f8AalMh+qb5E88qeSHmB2WG4Y9WOAp1ie5gDLwVBJOTUA5mwkMvthoVd7F+yTgKrVGD51KqA/QqdfhYZRQAmOVIgyoNlsGEROydVcE2ofyR27puvi5MSeH9sgkVfKCOlXa1dDhyVRUkTRRx9fAi4t5yK5Y=",
//                    "ewogICJ0aW1lc3RhbXAiIDogMTY1ODU3MjI4NTIwOSwKICAicHJvZmlsZUlkIiA6ICJlMjNkNWQ5YTUzM2Y0MTFmODFhZTFjMzQxZWJjODg1NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJhZ29ueTIwMDEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA0ODJjYzVhYzU2MGZhM2Q5ZDc3ZjYyOTRkNWQ2ZjdjMDBkMTFkZDMwOGJkNmNmMTIxYzQzYjVhM2Q4MzhiZSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9"
//            );
//        });
//        npcList.add(npc);
    }

    public NPC getNpc(String npcType) {
        for (NPC n : npcList) {
            if (n.data().get("kalos_spawn").equals(npcType)) {
                return n;
            }
        }
        return null;
    }

    public void clear(){
        for (NPC n:npcList) {
            n.destroy();
        }
    }
}
