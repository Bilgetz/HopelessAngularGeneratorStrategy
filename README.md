# README #

### Informations ###

This is strategie for generate Angular component for [Hopeless Entity Analyzer](https://github.com/Bilgetz/HopelessEntityAnalyzer)

### How to use ###

```
buildscript {
 ...
    dependencies {
        classpath("fr.hopelessworld.plugin:EntityAnalyzerPlugin:0.2")
        classpath("fr.hopelessworld.plugin:EmberGeneratorStrategy:1.0")
   }
}

apply plugin: 'fr.hopelessworld.plugin.entity-analyzer'

analyzeEntity {
     entityDirectory = file("/src/main/java/fr/hopelessworld/entity")
    strategies {
        controllers {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.AngularControllerStrategy"
            outputFiles = file("${buildDir}/generated/js/controllers.js")
        }
        directives {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.AngularDirectiveStrategy"
            outputFiles = file("${buildDir}/generated/directives.js")
        }
        factory {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.AngularFactoryStrategy"
            outputFiles = file("${buildDir}/generated/js/factorys.js")
        }
        route {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.AngularRouteStrategy"
            outputFiles = file("${buildDir}/generated/js/routes.js")
        }
        templates {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.AngularTemplateStrategy"
            outputFiles = file("${buildDir}/generated/js/templates.js")
        }
        customRest {
            strategyClass = "fr.hopelessword.plugin.strategy.impl.AngularSpringDataRestCustomControllerStrategy"
            outputFiles = file("${buildDir}/generated/java")
        }
    }
}
```