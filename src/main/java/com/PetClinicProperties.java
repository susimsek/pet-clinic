package com;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "petclinic")
public class PetClinicProperties {

    private boolean displayOwnersWithPath=false;

    public boolean isDisplayOwnersWithPath() {
        return displayOwnersWithPath;
    }

    public void setDisplayOwnersWithPath(boolean displayOwnersWithPath) {
        this.displayOwnersWithPath = displayOwnersWithPath;
    }
}
