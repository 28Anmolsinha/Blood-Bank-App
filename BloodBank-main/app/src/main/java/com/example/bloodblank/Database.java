package com.example.bloodblank;

public class Database {

    public static Database Instance = null;

    public String[] BloodGroups = new String[]{"A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"};

    public static Database getInstance() {
        if (Instance == null) {
            synchronized (Database.class) {
                Instance = new Database();
            }
        }
        return Instance;
    }
}
