# README #

### Informations ###

This is strategie for generate Angular component for [Hopeless Entity Analyzer](https://github.com/Bilgetz/HopelessEntityAnalyzer)

### How to use ###

```
buildscript {
 ...
    dependencies {
        classpath("fr.hopelessworld.plugin:HopelessEntityAnalyzer:0.2")
        classpath("fr.hopelessworld.plugin:HopelessAngularGeneratorStrategy:0.1")
   }
}

apply plugin: 'fr.hopelessworld.plugin.entity-analyzer'

analyzeEntity {
     entityDirectory = file("/src/main/java/fr/hopelessworld/sample/entity")
    strategies {
        controllers {
            strategyClass = "fr.hopelessworld.plugin.strategy.impl.AngularControllerStrategy"
            outputFiles = file("${buildDir}/generated/js/controllers.js")
        }
        directives {
            strategyClass = "fr.hopelessworld.plugin.strategy.impl.AngularDirectiveStrategy"
            outputFiles = file("${buildDir}/generated/js/directives.js")
        }
        factory {
            strategyClass = "fr.hopelessworld.plugin.strategy.impl.AngularFactoryStrategy"
            outputFiles = file("${buildDir}/generated/js/factorys.js")
        }
        route {
            strategyClass = "fr.hopelessworld.plugin.strategy.impl.AngularRouteStrategy"
            outputFiles = file("${buildDir}/generated/js/routes.js")
        }
        templates {
            strategyClass = "fr.hopelessworld.plugin.strategy.impl.AngularTemplateStrategy"
            outputFiles = file("${buildDir}/generated/js/templates.js")
        }
        customRest {
            strategyClass = "fr.hopelessworld.plugin.strategy.impl.AngularSpringDataRestCustomControllerStrategy"
            outputFiles = file("${buildDir}/generated/java")
        }
    }
}
```