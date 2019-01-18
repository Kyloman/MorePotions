package ascendthespire.potions;


import ascendthespire.patches.PotionRarityEnum;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.StrengthPower;



public class SwiftPhial extends CustomPotion
{


    public static final String POTION_ID = "ATS:SwiftPhial";


    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public SwiftPhial() {
        super(NAME, POTION_ID, PotionRarityEnum.MINI, PotionSize.S, PotionColor.STRENGTH);

        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1]);
        this.isThrown = false;
        this.targetRequired = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void use(final AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.potency));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new SwiftPhial();
    }

    @Override
    public int getPotency(final int ascensionLevel) {
        return 1;
    }

}