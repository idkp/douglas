package com.github.foskel.douglas.plugin.dependency;

import com.github.foskel.douglas.plugin.Plugin;
import com.github.foskel.douglas.plugin.locate.PluginLocatorService;
import com.github.foskel.douglas.plugin.manifest.PluginDescriptor;
import com.github.foskel.haptor.DependencySystem;

/**
 * @author Foskel
 */
public interface PluginDependencySystem extends DependencySystem<PluginDescriptor, PluginDescriptor, Plugin> {
    void satisfy(PluginLocatorService dependencyLocator);

    <T extends Plugin> T find(String groupId, String artifactId);
}