package ascendthespire.potions;

import ascendthespire.AscendTheSpire;
import ascendthespire.patches.PotionRarityEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

//logger
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ascendthespire.powers.OmnipresentPower;

public class OmnipresenceNectar extends AbstractPotion
{
    public static final Logger logger = LogManager.getLogger(AscendTheSpire.class.getName());

    public static final String POTION_ID = "ATS:OmnipresenceNectar";


    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public OmnipresenceNectar() {


        super(NAME, POTION_ID, PotionRarityEnum.LEGENDARY, PotionSize.GHOST, PotionColor.WHITE);

        //
        logger.info("Generating potion");

        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0]);
        this.isThrown = false;
        this.targetRequired = false;
        this.tips.add(new PowerTip(this.name, this.description));

        logger.info("Finished generating potion");
    }


    public void use(final AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new OmnipresentPower(AbstractDungeon.player,this.potency), this.potency));

        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player, new StrengthPower(target, this.potency), this.potency)); give enemy 5 Str
    }

    //com.megacrit.cardcrawl.potions.AbstractPotion.PotionRarity

    @Override
    public AbstractPotion makeCopy() {
        return new OmnipresenceNectar();
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