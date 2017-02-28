
package com.shahinjo.dev.chartsitunes.models.store_apps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImContentType {

    @SerializedName("attributes")
    @Expose
    private ImContentTypeAttributes attributes;

    public ImContentTypeAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ImContentTypeAttributes attributes) {
        this.attributes = attributes;
    }

}
