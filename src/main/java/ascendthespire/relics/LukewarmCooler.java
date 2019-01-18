package ascendthespire.relics;



import ascendthespire.AscendTheSpire;
import ascendthespire.patches.PotionRarityEnum;
import basemod.abstracts.CustomPotion;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.PotionPopUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.returnRandomPotion;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.returnTotallyRandomPotion;


public class LukewarmCooler extends CustomRelic implements ClickableRelic
{

    public static final Logger logger = LogManager.getLogger(AscendTheSpire.class.getName());

    public static final String ID = "ATS:AlchemicalLiquidizer";
    public static final String IMG_PATH = "relics/atsbetarelic.png";
    public static final String OUTLINE_IMG_PATH = "relics/atsbetarelicOUTLINE.png";

    private static ArrayList<AbstractPotion> coolerPotions = new ArrayList<>();

    boolean canUse = true;


    public LukewarmCooler() {
        super(ID, new Texture(AscendTheSpire.getResourcePath(IMG_PATH)), new Texture(AscendTheSpire.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }



   public void onEquip() {
        this.counter = 0;
    }

//AbstractDungeon.player.obtainPotion(slot, potion);
    //PotionPopUp.slot = -1;
    //this.potion


    @Override
    public void onGainGold() {

        if(AbstractDungeon.player.hasRelic("ATS:LukewarmCooler")) {

        }
    }

    public static void storePotion(CustomPotion p){
    coolerPotions.add(p);
    }

    @Override
    public void onRightClick() {
        int potionsGrabbed = AbstractDungeon.player.potions.size() - AbstractDungeon.player.potionSlots;

        if (counter > 0) {
            for (int i = 0; i < potionsGrabbed; ++i) {
                AbstractDungeon.actionManager.addToBottom(new ObtainPotionAction(coolerPotions.get(i)));
                counter--;
            }
        }

    }

    @Override
    public void onEnterRoom(AbstractRoom room) {

    }

    @Override
    public void onVictory(){

    }

    @Override
    public AbstractRelic makeCopy() {
        return new LukewarmCooler();
    }
}
