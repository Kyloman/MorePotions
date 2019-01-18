package ascendthespire.powers;

import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import com.megacrit.cardcrawl.powers.MagnetismPower;
import ascendthespire.actions.QueueCardFrontAction;

public class OmnipresentPower extends AbstractPower
{
    public static final String POWER_ID = "ATS:OmnipresentPower";
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OmnipresentPower(final AbstractCreature owner, final int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("doubleTap");
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0) {
            this.flash();
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }
            for (int i=1;i<this.amount;i++) {
                final AbstractCard tmp = card.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = card.current_x;
                tmp.current_y = card.current_y;
                tmp.target_x = Settings.WIDTH / 2.0f - (300.0f * Settings.scale)*i;
                tmp.target_y = Settings.HEIGHT / 2.0f;
                tmp.freeToPlayOnce = true;
                if (m != null) {
                    tmp.calculateCardDamage(m);
                }
                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.addToBottom(new QueueCardFrontAction(tmp,m,card.energyOnUse));
            }
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.POWER_ID));
        }
    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (isPlayer) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.POWER_ID));
        }
    }
}

/*

package ascendthespire.powers;

import ascendthespire.AscendTheSpire;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import com.megacrit.cardcrawl.powers.MagnetismPower;
import ascendthespire.actions.QueueCardFrontAction;

public class OmnipresentPower extends AbstractPower
{
    public static final String POWER_ID = "ATS:OmnipresentPower";


    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = AscendTheSpire.makePath(AscendTheSpire.COMMON_POWER);

    public OmnipresentPower(final AbstractCreature owner, final int amount) {

        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = new Texture(IMG);

    }

    @Override
    public void updateDescription() {
        this.description = String.format(this.DESCRIPTIONS[0], this.amount, this.DESCRIPTIONS[1]);
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0 && card.rarity != AbstractCard.CardRarity.RARE) {
            this.flash();
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }
            for (int i=1;i<this.amount;i++) {
                final AbstractCard tmp = card.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = card.current_x;
                tmp.current_y = card.current_y;
                tmp.target_x = Settings.WIDTH / 2.0f - (300.0f * Settings.scale)*i;
                tmp.target_y = Settings.HEIGHT / 2.0f;
                tmp.freeToPlayOnce = true;
                if (m != null) {
                    tmp.calculateCardDamage(m);
                }
                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.addToBottom(new QueueCardFrontAction(tmp,m,card.energyOnUse));
            }
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.POWER_ID));
        }
    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (isPlayer) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.POWER_ID));
        }
    }
}
 */