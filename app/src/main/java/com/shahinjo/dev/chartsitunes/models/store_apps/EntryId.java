
package com.shahinjo.dev.chartsitunes.models.store_apps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryId {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("attributes")
    @Expose
    private EntryIdAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public EntryIdAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(EntryIdAttributes attributes) {
        this.attributes = attributes;
    }

}
