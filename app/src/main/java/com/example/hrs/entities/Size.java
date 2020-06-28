package com.example.hrs.entities;

public class Size {
int sizeId;
int sizeName;
int status;

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getSizeName() {
        return sizeName;
    }

    public void setSizeName(int sizeName) {
        this.sizeName = sizeName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Size() {
    }

    public Size(int sizeId, int sizeName, int status) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.status = status;
    }
}
