package rotmg.application.api;

public interface ApplicationSetup extends DebugSetup {

	String getBuildLabel();

	String getAppEngineUrl(boolean param1);

	String getAnalyticsCode();

	boolean useLocalTextures();

	boolean isToolingEnabled();

	boolean areDeveloperHotkeysEnabled();

	boolean isGameLoopMonitored();

	boolean useProductionDialogs();

	boolean areErrorsReported();

	boolean isServerLocal();

	String getServerDomain();

}
