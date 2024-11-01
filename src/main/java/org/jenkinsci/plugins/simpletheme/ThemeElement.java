package org.jenkinsci.plugins.simpletheme;

import hudson.ExtensionPoint;
import hudson.model.AbstractDescribableImpl;
import org.kohsuke.stapler.DataBoundSetter;

import java.util.Set;

public abstract class ThemeElement extends AbstractDescribableImpl<ThemeElement> implements ExtensionPoint {

    private boolean blueOcean = false;

    public boolean isBlueOcean() {
        return blueOcean;
    }

    @DataBoundSetter
    public void setBlueOcean(boolean blueocean) {
        this.blueOcean = blueocean;
    }

    public abstract void collectHeaderFragment(Set<String> fragments, boolean injectCss);
}
