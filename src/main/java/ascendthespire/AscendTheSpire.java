package ascendthespire;
//AbstractDungeon.actionManager.addToBottom(new RemoveAllPowersAction());
import ascendthespire.relics.BottleFactory;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import ascendthespire.potions.*;
import ascendthespire.relics.*;
import ascendthespire.patches.*;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.potions.LiquidBronze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static basemod.BaseMod.loadCustomStringsFile;


@SpireInitializer
public class AscendTheSpire implements PostInitializeSubscriber, EditStringsSubscriber {

    public static final Logger logger = LogManager.getLogger(AscendTheSpire.class.getName());

    public AscendTheSpire() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new AscendTheSpire();
    }

    @Override
    public void receivePostInitialize() {
        //POTIONS
        BaseMod.addPotion(InfernoPotion.class, Color.RED, Color.BLACK, Color.RED, InfernoPotion.POTION_ID);
        BaseMod.addPotion(PlaguePotion.class, Color.GREEN, Color.GRAY, Color.BLACK, PlaguePotion.POTION_ID);
        BaseMod.addPotion(ToxicWaste.class, Color.GREEN, Color.YELLOW, Color.RED, ToxicWaste.POTION_ID);
        BaseMod.addPotion(HotCoffee.class, Color.BROWN, Color.BROWN, Color.BROWN, HotCoffee.POTION_ID);
        BaseMod.addPotion(SmallUnstableElixir.class, Color.BLACK, Color.BLACK, Color.BLACK, SmallUnstableElixir.POTION_ID);
        BaseMod.addPotion(MediumUnstableElixir.class, Color.BLACK, Color.BLACK, Color.BLACK, MediumUnstableElixir.POTION_ID);
        BaseMod.addPotion(LargeUnstableElixir.class, Color.BLACK, Color.BLACK, Color.BLACK, LargeUnstableElixir.POTION_ID);
        BaseMod.addPotion(EmptyVial.class, Color.CLEAR, Color.CLEAR, Color.CLEAR, EmptyVial.POTION_ID);
        BaseMod.addPotion(FlashPotion.class, Color.BLACK, Color.YELLOW, Color.ORANGE, FlashPotion.POTION_ID);

    BaseMod.addPotion(OmnipresenceNectar.class, Color.GOLD, Color.GOLD, Color.GOLD, OmnipresenceNectar.POTION_ID);

    //MINIPOTIONS
        BaseMod.addPotion(StrengthPhial.class, Color.CORAL, Color.CORAL, Color.CORAL, StrengthPhial.POTION_ID);
        BaseMod.addPotion(DexterityPhial.class, Color.CORAL, Color.CORAL, Color.CORAL, DexterityPhial.POTION_ID);
        BaseMod.addPotion(EnergyPhial.class, Color.CORAL, Color.CORAL, Color.CORAL, EnergyPhial.POTION_ID);
        BaseMod.addPotion(EmberPhial.class, Color.CORAL, Color.CORAL, Color.CORAL, EmberPhial.POTION_ID);

        BaseMod.addPotion(LiquidBronzePhial.class, Color.CORAL, Color.CORAL, Color.CORAL, LiquidBronzePhial.POTION_ID);
        BaseMod.addPotion(PhailedPhial.class, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, PhailedPhial.POTION_ID);
        BaseMod.addPotion(WeaknessPhial.class, Color.CORAL, Color.CORAL, Color.CORAL, WeaknessPhial.POTION_ID);
        BaseMod.addPotion(VulnerabilityPhial.class, Color.CORAL, Color.CORAL, Color.CORAL, VulnerabilityPhial.POTION_ID);
        BaseMod.addPotion(SwiftPhial.class, Color.CORAL, Color.CORAL, Color.CORAL, SwiftPhial.POTION_ID);
        BaseMod.addPotion(ShinyPhial.class, Color.GOLD, Color.GOLD, Color.GOLD, ShinyPhial.POTION_ID);
    //Relics

    BaseMod.addRelic(new BottleFactory(), RelicType.SHARED);
    BaseMod.addRelic(new AlchemicalLiquidizer(), RelicType.SHARED);
    }

    public void receiveEditStrings() {
        logger.info("begin editing strings");
        loadCustomStringsFile(PotionStrings.class, "localization/AscendTheSpire-PotionStrings.json");
        loadCustomStringsFile(RelicStrings.class, "localization/AscendTheSpire-RelicStrings.json");
        loadCustomStringsFile(PowerStrings.class, "localization/AscendTheSpire-PowerStrings.json");
        logger.info("done editing strings");
    }

    public static final String getResourcePath(String resource) {
        return "" + resource;
    }
}

/*
POTIONS IDEAS:
Every time you gain gold, gain a potion whose rarity depends on how much gold you gained
Every time you drink a potion, gain a mini potion Leftovers
    You can check to see if it's not a mini potion by making all the minis Placeholder or patching a Mini rarity and then checking when a potion is used if Rarity= mini
Gain block on your first turn for every potion youve used Alchemist's Journal

Time Potion
Your opponents age 50 years
    They become 25% smaller, weak for 99 turns, and slow and lose al their strength if they have it

Flash Potion

Mist Potion (10% chance for enemies to miss)
 */




