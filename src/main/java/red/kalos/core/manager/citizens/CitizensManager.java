package red.kalos.core.manager.citizens;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Location;
import org.bukkit.World;
import red.kalos.core.entity.NPCs;
import red.kalos.core.util.ColorParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: carl0
 * @DATE: 2022/8/3 17:41
 */
public class CitizensManager {
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

        npc = NPCs.createNPC(new Location(world, -217.5, 103, -14.5, 0, 0),
                Arrays.asList(ColorParser.parse("&7&o在这里与其它玩家进行物品交易。"), ColorParser.parse("&b舰长")), 0.25, n -> {
            n.data().set("kalos_spawn", "市场");
            SkinTrait skinTrait = n.getOrAddTrait(SkinTrait.class);
            skinTrait.setSkinPersistent(
                    "cb6f3204b1204cc398ff444ff6a761bd",
                    "wCeppxRGULUXL+Sw6QRlzv1RCpK2LawiaRqM3vhly+H2weaJFhpZi5iiOlBxJZNderTpdz0GxnYPW/P/COaYlE8zhY7RafKMQx2Ia+vFuL6i8zDnbG0oAku486Fhm0ZrkQQkmx9UstO6p29wrlMpwhZRYKXBv5z5feHAIkE4XIFKjVLlDzsmtGW8tMeRslyZAi77lDPO1oid9c1DcrJprv4nOeHwmeg9G5xtsh7U53vyEeUITogstZboSeCpVZvv5JVRBnSEmvijHsDCKzKAIz3KPLMuZXod1XHudI9QGAP+FiXZWl+GK4R1QqtOAFCLmMaj3h5R1U8QymTI2J06cGVecyqUKIbL5W3CLRddtCYk13pxxLxTlZiPmvAZz2D1FIFeQQCbi+EWSJF3P1jrh50WKNy7YVUlb2JkJcZMstoCNLT3UWEpZQIdd7qlxsy2rAL/23aencnI2QnfhUnUpMGHM+o8Y/Oz8V9wEe/4Ddr/300ok7TIcrKH8IZ9v7oHA9JotWMK+HtB0sGknXxzZFKa6GkBrtV48inZYSZKIlFzqdh1ueEVFXu0D98kCpqr8qnJDA7zft5245ttnxjW8mYiZSIIkE+EPoeJvxmHsj/ZF6ZpOXkcNpjHZLgjWOWVNIXOoLqoZLNDFGXU3xz73FMMcbrgRUNM0RXTIPb3bRo=",
                    "ewogICJ0aW1lc3RhbXAiIDogMTY1NzgxNjUwNjEzOSwKICAicHJvZmlsZUlkIiA6ICJjYjZmMzIwNGIxMjA0Y2MzOThmZjQ0NGZmNmE3NjFiZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0cmFuZ2N1dGUxOXQ5Mms4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQ5YzMxYTBlMjFkZTUwNTM1MjhjNjhjOTQzMjk3YWRjNWM3MDE0ZjkzOTM0MDQzODFiMWM2ZGU4NzIzYjBkM2UiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfSwKICAgICJDQVBFIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzQwYzBlMDNkZDI0YTExYjE1YThiMzNjMmE3ZTllMzJhYmIyMDUxYjI0ODFkMGJhN2RlZmQ2MzVjYTdhOTMzIgogICAgfQogIH0KfQ=="
            );
        });
        npcList.add(npc);

        npc = NPCs.createNPC(new Location(world, -189.5, 100, 7.5, -7, 0),
                Arrays.asList(ColorParser.parse("&7&o我这里有许多任务，想尝试吗?"), ColorParser.parse("&6老仙")), 0.25, n -> {
                    n.data().set("kalos_spawn", "任务");
            SkinTrait skinTrait = n.getOrAddTrait(SkinTrait.class);
            skinTrait.setSkinPersistent(
                    "b036ca10c80e484c95dd8b5e3d2c352c",
                    "sm3d3f4piVm0LWPnP//4qnhOuvPBpBg/JOAJAhnD3e50Uuz2YDhj/GFhnhJHWPbzdfDbWvUFhLrEhNq7Bs5VH5Ld7GuLp+s6VLD7hqzmmRMEXyKodbz5DB3fHEA20GIPuC54irNAGkHMcEbq+NU/YJebR8B3HFz5envZsSq9JzCv4lW/TZZTyQdtni5MVgGzBy1lOQiLa8tHmbUEqMuEg9YJVPTbLNEDIXywpQfJmErxXwU6EmSWFtH80cSX+fTAnskZrwW3jCl45XLufgH7gTZqtEPWPZRkpVFrNb4qQtu33Y6WRm5q6UtHzKFp2AjrmLaXF5VX4SxuGWW5X5m6LWBpn3UKVnr0Q00TIUy989BUGO64fcxjvbysS2oqPGGpoTwmO0rLqdM1U5DZhnv08hpuEmFupnrOCUtIy+ITFzF8C015mxCc/7QbtFlHZn8r4u4xNsQP1hxNukVHWwrGseu34+7TSLybvHgYp2zuqF6gdOHzyZT6wcD5T6ZeZ/ZasZGKB8CizI7Whr0n2HevojJvKFU2iQm0Nt1X/YzOJReYRZFDEiXCD5fJ0IL3I+bk4RTiVwMU/WGhaxZtNoHVkiQCSdAMtFNch9PvIzn6MMHKu5njFhBldwqwr0AJIhwyuG/OEDi7DuvHGQ7i9aNH92NgDl/uyjFJ2EucLdzdyZE=",
                    "ewogICJ0aW1lc3RhbXAiIDogMTY1OTg1NTY5MTQ2NSwKICAicHJvZmlsZUlkIiA6ICJiMDM2Y2ExMGM4MGU0ODRjOTVkZDhiNWUzZDJjMzUyYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJhbHRvbnMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2M0ZjRlOTY3NWJjYjU4NTVhOWFiOTFiZDliMDI5MTRmZDQyOTg2NGZjZTljNTAwNmM2MTAzYzEyMzM0YTEyMyIKICAgIH0KICB9Cn0="
            );
        });
        npcList.add(npc);

        npc = NPCs.createNPC(new Location(world, -218.5, 103, -3.5, -180, 0),
                Arrays.asList(ColorParser.parse("&7&o需要购买物品吗?"), ColorParser.parse("&a骚蛇")), 0.25, n -> {
                    n.data().set("kalos_spawn", "出售");
                    SkinTrait skinTrait = n.getOrAddTrait(SkinTrait.class);
                    skinTrait.setSkinPersistent(
                            "b9dd2bb525244e058e27b107ccc3155d",
                            "uEbzTYw5smQOq98Vok7+CauTJRi4l8IaJtZ+NqeOvruFpJWXgbtjRkhDJDFQkr7VjWTtBFggCbviYnGfLPov/ZcjrCYLett3t9gNx3NQGvfwE2VuYh6/K2N4reEiiK0eJUhSfxc6fiy6Yd8dw7C6iFZ/oUuVmPT/DpllK3cGIdqhjN3TA9CsoxKs/5fyFNz1gJOMwHZcqTrgt2S68z7bsBRCTl42hIqI8HDE/8p5TjChW78Il7bsvO7xxE4bNA7rd+KsHeeFOC5QCg6KMlwJfpBkahueMzacwy8k/DGZQrKpm2UIJqbYccarRbTlPb1C4ffn2Tn6weyjB4PdfGjJcRoNEXuUak1W1ANHdKntIfEnQszoxRywdrDPzce9bVQRyhapUwtQbYbISCKWDr7bFN9a1el+KZyPxBWENvEllDEF/WqF7OwSGM0AuDINEBKHX4edawYgEyQ8gpOz7+mURdYnPB6iuxIYe/Vfjgk95RvI+mGyMqzQDOuqcKgOiPT/r1mUYzsLN2fTBrodj6fg+Eq/6f5Y6oNRlXDsq1f5y/kX40uYw6KtiCj4eeUp7cKVqPfC2KNVuhpozOUFTMRKdpj/nG4AfWZIBpfRF9HPFgYbH2i14gzbFvep3SLlsrjrdeM4yPjoI8qmZdkGrMiMc2OiVWR1jMj2RS+MhF8X5U4=",
                            "ewogICJ0aW1lc3RhbXAiIDogMTY1OTg1Mzc3NjEwMiwKICAicHJvZmlsZUlkIiA6ICJiOWRkMmJiNTI1MjQ0ZTA1OGUyN2IxMDdjY2MzMTU1ZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJhcm9yXzIwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzcyMzQzODNjZTE0NmQwNjQ5NjQ2YWJiMWI3NmZkZjQ3YWRiMmNmZGU4NDQyYjdmMTUyNWExYTM3Y2UwM2RiNDkiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="
                    );
                });
        npcList.add(npc);

        npc = NPCs.createNPC(new Location(world, -216.5, 103, -3.5, -180, 0),
                Arrays.asList(ColorParser.parse("&7&o需要售卖物品吗?"), ColorParser.parse("&a沐泽")), 0.25, n -> {
                    n.data().set("kalos_spawn", "收购");
                    SkinTrait skinTrait = n.getOrAddTrait(SkinTrait.class);
                    skinTrait.setSkinPersistent(
                            "05fef517e30445d0b81d69009183285d",
                            "bNhLsrjoRpN0v5FPElWuQFF6wYpjxhZW2AfFP1CYsiJmdGpwrAqpSpVxtUMmGaTouFEqVBxgXvlbXG5h2sa9F4cWvriVCebqfgS57r0ZuHSupNUWHj3PK+fUR5F6LMA1WXqXWYpoFlm3Hd1lzuezj3Mn3ltvHoV9TZ5URe2quM/O8rzpJO+fu0/rsFm3xLvEd0RDrwOCcr9G3ZKGqkj1U9iBnR8GctLP/2pnl7t504ZA+GvnS9NyER2mhNF7rLTCLHkauu/KrY06AYWXOmRh9qfCd6zkUQuQs37oIGhFvciSgWHUyNukdZFbb4T6+p/ssSTyRLIFS9NOxn9cRfEXJ16dYHP+I/s89oQO8muXJRK1ucmhvpYySaAXgdNlSlcoGbZO5SWRg+y7QtlOhIQa+WKGB3a6KkcB8V78Pb+WsR7hgZtX6qS7p210D4FeEOuCk2/9KGEIOonVP8bxiPVIpCTS9TeW4S05vzprImsLp5kqMCNaU2zxHguJiz5CiWZMPDK1YeFVWtu0eAx9rkNHWxACl1gdqxG43ED81sWrRl+OXx7R+R0nwkYEtihquJESB9GwC2PpWCrlXQDJPp9aCeVosmDuWAgf6M5kwBlVFLNlcZ8GqnwV00tren4fZ54rUwoM00EPefiSAo83JX+KtjiEs0TBfSGAZbKZ7hO8m5M=",
                            "ewogICJ0aW1lc3RhbXAiIDogMTY1OTg1Mzg5OTg3NywKICAicHJvZmlsZUlkIiA6ICIwNWZlZjUxN2UzMDQ0NWQwYjgxZDY5MDA5MTgzMjg1ZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJXaGVhdE1pbmlvbl8iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzIzZTRmNmZmNWRmOTY0NDhlMDliOTAyNjdhYTg3Y2JjODBlMDQ2YTQyZTMzMjAzYjg5MmZlN2ZiNGY1MTBlMCIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9LAogICAgIkNBUEUiIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIzNDBjMGUwM2RkMjRhMTFiMTVhOGIzM2MyYTdlOWUzMmFiYjIwNTFiMjQ4MWQwYmE3ZGVmZDYzNWNhN2E5MzMiCiAgICB9CiAgfQp9"
                    );
                });
        npcList.add(npc);

        npc = NPCs.createNPC(new Location(world, -222, 103, -9, -90, 0),
                Arrays.asList(ColorParser.parse("&7&o您可以在这里支持我们！"), ColorParser.parse("&d卡尔")), 0.25, n -> {
                    n.data().set("kalos_spawn", "支持");
                    SkinTrait skinTrait = n.getOrAddTrait(SkinTrait.class);
                    skinTrait.setSkinPersistent(
                            "1334990e1ac5419b970127e518ed11df",
                            "bIttCDlbhWbECSkbtz6FJHjNVsYRiCmuRixHPk2kixJVsLaH+h5kPKfvGUJXF8EEOfdUN/j+POqXYm4Fdrxr6U5n4M3b+vtud1irse9zRajaAD44/OUL1zOS7GsyyhthSEWzUQjHlkOj45BXrkLJkIsS208BRv2aQutEeufu9TxRkDnMPvjCbmXEE9hGQ5jqpUY0ylrKJWYTR4fBjnXaId7cXR+LRINBPWD9XfLhgIlvCDlhebsS255uMa4QG+yT0FHf34N3g6kXqDyE0muBPmVBmkb6GW54qcww2fYtEF+aZ0hhCyoyY2agCxgidLPYNLPvuzn78PuL5Lsr6j594qq3ZJ6BgLQT+yc9gmHY1/8MdJWrZYTAYswlALL6bQf2YSxyq7hq0n3K3i/rozcBrS7x43bJ7b1iCzVASNW7NF/kRQvlQuGeYOTaR1Kcl0htbZeEV+uWPKY7VNWlkpBTVwCeRph433FNfQY1WM34SzLjrLdMJux93ZlB6q0vB9poCD7wmopPBwdTbnatnFwp8aqKsNyxW5IED+jNu47nvN4ix8/W7SxXCfPM09xme7iPzRr80wAgLZSR/z6pQ+EAK0ooJWsty7VBjwc2vuGYESsBwxi4cCzZ6ecN4YCAQJxl9qFiEW3s3GYHbZVzO3b4i153qTZEj6w65o0fBQR9dTg=",
                            "ewogICJ0aW1lc3RhbXAiIDogMTY1OTg1NDcxMDU0NCwKICAicHJvZmlsZUlkIiA6ICIxMzM0OTkwZTFhYzU0MTliOTcwMTI3ZTUxOGVkMTFkZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZWRfQ2FybCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jMDc5OTI1ZjMyZjZmMTE0ODgzYzI2MTc5NDA4OWNiYzA1NDEzMTA5NjIxMjc5MTFkNTlkNDkzMGRiNjllZTc0IgogICAgfSwKICAgICJDQVBFIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzQwYzBlMDNkZDI0YTExYjE1YThiMzNjMmE3ZTllMzJhYmIyMDUxYjI0ODFkMGJhN2RlZmQ2MzVjYTdhOTMzIgogICAgfQogIH0KfQ=="
                    );
                });
        npcList.add(npc);

        npc = NPCs.createNPC(new Location(world, -207, 101, -3, -90, 0),
                Arrays.asList(ColorParser.parse("&7&o需要建筑方块吗?"), ColorParser.parse("&d三寸")), 0.25, n -> {
                    n.data().set("kalos_spawn", "建筑");
                    SkinTrait skinTrait = n.getOrAddTrait(SkinTrait.class);
                    skinTrait.setSkinPersistent(
                            "c9b9e746a1f341428b677ce0501c657e",
                            "FvoUWkhpqVpqP2bV5QryEexKg5J8SaOjdfQ9cIcm3uGtR3QjSYafNnWwjWyNHhzT9+TCPZHwbPkD657P2/ulBW0UxuD2lHocgfdeCmZIhHXA1DKG7ev6yFsoB6Mslmzk106h68Rw17msFK2dC7hzSt1dNaxlRbEtx4hubYk+ArhVhwc3YbcItln1PFJ2t2P5HaRanxURtZNmMUWq5ZVL3W09LMRCtAp+svARQzRh/nC+yzcL/sEEZiR/RF+j3US0euKPEbn8J2pGEM78NGxVCMCHx2Q/uReOle7umLEwzAXgJX5H4jHVOZEz9KeLld1iTJ9ScUVGntGFbFtsXsFiFUUMXbAggAaulTWAmGiSAdBrJXtHYkk5rTvL3W7WArh6xrp7F/jEREk+LGY4jJgI0bhORfhJRVtAbG+PHci7qO4pIS3gnrFqBcc/Vn77cagJlS37oArYyX5ft7DHi5pIKQQjsorJaND1q9ZIi6RVEkvyVuB+B3u+SVwSL+Zn+/3vYYfwaKrMzT8SJjsgAomcNXlAbryQkrwVqzW0FpMUTjLXdhK0CfQ2Z2rmUQRO0rfnyL/D59AKvr1ZJMAQTHLyobZoc3iDnzftAKuQRv6GysHdOnxBvtg/XcRJqLNDqN+l+u8kgRuozr1XSrugsDFuKICpPpyrZAJS+GUkdUP9YCw=",
                            "ewogICJ0aW1lc3RhbXAiIDogMTY1OTg3OTczMDIyNywKICAicHJvZmlsZUlkIiA6ICJjOWI5ZTc0NmExZjM0MTQyOGI2NzdjZTA1MDFjNjU3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJOeWFuZDNyZnVsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZiNjFiM2E1YWNlZmRjOWRkNmRjMGQ4YmViOGIxYWFiNWU3YTA3NTJkMDExOTY1MmU0MmUyNmYwZTVhYTUxYWMiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfSwKICAgICJDQVBFIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzQwYzBlMDNkZDI0YTExYjE1YThiMzNjMmE3ZTllMzJhYmIyMDUxYjI0ODFkMGJhN2RlZmQ2MzVjYTdhOTMzIgogICAgfQogIH0KfQ=="
                    );
                });
        npcList.add(npc);
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
