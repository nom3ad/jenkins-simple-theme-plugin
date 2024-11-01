package org.jenkinsci.plugins.simpletheme.blue;

import hudson.Extension;
import hudson.ExtensionList;
import io.jenkins.blueocean.BluePageDecorator;
import jenkins.model.Jenkins;
import org.codefirst.SimpleThemeDecorator;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;

@Extension(optional = true)
@Restricted(NoExternalUse.class)
public class SimpleThemeBluePageDecorator extends BluePageDecorator {

    public String getHeaderHtml() {
        SimpleThemeDecorator decorator = ExtensionList.lookupSingleton(SimpleThemeDecorator.class);
        if (decorator != null) {
            return decorator.getHeaderHtml(true);
        }
        return null;
    }
}