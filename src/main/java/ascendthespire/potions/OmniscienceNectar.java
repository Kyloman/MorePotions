package ascendthespire.potions;

import ascendthespire.patches.PotionRarityEnum;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

//logger
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


public class OmniscienceNectar extends CustomPotion
{
    //public static final Logger logger = LogManager.getLogger(AscendTheSpire.class.getName());

    public static final String POTION_ID = "ATS:OmniscienceNectar";


    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public OmniscienceNectar() {

        super(NAME, POTION_ID, PotionRarityEnum.LEGENDARY, PotionSize.GHOST, PotionColor.WHITE);

        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1] + this.potency + DESCRIPTIONS[2]);
        this.isThrown = false;
        this.targetRequired = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void use(final AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom(new ApotheosisAction());
    }

    @Override
    public AbstractPotion makeCopy() {
        return new OmniscienceNectar();
    }

    @Override
    public int getPotency(final int ascensionLevel) {
        return 5;
    }

    @Override
    public int getPrice() {
        return 150;
    }

}