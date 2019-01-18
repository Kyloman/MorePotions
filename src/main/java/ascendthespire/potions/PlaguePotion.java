package ascendthespire.potions;


import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

//logger
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


public class PlaguePotion extends CustomPotion
{
    //public static final Logger logger = LogManager.getLogger(AscendTheSpire.class.getName());

    public static final String POTION_ID = "ATS:PlaguePotion";


    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public PlaguePotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.BOTTLE, PotionColor.POISON);

        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1]);
        this.isThrown = true;
        this.targetRequired = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void use(final AbstractCreature target) {


        for (final AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, null, new PoisonPower(monster, null, this.getPotency()), this.getPotency()));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, null, new StrengthPower(monster, -this.getReduction(1)), -this.getReduction(1), true, AbstractGameAction.AttackEffect.NONE));

            }
        }
    }

    @Override
    public AbstractPotion makeCopy() {
        return new PlaguePotion();
    }

    @Override
    public int getPotency(final int ascensionLevel) {
        return 4;
    }

    public int getReduction(final int ascensionLevel) {
        return 1;
    }

}