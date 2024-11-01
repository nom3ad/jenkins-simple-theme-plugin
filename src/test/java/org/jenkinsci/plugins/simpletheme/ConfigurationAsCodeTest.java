package org.jenkinsci.plugins.simpletheme;

import static io.jenkins.plugins.casc.misc.Util.toStringFromYamlFile;
import static io.jenkins.plugins.casc.misc.Util.toYamlString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

import hudson.ExtensionList;
import io.jenkins.plugins.casc.ConfigurationContext;
import io.jenkins.plugins.casc.ConfiguratorRegistry;
import io.jenkins.plugins.casc.impl.configurators.GlobalConfigurationCategoryConfigurator;
import io.jenkins.plugins.casc.misc.ConfiguredWithCode;
import io.jenkins.plugins.casc.misc.JenkinsConfiguredWithCodeRule;
import io.jenkins.plugins.casc.model.CNode;
import io.jenkins.plugins.casc.model.Mapping;

import java.util.Objects;

import jenkins.appearance.AppearanceCategory;
import jenkins.model.GlobalConfigurationCategory;
import org.codefirst.SimpleThemeDecorator;
import org.junit.ClassRule;
import org.junit.Test;

public class ConfigurationAsCodeTest {

    @ClassRule
    @ConfiguredWithCode("ConfigurationAsCode.yml")
    public static JenkinsConfiguredWithCodeRule j = new JenkinsConfiguredWithCodeRule();

    @Test
    public void testConfig() {
        SimpleThemeDecorator decorator = j.jenkins.getDescriptorByType(SimpleThemeDecorator.class);

        assertNotNull(decorator.getElements());
        assertThat(decorator.getElements(), hasSize(5));
        assertThat(
                decorator.getElements(),
                containsInAnyOrder(
                        hasProperty("url", containsString("test.css")),
                        hasProperty("text", containsString(".testcss")),
                        hasProperty("url", containsString("test.js")),
                        allOf(
                                hasProperty("url", containsString("Favicon.ico")),
                                hasProperty("blueOcean", equalTo(false))
                        ),
                        allOf(
                                hasProperty("url", containsString("blue.js")),
                                hasProperty("blueOcean", equalTo(true))
                        )
                )
        );
    }

    @Test
    public void testExport() throws Exception {
        ConfigurationContext context = new ConfigurationContext(ConfiguratorRegistry.get());
        CNode yourAttribute = getAppearanceRoot(context).get("simpleTheme");

        String exported = toYamlString(yourAttribute);

        String expected = toStringFromYamlFile(this, "ConfigurationAsCodeExport.yml");

        assertThat(exported, is(expected));
    }

    private static Mapping getAppearanceRoot(ConfigurationContext context) throws Exception {
        GlobalConfigurationCategory category =
                ExtensionList.lookup(AppearanceCategory.class).get(0);
        GlobalConfigurationCategoryConfigurator configurator = new GlobalConfigurationCategoryConfigurator(category);
        return Objects.requireNonNull(configurator.describe(configurator.getTargetComponent(context), context))
                .asMapping();
    }
}
