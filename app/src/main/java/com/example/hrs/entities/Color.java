package com.example.hrs.entities;

public class Color {
    int colorId;
    String colorName;
    String colorCode;
    int status;

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Color() {
    }

    public Color(int colorId, String colorName, String colorCode, int status) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorCode = colorCode;
        this.status = status;
    }
}
