package rotmg.api;

public interface ApplicationSetup {

    String getBuildLabel();

    String getAppEngineUrl(boolean param1);

    String getAnalyticsCode();

    boolean useLocalTextures();

    boolean isToolingEnabled();

    boolean areDeveloperHotkeysEnabled();

    boolean isGameLoopMonitored();

    boolean useProductionDialogs();

    boolean areErrorsReported();

}
