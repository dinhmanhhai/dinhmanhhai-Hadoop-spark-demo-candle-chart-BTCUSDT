package io.saagie.example.hdfs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class TradeData {
    private String e;
    private Long E;
    private String s;
    private TradeInfo k;

    public String getE() {
        return e;
    }

    public void setE(Long e) {
        E = e;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public TradeInfo getK() {
        return k;
    }

    public void setK(TradeInfo k) {
        this.k = k;
    }

    public void setE(String e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return e  +
                ", " + E +
                ", " + s  +
                ", " + k.toString();
    }
}
