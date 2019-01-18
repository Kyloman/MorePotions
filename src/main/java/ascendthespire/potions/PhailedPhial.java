package ascendthespire.potions;


import ascendthespire.patches.PotionRarityEnum;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

//logger
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


public class PhailedPhial extends CustomPotion
{
    //public static final Logger logger = LogManager.getLogger(AscendTheSpire.class.getName());

    public static final String POTION_ID = "ATS:PhailedPhial";


    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public PhailedPhial() {
        super(NAME, POTION_ID, PotionRarityEnum.MINI, PotionSize.SPHERE, PotionColor.FIRE);

        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1]);
        this.isThrown = true;
        this.targetRequired = true;
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void use(final AbstractCreature target) {

        final DamageInfo info = new DamageInfo(AbstractDungeon.player, this.potency, DamageInfo.DamageType.THORNS);
        info.applyEnemyPowersOnly(target);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(target, info, AbstractGameAction.AttackEffect.FIRE));


    }

    @Override
    public AbstractPotion makeCopy() {
        return new PhailedPhial();
    }

    @Override
    public int getPotency(final int ascensionLevel) {
        return 1;
    }

}