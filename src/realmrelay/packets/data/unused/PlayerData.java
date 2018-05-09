package realmrelay.packets.data.unused;

import realmrelay.packets.data.StatData;
import realmrelay.packets.data.WorldPosData;

public class PlayerData extends StatData {

	// values

	public int max_hp_stat = 0;
	public int hp_stat = 0;
	public int size_stat = 0;
	public int max_mp_stat = 0;
	public int mp_stat = 0;
	public int next_level_exp_stat = 0;
	public int exp_stat = 0;
	public int level_stat = 0;
	public int attack_stat = 0;
	public int defense_stat = 0;
	public int speed_stat = 0;
	public int[] inventory = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	public int vitality_stat = 0;
	public int wisdom_stat = 0;
	public int dexterity_stat = 0;
	public int condition_stat = 0;
	public int num_stars_stat = 0;
	public String name_stat = "null";
	public int tex1_stat = 0;
	public int tex2_stat = 0;
	public int merchandise_type_stat = 0;
	public int credits_stat = 0;
	public int merchandise_price_stat = 0;
	public int active_stat = 0;
	public String account_id_stat = "";
	public int fame_stat = 0;
	public int merchandise_currency_stat = 0;
	public int connect_stat = 0;
	public int merchandise_count_stat = 0;
	public int merchandise_mins_left_stat = 0;
	public int merchandise_discount_stat = 0;
	public int merchandise_rank_req_stat = 0;
	public int max_hp_boost_stat = 0;
	public int max_mp_boost_stat = 0;
	public int attack_boost_stat = 0;
	public int defense_boost_stat = 0;
	public int speed_boost_stat = 0;
	public int vitality_boost_stat = 0;
	public int wisdom_boost_stat = 0;
	public int dexterity_boost_stat = 0;
	public int owner_account_id_stat = 0;
	public int rank_required_stat = 0;
	public int name_chosen_stat = 0;
	public int curr_fame_stat = 0;
	public int next_class_quest_fame_stat = 0;
	public int legendary_rank_stat = 0;
	public int sink_level_stat = 0;
	public int alt_texture_stat = 0;
	public String guild_name_stat = "";
	public int guild_rank_stat = 0;
	public int breath_stat = 0;
	public int xp_boosted_stat = 0;
	public int xp_timer_stat = 0;
	public int ld_timer_stat = 0;
	public int lt_timer_stat = 0;
	public int health_potion_stack_stat = 0;
	public int magic_potion_stack_stat = 0;
	public int[] backPack = { -1, -1, -1, -1, -1, -1, -1, -1 };
	public int hasbackpack_stat = 0;
	public int texture_stat = 0;
	public int pet_instanceid_stat = 0;
	public int pet_name_stat = 0;
	public int pet_type_stat = 0;
	public int pet_rarity_stat = 0;
	public int pet_maxabilitypower_stat = 0;
	public int pet_family_stat = 0;
	public int pet_firstability_point_stat = 0;
	public int pet_secondability_point_stat = 0;
	public int pet_thirdability_point_stat = 0;
	public int pet_firstability_power_stat = 0;
	public int pet_secondability_power_stat = 0;
	public int pet_thirdability_power_stat = 0;
	public int pet_firstability_type_stat = 0;
	public int pet_secondability_type_stat = 0;
	public int pet_thirdability_type_stat = 0;
	public int new_con_stat = 0;
	public int fortune_token_stat = 0;

	/* User defined variables */
	public WorldPosData pos = new WorldPosData();
	public String mapName = "null";

	public short objectType; //class :)

	//

	public void parseNewTICK(StatData[] statData) {

		for (StatData data : statData) {
			parseStatData(data.statType, data.statValue, data.strStatValue);
		}

	}

	private void parseStatData(int id, int intValue, String stringValue) {

        switch (id) {
            case MAX_HP_STAT:
                this.max_hp_stat = intValue;
                break;
            case HP_STAT:
                this.hp_stat = intValue;
                break;
            case SIZE_STAT:
                this.size_stat = intValue;
                break;
            case MAX_MP_STAT:
                this.max_mp_stat = intValue;
                break;
            case MP_STAT:
                this.mp_stat = intValue;
                break;
            case NEXT_LEVEL_EXP_STAT:
                this.next_level_exp_stat = intValue;
                break;
            case EXP_STAT:
                this.exp_stat = intValue;
                break;
            case LEVEL_STAT:
                this.level_stat = intValue;
                break;
            case ATTACK_STAT:
                this.attack_stat = intValue;
                break;
            case DEFENSE_STAT:
                this.defense_stat = intValue;
                break;
            case SPEED_STAT:
                this.speed_stat = intValue;
                break;
            case INVENTORY_0_STAT:
                this.inventory[0] = intValue;
                break;
            case INVENTORY_1_STAT:
                this.inventory[1] = intValue;
                break;
            case INVENTORY_2_STAT:
                this.inventory[2] = intValue;
                break;
            case INVENTORY_3_STAT:
                this.inventory[3] = intValue;
                break;
            case INVENTORY_4_STAT:
                this.inventory[4] = intValue;
                break;
            case INVENTORY_5_STAT:
                this.inventory[5] = intValue;
                break;
            case INVENTORY_6_STAT:
                this.inventory[6] = intValue;
                break;
            case INVENTORY_7_STAT:
                this.inventory[7] = intValue;
                break;
            case INVENTORY_8_STAT:
                this.inventory[8] = intValue;
                break;
            case INVENTORY_9_STAT:
                this.inventory[9] = intValue;
                break;
            case INVENTORY_10_STAT:
                this.inventory[10] = intValue;
                break;
            case INVENTORY_11_STAT:
                this.inventory[11] = intValue;
                break;
            case VITALITY_STAT:
                this.vitality_stat = intValue;
                break;
            case WISDOM_STAT:
                this.wisdom_stat = intValue;
                break;
            case DEXTERITY_STAT:
                this.dexterity_stat = intValue;
                break;
            case CONDITION_STAT:
                this.condition_stat = intValue;
                break;
            case NUM_STARS_STAT:
                this.num_stars_stat = intValue;
                break;
            case NAME_STAT:
                this.name_stat = stringValue;
                break;
            case TEX1_STAT:
                this.tex1_stat = intValue;
                break;
            case TEX2_STAT:
                this.tex2_stat = intValue;
                break;
            case MERCHANDISE_TYPE_STAT:
                this.merchandise_type_stat = intValue;
                break;
            case CREDITS_STAT:
                this.credits_stat = intValue;
                break;
            case MERCHANDISE_PRICE_STAT:
                this.merchandise_price_stat = intValue;
                break;
            case ACTIVE_STAT:
                this.active_stat = intValue;
                break;
            case ACCOUNT_ID_STAT:
                this.account_id_stat = stringValue;
                break;
            case FAME_STAT:
                this.fame_stat = intValue;
                break;
            case MERCHANDISE_CURRENCY_STAT:
                this.merchandise_currency_stat = intValue;
                break;
            case CONNECT_STAT:
                this.connect_stat = intValue;
                break;
            case MERCHANDISE_COUNT_STAT:
                this.merchandise_count_stat = intValue;
                break;
            case MERCHANDISE_MINS_LEFT_STAT:
                this.merchandise_mins_left_stat = intValue;
                break;
            case MERCHANDISE_DISCOUNT_STAT:
                this.merchandise_discount_stat = intValue;
                break;
            case MERCHANDISE_RANK_REQ_STAT:
                this.rank_required_stat = intValue;
                break;
            case MAX_HP_BOOST_STAT:
                this.max_hp_boost_stat = intValue;
                break;
            case MAX_MP_BOOST_STAT:
                this.max_mp_boost_stat = intValue;
                break;
            case ATTACK_BOOST_STAT:
                this.attack_boost_stat = intValue;
                break;
            case DEFENSE_BOOST_STAT:
                this.defense_boost_stat = intValue;
                break;
            case SPEED_BOOST_STAT:
                this.speed_boost_stat = intValue;
                break;
            case VITALITY_BOOST_STAT:
                this.vitality_boost_stat = intValue;
                break;
            case WISDOM_BOOST_STAT:
                this.wisdom_boost_stat = intValue;
                break;
            case DEXTERITY_BOOST_STAT:
                this.dexterity_boost_stat = intValue;
                break;
            case OWNER_ACCOUNT_ID_STAT:
                this.owner_account_id_stat = intValue;
                break;
            case RANK_REQUIRED_STAT:
                this.rank_required_stat = intValue;
                break;
            case NAME_CHOSEN_STAT:
                this.name_chosen_stat = intValue;
                break;
            case CURR_FAME_STAT:
                this.curr_fame_stat = intValue;
                break;
            case NEXT_CLASS_QUEST_FAME_STAT:
                this.next_class_quest_fame_stat = intValue;
                break;
            case LEGENDARY_RANK_STAT:
                this.legendary_rank_stat = intValue;
                break;
            case SINK_LEVEL_STAT:
                this.sink_level_stat = intValue;
                break;
            case ALT_TEXTURE_STAT:
                this.alt_texture_stat = intValue;
                break;
            case GUILD_NAME_STAT:
                this.guild_name_stat = stringValue;
                break;
            case GUILD_RANK_STAT:
                this.guild_rank_stat = intValue;
                break;
            case BREATH_STAT:
                this.breath_stat = intValue;
                break;
            case XP_BOOSTED_STAT:
                this.xp_boosted_stat = intValue;
                break;
            case XP_TIMER_STAT:
                this.xp_timer_stat = intValue;
                break;
            case LD_TIMER_STAT:
                this.ld_timer_stat = intValue;
                break;
            case LT_TIMER_STAT:
                this.lt_timer_stat = intValue;
                break;
            case HEALTH_POTION_STACK_STAT:
                this.health_potion_stack_stat = intValue;
                break;
            case MAGIC_POTION_STACK_STAT:
                this.magic_potion_stack_stat = intValue;
                break;
            case BACKPACK_0_STAT:
                this.backPack[0] = intValue;
                break;
            case BACKPACK_1_STAT:
                this.backPack[1] = intValue;
                break;
            case BACKPACK_2_STAT:
                this.backPack[2] = intValue;
                break;
            case BACKPACK_3_STAT:
                this.backPack[3] = intValue;
                break;
            case BACKPACK_4_STAT:
                this.backPack[4] = intValue;
                break;
            case BACKPACK_5_STAT:
                this.backPack[5] = intValue;
                break;
            case BACKPACK_6_STAT:
                this.backPack[6] = intValue;
                break;
            case BACKPACK_7_STAT:
                this.backPack[7] = intValue;
                break;
            case HASBACKPACK_STAT:
                this.hasbackpack_stat = intValue;
                break;
            case TEXTURE_STAT:
                this.texture_stat = intValue;
                break;
            case PET_INSTANCEID_STAT:
                this.pet_instanceid_stat = intValue;
                break;
            case PET_NAME_STAT:
                this.pet_name_stat = intValue;
                break;
            case PET_TYPE_STAT:
                this.pet_type_stat = intValue;
                break;
            case PET_RARITY_STAT:
                this.pet_rarity_stat = intValue;
                break;
            case PET_MAXABILITYPOWER_STAT:
                this.pet_maxabilitypower_stat = intValue;
                break;
            case PET_FAMILY_STAT:
                this.pet_family_stat = intValue;
                break;
            case PET_FIRSTABILITY_POINT_STAT:
                this.pet_firstability_point_stat = intValue;
                break;
            case PET_SECONDABILITY_POINT_STAT:
                this.pet_secondability_point_stat = intValue;
                break;
            case PET_THIRDABILITY_POINT_STAT:
                this.pet_thirdability_point_stat = intValue;
                break;
            case PET_FIRSTABILITY_POWER_STAT:
                this.pet_firstability_power_stat = intValue;
                break;
            case PET_SECONDABILITY_POWER_STAT:
                this.pet_secondability_power_stat = intValue;
                break;
            case PET_THIRDABILITY_POWER_STAT:
                this.pet_thirdability_power_stat = intValue;
                break;
            case PET_FIRSTABILITY_TYPE_STAT:
                this.pet_firstability_type_stat = intValue;
                break;
            case PET_SECONDABILITY_TYPE_STAT:
                this.pet_secondability_power_stat = intValue;
                break;
            case PET_THIRDABILITY_TYPE_STAT:
                this.pet_thirdability_point_stat = intValue;
                break;
            case NEW_CON_STAT:
                this.new_con_stat = intValue;
                break;
            case FORTUNE_TOKEN_STAT:
                this.fortune_token_stat = intValue;
                break;
        }
	}

	public String getStringValueFor(int id) {

        switch (id) {
            case 31:
                return name_stat;
            case 62:
                return guild_name_stat;
            case 38:
                return account_id_stat;
            default:
                System.err.println("Invalid playerData id, could not get string value for id : " + id);
                break;
        }
		return null;
	}

	public int getIntValueFor(int id) {

        switch (id) {
            case 0:
                return max_hp_stat;
            case 1:
                return hp_stat;
            case 2:
                return size_stat;
            case 3:
                return max_mp_stat;
            case 4:
                return mp_stat;
            case 5:
                return next_level_exp_stat;
            case 6:
                return exp_stat;
            case 7:
                return level_stat;
            case 20:
                return attack_stat;
            case 21:
                return defense_stat;
            case 22:
                return speed_stat;
            case 8:
                return inventory[0];
            case 9:
                return inventory[1];
            case 10:
                return inventory[2];
            case 11:
                return inventory[3];
            case 12:
                return inventory[4];
            case 13:
                return inventory[5];
            case 14:
                return inventory[6];
            case 15:
                return inventory[7];
            case 16:
                return inventory[8];
            case 17:
                return inventory[9];
            case 18:
                return inventory[10];
            case 19:
                return inventory[11];
            case 26:
                return vitality_stat;
            case 27:
                return wisdom_stat;
            case 28:
                return dexterity_stat;
            case 29:
                return condition_stat;
            case 30:
                return num_stars_stat;
            case 32:
                return tex1_stat;
            case 33:
                return tex2_stat;
            case 34:
                return merchandise_type_stat;
            case 35:
                return credits_stat;
            case 36:
                return merchandise_price_stat;
            case 37:
                return active_stat;
            case 39:
                return fame_stat;
            case 40:
                return merchandise_currency_stat;
            case 41:
                return connect_stat;
            case 42:
                return merchandise_count_stat;
            case 43:
                return merchandise_mins_left_stat;
            case 44:
                return merchandise_discount_stat;
            case 45:
                return merchandise_rank_req_stat;
            case 46:
                return max_hp_boost_stat;
            case 47:
                return max_mp_boost_stat;
            case 48:
                return attack_boost_stat;
            case 49:
                return defense_boost_stat;
            case 50:
                return speed_boost_stat;
            case 51:
                return vitality_boost_stat;
            case 52:
                return wisdom_boost_stat;
            case 53:
                return dexterity_boost_stat;
            case 54:
                return owner_account_id_stat;
            case 55:
                return rank_required_stat;
            case 56:
                return name_chosen_stat;
            case 57:
                return curr_fame_stat;
            case 58:
                return next_class_quest_fame_stat;
            case 59:
                return legendary_rank_stat;
            case 60:
                return sink_level_stat;
            case 61:
                return alt_texture_stat;
            case 63:
                return guild_rank_stat;
            case 64:
                return breath_stat;
            case 65:
                return xp_boosted_stat;
            case 66:
                return xp_timer_stat;
            case 67:
                return ld_timer_stat;
            case 68:
                return lt_timer_stat;
            case 69:
                return health_potion_stack_stat;
            case 70:
                return magic_potion_stack_stat;
            case 71:
                return backPack[0];
            case 72:
                return backPack[1];
            case 73:
                return backPack[2];
            case 74:
                return backPack[3];
            case 75:
                return backPack[4];
            case 76:
                return backPack[5];
            case 77:
                return backPack[6];
            case 78:
                return backPack[7];
            case 79:
                return hasbackpack_stat;
            case 80:
                return texture_stat;
            case 81:
                return pet_instanceid_stat;
            case 82:
                return pet_name_stat;
            case 83:
                return pet_type_stat;
            case 84:
                return pet_rarity_stat;
            case 85:
                return pet_maxabilitypower_stat;
            case 86:
                return pet_family_stat;
            case 87:
                return pet_firstability_point_stat;
            case 88:
                return pet_secondability_point_stat;
            case 89:
                return pet_thirdability_point_stat;
            case 90:
                return pet_firstability_power_stat;
            case 91:
                return pet_secondability_power_stat;
            case 92:
                return pet_thirdability_power_stat;
            case 93:
                return pet_firstability_type_stat;
            case 94:
                return pet_secondability_type_stat;
            case 95:
                return pet_thirdability_type_stat;
            case 96:
                return new_con_stat;
            case 97:
                return fortune_token_stat;
            default:
                System.err.println("Invalid playerData id, could not get int value for id : " + id);
                break;
        }
		return 0;
	}

}
