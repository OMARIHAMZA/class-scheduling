package models;

public class Stage {

    private String name;
    private boolean isLab;

    public Stage(String name, boolean isLab) {
        this.name = name;
        this.isLab = isLab;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLab() {
        return isLab;
    }

    public void setLab(boolean lab) {
        isLab = lab;
    }
}
