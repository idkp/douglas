package com.github.foskel.douglas.plugin.impl.load;

import com.github.foskel.douglas.plugin.Plugin;
import com.github.foskel.douglas.plugin.load.PluginLoader;
import com.github.foskel.douglas.plugin.load.priority.PluginPriorityResolver;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * @author Foskel
 */
public final class StandardPluginLoader implements PluginLoader {
    private final Comparator<Plugin> loadPriorityComparator;
    private final Comparator<Plugin> unloadPriorityComparator;
    private List<Plugin> cachedPlugins;

    @Inject
    StandardPluginLoader(PluginPriorityResolver priorityResolver) {
        this.loadPriorityComparator = new PluginLoadingPriorityComparator(priorityResolver);
        this.unloadPriorityComparator = new PluginUnloadingPriorityComparator(priorityResolver);
    }

    @Override
    public void load(Collection<Plugin> plugins) {
        if (cachedPlugins == null || !cachedPlugins.equals(plugins)) {
            cachedPlugins = new ArrayList<>(plugins);
        }

        cachedPlugins.sort(loadPriorityComparator);
        cachedPlugins.forEach(Plugin::load);
        /*plugins.stream()
                .sorted(LOAD_PRIORITY_COMPARATOR)
                .forEach(Plugin::load);*/
    }

    @Override
    public void unload(Collection<Plugin> plugins) {
        if (cachedPlugins == null || !cachedPlugins.equals(plugins)) {
            cachedPlugins = new ArrayList<>(plugins);
        }

        cachedPlugins.sort(unloadPriorityComparator);
        cachedPlugins.forEach(Plugin::unload);
    }

    private static class PluginLoadingPriorityComparator implements Comparator<Plugin> {
        private final PluginPriorityResolver resolver;

        PluginLoadingPriorityComparator(PluginPriorityResolver resolver) {
            this.resolver = resolver;
        }

        @Override
        public int compare(Plugin firstPlugin, Plugin secondPlugin) {
            return this.resolver.resolveLoadingPriority(firstPlugin).compareTo(
                    this.resolver.resolveLoadingPriority(secondPlugin));
        }
    }

    private static class PluginUnloadingPriorityComparator implements Comparator<Plugin> {
        private final PluginPriorityResolver resolver;

        PluginUnloadingPriorityComparator(PluginPriorityResolver resolver) {
            this.resolver = resolver;
        }

        @Override
        public int compare(Plugin firstPlugin, Plugin secondPlugin) {
            return this.resolver.resolveUnloadingPriority(firstPlugin).compareTo(
                    this.resolver.resolveUnloadingPriority(secondPlugin));
        }
    }
}