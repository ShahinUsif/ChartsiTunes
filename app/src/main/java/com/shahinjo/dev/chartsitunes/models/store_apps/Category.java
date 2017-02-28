
package com.shahinjo.dev.chartsitunes.models.store_apps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("attributes")
    @Expose
    private CategoryAttributes attributes;

    public CategoryAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(CategoryAttributes attributes) {
        this.attributes = attributes;
    }

}
