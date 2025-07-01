package com.vini.dev.twapi;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModularityTests {

    ApplicationModules modules = ApplicationModules.of(TwapiApplication.class);

    @Test
    void verifiesModularStructure() {
	    this.modules.verify();
    }

    @Test
    void createModuleDocumentation() {
        new Documenter(this.modules).writeDocumentation().writeModulesAsPlantUml();
    }

}
