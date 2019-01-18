package ascendthespire.potions;



import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class EmptyVial extends AbstractPotion
{


    public static final String POTION_ID = "ATS:EmptyVial";


    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public EmptyVial() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SPHERE, PotionColor.FIRE);

        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0]);
        this.isThrown = false;
        this.targetRequired = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void use(final AbstractCreature target) {

        final AbstractPlayer player = AbstractDungeon.player;
        player.potionSlots += 1;
        AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));

    }

    @Override
    public boolean canUse() {
        return (!AbstractDungeon.actionManager.turnHasEnded || AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) && (AbstractDungeon.getCurrRoom().event == null || !(AbstractDungeon.getCurrRoom().event instanceof WeMeetAgain));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new EmptyVial();
    }

    @Override
    public int getPotency(final int ascensionLevel) {
        return 50;
    }

}