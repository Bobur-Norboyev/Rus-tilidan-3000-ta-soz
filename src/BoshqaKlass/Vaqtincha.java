package BoshqaKlass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Vaqtincha {

    public static String bolimNomi;
    public static Integer bolimId;

    public static Integer turkumId, tanlangan, id;
    public static String ozi, tarjima, audio_nomi;

    public static String darsSarlavha, darsMatni = "";

    public static void bolimAlmashtir(String satr, int id) {
        bolimNomi = satr;
        bolimId = id;
    }

    public static void javoblarniOzgartir(String suzning_uzi, String tarjimasi, String audio_nom, Integer turkumi, Integer tanlangani, Integer iden) {
        ozi = suzning_uzi;
        tarjima = tarjimasi;
        audio_nomi = audio_nom;
        turkumId = turkumi;
        tanlangan = tanlangani;
        id = iden;

    }

    public static void darsniOzgartir(String Matni, String Sarlavha) {
        darsSarlavha = Sarlavha;
        darsMatni = null;
        String otvet = "";
        Matni = "DarsMatnlari/" + Matni;
        Scanner fayl = null;
        try {
            fayl = new Scanner(new FileInputStream(Matni));
        } catch (FileNotFoundException ex) {
            System.out.println("Faylni o'qib olishdagi xatolik.....");
        }
        while (fayl.hasNext()) {
            otvet += "\n" + fayl.nextLine();
        }
        //darsMatni = otvet;
        try{
        darsMatni = new String(otvet.getBytes(),"UTF-8");}
        /*
        "
        Fayldan o'qib olinayotgan ma'lumotlarning kodirovkasi ,umuman satrlarning kodirovkasi bilan
        bog'liq xatoliklarni shunday qilib tuzatish mumkin.
        Bu usul yaratildi : 07.05.2020 15:10
        "
        */
        catch(UnsupportedEncodingException e){
            System.out.println("Kodirovka xato tanlandi");
        }
        darsMatni += "\n";
        fayl.close();
    }

}
