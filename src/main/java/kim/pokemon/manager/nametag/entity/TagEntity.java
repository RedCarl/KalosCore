package kim.pokemon.manager.nametag.entity;

public class TagEntity {

    public TagEntity(String name, String permission, String prefix, String suffix, int priority) {
        Name = name;
        Permission = permission;
        Prefix = prefix;
        Suffix = suffix;
        Priority = priority;
    }

    private String Name;
    private String Permission;
    private String Prefix;
    private String Suffix;
    private int Priority;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPermission() {
        return Permission;
    }

    public void setPermission(String permission) {
        Permission = permission;
    }

    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String prefix) {
        Prefix = prefix;
    }

    public String getSuffix() {
        return Suffix;
    }

    public void setSuffix(String suffix) {
        Suffix = suffix;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }
}
