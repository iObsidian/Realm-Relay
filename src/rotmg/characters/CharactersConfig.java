package rotmg.characters;

import robotlegs.bender.extensions.signalCommandMap.api.ISignalCommandMap;
import robotlegs.bender.framework.api.IConfig;
import robotlegs.bender.framework.api.IContext;
import rotmg.characters.deletion.DeletionConfig;
import rotmg.characters.reskin.ReskinConfig;

public class CharactersConfig implements IConfig {

	public IContext context;

	public ISignalCommandMap commandMap;

	public CharactersConfig() {
		super();
	}

	public void configure() {
		//this.injector.map(CharacterModel).toSingleton(LegacyCharacterModel);
		this.context.configure(DeletionConfig.class);
		this.context.configure(ReskinConfig.class);
	}
}