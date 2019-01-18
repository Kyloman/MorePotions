package ascendthespire.potions;


import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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


public class FlashPotion extends AbstractPotion
{
    //public static final Logger logger = LogManager.getLogger(AscendTheSpire.class.getName());

    public static final String POTION_ID = "ATS:FlashPotion";


    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public FlashPotion() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.SPHERE, PotionColor.SMOKE);

        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0]);
        this.isThrown = true;
        this.targetRequired = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void use(final AbstractCreature target) {


        for (final AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, null, new StunMonsterPower(monster)));
            }
        }
    }

    @Override
    public AbstractPotion makeCopy() {
        return new FlashPotion();
    }

    @Override
    public int getPotency(final int ascensionLevel) {
        return 4;
    }


}