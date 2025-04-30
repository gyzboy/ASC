package com.gyz.myplugin

class MethodHookExtension {
    private String targetPackage = "com/gyz/androidsamplecode";

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String value) {
        targetPackage = value;
    }
}