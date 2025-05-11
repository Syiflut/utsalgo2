package com.example.utssyifa;

import java.io.*;
import java.util.*;

public class siswaservice {

    private static final String FILENAME = "siswa.txt";

    public static List<siswa> readData() throws IOException {
        List<siswa> siswaList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            siswa siswa = new siswa(data[1], data[0], Double.parseDouble(data[2]));
            siswaList.add(siswa);
        }
        reader.close();
        return siswaList;
    }

    public static void writeData(List<siswa> siswaList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
        for (siswa siswa : siswaList) {
            writer.write(siswa.toString());
            writer.newLine();
        }
        writer.close();
    }

    public static void addSiswa(siswa siswa) throws IOException {
        List<siswa> siswaList = readData();
        siswaList.add(siswa);
        writeData(siswaList);
    }

    public static void sortByNilai(List<siswa> siswaList) {
        siswaList.sort((s1, s2) -> Double.compare(s2.getNilai(), s1.getNilai()));
    }

    public static siswa binarySearch(List<siswa> siswaList, String nama) {
        int low = 0;
        int high = siswaList.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            siswa midSiswa = siswaList.get(mid);
            int comparison = midSiswa.getNama().compareToIgnoreCase(nama);
            if (comparison == 0) {
                return midSiswa;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static double hitungRataRata(List<siswa> siswaList, int index) {
        if (index == siswaList.size()) {
            return 0;
        }
        return (siswaList.get(index).getNilai() + hitungRataRata(siswaList, index + 1)) / siswaList.size();
    }
}

