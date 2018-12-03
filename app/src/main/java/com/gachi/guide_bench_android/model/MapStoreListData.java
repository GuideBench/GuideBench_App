package com.gachi.guide_bench_android.model;

public class MapStoreListData {

    private String store_name;
    private String store_address;
    private String store_type;

    public MapStoreListData(String store_name, String store_type, String store_address) {
        this.store_name = store_name;
        this.store_type = store_type;
        this.store_address = store_address;
    }
    public String getStorename() {
        return store_name;
    }

    public String getStoretype() {
        return store_type;
    }

    public String getStoreaddress() {
        return store_address;
    }


}
