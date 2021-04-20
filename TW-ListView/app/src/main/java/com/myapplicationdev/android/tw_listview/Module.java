package com.myapplicationdev.android.tw_listview;

public class Module {

    private String moduleName;
    private boolean moduleImage;

    public Module(String moduleName, boolean moduleImage) {
        this.moduleName = moduleName;
        this.moduleImage = moduleImage;
    }

    public String getName() {return moduleName;}

    public boolean getImage() {return moduleImage;}
}
