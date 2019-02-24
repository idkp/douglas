package com.github.foskel.douglas.module;

import com.github.foskel.douglas.core.traits.Loadable;
import com.github.foskel.douglas.core.traits.Named;
import com.github.foskel.douglas.core.traits.Reloadable;
import com.github.foskel.haptor.DependencySystem;

/**
 * @author Foskel
 * @since 4/4/2017
 */
public interface Module extends Named, Loadable, Reloadable {

    @Override
    String getName();

    @Override
    void load();

    @Override
    void unload();

    @Override
    default void reload() {
        this.unload();
        this.load();
    }

    DependencySystem<Class<? extends Module>, Module> getDependencySystem();
}