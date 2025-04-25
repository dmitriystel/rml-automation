package net.rml.auto.core.utils;

import java.io.File;
import java.util.regex.Matcher;

/**
 * It is in charged to perform operations to paths.
 */
public final class PathUtils {

    private static final String CURRENT_DIRECTORY = ".";
    private static final String EMPTY_STRING = "";
    private static final String REPLACE_DOT_REGEX = "\\.$";
    private static final String PATH_WINDOWS_REGEX = "^[A-Z]:\\\\.*";
    private static final String SLASH = "/";
    private static final String BACK_SLASH = "\\";
    private static final String PATH_REGEX = ".+[/\\\\]rml-automation-[\\w]{1,4}[/\\\\]\\.$";
    private static final String REPLACE_REGEX = "rml-automation-[\\w]{1,4}[/\\\\]\\.$";

    /**
     * Private constructor for {@link PathUtils} utility class.
     */
    private PathUtils() {
        //Default constructor.
    }

    /**
     * Gets the current absolute path of the working directory.
     *
     * @param path base directory path.
     * @return current absolute path.
     */
    public static String getRelativePath(final String path) {
        File currentDirectory = new File(new File(CURRENT_DIRECTORY).getAbsolutePath());
        String currentPath = currentDirectory.getAbsolutePath();
        String relativePath = currentPath.replaceAll(REPLACE_DOT_REGEX, EMPTY_STRING).concat(path);
        return relativePath.matches(PATH_WINDOWS_REGEX)
                ? relativePath.replaceAll(SLASH, Matcher.quoteReplacement(BACK_SLASH)) : relativePath;
    }

    /**
     * Gets the current absolute path of the working directory.
     *
     * @param path base directory path.
     * @return current absolute path.
     */
    public static String getAbsPath(final String path) {
        String currentPath = new File(CURRENT_DIRECTORY).getAbsolutePath();
        String relativePath = currentPath.matches(PATH_REGEX)
                ? currentPath.replaceAll(REPLACE_REGEX, EMPTY_STRING).concat(path)
                : currentPath.replaceAll(REPLACE_DOT_REGEX, EMPTY_STRING).concat(path);
        return relativePath.matches(PATH_WINDOWS_REGEX)
                ? relativePath.replaceAll(SLASH, Matcher.quoteReplacement(BACK_SLASH)) : relativePath;
    }
}
