package com.github.foskel.douglas.module;

import com.github.foskel.haptor.DependencySystem;
import com.github.foskel.haptor.Haptor;

import java.util.Objects;

/**
 * @author Foskel
 * @since 2/21/2017
 */
@SuppressWarnings("WeakerAccess")
public abstract class AbstractModule implements Module {
    private final String name;
    private final DependencySystem<Class<? extends Module>, Module> dependencySystem;

    public AbstractModule(String name) {
        this.name = name;
        this.dependencySystem = Haptor.newDependencySystem();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void load() {
    }

    @Override
    public void unload() {
    }

    @Override
    public DependencySystem<Class<? extends Module>, Module> getDependencySystem() {
        return this.dependencySystem;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int hash = 1;

        hash = 31 * hash + this.name.hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Module)) {
            return false;
        }

        Module other = (Module) object;

        return Objects.equals(other.getName(), this.getName());
    }
}