package ksif.r2021.zadanie2.nemenit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import ksif.r2021.zadanie2.student.*;

import javax.sound.midi.Soundbank;

public class Main {

    public static void main(String[] args) {

        /*Key k= new Key();
        System.out.println(k.randomKey());
        char[] x= {'b', 'c', 'a'};

        //k.inversePermutation(x);
        Decryption d= new Decryption();
        System.out.println(d.decryptMonoalfSubs("acababc",k.inversePermutation(x) ));

        String s1 = "ahoj";
        String s2= s1;
        char[] ss2= s2.toCharArray();
        char[] ss1= s1.toCharArray();
        ss2[0] = 'x';
        System.out.println(s1 + " " + s2 + " " + Arrays.toString(ss1) + " " + Arrays.toString(ss2));*/

        String pt1 = "duringworldwariiseveralsovietspyringscommunicatedtomoscowcentreusingtwocipherswhichareessentiallyevolutionaryimprovementsonthebasicnihilistcipheraverystrongversionwasusedbymaxclauseninrichardsorgesnetworkinjapanandbyalexanderfooteinthelucyspyringinswitzerlandaslightlyweakerversionwasusedbytherotekapellenetwork";
        String pt2 = "matsumotogivesthreecouriersasealedpacketeachtodelivertotokyoheinformsthemthatacopyofthetreatyisinsideoneofthemhaghiobtainsallthreepackagesandfindsonlynewspapersbuthehasonemorecarduphissleevematsumotopitieskittyayoungwomanhefindshuddlinginadoorwayduringarainstormandtakesherinwhenhepreparestoleaveforjapanwiththetreatyshebegshimtospendafewhourswithherhegivesinattractedbyherbeautybutwhenhewakesuplatersheisgonewiththetreatydisgracedhecommitsritualsuicidetracksjellusicdowninhishomecountrybutistoolatehaghihasalreadybetrayedhimandwhenconfrontedbyhissuperiorsjellusicshootshimselfwirestheserialnumbersofthebanknotesusedtopayjellusicwhichjasonpassesontoagentnoworkingundercoverasacircusclownnamednemototraceonatraintripoutofthecountryinpursuitofthestolentreatyisnearlykilledinatrapsetbyhaghiwhileheissleepinghiscarisdetachedandleftinatunnelheawakensjustbeforeanothertrainsmashesintoitsonjawhohadbeentrickedintobeingtheonetosmugglethetreatyoutofthecountrybyhaghispromisenottoharmlearnsofthecrashracestothesiteandisreunitedwithherlove";
        Integer[] perm = {18, 4, 19, 1, 10, 6, 17, 14, 13, 0, 2, 9, 7, 16, 11, 5, 12, 15, 3, 8};
        String withoutT = "tswhutzwzedckhwmrkkozurdkrhshksbkxlsojkwksomwzxkbdckrwzwzjazmkdfgzrthwmktwmswsozlazgwmkwrkswadhdfhdxkzfkzgwmktmsemdzpwsdfhsbbwmrkklsojsekhsfxgdfxhzfbafkqhlslkrhpuwmkmshzfktzrkosrxulmdhhbkkcktswhutzwzldwdkhjdwwasazufeqztsfmkgdfxhmuxxbdfedfsxzzrqsaxurdfesrsdfhwzrtsfxwsjkhmkrdfqmkfmklrklsrkhwzbksckgzryslsfqdwmwmkwrkswahmkpkehmdtwzhlkfxsgkqmzurhqdwmmkrmkedckhdfswwrsowkxpamkrpksuwapuwqmkfmkqsjkhulbswkrhmkdhezfkqdwmwmkwrkswaxdhersokxmkozttdwhrdwusbhudodxkwrsojhykbbuhdoxzqfdfmdhmztkozufwrapuwdhwzzbswkmsemdmshsbrksxapkwrsakxmdtsfxqmkfozfgrzfwkxpamdhhulkrdzrhykbbuhdohmzzwhmdthkbgqdrkhwmkhkrdsbfutpkrhzgwmkpsfjfzwkhuhkxwzlsaykbbuhdoqmdomyshzflshhkhzfwzsekfwfzqzrjdfeufxkrozckrshsodrouhobzqffstkxfktzwzwrsokzfswrsdfwrdlzuwzgwmkozufwradflurhudwzgwmkhwzbkfwrkswadhfksrbajdbbkxdfswrslhkwpamsemdqmdbkmkdhhbkkldfemdhosrdhxkwsomkxsfxbkgwdfswuffkbmksqsjkfhyuhwpkgzrksfzwmkrwrsdfhtshmkhdfwzdwhzfysqmzmsxpkkfwrdojkxdfwzpkdfewmkzfkwzhtueebkwmkwrkswazuwzgwmkozufwrapamsemdhlrztdhkfzwwzmsrtbksrfhzgwmkorshmrsokhwzwmkhdwksfxdhrkufdwkxqdwmmkrbzck";
        try {
            // init
            String ct1 = new String(Files.readAllBytes(Paths.get("msg_short.txt")));
            String ct2 = new String(Files.readAllBytes(Paths.get("msg_long.txt")));
            Solver s = new Solver();

            // 1. vigenere
            // vieme ze na konci ZT je "bordel" co rusi statistiky
            // porovname po vylusteni len PT cast bez kluca
            String pt1cand = s.solveVigenere(ct1);
            System.out.println("4b - " + pt1.equals(pt1cand.substring(0, pt1cand.length() - 20)));
            // 2. inv perm
            // zoberieme z predch. ulohy kluc
            // prevedieme pismena na permutaciu (do toho tvaru s cim robi nasa Transpozicia)
            // porovname permutaciu
            String transpKey = pt1cand.substring(pt1cand.length() - 20, pt1cand.length());
            Integer[] permutationCand = s.getPermutation(transpKey);
            System.out.println("2b - " + Arrays.toString(perm).equals(Arrays.toString(permutationCand)));
            // 3. remove transp
            // desifrujeme jednu clast zlozenej sifry
            // porovname vysledok
            String tRemoved = s.solveTransposition(ct2, perm);
            System.out.println("3b - " + withoutT.equals(tRemoved));
            // 4. 
            // desifrujeme druhu cast zlozenej sifry
            // porovname vysledok
            String pt2cand = s.solveSubstitution(tRemoved);
            System.out.println("6b - " + pt2.equals(pt2cand));

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
