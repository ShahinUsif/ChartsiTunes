
package com.shahinjo.dev.chartsitunes.models.store_apps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryLink {

    @SerializedName("attributes")
    @Expose
    private EntryLinkAttributes attributes;

    public EntryLinkAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(EntryLinkAttributes attributes) {
        this.attributes = attributes;
    }

}
