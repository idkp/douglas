package com.github.foskel.douglas;

import com.github.foskel.douglas.core.version.Tag;
import com.github.foskel.douglas.core.version.Version;
import com.github.foskel.douglas.plugin.descriptor.extract.PluginDescriptorExtractor;
import com.github.foskel.douglas.plugin.descriptor.extract.PluginDescriptorExtractors;
import com.github.foskel.douglas.plugin.impl.descriptor.extract.ClassLoaderDataFileURLExtractor;
import com.github.foskel.douglas.plugin.impl.descriptor.extract.PluginDescriptorExtractorBuilder;
import com.github.foskel.douglas.plugin.impl.descriptor.extract.XMLPluginDescriptorParser;

@SuppressWarnings("WeakerAccess")
public final class Douglas {
    private static final Version VERSION = Version.builder()
            .major(0)
            .minor(1)
            .patch(0)
            .addTag(Tag.RELEASE)

            .build();

    private Douglas() {
    }

    public static Version getVersion() {
        return VERSION;
    }

    public static PluginDescriptorExtractor newPluginDescriptorExtractor() {
        return newPluginDescriptorExtractorBuilder()
                .withDataFileURLExtractor(new ClassLoaderDataFileURLExtractor())
                .withDescriptorParser(new XMLPluginDescriptorParser())
                .withDataFilePath(PluginDescriptorExtractors.getStandardConfigurationFilePath())

                .build();
    }

    public static PluginDescriptorExtractorBuilder newPluginDescriptorExtractorBuilder() {
        return new PluginDescriptorExtractorBuilder();
    }
}