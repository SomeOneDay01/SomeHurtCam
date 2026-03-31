package com.somehurtcam.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MinecraftVersionCompat {
    private static final Pattern SEMVER_NUMBERS = Pattern.compile("^(\\d+)\\.(\\d+)(?:\\.(\\d+))?.*");

    private MinecraftVersionCompat() {
    }

    public static String getCurrentMinecraftVersion() {
        Optional<ModMetadata> metadata = FabricLoader.getInstance()
            .getModContainer("minecraft")
            .map(container -> container.getMetadata());
        return metadata.map(modMetadata -> modMetadata.getVersion().getFriendlyString()).orElse("0.0.0");
    }

    public static boolean isAtLeast(int major, int minor, int patch) {
        int[] current = parseVersion(getCurrentMinecraftVersion());
        return compare(current, new int[]{major, minor, patch}) >= 0;
    }

    public static boolean isBetweenInclusive(int minMajor, int minMinor, int minPatch, int maxMajor, int maxMinor, int maxPatch) {
        int[] current = parseVersion(getCurrentMinecraftVersion());
        return compare(current, new int[]{minMajor, minMinor, minPatch}) >= 0
            && compare(current, new int[]{maxMajor, maxMinor, maxPatch}) <= 0;
    }

    private static int[] parseVersion(String rawVersion) {
        Matcher matcher = SEMVER_NUMBERS.matcher(rawVersion);
        if (!matcher.matches()) {
            return new int[]{0, 0, 0};
        }

        int major = Integer.parseInt(matcher.group(1));
        int minor = Integer.parseInt(matcher.group(2));
        int patch = matcher.group(3) == null ? 0 : Integer.parseInt(matcher.group(3));
        return new int[]{major, minor, patch};
    }

    private static int compare(int[] left, int[] right) {
        if (left[0] != right[0]) {
            return Integer.compare(left[0], right[0]);
        }
        if (left[1] != right[1]) {
            return Integer.compare(left[1], right[1]);
        }
        return Integer.compare(left[2], right[2]);
    }
}
