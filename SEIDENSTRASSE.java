import java.util.Scanner; //§
public class SEIDENSTRASSE{
    GRAPH_MATRIX m1;
    private String Zustand = "Start";
    private Scanner Eingabe = new Scanner(System.in);
    public SEIDENSTRASSE(){        
    }
    
    private void KnotenInMatrixEinfuegen(){
        m1.KnotenEinfuegen("Kon");
        m1.KnotenEinfuegen("Kai");
        m1.KnotenEinfuegen("Pal");
        m1.KnotenEinfuegen("Kas");
        m1.KnotenEinfuegen("Kuc");
        m1.KnotenEinfuegen("Dun");       
        m1.KnotenEinfuegen("Anx");
        m1.KnotenEinfuegen("Cha");
        m1.KnotenEinfuegen("Pek");
        m1.KnotenEinfuegen("Han");
        
    }    
        
    private void KantenInMatrixEinfuegen(){
        m1.KanteEinfuegen("Kon","Pal", 17);
        m1.KanteEinfuegen("Pal","Kon", 17);
        
        m1.KanteEinfuegen("Kai","Pal", 18);
        m1.KanteEinfuegen("Pal","Kai", 18);
        
        m1.KanteEinfuegen("Pal","Kas", 60);
        m1.KanteEinfuegen("Kas","Pal", 60);
        
        m1.KanteEinfuegen("Kas","Kuc", 9);
        m1.KanteEinfuegen("Kuc","Kas", 9);
        
        m1.KanteEinfuegen("Kas","Dun", 33);
        m1.KanteEinfuegen("Dun","Kas", 33);
        
        m1.KanteEinfuegen("Kuc","Dun", 22);
        m1.KanteEinfuegen("Dun","Kuc", 22);
        
        
        m1.KanteEinfuegen("Kuc","Anx", 16);
        m1.KanteEinfuegen("Anx","Kuc", 16);
        
        m1.KanteEinfuegen("Dun","Anx", 2);
        m1.KanteEinfuegen("Anx","Dun", 2);
        
        m1.KanteEinfuegen("Anx","Cha", 19);
        m1.KanteEinfuegen("Cha","Anx", 19);
        
        m1.KanteEinfuegen("Cha","Pek", 12);
        m1.KanteEinfuegen("Pek","Cha", 12);
        
        m1.KanteEinfuegen("Cha","Han", 14);
        m1.KanteEinfuegen("Han","Cha", 14);
        
        
        
    }
    
    
    
    public void StrasseBauenUndTestenInterface(){ //§
        m1 = new GRAPH_MATRIX(10);
        System.out.println("Seidenstrasse Beta"); 
        System.out.println("========================");
        while (!(Zustand.equals("Fertig"))){
            switch(Zustand){
                case "Start":{
                    System.out.println("Willkommen!");
                    System.out.println("Seidenstraße wird erstellt");
                    KnotenInMatrixEinfuegen();
                    KantenInMatrixEinfuegen();
                    m1.MatrixAusgeben();
                    Zustand = "Laeuft";
                }
                case "Laeuft":{
                    System.out.println();  
                    String Option = OptionAbfragen();                    
                    OptionUmsetzen(Option);
                                                                                                  
                }                
            }        
        }
        System.out.println("Auf Wiedersehen!");
    }
    
    private String OptionAbfragen(){
        String EingabeBefehl = "Nichts";
        boolean EingabeGueltig = false;
        
        System.out.println("1: Knoten einfuegen");
        System.out.println("2: Kante einfuegen");
        System.out.println("3: Knoten entfernen");
        System.out.println("4: Kante entfernen");
        System.out.println("5: Kante aendern");
        System.out.println("6: Alle Kanten einmal ablaufen()");
        System.out.println("7: Wege suchen von A nach B");
        System.out.println("8: Matrix ausgeben");
        System.out.println("0: Beenden");
        System.out.println();            
      
        while(!(EingabeGueltig)){
            System.out.println("Bitte gib deine gewünschte Option ein:");
            EingabeBefehl = Eingabe.next();
            System.out.println(EingabeBefehl);
            if(EingabeBefehl.equals ("1")||
                EingabeBefehl.equals ("2")||
                EingabeBefehl.equals ("3")||
                EingabeBefehl.equals ("4")||
                EingabeBefehl.equals ("5")||
                EingabeBefehl.equals ("6")||
                EingabeBefehl.equals ("7")||
                EingabeBefehl.equals ("8")||
                EingabeBefehl.equals ("0")){
                    EingabeGueltig = true;
            }else{
                System.out.println("Ungültige Eingabe!");
            }
            
        }
        return EingabeBefehl;
    }
    
    private void OptionUmsetzen(String EingabeBefehl){
        switch (EingabeBefehl) {
            case "1": {
                System.out.println("Welchen Knoten möchtest du einfuegen? (3 Buchstaben)");               
                m1.KnotenEinfuegen(KnotenAbfragen());
                m1.MatrixAusgeben();
                break;
            }
            case "2": {
                System.out.println("Welche Kante möchtest du einfuegen?");
                System.out.println("Knoten A:");
                String A = KnotenAbfragen();
                System.out.println("Knoten B:");
                String B = KnotenAbfragen();
                System.out.println("Kante:");
                int s = KanteAbfragen();
                m1.KanteEinfuegen(A,B,s);
                m1.MatrixAusgeben();
                break;
            }
            case "3":{
                System.out.println("Welchen Knoten moechtest du entfernen?");
                System.out.println(m1.KnotenEntfernen(KnotenAbfragen()));
                m1.MatrixAusgeben();
                break;
            }
            case "4":{
                System.out.println("Welche Kante moechtest du entfernen?");
                System.out.println("Knoten A:");
                String A = KnotenAbfragen();
                System.out.println("Knoten B:");
                String B = KnotenAbfragen();                
                m1.KanteLoeschen(A,B);
                m1.MatrixAusgeben();
                break;
            }
            case "5":{
                System.out.println("Welche Kante moechtest du aendern?");
                System.out.println("Knoten A:");
                String A = KnotenAbfragen();
                System.out.println("Knoten B:");
                String B = KnotenAbfragen();
                System.out.println("Gewichtung:");
                int s = KanteAbfragen();
                m1.KanteAendern(A,B,s);
                m1.MatrixAusgeben();
                break;
            }
            case "6":{
                System.out.println("Bei welchem Knoten soll die Tiefensuche starten? (3 Buchstaben)");
                m1.Tiefensuche(KnotenAbfragen());
                break;
            }
            case "7":{
                System.out.println("Von welchem Knoten zu welchem Knoten moechtest du die Wege angezeigt bekommen? (3 Buchstaben)");
                System.out.println("Knoten A:");
                String A = KnotenAbfragen();
                System.out.println("Knoten B:");
                String B = KnotenAbfragen();
                m1.WegeSuchen(A,B);
                break;
            }
            case "8":{
                m1.MatrixAusgeben();
                break;
            }
            case "0":{
                Zustand = "Fertig";
                break;
            }
        }
    }
    
    private int KanteAbfragen(){
        return Eingabe.nextInt();
    }
    
    private String KnotenAbfragen(){
        // To Do abfragen nach 3 Buchstaben
        return Eingabe.next();
    }
    
    public void StrasseBauenUndTesten(){
        m1 = new GRAPH_MATRIX(10);
        
        KnotenInMatrixEinfuegen();
        m1.MatrixAusgeben();
        System.out.println("Knoten in Matrix eingefügt");
        System.out.println();
        
        KantenInMatrixEinfuegen();
        m1.MatrixAusgeben();
        System.out.println("Kanten in Matrix eingefügt");            
        System.out.println();               
        
        System.out.println("Graphendurchlauf");
        m1.Tiefensuche("Kon");        
        System.out.println();
                        
        System.out.println("Weg von Palmyra nach Hangzhou");
        m1.WegeSuchen("Pal","Han"); 
        System.out.println();
        
        System.out.println("Um Seewege erweitern");
        
        m1.KnotenEinfuegen("Gua"); //Stadt Guangzhou einfuegen
        m1.KanteEinfuegen("Han","Gua", 20);
        m1.KanteEinfuegen("Gua","Han", 20);
        
        m1.KnotenEinfuegen("Oce");
        m1.KanteEinfuegen("Gua","Oce", 21);
        m1.KanteEinfuegen("Oce","Gua", 21);
        
        m1.KnotenEinfuegen("Mac");
        m1.KanteEinfuegen("Oce","Mac", 36);
        m1.KanteEinfuegen("Mac","Oce", 36);
        
        m1.KnotenEinfuegen("Umm");
        m1.KanteEinfuegen("Mac","Umm", 60);
        m1.KanteEinfuegen("Umm","Mac", 60);
        
        // Seeweg mit Palmyra verknüpfen
        m1.KanteEinfuegen("Umm","Pal", 14); 
        m1.KanteEinfuegen("Pal","Umm", 14); 
                        
        m1.MatrixAusgeben();
        System.out.println("Um Seewege erweitert");
        System.out.println();
        
        System.out.println("Weg von Palmyra nach Hangzhou mit Seewegen");
        m1.WegeSuchen("Pal","Han"); 
        System.out.println();
        
        m1.KanteLoeschen("Anx", "Cha");
        m1.MatrixAusgeben();
        System.out.println("Kante zwischen Anxi und Chang'an entfernt");
        System.out.println();
        
        System.out.println("Weg von Palmyra nach Hangzhou ohne Kante zwischen Anxi und Chang'an ");
        m1.WegeSuchen("Pal","Han");
                
        System.out.println();
        m1.KanteAendern("Umm", "Pal", 42);        
        m1.MatrixAusgeben();
        System.out.println("Kante zwischen Umm-Quasar und Palmyra geändert");
        
        System.out.println();
        System.out.println(m1.KnotenEntfernen("Kas"));
        m1.MatrixAusgeben();
        System.out.println("Knoten Kaschgar entfernt");
    }
}