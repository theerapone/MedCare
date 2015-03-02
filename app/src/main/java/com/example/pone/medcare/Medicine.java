package com.example.pone.medcare;

public class Medicine {
    private int _id;
    private String name;
    private String description;
    private String how_to_use;
    private String miss_dose;
    private String overdose;
    private String avoid;
    private String side_effect;

    public int getId() { return _id;  }
    public Medicine setId(int id) {  this._id = id; return this; }
    public String getName() { return name; }
    public Medicine setName(String name) { this.name = name; return this; }
    public String getDescription() { return description;  }
    public Medicine setDescription(String description) { this.description = description;return this;  }
    public String getHow_to_use() { return how_to_use;  }
    public Medicine setHow_to_use(String how_to_use) {  this.how_to_use = how_to_use;return this; }
    public String getMiss_dose() { return miss_dose;  }
    public Medicine setMiss_dose(String miss_dose) { this.miss_dose = miss_dose; return this;  }
    public String getOverdose() {  return overdose; }
    public Medicine setOverdose(String overdose) { this.overdose = overdose; return this;  }
    public String getAvoid() { return avoid;  }
    public Medicine setAvoid(String avoid) { this.avoid = avoid; return this; }
    public String getSide_effect() { return side_effect; }
    public Medicine setSide_effect(String side_effect) {  this.side_effect = side_effect; return this; }
}
