package com.liyongquan.design;

public class Hex {
    public int x;
    public int y;
    public int z;

    public Hex() {
    }

    public Hex(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Hex move(Hex dir) {
        this.x += dir.x;
        this.y += dir.y;
        this.z += dir.z;
        return this;
    }

    @Override
    public Hex clone() {
        return new Hex(this.x, this.y, this.z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Hex)) {
            return false;
        }
        Hex hex = (Hex) obj;
        return this.x == hex.x && this.y == hex.y && this.z == hex.z;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + x;
        hash = hash * 31 + y;
        return hash;
    }
}
