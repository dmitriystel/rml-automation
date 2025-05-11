package net.rml.auto.ui.context;

public class TestContext {
    private static final ThreadLocal<String> baseUrl = new ThreadLocal<>();

    // Set the baseUrl for each thread
    public static void setBaseUrl(String url) {
        baseUrl.set(url);
    }

    // Retrieve the baseUrl for the current thread
    public static String getBaseUrl() {
        return baseUrl.get();
    }

    // Clean up the ThreadLocal value
    public static void clear() {
        baseUrl.remove();
    }
}
