package org.jenkinsci.plugins.simpletheme.JsUrlThemeElement

import lib.FormTagLib

def f = namespace(FormTagLib)

f.entry(field: 'text', title: _("Extra CSS"), help: "/plugin/simple-theme-plugin/help-cssRules.html") {
    f.textarea()
}

if(app.pluginManager.getPlugin("blueocean-web") != null) {
    f.entry(field: "blueOcean", title: _("For BlueOcean")) {
        f.checkbox()
    }
}