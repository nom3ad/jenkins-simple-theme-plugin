package org.jenkinsci.plugins.simpletheme.JsUrlThemeElement

import lib.FormTagLib

def f = namespace(FormTagLib)

f.entry(field: "url", title: _("URL of theme JavaScript")) {
    f.textbox()
}

if(app.pluginManager.getPlugin("blueocean-web") != null) {
    f.entry(field: "blueOcean", title: _("For BlueOcean")) {
        f.checkbox()
    }
}