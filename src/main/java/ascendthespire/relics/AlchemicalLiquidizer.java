package ascendthespire.relics;



import ascendthespire.AscendTheSpire;
import ascendthespire.patches.PotionRarityEnum;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.returnRandomPotion;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.returnTotallyRandomPotion;


public class AlchemicalLiquidizer extends CustomRelic
{

    public static final Logger logger = LogManager.getLogger(AscendTheSpire.class.getName());

    public static final String ID = "ATS:AlchemicalLiquidizer";
    public static final String IMG_PATH = "relics/atsbetarelic.png";
    public static final String OUTLINE_IMG_PATH = "relics/atsbetarelicOUTLINE.png";

    int previousGold;


    public AlchemicalLiquidizer() {
        super(ID, new Texture(ascendthespire.AscendTheSpire.getResourcePath(IMG_PATH)), new Texture(ascendthespire.AscendTheSpire.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }



   public void onEquip() {
        previousGold = AbstractDungeon.player.gold;
    }


    @Override
    public void onGainGold() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        int amount = AbstractDungeon.player.gold - previousGold;
        logger.info("AMOUNT IS: " + amount);
        if (amount > 0) {
            if (amount < 20){
                AbstractDungeon.player.obtainPotion(returnRandomPotion(PotionRarityEnum.MINI, true));
            }
            else if (amount >= 20 && amount < 40){
                AbstractDungeon.player.obtainPotion(returnRandomPotion(AbstractPotion.PotionRarity.COMMON, true));
            }
            else if (amount >= 40 && amount < 75){
                AbstractDungeon.player.obtainPotion(returnRandomPotion(AbstractPotion.PotionRarity.UNCOMMON, true));
            }
            else if (amount >= 75 && amount < 100){
                AbstractDungeon.player.obtainPotion(returnRandomPotion(AbstractPotion.PotionRarity.RARE, true));
            }
            else if (amount >= 100){
                logger.info("Amount is over 100, so here is a legendary!");
                AbstractDungeon.player.obtainPotion(returnRandomPotion(PotionRarityEnum.LEGENDARY, true));
            }
            else { //how do we get here? idk. lets be sure of no SHENNANIGANS though
                AbstractDungeon.player.obtainPotion(returnTotallyRandomPotion());
            }
        }
        previousGold = AbstractDungeon.player.gold;
    }

    @Override
    public void onLoseGold() {
        previousGold = AbstractDungeon.player.gold;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new AlchemicalLiquidizer();

    }
}
