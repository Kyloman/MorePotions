package ascendthespire.relics;



import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BottleFactory extends CustomRelic
{

    public static final String ID = "ATS:BottleFactory";
    public static final String IMG_PATH = "relics/atsbetarelic.png";
    public static final String OUTLINE_IMG_PATH = "relics/atsbetarelicOUTLINE.png";


    public BottleFactory() {
        super(ID, new Texture(ascendthespire.AscendTheSpire.getResourcePath(IMG_PATH)), new Texture(ascendthespire.AscendTheSpire.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 4 + this.DESCRIPTIONS[1];
    }



   public void onEquip() {
        this.counter = 0;
    }


    public void onEnterRoom(AbstractRoom room) {
        if (this.counter < 4) {
            this.counter++;
        }
        else {
            this.flash();
            this.counter = 0;
            AbstractDungeon.player.potionSlots += 1;
            AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
        }
    }

        /*

        //POTION DISPENSER RANDOM EVENT:
        //LETS YOU KEEP BUYING POTIONS FOR 50 A POP
        */





    @Override
    public AbstractRelic makeCopy() {
        return new BottleFactory();

    }
}
