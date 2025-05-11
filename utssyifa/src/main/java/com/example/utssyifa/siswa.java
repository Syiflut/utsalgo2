package com.example.utssyifa;

public class siswa {
    private String nama;
    private String nim;
    private double nilai;

    public siswa(String nama, String nim, double nilai) {
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public double getNilai() {
        return nilai;
    }

    @Override
    public String toString() {
        return nim + "," + nama + "," + nilai;
    }
}

