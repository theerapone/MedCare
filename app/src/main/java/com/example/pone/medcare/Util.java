package com.example.pone.medcare;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util{
    private MedicineTable medicineTable;
    public static List<String[]> readCSV(String file){
        List<String[]> entities = new ArrayList<>();
        String csvFile = file;
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] rs = line.split(",");
                entities.add(rs);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return entities;
    }
    public void readCSVMedicine(String file, Context connext){
        medicineTable = new MedicineTable(connext);
        List<String[]> entities = new ArrayList<>();
        String csvFile = file;
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] rs = line.split(",");
                Medicine medicine = new Medicine().setName(rs[1])
                                   .setDescription(rs[2])
                                   .setHow_to_use(rs[3])
                                   .setMiss_dose(rs[4])
                                   .setOverdose(rs[5])
                                   .setAvoid(rs[6])
                                   .setSide_effect(rs[7]);
                medicineTable.insert(medicine);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
