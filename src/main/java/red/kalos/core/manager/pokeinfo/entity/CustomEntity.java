package red.kalos.core.manager.pokeinfo.entity;

public class CustomEntity {
    //皮肤名称
    private String SkinName;
    //使用皮肤的精灵
    private String Species;
    //皮肤材质名称
    private String Texture;

    public String getSkinName() {
        return SkinName;
    }

    public void setSkinName(String skinName) {
        SkinName = skinName;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public String getTexture() {
        return Texture;
    }

    public void setTexture(String texture) {
        Texture = texture;
    }
}
