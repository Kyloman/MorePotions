package ascendthespire.patches;

import ascendthespire.AscendTheSpire;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.AbstractPotion.PotionRarity;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.returnRandomPotion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@SpirePatch(
        clz=AbstractDungeon.class,
        method="returnRandomPotion",
        paramtypez={
        boolean.class
        }
)
public class PotionRarityPatch { //needs to be refactored with SpireInsert eventually
    public static final Logger logger = LogManager.getLogger(AscendTheSpire.class.getName());
    @SpirePostfixPatch
    public static AbstractPotion Postfix(AbstractPotion result, boolean limited) {



            if (result.rarity == PotionRarity.RARE){
                final int roll = AbstractDungeon.relicRng.random(0, 99);
                logger.info("Rolling for legendary...");
                if (roll < 1000) {
                    logger.info("Rolled a legendary!");
                    AbstractPotion p = returnRandomPotion(PotionRarityEnum.LEGENDARY, limited);
                    return p; //return Legendary potion
                }
            }
            return result; //potion was not converted to Legendary

    }


}


/*
        public static AbstractPotion returnRandomPotion ( final boolean limited){
            final int roll = AbstractDungeon.potionRng.random(0, 99);
            if (roll < PotionHelper.POTION_COMMON_CHANCE) {
                return returnRandomPotion(AbstractPotion.PotionRarity.COMMON, limited);
            }
            if (roll < PotionHelper.POTION_UNCOMMON_CHANCE + PotionHelper.POTION_COMMON_CHANCE) {
                return returnRandomPotion(AbstractPotion.PotionRarity.UNCOMMON, limited);
            }
            return returnRandomPotion(AbstractPotion.PotionRarity.RARE, limited);
 */