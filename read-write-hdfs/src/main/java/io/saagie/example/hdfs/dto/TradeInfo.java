package io.saagie.example.hdfs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class TradeInfo {
    private Long t;
    private Long T;
    private String s;
    private String i;
    private Long f;
    private Long L;
    private String o;
    private String c;
    private String h;
    private String l;
    private String v;
    private Integer n;
    private Boolean x;
    private String q;
    private String V;
    private String Q;
    private String B;

    public Long getT() {
        return t;
    }

    public void setT(Long t) {
        this.t = t;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public Long getF() {
        return f;
    }

    public void setF(Long f) {
        this.f = f;
    }

    public Long getL() {
        return L;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Boolean getX() {
        return x;
    }

    public void setX(Boolean x) {
        this.x = x;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public void setL(Long l) {
        L = l;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return  t +
                ", " + T +
                ", " + s +
                ", " + i +
                ", " + f +
                ", " + L +
                ", " + o +
                ", " + c +
                ", " + h +
                ", " + l +
                ", " + v +
                ", " + n +
                ", " + x +
                ", " + q +
                ", " + V +
                ", " + Q +
                ", " + B;
    }
}
