package ascendthespire.potions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

//logger
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


public class HotCoffee extends AbstractPotion
{
    //public static final Logger logger = LogManager.getLogger(AscendTheSpire.class.getName());

    public static final String POTION_ID = "ATS:HotCoffeePotion";


    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public HotCoffee() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.BOLT, PotionColor.ENERGY);

        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1] + this.potency/2 + DESCRIPTIONS[2]);
        this.isThrown = false;
        this.targetRequired = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void use(final AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.potency));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Burn(), 2));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new HotCoffee();
    }

    @Override
    public int getPotency(final int ascensionLevel) {
        return 4;
    }

}